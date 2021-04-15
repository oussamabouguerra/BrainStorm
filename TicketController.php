<?php

namespace App\Controller;

use App\Entity\Ticket;
use App\Form\TicketType;
use App\Repository\TicketRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class TicketController extends AbstractController
{
    /**
     * @Route("/acceuil", name="acceuil")
     */
    public function index(): Response
    {
        return $this->render('ticket/acceuil.html.twig', [
            'controller_name' => 'TicketController',
        ]);
    }
    /**
     * @Route("/AfficheT", name="AfficheT")
     */
    public function afficherback(TicketRepository  $ticketRep ,Request $request)
    {

        $ticket = $ticketRep->findAll();

        return $this->render('ticket/index.html.twig', [
            'ticket' => $ticket
        ]);

    }
    /**
     * @Route("/Affichefront", name="Affichefront")
     */
   public function afficherfront(TicketRepository $ticketRep ,Request $request)
    {

        $ticket = $ticketRep->findAll();

        return $this->render('ticket/affichefront.html.twig', [
            'ticket' => $ticket
        ]);

    }




    /**
     * @Route("/Ajouterticket", name="Ajouterticket")
     */
    public function ajouter(Request $request)
    {
        $ticket = new Ticket();//creation instance
        $form = $this->createForm(TicketType::class, $ticket);//Récupération du formulaire dans le contrôleur:
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid() ) {

            $em = $this->getDoctrine()->getManager();//recupuration entity manager

            $em->persist($ticket);//l'ajout de l'objet cree
            $em->flush();
            return $this->redirectToRoute('AfficheT');//redirecter la pagee la page dafichage
        }



        return $this->render('ticket\ajouter.html.twig', [
            'form' => $form->createview()
        ]);


    }
    /**
     * @route ("ticket/modifier/{id}", name="u")
     */
    function modifier(TicketRepository $repository,Request $request,$id)
    {
        $ticket = $repository->find($id);
        $form = $this->createForm(TicketType::class,$ticket);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('AfficheT');
        }
        return $this->render('ticket/modifier.html.twig', [
            'form' => $form->createView()
        ]);

    }



    /**
     * @Route("/d/{id}", name="d")
     */
    public function supprimer ($id)
    {
        $ticket=$this->getDoctrine()->getRepository(ticket::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($ticket);//suprrimer lobjet dans le parametre
        $em->flush();
        return $this->redirectToRoute('AfficheT');


    }
}
