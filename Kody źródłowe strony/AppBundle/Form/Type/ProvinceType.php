<?php

namespace AppBundle\Form\Type;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ProvinceType extends AbstractType
{

    public function getParent()
    {
        return ChoiceType::class;
    }
    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'choices' => array(
                'Dolnośląskie' => 'Dolnośląskie',
                'Kujawsko-Pomorskie' => 'Kujawsko-Pomorskie',
                'Lubelskie' => 'Lubelskie',
                'Lubuskie' => 'Lubuskie',
                'Łudzkie' => 'Łudzkie',
                'Małopolskie' => 'Małopolskie',
                'Mazowieckie' => 'Mazowieckie',
                'Opolskie' => 'Opolskie',
                'Podkarpackie' => 'Podkarpackie',
                'Podlaskie' => 'Podlaskie',
                'Pomorskie' => 'Pomorskie',
                'Śląskie' => 'Śląskie',
                'Świętokrzyskie' => 'Świętokrzyskie',
                'Warmińsko-Mazurskie' => 'Warmińsko-Mazurskie',
                'Wielkopolskie' => 'Wielkopolskie',
                'Zachodniopomorskie' =>'Zachodniopomorskie'
            ),
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'provinces';
    }

}
