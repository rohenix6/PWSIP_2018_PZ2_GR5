<?php

namespace AppBundle\Entity;

use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * Meeting
 *
 * @ORM\Table(name="messages")
 * @ORM\Entity(repositoryClass="AppBundle\Repository\MessageRepository")
 */
class Message
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
     * @var Doctor
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\Doctor", inversedBy="meetings")
     * @ORM\JoinColumn(name="doctor_id", referencedColumnName="id", nullable=false)
     */
    private $doctor;

    /**
     * @var User
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\User", inversedBy="meetings")
     * @ORM\JoinColumn(name="user_id", referencedColumnName="id", nullable=false)
     */
    private $user;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="datetime")
     */
    private $date;

    /**
     * @var string|null
     *
     * @ORM\Column(name="message", type="text", nullable=true)
     */
    private $message;

    /**
     * @var Message
     *
     * @ORM\OneToOne(targetEntity="AppBundle\Entity\Message")
     */
    private $answer;

    public function __construct(User $user = null)
    {
        $this->user = $user;
        $this->date = new \DateTime();
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
     * @return Message
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
     * @return Message
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
     * @return \DateTime
     */
    public function getDate(): \DateTime
    {
        return $this->date;
    }

    /**
     * @param \DateTime $date
     */
    public function setDate(\DateTime $date)
    {
        $this->date = $date;
    }

    /**
     * @return null|string
     */
    public function getMessage()
    {
        return $this->message;
    }

    /**
     * @param null|string $message
     */
    public function setMessage($message)
    {
        $this->message = $message;
    }

    /**
     * @return Message
     */
    public function getAnswer()
    {
        return $this->answer;
    }

    /**
     * @param Message $answer
     */
    public function setAnswer(?Message $answer)
    {
        $this->answer = $answer;
    }


    public function __toString()
    {
        return $this->message;
    }

}
