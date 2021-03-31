/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Ticket;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author oussama
 */
public interface IserviceTicket {
public void AddTicket(Ticket t) throws SQLException;
public List<Ticket> AfficherTicket() throws SQLException;
public void supprimer(Ticket t );
public void modifierTicket( Ticket t,int id)throws SQLException ;
 public List<Ticket> recherche(Ticket t) throws SQLException;


    
}
