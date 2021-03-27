/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Administrateur;
import Services.IserviceAdministrateur;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author mouad
 */
public class ServiceAdministrateur implements IserviceAdministrateur{
     Connection cnx;

    public ServiceAdministrateur() {
        cnx=Maconnexion.getInstance().getConnection();
    }

     
     
    @Override
    public void inscription(Administrateur U) {
        String query ="INSERT INTO administrateurs (`nom`, `prenom`, `cin`, `mail`, `mdp`) VALUES (?,?,?,?,?)";
        
        try {
            PreparedStatement stm=cnx.prepareStatement(query);
            
            stm.setString(1, U.getNom());
            stm.setString(2, U.getPrenom());
            stm.setInt(3, U.getCin());
            stm.setString(4, U.getMail());
            stm.setString(5, U.getMdp());
            
            stm.executeUpdate();
            System.out.println("Administrateur ajoutée!");

        } catch (SQLException e) {
            System.out.println("erreur d'ajout!");
            System.out.println(e.getMessage());
        }    }

}