<?php


namespace AppBundle\Controller;

use AppBundle\Entity\Message;
use AppBundle\Form\ContactType;
use EasyCorp\Bundle\EasyAdminBundle\Controller\AdminController as BaseAdminController;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Security;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;


class AdminController extends BaseAdminController
{
    
    public function answerAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $repository = $this->getDoctrine()->getRepository(Message::class);

        $id = $request->query->get('id');
        $message = $repository->find($id);
        /* @var Message $message */
        $answer = new Message($message->getUser());
        $answer->setDoctor($message->getDoctor());
        $answer->setDate(new \DateTime());
        $answer->setAnswer($message);

        $form = $this->createForm(ContactType::class, $answer);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){

            $em->persist($answer);
            $em->flush();
            return $this->redirectToRoute('easyadmin', array(
                'action' => 'list',
                'entity' => 'Messages',
            ));
        }

        return $this->render('@App/Admin/answer.html.twig', ['message' => $message, 'form' => $form->createView()]);
    }
}