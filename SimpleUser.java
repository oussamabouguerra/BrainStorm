/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author mouad
 */
public class SimpleUser {
    private int id,cin;
    private String nom, prenom,mail, mdp, block, codem;

    public SimpleUser(String mail, String mdp) {
        this.mail = mail;
        this.mdp = mdp;
    }

    
    
    public SimpleUser(int id, int cin, String nom, String prenom, String mail, String mdp, String block, String codem) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.block = block;
        this.codem = codem;
    }

    public SimpleUser(int cin, String nom, String prenom, String mail, String mdp, String block, String codem) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.block = block;
        this.codem = codem;
    }

    public SimpleUser(int cin, String nom, String prenom, String mail, String mdp) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
    }

    public SimpleUser() {
    }
    
    

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getCodem() {
        return codem;
    }

    public void setCodem(String codem) {
        this.codem = codem;
    }

    @Override
    public String toString() {
        return "SimpleUser{" + "id=" + id + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", mdp=" + mdp + ", block=" + block + ", codem=" + codem + '}';
    }
    
    
    
}
