/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Med Aziz
 */
public class Film {
    
    int id,idadmin,idpromo;
    String nom,categorie,duree,photos,synopsis,actor,trailer;
    ImageView photo;
    MediaPlayer trailers;
    float prix;

    public Film() {
    }
    public static int Pdp;

    public static int getPdp() {
        return Pdp;
    }

    public static void setPdp(int Pdp) {
        Film.Pdp = Pdp;
    }

 public Film(int id,  String nom, String categorie, String duree, String photos, float prix,String synopsis, String actor) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.duree = duree;
        this.photos = photos;
        this.prix = prix;
        this.synopsis=synopsis;
        this.actor=actor;
        }

    public Film(String photos) {
        this.photos=photos;
     }

    public Film(int id,  String nom, String categorie, String duree, String photos, float prix,String synopsis, String actor,String trailer) {
            this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.duree = duree;
        this.photos = photos;
        this.prix = prix;
        this.synopsis=synopsis;
        this.actor=actor;
        this.trailer=trailer;
     }

    

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public MediaPlayer getTrailers() {
        return trailers;
    }

    public void setTrailers(MediaPlayer trailers) {
        this.trailers = trailers;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(int idadmin) {
        this.idadmin = idadmin;
    }

    public int getIdpromo() {
        return idpromo;
    }

    public void setIdpromo(int idpromo) {
        this.idpromo = idpromo;
    }

    public String getNom() {
        return nom;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Film{" + "id=" + id + ", idadmin=" + idadmin + ", idpromo=" + idpromo + ", nom=" + nom + ", categorie=" + categorie + ", duree=" + duree + ", synopsis=" + synopsis + ", actor=" + actor + ", photo=" + photo + ", trailers=" + trailers + ", prix=" + prix + '}';
    }

   

   
    

  


   
    
    
}
