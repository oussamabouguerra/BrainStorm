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
import java.util.Timer;
import java.util.TimerTask;

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
    
  public Promotion displayById(int id) throws SQLException {
      Promotion p = new Promotion();
      Statement stm = cnx.createStatement();
        String query = "select * from Promotion where ID =" + id;
        ResultSet resultat = stm.executeQuery(query) ;  

        try {

            // while(rs.next()){
            resultat.next();
            p.setId(resultat.getInt(1));
            p.setNom(resultat.getString(2));
            p.setType(resultat.getString("Type"));
            p.setDescription(resultat.getString("Description"));
            p.setPourcentage(resultat.getString("Pourcentage"));
            p.setCategorie(resultat.getString("categorie"));
            p.setCode(resultat.getString("code"));


            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    @Override
    public List<Promotion> AfficherPromotion() throws SQLException {
        List<Promotion> resulta = new ArrayList<>();
        
        Statement stm = cnx.createStatement();
     String query="select * from promotion";
     ResultSet resultat = stm.executeQuery(query) ;   
              Promotion p=new Promotion();
      while(resultat.next()) {
           p.setId(resultat.getInt("ID"));
             p.setNom(resultat.getString("Nom"));
               p.setType(resultat.getString("Type"));
               p.setDescription(resultat.getString("Description"));
               p.setPourcentage(resultat.getString("Pourcentage"));
                              p.setCategorie(resultat.getString("categorie"));

               resulta.add(p);
               p=new Promotion() ;
               System.out.println(p);
    }
      return resulta;
        
    }

    @Override
    public void AddPromotion(Promotion p) throws SQLException {
         String req ="INSERT INTO promotion ( Nom, Type, Description, Pourcentage,categorie,Code) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(req);
             
             stm.setString(1,p.getNom());
             stm.setString(2, p.getType());
             stm.setString(3,p.getDescription());
             stm.setString(4, p.getPourcentage());
             stm.setString(5, p.getCategorie());
             stm.setString(6, p.getCode());

             stm.executeUpdate();
             System.out.println("promotion ajouté");
                     
        } catch (SQLException ex) {
            System.out.println("probleme");
            System.out.println(ex.getMessage());
        }       
    
    }
   

    @Override
    public void Supprimerpromotion(Promotion p) {
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
    public void ModifierPromotion(int id, Promotion p)throws SQLException {
        String query="update promotion set `ID`=?, `Nom`=?, `Type`=?, `Description`=?,`Pourcentage`=?, `categorie`=?,`code` =? where `ID`= "+id+" ";
           
             
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
                        stm.setInt(1, p.getId());

            stm.setString(2, p.getNom());
            stm.setString(3, p.getType());
            stm.setString(4, p.getDescription());
            stm.setString(5, p.getPourcentage());
            stm.setString(6, p.getCategorie());
            stm.setString(7, p.getCode());

            
           
           
            stm.executeUpdate();
           
            System.out.println("Promotion Modifiée!");
           
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
           
    }
 

   
    
    
    

   
    

}
