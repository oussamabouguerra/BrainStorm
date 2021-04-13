<?php

namespace App\Controller;

use App\Entity\Fidelite;
use App\Form\FideliteType;
use App\Repository\FideliteRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class FideliteController extends AbstractController
{
    /**
     * @Route("/fidelite", name="fidelite")
     */
    public function index(): Response
    {
        return $this->render('fidelite/home.html.twig', [
            'controller_name' => 'FideliteController',
        ]);
    }
    /**
     * @Route("/AfficheF", name="AfficheF")
     */
    public function afficher(FideliteRepository $fideliteRep ,Request $request)
    {

        $fidelite = $fideliteRep->findAll();

        return $this->render('fidelite/index.html.twig', [
            'fidelite' => $fidelite
        ]);

    }
    /**
     * @Route("/Affichefrontfidelite", name="Affichefrontfidelite")
     */
    public function afficherf(FideliteRepository $fideliteRep ,Request $request)
    {

        $fidelite = $fideliteRep->findAll();

        return $this->render('fidelite/afficherfront.html.twig', [
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
     * @route ("/modifierfidelite/{id}", name="modif")
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
     * @Route("/delete/{id}", name="delete")
     */
    public function supprimer ($id)
    {
        $fidelite=$this->getDoctrine()->getRepository(fidelite::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($fidelite);//suprrimer lobjet dans le parametre
        $em->flush();
        return $this->redirectToRoute('AfficheF');


    }
}
