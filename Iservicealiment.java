/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Aliment;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 21624
 */
public interface Iservicealiment {
public void AddAliment(Aliment a) throws SQLException;
public List<Aliment> AfficherAliment() throws SQLException;
public void ModifierAliment(int idd, Aliment a)throws SQLException ;
}
