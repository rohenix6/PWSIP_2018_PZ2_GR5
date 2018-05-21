<?php

namespace AppBundle\Form;

use AppBundle\Entity\Doctor;
use AppBundle\Entity\Message;
use AppBundle\Form\Type\VisitTimeType;
use AppBundle\Repository\DoctorRepository;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ContactType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('doctor', EntityType::class,[
                'label' => 'Lekarz',
                'class' => Doctor::class,
                'query_builder' => function(DoctorRepository $repo) {
                    return $repo->getActive();
                },
                'attr' => [
                    'class' => 'doctor-selector'
                ]
            ])
            ->add('message', null, [
                'label' => 'Wiadomość'
            ]);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => Message::class
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'appbundle_message';
    }


}
