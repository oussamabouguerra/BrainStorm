<?php

namespace App\Entity;

use App\Repository\TicketRepository;
use  Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;
use App\Entity\Promotion;

/**
 * @ORM\Entity(repositoryClass=TicketRepository::class)
 */
class Ticket
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $PrixTicket;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $NbPlace;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $Salle;

    /**
     * @ORM\Column(type="date")
     * @Assert\NotBlank(message="Ce champs est obligatoire")

     */
    private $dateh;


    /**
     * @ORM\ManyToOne(targetEntity=Promotion::class)
     * @ORM\JoinColumn(nullable=false)
     */
    private $promo;

    /**
     * @ORM\ManyToOne(targetEntity=Film::class)
     * @ORM\JoinColumn(nullable=false)
     */
    private $film;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getPrixTicket(): ?string
    {
        return $this->PrixTicket;
    }

    public function setPrixTicket(string $PrixTicket): self
    {
        $this->PrixTicket = $PrixTicket;

        return $this;
    }

    public function getNbPlace(): ?string
    {
        return $this->NbPlace;
    }

    public function setNbPlace(string $NbPlace): self
    {
        $this->NbPlace = $NbPlace;

        return $this;
    }

    public function getSalle(): ?string
    {
        return $this->Salle;
    }

    public function setSalle(string $Salle): self
    {
        $this->Salle = $Salle;

        return $this;
    }


    public function getDateh(): ?\DateTimeInterface
    {
        return $this->dateh;
    }

    public function setDateh(\DateTimeInterface $dateh): self
    {
        $this->dateh = $dateh;

        return $this;
    }

    public function getPromo(): ?Promotion
    {
        return $this->promo;
    }

    public function setPromo(?Promotion $promo): self
    {
        $this->promo = $promo;

        return $this;
    }

    public function getFilm(): ?Film
    {
        return $this->film;
    }

    public function setFilm(?Film $film): self
    {
        $this->film = $film;

        return $this;
    }

    public function __toString()
    {
        return (string) $this->getFilm();
    }

}
