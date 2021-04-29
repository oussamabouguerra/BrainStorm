<?php

namespace App\Controller;

use App\Entity\Film;
use App\Form\FilmType;
use App\Form\SearchFilmType;
use App\Repository\FilmRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\User\User;

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
     * @Route("/Afficherback", name="Afficherback")
     */

    public function affiche(FilmRepository $filmRepository,Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $Films=$em->getRepository(Film::class)->findAll();
        if($request->isMethod('POST'))
        {
            $nom=$request->get('nom');
            $Films=$em->getRepository(Film::class)->findBy(array('nom'=>$nom));
        }
        return $this->render('back/index.html.twig', array('film' => $Films)
        );

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


    /**
     * @route ("/AffichefrontFilm", name="AffichefrontFilm")
     */
 public function recherche (Request $request ,Request $request1, PaginatorInterface $paginator){
        $em=$this->getDoctrine()->getManager();
        $Films=$em->getRepository(Film::class)->findAll();

$Film = $paginator->paginate(

         $Films,
         $request->query->getInt('page', 1),
         // Items per page
         2
     );
        return $this->render('film/film.html.twig',['film'=>$Film]);

 }
    /**
     * @Route("/trinomfilm", name="trinomfilm")
     */

    public function TriN()
    {

        $Film= $this->getDoctrine()->getRepository(Film::class)->listOrderByD();
        return $this->render("back/index.html.twig",['film'=>$Film]);

    }
    /**
     * @Route("/tricategorie", name="tricategorie")
     */

    public function TriC()
    {

        $Film= $this->getDoctrine()->getRepository(Film::class)->listOrderByC();
        return $this->render("back/index.html.twig",['film'=>$Film]);

    }
    /**
     * @Route("/lista", name="film_list")
     */
    public function lista()
    {
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $repository = $this->getDoctrine()->getrepository(Film::Class);//recuperer repisotory
        $film = $repository->findAll();//affichage
        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('back\lista.html.twig', [
            'film' => $film,
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("Films.pdf", [
            "Attachment" => true
        ]);
    }


}
