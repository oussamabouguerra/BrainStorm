<?php

namespace App\Controller;

use App\Entity\Fidelite;
use App\Form\FideliteType;
use App\Form\SearchFideliteType;
use App\Repository\FideliteRepository;
use App\Repository\PromotionRepository;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
use Dompdf\Dompdf;
use Dompdf\Options;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class
FideliteController extends AbstractController
{
    /**
     * @Route("/home", name="home")
     */
    public function index(): Response
    {
        return $this->render('front/home.html.twig', [
            'controller_name' => 'FideliteController',
        ]);
    }
    /**
     * @Route("/AfficheF", name="AfficheF")
     */
    public function afficher(FideliteRepository $fideliteRep ,Request $request,PaginatorInterface $paginator)
    {

        $fidelite = $fideliteRep->findAll();

        $fidelite = $paginator->paginate(
            $fidelite, // Requête contenant les données à paginer (ici nos articles)
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            2 // Nombre de résultats par page
        );


        $form= $this->createForm(SearchFideliteType::class);
        $search =$form->handleRequest($request);
        if ($form->isSubmitted()&&$form->isValid()){
            $fidelite=$fideliteRep->search(
                $search->get('mots')->getData()
            );
        }
        return $this->render('fidelite/index.html.twig', [
            'fidelite' => $fidelite ,
            'form'=>$form->createView()
        ]);

    }
    /**
     * @Route("/Affichefrontfidelite", name="Affichefrontfidelite")
     */
    public function afficherf(FideliteRepository $fideliteRep ,Request $request,PaginatorInterface $paginator)
    {

        $session = $request->getSession();
        $user = $session->get('user') ;
        $fidelite = $fideliteRep->findBy(['usr' => $user->getId() ]);
        $fidelite = $paginator->paginate(
            $fidelite, // Requête contenant les données à paginer (ici nos articles)
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            2// Nombre de résultats par page
        );

        return $this->render('front/affichefidelite.html.twig', [
            'fidelite' => $fidelite
        ]);

    }





    /**
     * @Route("/Ajoutfidelite", name="Ajoutfidelite")
     */
    public function ajouter(Request $request)
    {
        $fidelite = new Fidelite();//creation instance
        $form = $this->createForm(FideliteType::class, $fidelite);//Récupération du formulaire dans le contrôleur:
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid() ) {

            $em = $this->getDoctrine()->getManager();//recupuration entity manager

            $em->persist($fidelite);//l'ajout de l'objet cree
            $em->flush();
            return $this->redirectToRoute('AfficheF');//redirecter la pagee la page dafichage
        }



        return $this->render('fidelite\ajouter.html.twig', [
            'form' => $form->createview()
        ]);


    }
    /**
     * @route ("/modifierfidelite/{id}", name="modifierfideli")
     */
    function modifier(FideliteRepository $repository,Request $request,$id)
    {
        $fidelite = $repository->find($id);
        $form = $this->createForm(FideliteType::class,$fidelite);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('AfficheF');
        }
        return $this->render('fidelite/modifier.html.twig', [
            'form' => $form->createView()
        ]);

    }



    /**
     * @Route("/delete/{id}", name="deletefideli")
     */
    public function supprimer ($id)
    {
        $fidelite=$this->getDoctrine()->getRepository(fidelite::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($fidelite);//suprrimer lobjet dans le parametre
        $em->flush();
        return $this->redirectToRoute('AfficheF');


    }

    /**
     * @route ("/mypdff", name="pdff")
     */
    public function imprimerpdf()
    {
        $fidelite=$this->getDoctrine()->getRepository(Fidelite::class)->findAll();
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('fidelite/mypdff.html.twig', [
            'fidelite' => $fidelite
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdff.pdf", [
            "Attachment" => true
        ]);
    }

}
