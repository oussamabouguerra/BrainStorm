/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author mouad
 */
public class Reservation {
    
    private int idRes,idUser,idTicket,idAlim;
    private float prix;
    private String date,heure;

    public Reservation(int idRes, int idUser, int idTicket, int idAlim, float prix, String date, String heure) {
        this.idRes = idRes;
        this.idUser = idUser;
        this.idTicket = idTicket;
        this.idAlim = idAlim;
        this.prix = prix;
        this.date = date;
        this.heure = heure;
    }
    
    
    
    public Reservation() {
    }

    public int getIdRes() {
        return idRes;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdAlim() {
        return idAlim;
    }

    public void setIdAlim(int idAlim) {
        this.idAlim = idAlim;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idRes=" + idRes + ", idUser=" + idUser + ", idTicket=" + idTicket + ", idAlim=" + idAlim + ", prix=" + prix + ", \n date=" + date + ", heure=" + heure +"\n"+ '}';
    }
    
    
    
    
}
