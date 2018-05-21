<?php

namespace AppBundle\Form;

use AppBundle\Form\Type\ProvinceType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\BirthdayType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use FOS\UserBundle\Form\Type\RegistrationFormType as ParentType;

class RegistrationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('pesel', NumberType::class,[
                'label' => 'PESEL'
            ])
            ->add('address', null, [
                'label' => 'Adres'
            ])
            ->add('city', null ,[
                'label' => 'Miasto'
            ])
            ->add('zipCode', null, [
                'label' => 'Kod pocztowy'
            ])
            ->add('phone', null, [
                'label' => 'Telefon'
            ])
            ->add('province', ProvinceType::class, [
                'label' => 'Województwo'
            ])
            ->add('about', TextareaType::class, [
                'label' => 'O mnie'
            ])
            ->add('birthDate', BirthdayType::class, [
                'label' => 'Data urodzenia',
                'widget' => 'single_text',
                'format' => 'dd-MM-yyyy',
                'attr' => [
                    'class' => 'datepicker-jquery'
                ]
            ])
            ->add('Regulamin', CheckboxType::class, [
                'label' => 'Zapoznałem się z treścią regulaminu',
                'required' => true,
                'mapped' => false
            ]);
    }

    public function getParent()
    {
        return ParentType::class;
    }

    public function getBlockPrefix()
    {
        return 'app_user_registration';
    }

    public function getName()
    {
        return $this->getBlockPrefix();
    }
}
