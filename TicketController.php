<?php
namespace App\Controller;

use App\Entity\Ticket;
use App\Form\SearchTicketType;
use App\Form\TicketType;
use App\Repository\TicketRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Console\Helper\TableCell;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Dompdf\Dompdf;
use Dompdf\Options;
use Knp\Component\Pager\PaginatorInterface;


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
     * @Route("/roulette", name="roulette")
     */

    public function roulette(): Response
    {

        return $this->render('ticket/roulette.html.twig', [
            'controller_name' => 'TicketController',
        ]);
    }
    /**
     * @Route("/AfficheT", name="AfficheT")
     */
    public function afficherback(TicketRepository  $ticketRep ,Request $request)
    {
            $em=$this->getDoctrine()->getManager();
            $ticket=$em->getRepository(Ticket::class)->findAll();
            if($request->isMethod('POST'))
            {
                $PrixTicket=$request->get('PrixTicket');
                $ticket=$em->getRepository(ticket::class)->findBy(array('PrixTicket'=>$PrixTicket));
            }
            return $this->render('ticket/index.html.twig', array('ticket' => $ticket)
            );



    }
    /**
     * @Route("/trisalle", name="trisalle")
     */

    public function TriN()
    {

        $ticket= $this->getDoctrine()->getRepository(Ticket::class)->listOrderByD();
        return $this->render("ticket/tri.html.twig",['ticket'=>$ticket]);

    }
    /**
     * @Route("/trifilm", name="trifilm")
     */

    public function Trifilm()
    {

        $ticket= $this->getDoctrine()->getRepository(Ticket::class)->listOrderByName();
        return $this->render("ticket/trifilm.html.twig",['ticket'=>$ticket]);

    }
    /**
     * @Route("/Affichefront", name="Affichefront")
     */
    public function afficherfront(TicketRepository $ticketRep ,Request $request,PaginatorInterface $paginator)
    {

        $ticket = $ticketRep->findAll();
        $ticket = $paginator->paginate(
            $ticket, // Requête contenant les données à paginer (ici nos articles)
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            2 // Nombre de résultats par page
        );


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

        $this->addFlash(
            'info',
            'Ajout avec success'
        );

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
        $this->addFlash(
            'info',
            'Modification avec success'
        );
        return $this->render('ticket/modifier.html.twig', [
            'form' => $form->createView()
        ]);

    }


public function calc($PrixTicket,$Promo)
{
    $calc = new Calc;
    $a = 100;
    $r =0;
    $r=($PrixTicket * $Promo)/$a;
    return r;
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
        $this->addFlash(
            'info',
            'Ticket Supprimer'
        );
        return $this->redirectToRoute('AfficheT');


    }
    /**
     * @route ("/mypdf", name="pdf")
     */
    public function imprimerpdf()
    {
        $ticket=$this->getDoctrine()->getRepository(Ticket::class)->findAll();
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('ticket/mypdf.html.twig', [
            'ticket' => $ticket
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);
    }



/**
     * @route("/changeLocale", name="changeLocale")
     */
    public function changeLocale(Request $request)
    {
        $form = $this->createFormBuilder(null)
            ->add('locale', ChoiceType::class, [
                'choices' => [
                    'Français' 		=> 'fr_FR',
                    'Arabe'		=> 'ar_AR',
                    'English(US)'	=> 'en_US'
                ]
            ])
            ->add('save', SubmitType::class)
            ->getForm()
        ;

        $form->handleRequest($request);

        if ($form->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            $locale = $form->getData()['locale'];
            $user = $this->getUser();
            $user->setLocale($locale);
            $em->persist($user);
            $em->flush();
        }

        return $this->render('dashboard/index.html.twig', [
            'form'		=> $form->createView()
        ]);
    }

}
