<?php

namespace App\Repository;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use App\Entity\Ticket;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Ticket|null find($id, $lockMode = null, $lockVersion = null)
 * @method Ticket|null findOneBy(array $criteria, array $orderBy = null)
 * @method Ticket[]    findAll()
 * @method Ticket[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)

 */
class TicketRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Ticket::class);
    }

    public function search($mots = null,$film = null){
        $query =$this->createQueryBuilder('a');
        if ($mots!= null){
            $query->where('MATCH_AGAINST(a.PrixTicket,a.Salle) AGAINST 
            (:mots boolean)>0' )
                ->setParameter('mots',$mots);

        }
        if ($film!=null){
            $query->leftJoin('a.film','c');
            $query->where('c.id= :id')
                ->setParameter('id',$film);

        }
        return $query->getQuery()->getResult();


    }

    public function listOrderByD()
    {
        return $this->createQueryBuilder('r')
            ->orderBy('r.Salle', 'ASC')
            ->getQuery()->getResult();
    }
    public function listOrderByName()
    {
        return $this->createQueryBuilder('r')
            ->orderBy('r.film', 'DESC')
            ->getQuery()->getResult();
    }

}
