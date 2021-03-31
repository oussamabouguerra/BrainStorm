/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author 21624
 */

public class Aliment {
int id,quantite,prix,idpromo;
String type,marque,photo;
ImageView photos;

    public Aliment(int id, int quantite, int prix, int idpromo, String type, String marque,String photo) {
        this.id = id;
        this.quantite = quantite;
        this.prix = prix;
        this.idpromo = idpromo;
        this.type = type;
        this.marque = marque;
    }
 public Aliment(String photo) {
        this.photo=photo;
     }
    public Aliment( int quantite, int prix, int idpromo, String type, String marque, String photo) {
        this.id = id;
        this.quantite = quantite;
        this.prix = prix;
        this.idpromo = idpromo;
        this.type = type;
        this.marque = marque;
        this.photo = photo;
    }

    

    public Aliment() {
        
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ImageView getPhotos() {
        return photos;
    }

    public void setPhotos(ImageView photos) {
        this.photos = photos;
    }

   

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getIdpromo() {
        return idpromo;
    }

    public void setIdpromo(int idpromo) {
        this.idpromo = idpromo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Override
    public String toString() {
        return "Aliment{" + "id=" + id + ", quantite=" + quantite + ", prix=" + prix + ", idpromo=" + idpromo + ", type=" + type + ", marque=" + marque + ", photos=" + photos + '}';
    }

    /*public void setPhoto(ImageView imgView) {
       this.photos=photos;
    }*/

    

}
