/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Promotion;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Med Aziz
 */
public interface IservicePromotion {
    
    public void AddPromotion(Promotion p) throws SQLException ;
    public List<Promotion> AfficherPromotion(int id) throws SQLException;
        public void supprimerpromotion(Promotion p);
        public void modifierpromotion(int id, Promotion p);

    
}
