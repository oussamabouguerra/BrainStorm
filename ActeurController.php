<?php

namespace App\Controller;


use App\Entity\Acteur;
use App\Form\ActeurType;
use App\Repository\ActeurRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ActeurController extends AbstractController
{
    /**
     * @Route("/acteur", name="acteur")
     */
    public function index(): Response
    {
        return $this->render('acteur/index.html.twig', [
            'controller_name' => 'ActeurController',
        ]);
    }
    /**
     * @Route("/afficheracteur", name="afficheracteur")
     */

    public function affiche(ActeurRepository $acteurRepository)
    {
        $acteur = $acteurRepository->findAll();

        return $this->render('acteur/acteur.html.twig', [
            'acteur' => $acteur,
        ]);

    }
    /**
     * @Route("/afficheracteurback", name="afficheracteurback")
     */

    public function afficheback(ActeurRepository $acteurRepository)
    {
        $acteur = $acteurRepository->findAll();

        return $this->render('back/afficheracteur.html.twig', [
            'acteur' => $acteur,
        ]);
    }
    /**
     * @Route("/supprimeracteur/{id}", name="supprimeracteur")
     */
    public function suppriF ($id)
    {
        $film=$this->getDoctrine()->getRepository(Acteur::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($film);//suprrimer lobjet dans le parametre
        $em->flush();
        return $this->redirectToRoute('afficheracteurback');
    }
    /**
     * @Route("/Ajoutacteur", name="Ajoutacteur")
     */
    public function ajouterfront(Request $request)
    {
        $acteur = new Acteur();//creation instance
        $form = $this->createForm(ActeurType::class, $acteur);//Récupération du formulaire dans le contrôleur:
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();//recupuration entity manager
            $em->persist($acteur);//l'ajout de la objet cree
            $em->flush();
            return $this->redirectToRoute('afficheracteurback');//redirecter la pagee la page dafichage
        }

        return $this->render('back\ajouteracteur.html.twig', [
            'form' => $form->createview()
        ]);
    }
    /**
     * @Route ("Modifieracteur/{id}", name="Modifieracteur")
     */
    function modifier(ActeurRepository  $repository,Request $request,$id)
    {
        $film = $repository->find($id);
        $form = $this->createForm(ActeurType::class,$film);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('afficheracteurback');
        }
        return $this->render('back/modifieracteur.html.twig', [
            'form' => $form->createView()
        ]);

    }
}
