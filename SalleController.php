<?php

namespace App\Controller;

use App\Entity\Salle;
use App\Form\SalleType;
use App\Repository\SalleRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class SalleController extends AbstractController
{
    /**
     * @Route("/salle", name="salle")
     */
    public function index(): Response
    {
        return $this->render('salle/home.html.twig', [
            'controller_name' => 'SalleController',
        ]);
    }
    /**
     * @Route("/AfficheS", name="AfficheS")
     */
    public function afficherback(SalleRepository  $salleRep ,Request $request)
    {

        $salle = $salleRep->findAll();

        return $this->render('salle/index.html.twig', [
            'salle' => $salle
        ]);

    }
    /**
     * @Route("/Affichesallef", name="Affichesallef")
     */
    public function afficherfront(SalleRepository $salleRep ,Request $request)
    {

        $salle = $salleRep->findAll();

        return $this->render('salle/afficherfront.html.twig', [
            'salle' => $salle
        ]);

    }




    /**
     * @Route("/Ajoutersalle", name="Ajoutersalle")
     */
    public function ajouter(Request $request)
    {
        $salle = new Salle();//creation instance
        $form = $this->createForm(SalleType::class, $salle);//Récupération du formulaire dans le contrôleur:
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid() ) {

            $em = $this->getDoctrine()->getManager();//recupuration entity manager

            $em->persist($salle);//l'ajout de l'objet cree
            $em->flush();
            return $this->redirectToRoute('AfficheS');//redirecter la pagee la page dafichage
        }



        return $this->render('salle\ajouter.html.twig', [
            'form' => $form->createview()
        ]);


    }
    /**
     * @route ("/modifiersalle/{id}", name="modif")
     */
    function modifier(SalleRepository $repository,Request $request,$id)
    {
        $salle = $repository->find($id);
        $form = $this->createForm(SalleType::class,$salle);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('AfficheS');
        }
        return $this->render('salle/modifier.html.twig', [
            'form' => $form->createView()
        ]);

    }



    /**
     * @Route("/delete/{id}", name="delete")
     */
    public function supprimer ($id)
    {
        $salle=$this->getDoctrine()->getRepository(salle::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($salle);//suprrimer lobjet dans le parametre
        $em->flush();
        return $this->redirectToRoute('AfficheS');


    }
}
