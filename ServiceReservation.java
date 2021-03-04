/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Reservation;
import Services.IserviceReservation;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mouad
 */
public class ServiceReservation implements IserviceReservation{
    
    Connection cnx;
    private List<Reservation> reservations;

    public ServiceReservation() {
        cnx=Maconnexion.getInstance().getConnection();
        
    }

    
    
    @Override
    public void AddReservation(Reservation R) {
        String query ="INSERT INTO reservations(`idUser`, `idTicket`, `idAlim`, `prix`, `date`, `heure`) VALUES (?,?,?,?,?,?)";
        //String query ="INSERT INTO `reservations`(`prix`, `date`, `heure`) VALUES ('"+R.getPrix()+","+R.getDate()+","+R.getHeure()+"')";

        try {
            PreparedStatement stm=cnx.prepareStatement(query);
            
            stm.setInt(1, R.getIdUser());
            stm.setInt(2, R.getIdTicket());
            stm.setInt(3, R.getIdAlim());
            stm.setFloat(4, R.getPrix());
            stm.setString(5, R.getDate());
            stm.setString(6, R.getHeure());
            
            stm.executeUpdate();
            System.out.println("Reservation ajoutée!");

        } catch (SQLException e) {
            System.out.println("erreur d'ajout!");
            System.out.println(e.getMessage());
        }
        
    }

    
    
    @Override
    public List<Reservation> AfficherReservation(int id) throws SQLException{
        List<Reservation> res= new ArrayList<>();
        Statement stm=cnx.createStatement();
        String query="select * from reservations where `idUser`= "+id+" "; 
        ResultSet res2 = stm.executeQuery(query);
        Reservation r=new Reservation();
        
        while(res2.next()){
            r.setIdRes(res2.getInt("idRes"));
            r.setIdUser(res2.getInt("idUser"));
            r.setIdTicket(res2.getInt("idTicket"));
            r.setIdAlim(res2.getInt("idAlim"));
            r.setPrix(res2.getFloat("prix"));
            r.setDate(res2.getString("date"));
            r.setHeure(res2.getString("heure"));
            
            res.add(r);
            
        }
        return res;
        
    }

    @Override
    public void SuppReservation(Reservation R) {
        
        String query="delete from reservations where `idRes`= ? "; 
       
        
        try {
            PreparedStatement stm=cnx.prepareStatement(query);
            stm=cnx.prepareStatement(query);
            stm.setInt(1, R.getIdRes());
            int i=stm.executeUpdate();
            System.out.println(i+"Reservation supp!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    

    @Override
    public void ModifierReservation(int id, Reservation R) {
        String query="update reservations set `idTicket`=?, `idAlim`=?, `prix`=?, `date`=?, `heure`=? where `idRes`= "+id+" ";
            
             
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            
            stm.setInt(1, R.getIdTicket());
            stm.setInt(2, R.getIdAlim());
            stm.setFloat(3, R.getPrix());
            stm.setString(4, R.getDate());
            stm.setString(5, R.getHeure());
            
            
            stm.executeUpdate();
            
            System.out.println("Reservation Modifiée!");
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
            
    }
    
    
}
