<?php

namespace AppBundle\Form;

use AppBundle\Entity\Doctor;
use AppBundle\Form\Type\VisitTimeType;
use AppBundle\Repository\DoctorRepository;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class MeetingType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('startDate', DateType::class, [
            'label' => 'Data wizyty',
            'format' => 'dd-MM-yyyy',
            'widget' => 'single_text',
                'attr' => [
                    'class' => 'datepicker-jquery'
                ]
        ])
        ->add('startTime', VisitTimeType::class, [
            'label' => 'Godzina wizyty',
            'attr' => ['class' => 'hour-visit']
        ])
            ->add('doctor', EntityType::class,[
                'label' => 'Lekarz',
                'class' => Doctor::class,
                'query_builder' => function(DoctorRepository $repo) {
                    return $repo->getActive();
                },
                'attr' => [
                    'class' => 'doctor-selector'
                ]
            ])
            ->add('details', null, [
                'label' => 'Opisz co ci dolega'
            ]);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'AppBundle\Entity\Meeting'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'appbundle_meeting';
    }


}
