<?php

namespace App\Entity;

use App\Repository\StarRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=StarRepository::class)
 */
class Star
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     */
    private $rateindex;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getRateindex(): ?int
    {
        return $this->rateindex;
    }

    public function setRateindex(int $rateindex): self
    {
        $this->rateindex = $rateindex;

        return $this;
    }
}
