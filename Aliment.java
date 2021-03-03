/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author 21624
 */

public class Aliment {
int id,quantite,prix,idpromo;
String type,marque;

    public Aliment(int id, int quantite, int prix, int idpromo, String type, String marque) {
        this.id = id;
        this.quantite = quantite;
        this.prix = prix;
        this.idpromo = idpromo;
        this.type = type;
        this.marque = marque;
    }


    public Aliment() {
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
        return "aliment{" + "id=" + id + ", quantite=" + quantite + ", prix=" + prix + ", idpromo=" + idpromo + ", type=" + type + ", marque=" + marque + '}';
    }

}
