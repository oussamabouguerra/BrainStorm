<?php

namespace App\Controller;

use App\Entity\Promotion;
use App\Form\PromotionType;
use App\Repository\PromotionRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class PromotionController extends AbstractController
{
    /**
     * @Route("/promotion", name="promotion")
     */
    public function index(): Response
    {
        return $this->render('promotion/home.html.twig', [
            'controller_name' => 'PromotionController',
        ]);
    }

    /**
     * @Route("/AfficheP", name="AfficheP")
     */
    public function afficher(PromotionRepository $promotionRep ,Request $request)
    {

        $promotion = $promotionRep->findAll();

        return $this->render('promotion/index.html.twig', [
            'promotion' => $promotion
        ]);

    }
    /**
     * @Route("/Affichefront", name="Affichefront")
     */
    public function afficherf(PromotionRepository $promotionRep ,Request $request)
    {

        $promotion = $promotionRep->findAll();

        return $this->render('promotion/afficher.html.twig', [
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
     * @route ("service/modifier/{id}", name="u")
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
     * @Route("/d/{id}", name="d")
     */
    public function supprimer ($id)
    {
        $promotion=$this->getDoctrine()->getRepository(promotion::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($promotion);//suprrimer lobjet dans le parametre
        $em->flush();
        return $this->redirectToRoute('AfficheP');


    }
}
