<?php

namespace AppBundle\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;


use FOS\UserBundle\Model\User as BaseUser;
use Kiczort\PolishValidatorBundle\Validator\Constraints  as KiczortAssert;

/**
 * User
 *
 * @ORM\Table(name="user")
 * @ORM\Entity(repositoryClass="AppBundle\Repository\UserRepository")
 */
class User extends BaseUser
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $id;

    /**
     * @ORM\Column(name="pesel", type="string", length=11)
     * @KiczortAssert\Pesel(
     *     message = "Nieprawidłowy Numer Pesel",
     *     strict = true
     * )
     */
    protected $pesel;

    /**
     * @var string
     *
     * @ORM\Column(name="city", type="string", length=100)
     */
    private $city;

    /**
     * @var string
     *
     * @ORM\Column(name="province", type="string", length=50)
     */
    private $province;

    /**
     * @var string
     *
     * @ORM\Column(name="phone", type="string", length=100)
     */
    private $phone;

    /**
     * @var string
     *
     * @ORM\Column(name="zip_code", type="string", length=100)
     */
    private $zipCode;

    /**
     * @var string
     *
     * @ORM\Column(name="address", type="string", length=255)
     */
    private $address;

    /**
     * @var string
     *
     * @ORM\Column(name="about", type="text")
     */
    private $about;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="birth_date", type="date")
     */
    private $birthDate;


    public function __construct()
    {
        parent::__construct();
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
     * @return string
     */
    public function getCity(): ?string
    {
        return $this->city;
    }

    /**
     * @param string $city
     */
    public function setCity(string $city)
    {
        $this->city = $city;
    }

    /**
     * @return string
     */
    public function getAddress(): ?string
    {
        return $this->address;
    }

    /**
     * @param string $address
     */
    public function setAddress(string $address)
    {
        $this->address = $address;
    }

    /**
     * @return string
     */
    public function getAbout(): ?string
    {
        return $this->about;
    }

    /**
     * @param string $about
     */
    public function setAbout(string $about)
    {
        $this->about = $about;
    }

    /**
     * @return mixed
     */
    public function getPesel()
    {
        return $this->pesel;
    }

    /**
     * @param mixed $pesel
     */
    public function setPesel($pesel)
    {
        $this->pesel = $pesel;
    }

    /**
     * @return string
     */
    public function getPhone(): ?string
    {
        return $this->phone;
    }

    /**
     * @param string $phone
     */
    public function setPhone(string $phone)
    {
        $this->phone = $phone;
    }

    /**
     * @return string
     */
    public function getZipCode(): ?string
    {
        return $this->zipCode;
    }

    /**
     * @param string $zipCode
     */
    public function setZipCode(string $zipCode)
    {
        $this->zipCode = $zipCode;
    }

    /**
     * @return string
     */
    public function getProvince(): ?string
    {
        return $this->province;
    }

    /**
     * @param string $province
     */
    public function setProvince(string $province)
    {
        $this->province = $province;
    }

    /**
     * @return string
     */
    public function getBirthDate(): ?\DateTime
    {
        return $this->birthDate;
    }

    /**
     * @param string $birthDate
     */
    public function setBirthDate(\DateTime $birthDate)
    {
        $this->birthDate = $birthDate;
    }


    public function __toString()
    {
        return $this->getUsername();
    }

}
