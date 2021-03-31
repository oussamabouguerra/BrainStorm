/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author oussama
 */
public class Ticket {

    public static void refresh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 

     
    private int ID;
    String Heurs,Date,Prix,NbPlace,Salle,Film,Promo;
 
    public Ticket(String Prix) {
        this.Prix=Prix;
     }
 

    
    public static int Pdp;
    public static String dates;

    public static String getDates() {
        return dates;
    }

    public static void setDates(String dates) {
        Ticket.dates = dates;
    }

    public static int getPdp() {
        return Pdp;
    }

    public static void setPdp(int Pdp) {
        Ticket.Pdp = Pdp;
    }

    public Ticket() {
       
    }

    public Ticket(String Prix, String NbPlace, String Salle, String Heurs, String Date, String Film, String Promo) {
        this.Prix = Prix;
        this.NbPlace = NbPlace;
        this.Salle = Salle;
        this.Film=Film;
        this.Promo=Promo;
        this.Heurs = Heurs;
        this.Date = Date;
    }

    public Ticket(int ID, String Prix, String NbPlace, String Salle, String Heurs, String Date,  String Film,String Promo) {
        this.ID=ID;
        this.Prix = Prix;
        this.NbPlace = NbPlace;
        this.Salle = Salle;
        this.Film=Film;
        this.Promo=Promo;
        this.Heurs = Heurs;
        this.Date = Date;    }

     
    
 
  
 

    public String getHeurs() {
        return Heurs;
    }

    public void setHeurs(String Heurs) {
        this.Heurs = Heurs;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

  
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
public String getPrix() {
        return Prix;
    }

    public void setPrix(String Prix) {
        this.Prix = Prix;
    }

    public String getNbPlace() {
        return NbPlace;
    }

    public void setNbPlace(String NbPlace) {
        this.NbPlace = NbPlace;
    }

    public String getSalle() {
        return Salle;
    }

    public void setSalle(String Salle) {
        this.Salle = Salle;
    }

    public String getFilm() {
        return Film;
    }

    public void setFilm(String Film) {
        this.Film = Film;
    }

    public String getPromo() {
        return Promo;
    }

    public void setPromo(String Promo) {
        this.Promo = Promo;
    }

    @Override
    public String toString() {
        return "Ticket{" + "ID=" + ID + ", Heurs=" + Heurs + ", Date=" + Date + ", Prix=" + Prix + ", NbPlace=" + NbPlace + ", Salle=" + Salle + ", Film=" + Film + ", Promo=" + Promo + '}';
    }

 

 

   
    
  
    public void AddTicket(Ticket t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    
}
