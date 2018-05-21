<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Doctor;
use AppBundle\Entity\Meeting;
use AppBundle\Entity\Message;
use AppBundle\Entity\User;
use AppBundle\Form\ContactType;
use AppBundle\Form\MeetingType;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;

class DefaultController extends Controller
{


    /**
     * @Route("/", name="home")
     */
    public function indexAction(Request $request)
    {
        // replace this example code with whatever you need
        return ['base_dir' => realpath($this->getParameter('kernel.project_dir')).DIRECTORY_SEPARATOR];
    }

    /**
     * @Route("/visit", name="visit")
     */
    public function visitAction(Request $request)
    {
        $visit = new Meeting();
        $visit->setUser($this->getUser());
        $form = $this->createForm(MeetingType::class, $visit);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $this->sendSuccessEmail($visit);
            $this->getManager()->persist($visit);
            $this->getManager()->flush();
            $this->addFlash('success','Zostałeś zapisany na wizytę');
            return $this->redirectToRoute('my-visits');
        }
        // replace this example code with whatever you need
        return ['form' => $form->createView()];
    }

    /**
     * @Route("/history-visits", name="history-visits")
     */
    public function historyVisitsAction(Request $request)
    {
        /** @var User $user */
        $user = $this->getUser();
        $meetingRepository = $this->get('doctrine.orm.entity_manager')->getRepository(Meeting::class);

        $visits = $meetingRepository->findOldForUser($user);
        return ['visits' => $visits];
    }

    /**
     * @Route("/my-visits", name="my-visits")
     */
    public function myVisitsAction(Request $request)
    {
        /** @var User $user */
        $user = $this->getUser();
        $meetingRepository = $this->get('doctrine.orm.entity_manager')->getRepository(Meeting::class);

        $visits = $meetingRepository->findNewForUser($user);
        return ['visits' => $visits];
    }


    /**
     * @Route("/contact", name="contact")
     */
    public function contactAction(Request $request)
    {
        $messages = [];
        $user = $this->getUser();
        if($user){
            $messages = $this->getManager()->getRepository(Message::class)->findByUser($user);
        }
        $message = new Message($user);
        $contactType = $this->createForm(ContactType::class, $message);
        $contactType->handleRequest($request);
        if($contactType->isValid() && $contactType->isSubmitted()){
            $em = $this->get('doctrine.orm.entity_manager');

            $em->persist($message);
            $em->flush();
            $this->addFlash('success','Wiadomośc została wysłana');
            return $this->redirectToRoute('contact');
        }

        return [
            'messages' => $messages,
            'form' => $contactType->createView()
        ];
    }

    /**
     * @Route("/regulamin", name="regulamin")
     */
    public function regulaminAction(Request $request)
    {
        return [];
    }

    /**
     * @Route("/api/doctor-visits/{doctor}/{date}", options={"expose"=true}, name="doctor_visits")
     */
    public function doctorVisitsAction(Doctor $doctor, $date)
    {
        $result = [];
        $meetings = $this->getManager()->getRepository(Meeting::class)->findBy(['doctor' => $doctor]);
        foreach ($meetings as $meeting) {
            /** @var Meeting $meeting */
            if($meeting->getStartDate()->format('d-m-Y') == $date){
                $result[] = $meeting->getStartDate()->format('H:i');
            }
        }

        return $this->json($result);
    }

    /**
     * @Route("/remove-visit/{id}", options={"expose"=true}, name="remove_visit")
     */
    public function removeVisitAction(Meeting $meeting)
    {
        /** @var User $user */
        $user = $this->getUser();
        if($user){
            $meetings = $this->getManager()->getRepository(Meeting::class)->findBy(['user'=>$user]);
            if(in_array($meeting, $meetings)){
                $this->getManager()->remove($meeting);
                $this->getManager()->flush();
            }
        }

        if($user){
            return $this->redirect($this->generateUrl('my-visits'));
        }
        return $this->redirect($this->generateUrl('home'));
    }

    public function getManager(){
        return $this->get('doctrine.orm.entity_manager');
    }

    public function sendSuccessEmail(Meeting $meeting)
    {
        $mailer = $this->get('swiftmailer.mailer');
        $message = (new \Swift_Message('Hello Email'))
            ->setFrom('send@example.com')
            ->setTo('recipient@example.com')
            ->setBody(
                $this->renderView(
                // templates/emails/registration.html.twig
                    '@App/emails/success-visit.html.twig',
                    array('name' => (string)$meeting->getUser(), 'visit' => $meeting)
                ),
                'text/html'
            )
        ;

        return $mailer->send($message);
    }
}
