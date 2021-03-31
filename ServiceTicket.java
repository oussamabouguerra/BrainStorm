/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entities.Ticket;
import Services.IserviceTicket;
import java.sql.Connection;
import java.util.List;
import Utils.Maconnexion;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author oussama
 */
public class ServiceTicket implements IserviceTicket{
   Connection cnx;
    private List<Ticket> tickets;

    public ServiceTicket() {
cnx =Maconnexion.getInstance().getConnection();
        }
   
    /**
     *
     * @param t
     * @throws SQLException
     */ 
    
   @Override
   public void AddTicket(Ticket t) throws SQLException  {
        
        String req ="INSERT INTO ticket (PrixTicket,NbPlace, Salle,Date,Heurs ,Film, Promo) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(req);
                stm.setString(1,t.getPrix());
                stm.setString(2, t.getNbPlace());
                stm.setString(3,t.getSalle());
                stm.setString(4, t.getDate());
                stm.setString(5,t.getHeurs());
                stm.setString(6,t.getFilm());
                stm.setString(7,t.getPromo());

                   stm.executeUpdate();
              System.out.println("Ticket ajouté");

        } catch (SQLException ex) {
            System.out.println("probleme");
            System.out.println(ex.getMessage());
        }       
    }
public Ticket displayById(int id) throws SQLException {
      Ticket p = new Ticket();
      Statement stm = cnx.createStatement();
        String query = "select * from ticket where IDTicket =" + id;
        ResultSet resultat = stm.executeQuery(query) ;  

        try {

            // while(rs.next()){
            resultat.next();
            p.setID(resultat.getInt(1));
            p.setPrix(resultat.getString(2));
            p.setNbPlace(resultat.getString("NbPlace"));
            p.setSalle(resultat.getString("Salle"));
            p.setDate(resultat.getString("Date"));
            p.setHeurs(resultat.getString("Heurs"));
            p.setFilm(resultat.getString("Film"));
            p.setPromo(resultat.getString("Promo"));


            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    /**
     *
     * @return
     * @throws SQLException
     */
  

    /**
     *
     * @return
     * @throws SQLException
     */
     public int getNbrt1() {
        
        String sql="SELECT COUNT(*) FROM `ticket` where `Film`='Joker'";
        ResultSet rs;
        int countIdFed=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdFed= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdFed;
    }
        public int getNbrt2() {
        
        String sql="SELECT COUNT(*) FROM `ticket` where Film='Spider-Man'";
        ResultSet rs;
        int countIdFed=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdFed= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdFed;
    }
        public int getNbrt3() {
        
        String sql="SELECT COUNT(*) FROM `ticket` where Film='Annabelle'";
        ResultSet rs;
        int countIdFed=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdFed= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdFed;
    }
     
 public List<Ticket> afficherTicket() throws SQLException {
     List<Ticket> resulta = new ArrayList<>();

     Statement stm=cnx.createStatement();
     String query="select * from `ticket` ";
     
ResultSet resultat = stm.executeQuery(query) ;   
               Ticket t=new Ticket();
       while(resultat.next()) {
           t.setID(resultat.getInt("IDTicket"));
             t.setPrix( ("PrixTicket"));
               t.setNbPlace( ("NbPlace"));
               t.setSalle( ("Salle"));
               t.setDate("Date");
               t.setHeurs("Heurs");
               t.setFilm(("Film"));
               t.setPromo(("Promo"));
               resulta.add(t);
       } 

       return resulta;   
 }
 
   @Override
    public void supprimer(Ticket t) {
           String req="delete from ticket where IDTicket=?";
       
        try {
            
            PreparedStatement stm;
            stm=cnx.prepareStatement(req);
            stm.setInt(1,t.getID() );
            int i=stm.executeUpdate();
            System.out.println(i+"Ticket suprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
      public List<Ticket> AfficherTicket() throws SQLException {
     List<Ticket> resulta = new ArrayList<>();

     Statement stm=cnx.createStatement();
     String query="select * from ticket ";
     
ResultSet resultat = stm.executeQuery(query) ;   
               Ticket t=new Ticket();
       while(resultat.next()) {
           t.setID(resultat.getInt("IDTicket"));
             t.setPrix( ("PrixTicket"));
               t.setNbPlace( ("NbPlace"));
               t.setSalle(("Salle"));
               t.setDate("Date");
               t.setHeurs("Heurs");
              t.setFilm( ("Film"));
              t.setPromo( ("Promo"));
               resulta.add(t);
       } 

       return resulta;   
 }
 
     
@Override
    public void modifierTicket(Ticket t,int id) throws SQLException {
 String query="UPDATE `ticket` SET `IDTicket`=? ,`PrixTicket`=?,`NbPlace`=?,`Salle`=?,`Date`=?,`Heurs`=?,`Film`=?,`Promo`=? WHERE  `IDTicket`= "+id+" ";
           
             
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setInt(1, t.getID());
            stm.setString(2, t.getPrix());
            stm.setString(3, t.getNbPlace());
            stm.setString(4, t.getSalle());
            stm.setString(5, t.getDate());
            stm.setString(6,t.getHeurs());
            stm.setString(7,t.getFilm());
            stm.setString(8,t.getPromo());
          

           
           
            stm.executeUpdate();
           
            System.out.println("Ticket Modifiée!");
           
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }    }

    @Override
    public List<Ticket> recherche(Ticket t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}


   



