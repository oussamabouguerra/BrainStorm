<?php

namespace App\Form;

use App\Entity\Film;
use App\Entity\Ticket;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SearchType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class SearchTicketType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('mots', SearchType::class, [
                'label' => false,
                'attr' => [
                    'class'=>'form-control',
                    'placeholder'=>'Rechercher'
                ],
                'required'=> false
            ])
            ->add('film', EntityType::class ,[
                'class'=> Ticket::class,
                'label'=>false,
                'attr' => [
                    'class'=>'form-control',

                ],
                'required'=> false
            ])
            ->add('Rechercher', SubmitType::class,[
                'attr' => [
                    'class'=>'btn primary',
                ]
            ])
        ;

    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            // Configure your form options here
        ]);
    }
}
