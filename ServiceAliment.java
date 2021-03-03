/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Aliment;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 21624
 */
public class ServiceAliment {
  Connection cnx;
    private List<Aliment> aliments;

    public ServiceAliment() {
cnx =Maconnexion.getInstance().getConnection();
        }  
    
   public void AddAliment(Aliment a) throws SQLException  {
        
        String req ="INSERT INTO aliment (id,quantite,prix,idpromo,type,marque) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(req);
             stm.setInt(1, a.getId());
             stm.setInt(2,a.getQuantite());
             stm.setInt(3, a.getPrix());
             stm.setInt(4,a.getIdpromo());
             stm.setString(5, a.getType());
             stm.setString(6,a.getMarque());    
             stm.executeUpdate();
             System.out.println("Aliment ajouté");
                     
        } catch (SQLException ex) {
            System.out.println("probleme");
            System.out.println(ex.getMessage());
        }       
    }
   public List<Aliment> afficherAliment() throws SQLException {
     List<Aliment> resulta = new ArrayList<>();

     Statement stm=cnx.createStatement();
     String query="select * from aliment ";
     
ResultSet resultat = stm.executeQuery(query) ;   
               Aliment a=new Aliment();
       while(resultat.next()) {
           a.setId(resultat.getInt("id"));
             a.setQuantite(resultat.getInt("quantite"));
               a.setPrix(resultat.getInt("prix"));
               a.setIdpromo(resultat.getInt("idpromo"));
              a.setType(resultat.getString("type"));
              a.setMarque(resultat.getString("marque"));
               resulta.add(a);
       } 

       return resulta;   
 }
   public void supprimerAliment(Aliment a){
       String req="delete from aliment where id=?";
       
        try {
            
            PreparedStatement stm;
            stm=cnx.prepareStatement(req);
            stm.setInt(1,a.getId() );
            int i=stm.executeUpdate();
            System.out.println(i+"aliment suprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }
    public void ModifierAliment(int id, Aliment a) throws SQLException {
 String query="update aliment set id`=?, quantite`=?, prix`=?, idpromo `=?, type`=?,marque`=? where id`= "+id+" ";
           
             
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
           
            stm.setInt(1, a.getId());
            stm.setInt(2, a.getQuantite());
            stm.setInt(3, a.getPrix());
            stm.setInt(4, a.getIdpromo());
            stm.setString(5, a.getType());
            stm.setString(5, a.getMarque());

           
           
            stm.executeUpdate();
           
            System.out.println("Aliment Modifiée!");
           
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }    }
}

