<?php

namespace App\Entity;

use App\Entity\Film;
use App\Repository\PromotionRepository;
use Doctrine\ORM\Mapping as ORM;
use  Symfony\Component\Validator\Constraints as Assert;

use App\Form\SearchPromotionType;

/**
 * @ORM\Entity(repositoryClass=PromotionRepository::class)
 * @ORM\Table(name="promotion", indexes={@ORM\Index(columns={"type","description"},flags={"fulltext"})})
 */
class Promotion
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
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $type;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $description;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $pourcentage;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $code;

    /**
     * @ORM\ManyToOne(targetEntity=Film::class)
     * @ORM\JoinColumn(nullable=false)
     */
    private $cat;



    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getPourcentage(): ?string
    {
        return $this->pourcentage;
    }

    public function setPourcentage(string $pourcentage): self
    {
        $this->pourcentage = $pourcentage;

        return $this;
    }

    public function getCode(): ?string
    {
        return $this->code;
    }

    public function setCode(string $code): self
    {
        $this->code = $code;

        return $this;
    }


  public function getCat(): ?Film
  {
      return $this->cat;
  }

  public function setCat(?Film $cat): self
  {
      $this->cat = $cat;

      return $this;
  }

      public function __toString()
      {
          return (string) $this->getCat();

      }

}
