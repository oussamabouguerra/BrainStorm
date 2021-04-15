<?php

namespace App\Form;

use App\Entity\Film;
use App\Entity\Promotion;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class TicketType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('PrixTicket')
            ->add('NbPlace')
            ->add('Salle')
            ->add('dateh', DateTimeType::class, [
                'date_widget' => 'single_text'
            ])
            ->add('film',EntityType::class,[
                'class'=>Film::class,
                'choice_label'=>'nom'
            ])
            ->add('promo',EntityType::class,[
                'class'=>Promotion::class,
                'choice_label'=>'pourcentage'
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
