<?php

namespace AppBundle\Entity;

use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * Recept
 *
 * @ORM\Table(name="recepts")
 * @ORM\Entity(repositoryClass="AppBundle\Repository\ReceptRepository")
 */
class Recept
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var Meeting
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\Meeting")
     * @ORM\JoinColumn(name="umeeting_id", referencedColumnName="id", nullable=false)
     */
    private $visit;

    /**
     * @var string
     *
     * @ORM\Column(name="diagnoze", type="text")
     */
    private $diagnoze;

    /**
     * @var string
     *
     * @ORM\Column(name="medicines", type="text")
     */
    private $medicines;


    /**
     * Get id.
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @return Meeting
     */
    public function getVisit(): ?Meeting
    {
        return $this->visit;
    }

    /**
     * @param Meeting $visit
     */
    public function setVisit(Meeting $visit)
    {
        $this->visit = $visit;
    }


    /**
     * Set diagnoze.
     *
     * @param string $diagnoze
     *
     * @return Recept
     */
    public function setDiagnoze($diagnoze)
    {
        $this->diagnoze = $diagnoze;

        return $this;
    }

    /**
     * Get diagnoze.
     *
     * @return string
     */
    public function getDiagnoze()
    {
        return $this->diagnoze;
    }

    /**
     * Set medicines.
     *
     * @param string $medicines
     *
     * @return Recept
     */
    public function setMedicines($medicines)
    {
        $this->medicines = $medicines;

        return $this;
    }

    /**
     * Get medicines.
     *
     * @return string
     */
    public function getMedicines()
    {
        return $this->medicines;
    }
}
