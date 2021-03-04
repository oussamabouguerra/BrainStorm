/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Promotion;
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
import Services.IservicePromotion;

/**
 *
 * @author Med Aziz
 */
public class ServicePromotion implements IservicePromotion {
    
    Connection cnx;
    private List<Promotion> promotions;
    public ServicePromotion(){
        cnx=Maconnexion.getInstance().getConnection();
    }

    //@Override
   /* public void AddFilm(Promotion f) throws SQLException {
        
        String req ="INSERT INTO recompense (nomRec, nbr_point) VALUES (?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(req);
             stm.setInt(1, f.getId());
             stm.setString(2,f.getNom());
             stm.setString(3, f.getCategorie());
             stm.setString(4,f.getDuree());
             stm.setString(5, f.getPhoto());
             stm.setFloat(6,f.getPrix());
             stm.executeUpdate();
             System.out.println("recompense ajouté");
                     
        } catch (SQLException ex) {
            System.out.println("probleme");
            System.out.println(ex.getMessage());
        }       
    }*/
               
       
       
    
       
        
    

    /*@Override
    public List<Promotion> AfficherFilm() throws SQLException {
        List<Promotion> resulta = new ArrayList<>();
        Statement stm;
     stm=cnx.createStatement();
     String query="select * from film ";
     ResultSet resultat = stm.executeQuery(query) ;   
              Promotion f=new Promotion();
      while(resultat.next()) {
           f.setId(resultat.getInt("ID"));
             f.setNom(resultat.getString("Nom"));
               f.setCategorie(resultat.getString("Categorie"));
               f.setDuree(resultat.getString("Duree"));
               f.setPhoto(resultat.getString("Photo"));
               f.setPrix(resultat.getFloat("Prix"));
               resulta.add(f);
               System.out.println(f);
       } 

   

       return resulta;
        
    }*/

   /* @Override
    public void AddFilm(Promotion f) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Promotion> AfficherFilm() throws SQLException {
        
     List<Promotion> resulta = new ArrayList<>();
        Statement stm;
     stm=cnx.createStatement();
     String query="select * from promotion ";
     ResultSet resultat = stm.executeQuery(query) ;   
              Promotion p=new Promotion();
      while(resultat.next()) {
           p.setId(resultat.getInt("ID"));
             p.setNom(resultat.getString("Nom"));
               p.setType(resultat.getString("Type"));
               p.setDescription(resultat.getString("Description"));
               p.setPourcentage(resultat.getString("Pourcentage"));
               resulta.add(p);
               System.out.println(p);
    }
      return resulta;
    }*/

   /* @Override
    public void AddPromotion(Promotion p) throws SQLException {
         String req ="INSERT INTO promotion (ID, Nom, Type, Description, Pourcentage) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(req);
             stm.setInt(1, p.getId());
             stm.setString(2,p.getNom());
             stm.setString(3, p.getType());
             stm.setString(4,p.getDescription());
             stm.setString(5, p.getPourcentage());
             stm.executeUpdate();
             System.out.println("promotion ajouté");
                     
        } catch (SQLException ex) {
            System.out.println("probleme");
            System.out.println(ex.getMessage());
        }       
    }*/
    

    @Override
    public List<Promotion> AfficherPromotion(int id) throws SQLException {
        List<Promotion> resulta = new ArrayList<>();
        Statement stm;
     stm=cnx.createStatement();
     String query="select * from promotion where `id`="+id+" ";
     ResultSet resultat = stm.executeQuery(query) ;   
              Promotion p=new Promotion();
      while(resultat.next()) {
           p.setId(resultat.getInt("ID"));
             p.setNom(resultat.getString("Nom"));
               p.setType(resultat.getString("Type"));
               p.setDescription(resultat.getString("Description"));
               p.setPourcentage(resultat.getString("Pourcentage"));
               resulta.add(p);
               System.out.println(p);
    }
      return resulta;
        
    }

    @Override
    public void AddPromotion(Promotion p) throws SQLException {
         String req ="INSERT INTO promotion (ID, Nom, Type, Description, Pourcentage) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(req);
             stm.setInt(1, p.getId());
             stm.setString(2,p.getNom());
             stm.setString(3, p.getType());
             stm.setString(4,p.getDescription());
             stm.setString(5, p.getPourcentage());
             stm.executeUpdate();
             System.out.println("promotion ajouté");
                     
        } catch (SQLException ex) {
            System.out.println("probleme");
            System.out.println(ex.getMessage());
        }       
    
    }
   

    @Override
    public void supprimerpromotion(Promotion p) {
           String req="delete from promotion where ID=?";
       
        try {
            
            PreparedStatement stm=cnx.prepareStatement(req );
            stm=cnx.prepareStatement(req);
            stm.setInt(1,p.getId() );
            int i=stm.executeUpdate();
            System.out.println(i+"Promotion supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   @Override
    public void modifierpromotion(int id, Promotion p) {
        String query="update promotion set `Nom`=?, `Type`=?, `Description`=?, `Pourcentage`=? where `id`= "+id+" ";
           
             
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
           
            stm.setString(1, p.getNom());
            stm.setString(2, p.getType());
            stm.setString(3, p.getDescription());
            stm.setString(4, p.getPourcentage());
            
           
           
            stm.executeUpdate();
           
            System.out.println("Promotion Modifiée!");
           
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
           
    }

   
    

}
