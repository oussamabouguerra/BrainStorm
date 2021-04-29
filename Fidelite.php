<?php

namespace App\Entity;

use App\Repository\FideliteRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use  Symfony\Component\Validator\Constraints as Assert;
use App\Entity\SimpleUser;

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
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     * @ORM\Column(type="integer")
     */
    private $nbr;

    /**
     * @ORM\ManyToOne(targetEntity=SimpleUser::class)
     * @ORM\JoinColumn(nullable=false)
     */
    private $usr;



    public function getId(): ?int
    {
        return $this->id;
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


   public function getUsr(): ?SimpleUser
   {
       return $this->usr;
   }

   public function setUsr(?SimpleUser $usr): self
   {
       $this->usr = $usr;

       return $this;
   }
    public function __toString()
    {
        return (string) $this->getUsr();
    }
}
