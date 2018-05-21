<?php

namespace AppBundle\Entity;

use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use ApiPlatform\Core\Annotation\ApiResource;

/**
 * Meeting
 * @ApiResource
 * @ORM\Table(name="meeting")
 * @ORM\Entity(repositoryClass="AppBundle\Repository\MeetingRepository")
 */
class Meeting
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
     * @var Collection
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\Doctor")
     * @ORM\JoinColumn(name="doctor_id", referencedColumnName="id", nullable=false)
     */
    private $doctor;

    /**
     * @var Collection
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\User")
     * @ORM\JoinColumn(name="user_id", referencedColumnName="id", nullable=false)
     */
    private $user;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="startDate", type="datetime")
     * @Assert\Range(
     *      min = "today",
     *      max = "+30 days"
     * )
     */
    private $startDate;

    /**
     * @var string|null
     *
     * @ORM\Column(name="details", type="text", nullable=true)
     */
    private $details;

    /**
     * @var string|null
     *
     * @ORM\Column(name="meetingResult", type="text", nullable=true)
     */
    private $meetingResult;

    public function __construct()
    {
        $this->startDate = new \DateTime();
    }


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
     * Set doctor.
     *
     * @param string $doctor
     *
     * @return Meeting
     */
    public function setDoctor($doctor)
    {
        $this->doctor = $doctor;

        return $this;
    }

    /**
     * Get doctor.
     *
     * @return string
     */
    public function getDoctor()
    {
        return $this->doctor;
    }

    /**
     * Set user.
     *
     * @param string $user
     *
     * @return Meeting
     */
    public function setUser($user)
    {
        $this->user = $user;

        return $this;
    }

    /**
     * Get user.
     *
     * @return string
     */
    public function getUser()
    {
        return $this->user;
    }

    /**
     * Set startDate.
     *
     * @param \DateTime $startDate
     *
     * @return Meeting
     */
    public function setStartDate(\DateTime $startDate)
    {
        $this->startDate->setDate((integer)$startDate->format('Y'), (integer)$startDate->format('m'), (integer)$startDate->format('d'));

        return $this;
    }

    /**
     * Get startDate.
     *
     * @return \DateTime
     */
    public function getStartDate()
    {
        return $this->startDate;
    }

    public function setStartTime(\DateTime $time)
    {
        $this->startDate->setTime((integer)$time->format('H'), (integer)$time->format('i'));
    }

    public function getStartTime()
    {
        return $this->startDate;
    }
    /**
     * @return null|string
     */
    public function getDetails()
    {
        return $this->details;
    }

    /**
     * @param null|string $details
     */
    public function setDetails($details)
    {
        $this->details = $details;
    }

    /**
     * @return null|string
     */
    public function getMeetingResult()
    {
        return $this->meetingResult;
    }

    /**
     * @param null|string $meetingResult
     */
    public function setMeetingResult($meetingResult)
    {
        $this->meetingResult = $meetingResult;
    }



    public function __toString()
    {
        return 'pacjent: '.$this->getUser(). ', doktor: '.$this->getDoctor(). ', dnia: '.$this->startDate->format('Y-m-d H:i');
    }


}
