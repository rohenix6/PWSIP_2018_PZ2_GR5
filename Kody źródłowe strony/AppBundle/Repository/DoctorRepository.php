<?php

namespace AppBundle\Repository;
use AppBundle\Entity\Doctor;

/**
 * DoctorRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class DoctorRepository extends \Doctrine\ORM\EntityRepository
{
    public function getActive(){
        $qb = $this->createQueryBuilder('d');

        $qb->andWhere('d.active = true')
            ->orderBy('d.lastName', 'asc');

        return $qb;

    }
}
