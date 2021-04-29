<?php

namespace App\Controller;

use App\Entity\Administrateur;
use App\Form\AdministrateurType;
use App\Repository\AdministrateurRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


class AdministrateurController extends AbstractController
{
    /**
     * @Route("/ajouter/administrateur", name="ajouter_administrateur")
     */
    public function index()
    {
        return $this->render('administrateur/index.html.twig', [
            'controller_name' => 'AdministrateurController',
        ]);
    }

    /**
     * @Route("/ajouterAdmin", name="admin")
     */
    public function ajouterAdmin(Request $request)
    {
        $simpleUser = new Administrateur();
        $form = $this->createForm(AdministrateurType::class, $simpleUser);
        $form->add("Ajouter",SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($simpleUser);
            $entityManager->flush();
            return $this->redirectToRoute("affAdmin");

        }

        return $this->render('administrateur/new.html.twig', [

            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/afficherAdmin", name="affAdmin")
     */
    function affichemedForAdmin(AdministrateurRepository $repo){

        $user=$repo->findAll();

        return $this->render("administrateur/show.html.twig",
            ['user'=>$user]);
    }

    /**
     * @Route("/suppAdmin/{id}", name="dAdmin")
     */
    function deleteDemande(AdministrateurRepository $repo , $id) {
        $user=$repo->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute("affAdmin");
    }

    /**
     * @Route("/updateAdmin/{id}", name="pereAdmin")
     */
    public function UpdateParents (AdministrateurRepository $rep , Request $request , $id)
    {
        $Directeur = $rep->find($id);
        $form =$this->createForm(AdministrateurType :: class , $Directeur);
        $form->add('Update' , SubmitType::class);
        $form->handleRequest($request);
        if ($form ->isSubmitted() && $form->isValid() ) {
            $em =$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("affAdmin");

        }
        return $this->render('administrateur/edit.html.twig',[
            'form' =>$form->createView(),
        ]);
    }

    /**
     * @Route ("/rechercherAdmin", name="rechercheAdmin2")
     */
    public function RechercheAdmin(AdministrateurRepository $repository, Request $request)
    {
        $data=$request->get('searchAdmin');
        $admin=$repository->findBy(['nom'=>$data]);
        return $this->render("administrateur/show.html.twig",
            ['user'=>$admin]);
    }

    /**
     * @Route ("/connexionAdmin", name="pageConnexionAdmin")
     */
    public function PageConnexion2 (AdministrateurRepository $repository, Request $request)
    {

        return $this->render("administrateur/connexion.html.twig",
            ['user'=>'pageConnexionAdmin']);
    }

    /**
     * @Route ("/CompteAdmin", name="ConnexionAdmin")
     */
    public function Connexion(AdministrateurRepository $repository, Request $request)
    {
        $data=$request->get('mail');
        $admin=$repository->findOneBy(['mail'=>$data]);

        if(($admin->getMdp())==($request->get('mdp')))
        {
            $session = $request->getSession();
            $session->start();
            $var=$admin->getId();
            $session->set('name', $var);

            $user=$repository->findAll();

            return $this->render("administrateur/show.html.twig",
                ['user'=>$user]);

        }
        else{return $this->redirectToRoute("pageConnexion");}
    }

    /**
     * @Route ("/triAdmin",name="triAdmin")
     */
    public function tri(AdministrateurRepository $repository , Request $request)
    {

        if (isset($_POST['tri'])) {
            $choix = $_POST['tri'];
            if ($choix == 'Nom') {
                $produit = $repository->OrderByNom();
                return $this->render("administrateur/show.html.twig", ['user' => $produit]);
            } elseif ($choix == 'Prenom') {
                $produit = $repository->OrderByPrenom();
                return $this->render("administrateur/show.html.twig", ['user' => $produit]);
            }


        }
    }

}