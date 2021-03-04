/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reservation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mouad
 */
public interface IserviceReservation {
    
    public void AddReservation(Reservation R) ;
    public List<Reservation> AfficherReservation(int id) throws SQLException;
    public void SuppReservation(Reservation R) ;
    public void ModifierReservation(int id,Reservation R);
    
}
