<?php

namespace App\Entity;

use App\Repository\SimpleUserRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

/**
 * @ORM\Entity(repositoryClass=SimpleUserRepository::class)
 * @UniqueEntity("mail")
 */
class SimpleUser
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="il faut remplir ce champ")
     */
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="il faut remplir ce champ")
     */
    private $prenom;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="il faut remplir ce champ")
     * @Assert\Length(min="8",
     *     max="8",
     *     minMessage="Doit contenir {{ limit }} caractères",
     *     maxMessage="Doit contenir {{ limit }} caractères"
     * )
     */
    private $cin;

    /**
     * @ORM\Column(type="string", length=255, unique=true)
     * @Assert\NotBlank(message="il faut remplir ce champ")
     * @Assert\Email(message="Email {{ value }} non valide")
     */
    private $mail;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="il faut remplir ce champ")
     * @Assert\Length(min="8",
     *     max="20",
     *     minMessage="Doit contenir {{ limit }} caractères",
     *     maxMessage="Doit contenir {{ limit }} caractères"
     * )
     */
    private $mdp;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $block;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $codem;

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

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getCin(): ?int
    {
        return $this->cin;
    }

    public function setCin(int $cin): self
    {
        $this->cin = $cin;

        return $this;
    }

    public function getMail(): ?string
    {
        return $this->mail;
    }

    public function setMail(string $mail): self
    {
        $this->mail = $mail;

        return $this;
    }

    public function getMdp(): ?string
    {
        return $this->mdp;
    }

    public function setMdp(string $mdp): self
    {
        $this->mdp = $mdp;

        return $this;
    }

    public function getBlock(): ?string
    {
        return $this->block;
    }

    public function setBlock(?string $block): self
    {
        $this->block = $block;

        return $this;
    }

    public function getCodem(): ?string
    {
        return $this->codem;
    }

    public function setCodem(?string $codem): self
    {
        $this->codem = $codem;

        return $this;
    }
}
