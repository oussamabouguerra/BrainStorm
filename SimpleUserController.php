<?php

namespace App\Controller;

use App\Entity\SimpleUser;
use App\Form\SimpleUserType;
use App\Repository\SimpleUserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\HttpFoundation\Session\Session ;




class SimpleUserController extends AbstractController
{
    /**
     * @Route("/ajouter/simple/user", name="ajouter_simple_user")
     */
    public function index(): Response
    {
        return $this->render('ajouter_simple_user/index.html.twig', [
            'controller_name' => 'SimpleUserController',
        ]);
    }

    /**
     * @Route("/AjouterSU", name="simple")
     */
    public function new(Request $request): Response
    {
        $simpleUser = new SimpleUser();
        $form = $this->createForm(SimpleUserType::class, $simpleUser);
        $form->add("Ajouter",SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($simpleUser);
            $entityManager->flush();
            return $this->redirectToRoute("aff");
        }

        return $this->render('simple_user/new.html.twig', [
            'simple_user' => $simpleUser,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/afficherSU", name="aff")
     */
    function affichemedForAdmin(SimpleUserRepository $repo, Request $request){

        $parent =$repo->CountParent();
        $parentColor= [];
        $enfColor = [];
        $countUser = [];
        $identifiant = [];
        foreach($parent as $parent ){

            if ($parent['identifiant'] == 'non')
                $parentColor[] = '#7FB3D5' ;
            elseif ($parent['identifiant'] == 'oui')
                $parentColor[] = '#F5B7B1 ';
            $countUser [] = $parent['countUser'];
            $identifiant [] = $parent['identifiant'];
        }

        $user=$repo->findAll();

        return $this->render("simple_user/show.html.twig",
            ['user'=>$user,
            'countUser' => json_encode($countUser),
            'identifiant' => json_encode($identifiant),
            'parentColor' => json_encode($parentColor)
            ] );
    }

    /**
     * @Route("/suppSU/{id}", name="d")
     */
    function deleteDemande(SimpleUserRepository $repo , $id) {
        $user=$repo->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute("aff");
    }

    /**
     * @Route("/supp2", name="d2")
     */
    function delete2(SimpleUserRepository $repo ,  Request $request){
        $session = $request->getSession();
        $session->start();
        $id=$session->get('name');
        $user = $repo->findOneBy(['id'=>$id]);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute("pageRegistery");
    }

    /**
     * @Route("/updateSU/{id}", name="pere")
     */
    public function UpdateParents (SimpleUserRepository $rep , Request $request , $id)
    {
        $Directeur = $rep->find($id);
        $form =$this->createForm(SimpleUserType :: class , $Directeur);
        $form->add('Update' , SubmitType::class);
        $form->handleRequest($request);
        if ($form ->isSubmitted() && $form->isValid() ) {
            $em =$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("aff");

        }
        return $this->render('simple_user/edit.html.twig',[
            'form' =>$form->createView(),
        ]);
    }
    /**
     * @Route("/updateSU", name="per1")
     */
    public function Update2 (SimpleUserRepository $rep , Request $request )
    {
        $session = $request->getSession();
        $session->start();
        $id=$session->get('name');
        $Directeur = $rep->findOneBy(['id'=>$id]);
        $form =$this->createForm(SimpleUserType :: class , $Directeur);
        $form->add('Update' , SubmitType::class);
        $form->handleRequest($request);
        if ($form ->isSubmitted() && $form->isValid() ) {
            $em =$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("pageConnexion");

        }
        return $this->render('simple_user/edit2.html.twig',[
            'form' =>$form->createView(),
        ]);
    }

    /**
     * @Route ("/rechercherSU", name="rechercheAdmin")
     */
    public function RechercherUser(SimpleUserRepository $repository, Request $request)
    {
        $parent =$repository->CountParent();
        $parentColor= [];
        $enfColor = [];
        $countUser = [];
        $identifiant = [];
        foreach($parent as $parent ){

            if ($parent['identifiant'] == 'non')
                $parentColor[] = '#7FB3D5' ;
            elseif ($parent['identifiant'] == 'oui')
                $parentColor[] = '#F5B7B1 ';
            $countUser [] = $parent['countUser'];
            $identifiant [] = $parent['identifiant'];
        }


        $data=$request->get('searchUser');
        $admin=$repository->findBy(['nom'=>$data]);
        return $this->render("simple_user/show.html.twig",
            ['user'=>$admin,
                'countUser' => json_encode($countUser),
                'identifiant' => json_encode($identifiant),
                'parentColor' => json_encode($parentColor)]);
    }

    /**
     * @Route ("/CineJamil", name="page1")
     */
    public function Page1 (SimpleUserRepository $repository, Request $request)
    {

        return $this->render("simple_user/page1.html.twig",
            ['user'=>'page1']);
    }

    /**
     * @Route ("/Deconexion", name="Deconexion")
     */
    public function Deconexion( Request $request)
    {
        $session = $request->getSession();
        $session->remove('user');
        return $this->render("simple_user/page1.html.twig",
            ['user'=>'page1']);
    }

    /**
     * @Route ("/NewAccountSU", name="pageRegistery")
     */
    public function PageRegistery (SimpleUserRepository $repository, Request $request)
    {
        $simpleUser = new SimpleUser();
        $form = $this->createForm(SimpleUserType::class, $simpleUser);
        $form->add("Ajouter",SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($simpleUser);
            $entityManager->flush();
            return $this->redirectToRoute("page1");
        }

        return $this->render('simple_user/registery.html.twig', [
            'simple_user' => $simpleUser,
            'form' => $form->createView(),
        ]);

    }

    /**
     * @Route ("/connexion", name="pageConnexion")
     */
    public function PageConnexion (SimpleUserRepository $repository, Request $request)
    {

        return $this->render("simple_user/connexion.html.twig",
            ['user'=>'pageConnexion']);
    }


    /**
     * @Route ("/CompteCineJamil", name="ConnexionSU")
     */
    public function Connexion(SimpleUserRepository $repository, Request $request)
    {
        $data=$request->get('mail');
        $admin=$repository->findOneBy(['mail'=>$data]);
        if(($admin->getBlock()==null)||($admin->getBlock()=='non')){
        if(($admin->getMdp())==($request->get('mdp')))
        {
            $session = $request->getSession();
            $session->start();
            $var=$admin->getId();
            $session->set('user', $admin);



            return $this->render("simple_user/page2.html.twig",
                ['userActuel'=>$admin]);
        }
        else{return $this->redirectToRoute("pageConnexion");}}
        else{return $this->redirectToRoute("pageConnexion");}
    }

    /**
     * @Route ("/triSU",name="triSU")
     */
    public function tri(SimpleUserRepository $repository , Request $request)
    {
        $parent =$repository->CountParent();
        $parentColor= [];
        $enfColor = [];
        $countUser = [];
        $identifiant = [];
        foreach($parent as $parent ){

            if ($parent['identifiant'] == 'non')
                $parentColor[] = '#7FB3D5' ;
            elseif ($parent['identifiant'] == 'oui')
                $parentColor[] = '#F5B7B1 ';
            $countUser [] = $parent['countUser'];
            $identifiant [] = $parent['identifiant'];
        }

        if (isset($_POST['tri']))
        {
            $choix = $_POST['tri'];
            if ($choix=='Nom')
            {
                $produit=$repository->OrderByNom();
                return $this->render("simple_user/show.html.twig",['user'=>$produit,
                    'countUser' => json_encode($countUser),
                    'identifiant' => json_encode($identifiant),
                    'parentColor' => json_encode($parentColor)
                ]);
            }
            elseif ($choix=='Prenom')
            {
                $produit=$repository->OrderByPrenom();
                return $this->render("simple_user/show.html.twig",['user'=>$produit,
                    'countUser' => json_encode($countUser),
                    'identifiant' => json_encode($identifiant),
                    'parentColor' => json_encode($parentColor)
                    ]);
            }


        }

    }

    /**
     * @Route ("/Bloquer/{id}", name="Bloquer")
     */
    public function Bloquer (SimpleUserRepository $repository, Request $request,$id)
    {
        $user=$repository->find($id);
        $user->setBlock('oui');
        $em =$this->getDoctrine()->getManager();
        $em->flush();
        return $this->redirectToRoute("aff");
    }

    /**
     * @Route ("/DBloquer/{id}", name="DBloquer")
     */
    public function DBloquer (SimpleUserRepository $repository, Request $request,$id)
    {
        $user=$repository->find($id);
        $user->setBlock('non');
        $em =$this->getDoctrine()->getManager();
        $em->flush();
        return $this->redirectToRoute("aff");
    }



    //mdp oublié
    /**
     * @Route ("/mailling", name="mailler")
     */
    function mot () {
        return $this->render("simple_user/mdpOublie.html.twig",
            ['user'=>'pageConnexionAdmin']);
    }
    /**
     * @Route ("/Motpasse", name="oubliepasse")
     */
    function genererChaineAleatoire(\Swift_Mailer $mailer , $longueur = 10 , Request $request  ,SimpleUserRepository  $repository )
    {
        $data=$request->get('mail');

        $caracteres = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        $longueurMax = strlen($caracteres);
        $chaineAleatoire = '';
        for ($i = 0; $i < $longueur; $i++)
        {
            $chaineAleatoire .= $caracteres[rand(0, $longueurMax - 1)];
        }
        $var = $chaineAleatoire ;

        $message = (new \Swift_Message('Validation'))
            ->setFrom('cinejamil94@gmail.com','Administration')
            ->setTo($data)
            ->setBody($var);

        $mailer->send($message);

        // $tuteur = new Validation() ;
        $tuteur = $repository->findOneBy(['mail'=>$data]);
        $tuteur->setCodem($var);
        $em=$this->getDoctrine()->getManager();
        $em->flush();
        $session = $request->getSession();
        $session->start();
        $var=$tuteur->getId();
        $session->set('name', $var);

        return $this->render("simple_user/mdpVerifie.html.twig",
            ['user'=>'pageConnexionAdmin']);
    }
    /**
     * @Route ("/verr", name="cod")
     */
    function Codev () {
        return $this->render("simple_user/mdpVerifie.html.twig",
            ['user'=>'pageConnexionAdmin']);
    }
    /**
     * @Route ("/verifier", name="coder")
     */

    function code (Request  $request , SimpleUserRepository  $repository)  {

        $data=$request->get('mail');

        $tuteur = $repository->findOneBy(['codem'=>$data]);

        if (($tuteur->getCodem()) ==($data )){
            $session = $request->getSession();
            $session->start();
            $var=$tuteur->getId();
            $session->set('name', $var);

            return $this->render("simple_user/mdpNew.html.twig",
                ['user'=>'pageConnexionAdmin']);
        }  else
            return $this->render("simple_user/mdpVerifier.html.twig",
                ['user'=>'pageConnexionAdmin']);

    }
    /**
     * @Route ("/passwordj", name="passsw")
     */

    function password (Request  $request , SimpleUserRepository  $repository)  {
        $data=$request->get('pass1');
        $data1=$request->get('pass2');
        $session = $request->getSession();
        $session->start();
        $var = $session->get('name');
        if ($data == $data1) {
            $tuteur = $repository->findOneBy(['id'=>$var]);

            $tuteur->setMdp($data);

            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->render("simple_user/connexion.html.twig",
                ['user'=>'pageConnexionAdmin']);
        }

        else  {
            return $this->render("simple_user/mdpNew.html.twig",
                ['user'=>'pageConnexionAdmin']);
        }


    }



}