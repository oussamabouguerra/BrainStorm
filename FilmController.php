<?php

namespace App\Controller;

use App\Entity\Film;
use App\Form\FilmType;
use App\Repository\FilmRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class FilmController extends AbstractController
{
    /**
     * @Route("/home", name="home")
     */
    public function index(): Response
    {
        return $this->render('film/index.html.twig', [
            'controller_name' => 'FilmController',
        ]);
    }
    /**
     * @Route("/back", name="back")
     */
    public function back(): Response
    {
        return $this->render('/index.html.twig', [
            'controller_name' => 'FilmController',
        ]);
    }

    /**
     * @Route("/Affichefront", name="Affichefront")
     */

    public function affiche(FilmRepository $filmRepository)
    {
        $film = $filmRepository->findAll();

        return $this->render('film/film.html.twig', [
            'film' => $film,
        ]);

    }
    /**
     * @Route("/Afficherback", name="Afficherback")
     */

    public function afficheback(FilmRepository $filmRepository)
    {
        $film = $filmRepository->findAll();

        return $this->render('back/index.html.twig', [
            'film' => $film
        ]);

    }
    /**
     * @Route("/supprimerfilm/{id}", name="supprimerfilm")
     */
    public function suppriF ($id)
    {
        $film=$this->getDoctrine()->getRepository(Film::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($film);//suprrimer lobjet dans le parametre
        $em->flush();
        return $this->redirectToRoute('Afficherback');
    }

    /**
     * @Route("/Ajoutfilm", name="Ajoutfilm")
     */
    public function ajouterfront(Request $request)
    {
        $film = new Film();//creation instance
        $form = $this->createForm(FilmType::class, $film);//Récupération du formulaire dans le contrôleur:
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $file = $film->getImage();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('img_directory'),
                    $fileName
                );
            }
            catch (FileException $e){

            }

            $film->setImage($fileName);
            $em = $this->getDoctrine()->getManager();//recupuration entity manager
            $em->persist($film);//l'ajout de la objet cree
            $em->flush();
            return $this->redirectToRoute('Afficherback');//redirecter la pagee la page dafichage
        }

        return $this->render('back\ajouter.html.twig', [
            'form' => $form->createview()
        ]);
    }
    /**
     * @Route ("Modifier/{id}", name="Modifier")
     */
    function modifier(FilmRepository  $repository,Request $request,$id)
    {
        $film = $repository->find($id);
        $form = $this->createForm(FilmType::class,$film);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $file = $film->getImage();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('img_directory'),
                    $fileName
                );
            }
            catch (FileException $e){

            }
            $film->setImage($fileName);
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('Afficherback');
        }
        return $this->render('back/modifier.html.twig', [
            'form' => $form->createView()
        ]);

    }
}
