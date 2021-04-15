<?php

namespace App\Form;

use App\Entity\Film;
use App\Entity\Acteur;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class FilmType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('image',FileType::class,array(
                'data_class' => null
            ))

            ->add('nom')
            ->add('categorie')
            ->add('duree')
            ->add('prix')
            ->add('synopsis')
            ->add('actor',EntityType::class,[
                'class'=>Acteur::class,
                'choice_label'=>'nom'
            ])



        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Film::class,
        ]);
    }
}
