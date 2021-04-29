<?php

namespace App\Controller;

use App\Entity\Reservation;
use App\Entity\SimpleUser;
use App\Form\ReservationType;
use App\Repository\ReservationRepository;
use App\Repository\SimpleUserRepository;
use App\Repository\TicketRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;


class ReservationController extends AbstractController
{
    /**
     * @Route("/ajouter/reservation", name="ajouter_reservation")
     */
    public function index(): Response
    {
        return $this->render('reservation/index.html.twig', [
            'controller_name' => 'ReservationController',
        ]);
    }

    /**
     * @Route("/ajouterRes", name="Res")
     */
    public function new(Request $request, \Swift_Mailer $mailer, SimpleUserRepository $repp): Response
    {
        $session = $request->getSession();
        $session->start();
        $id=$session->get('name');
        $simpleUser = new Reservation();
        $simpleUser->setIdUser($id);

        $recever=$repp->findOneBy(['id'=>$id]);

        $form = $this->createForm(ReservationType::class, $simpleUser);
        $form->add("Ajouter",SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($simpleUser);
            $entityManager->flush();

            //mail
            $message = (new \Swift_Message('Cine Jamil'))
                ->setFrom('cinejamil94@gmail.com','Administration')
                ->setTo($recever->getMail())
                ->setBody(
                   'Votre réservation a été bien ajoutée!'
                );
            $mailer->send($message);
            return $this->redirectToRoute("affRes");
        }

        return $this->render('reservation/new.html.twig', [
            'simple_user' => $simpleUser,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/afficherRes", name="affRes")
     */
    function affichemedForAdmin(ReservationRepository $repo, Request $request){
        $session = $request->getSession();
        $session->start();
        $id=$session->get('name');
        $user=$repo->findBy(['idUser'=>$id]);

        return $this->render("reservation/show.html.twig",
            ['user'=>$user]);
    }


    /**
     * @Route("/suppRes/{id}", name="dRes")
     */
    function deleteDemande(ReservationRepository $repo , $id) {
        $user=$repo->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute("affRes");
    }

    /**
     * @Route("/updateRes/{id}", name="pereRes")
     */
    public function UpdateParents (ReservationRepository $rep , Request $request , $id)
    {
        $Directeur = $rep->find($id);
        $form =$this->createForm(ReservationType :: class , $Directeur);
        $form->add('Update' , SubmitType::class);
        $form->handleRequest($request);
        if ($form ->isSubmitted() && $form->isValid() ) {
            $em =$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("affRes");

        }
        return $this->render('reservation/edit.html.twig',[
            'form' =>$form->createView(),
        ]);
    }


    /**
     * @Route("/imprimerRes", name="imprimerRes")
     */
    function ImprimerRes(ReservationRepository $repo, Request $request){

        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        $session = $request->getSession();
        $session->start();
        $id=$session->get('name');
        $user=$repo->findBy(['idUser'=>$id]);


        // Retrieve the HTML generated in our twig file
        $html = $this->renderView("reservation/listeRes.html.twig",
            ['user'=>$user]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => false
        ]);


    }


}