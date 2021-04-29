<?php

namespace App\Controller;

use App\Entity\Promotion;
use App\Form\PromotionType;
use App\Form\SearchPromotionType;
use App\Repository\PromotionRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class PromotionController extends AbstractController
{
    /**
     * @Route("/home", name="home")
     */
    public function index(): Response
    {
        return $this->render('front/home.html.twig', [
            'controller_name' => 'PromotionController',
        ]);
    }

    /**
     * @Route("/AfficheP", name="AfficheP")
     */
    public function afficher(PromotionRepository $promotionRep ,Request $request)
    {
        $promotion = $promotionRep->findAll();
        $form= $this->createForm(SearchPromotionType::class);
        $search = $form->handleRequest($request);
        if($form->isSubmitted()&&$form->isValid()){
            $promotion= $promotionRep->search(
                $search->get('mots')->getData(),
                $search->get('categorie')->getData()
            );
        }
        return $this->render('promotion/index.html.twig', [
            'promotion' => $promotion,
            'form'=>$form->createView()

        ]);
    }


    /**
     * @Route("/AffichefrontPromotion", name="AffichefrontPromotion")
     */
    public function afficherf(PromotionRepository $promotionRep ,Request $request)
    {

        $promotion = $promotionRep->findAll();

        return $this->render('front/affichepromotion.html.twig', [
            'promotion' => $promotion
        ]);

    }


    /**
     * @Route("/Ajoutpromotion", name="Ajoutpromotion")
     */
    public function ajouter(Request $request)
    {
        $promotion = new Promotion();//creation instance
        $form = $this->createForm(PromotionType::class, $promotion);//Récupération du formulaire dans le contrôleur:
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid() ) {

            $em = $this->getDoctrine()->getManager();//recupuration entity manager

            $em->persist($promotion);//l'ajout de l'objet cree
            $em->flush();
            return $this->redirectToRoute('AfficheP');//redirecter la pagee la page dafichage
        }



        return $this->render('promotion\ajouter.html.twig', [
            'form' => $form->createview()
        ]);


    }
    /**
     * @route ("/modifierpromo/{id}", name="a")
     */
    function modifier(PromotionRepository $repository,Request $request,$id)
    {
        $promotion = $repository->find($id);
        $form = $this->createForm(PromotionType::class,$promotion);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('AfficheP');
        }
        return $this->render('promotion/modifier.html.twig', [
            'form' => $form->createView()
        ]);

    }



    /**
     * @Route("/d/{id}", name="c")
     */
    public function supprimer ($id)
    {
        $promotion=$this->getDoctrine()->getRepository(promotion::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($promotion);//suprrimer lobjet dans le parametre
        $em->flush();
        return $this->redirectToRoute('AfficheP');


    }

    /**
     * @route ("/mypdfPromotion", name="mypdfPromotion")
     */
    public function imprimerpdf()
    {
        $promotion=$this->getDoctrine()->getRepository(Promotion::class)->findAll();
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('promotion/mypdf.html.twig', [
            'promotion' => $promotion
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
     * @Route("/tritype", name="tritype")
     */

    public function TriT(PromotionRepository $promotionRep)
    {        $promotion=$promotionRep->findAll();

        $promotion= $this->getDoctrine()->getRepository(Promotion::class)->listOrderByType();
        return $this->render("promotion/TriType.html.twig",['promotion'=>$promotion]);

    }
    /**
     * @Route("/trinom", name="trinom")
     */

    public function TriN(PromotionRepository $promotionRep)
    {        $promotion=$promotionRep->findAll();

        $promotion= $this->getDoctrine()->getRepository(Promotion::class)->listOrderByNom();
        return $this->render("promotion/TriNom.html.twig",['promotion'=>$promotion]);

    }
}
