/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Med Aziz
 */
public class Promotion {
    
    int id;
    String nom,type,description,pourcentage,categorie;
    private String code;
public static int pdp ;

    public static int getPdp() {
        return pdp;
    }

    public static void setPdp(int pdp) {
        Promotion.pdp = pdp;
    }


    public Promotion() {
    }

    public Promotion(int id, String nom, String type, String description, String pourcentage,String categorie,String code) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.pourcentage = pourcentage;
        this.categorie = categorie;
        this.code=code;

    }
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(String pourcentage) {
        this.pourcentage = pourcentage;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", description=" + description + ", pourcentage=" + pourcentage + ", categorie=" + categorie + '}';
    }

    
    
      
    
}
