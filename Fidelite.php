<?php

namespace App\Entity;

use App\Repository\FideliteRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=FideliteRepository::class)
 */
class Fidelite
{
    /**
     * @ORM\Id
     *@ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     */
    private $idUser;

    /**
     * @ORM\nbr
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $nbr;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdUser(): ?int
    {
        return $this->idUser;
    }

    public function setIdUser(int $idUser): self
    {
        $this->idUser = $idUser;

        return $this;
    }

    public function getNbr(): ?int
    {
        return $this->nbr;
    }

    public function setNbr(int $nbr): self
    {
        $this->nbr = $nbr;

        return $this;
    }
}
