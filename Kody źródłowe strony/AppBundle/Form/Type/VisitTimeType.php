<?php

namespace AppBundle\Form\Type;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class VisitTimeType extends AbstractType
{


    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'choices' => $this->createTimeRange(),
            'expanded' => true
        ));
    }


    private function createTimeRange() {
        $startTime = (new \DateTime())->setTime(8,0);
        $endTime   = (new \DateTime())->setTime(16,0);

        $addTime   = new \DateInterval('PT30M');

        $times = [];
        while ($startTime <= $endTime) {
            $times[(clone $startTime)->format('H:i')] = (clone $startTime);
            $startTime->add($addTime);
        }
        return $times;
    }

    public function getParent()
    {
        return ChoiceType::class;
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'visit_times';
    }
}
