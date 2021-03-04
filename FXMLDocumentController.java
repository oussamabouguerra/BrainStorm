/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_film;

import Entities.Promotion;
import Service.ServicePromotion;
import Utils.Maconnexion;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Med Aziz
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfpourcentage;
    @FXML
    private TextField tfid;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmod;
    @FXML
    private Button btnafficher;
    @FXML
    private Label affiche;
    @FXML
    private Button btnsupprimer;
    
    private void handleButtonAction(ActionEvent event) {
        
        System.out.println("hello");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML
    private void ajouterpromotion(ActionEvent event) {
         ServicePromotion ff=new ServicePromotion();
          Promotion f=new Promotion();
         f.setId(Integer.parseInt(tfid.getText()));
         f.setNom(tfnom.getText());
         f.setType(tftype.getText());
         f.setDescription(tfdescription.getText());
         f.setPourcentage(tfpourcentage.getText());
         
         
        try {
            ff.AddPromotion(f);
        } catch (SQLException ex) {
        }
        
        
    }
    
    


    @FXML
    private void afficherpromotion(ActionEvent event) {
        ServicePromotion ff =new ServicePromotion();
        int id=Integer.parseInt(tfid.getText()) ;
            Promotion f =new Promotion();
        try {
            
            System.out.println(ff.AfficherPromotion(id).toString());
            affiche.setText(ff.AfficherPromotion(id).toString());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

   @FXML
    private void modifierpromotion(ActionEvent event) { //problem
        ServicePromotion pr=new ServicePromotion();
        Promotion p=new Promotion();
        int id;
        id=Integer.parseInt(tfid.getText());
        p.setNom(tfnom.getText());
//        r.setIdTicket(Integer.parseInt(cbIdTicket.getValue()));  //error
//        r.setIdAlim(Integer.parseInt(cbIdAliment.getValue()));    //erro
        p.setType(tftype.getText());
        p.setDescription(tfdescription.getText());
        p.setPourcentage(tfpourcentage.getText());
        
       
       
         try {
            pr. modifierpromotion(id, p);
        } catch (Exception e) {
            System.out.println("Pas de modification!");
       }
       
    }

    @FXML
    private void supprimerpromotion(ActionEvent event) {
         ServicePromotion pr=new ServicePromotion();
         Promotion p=new Promotion() ;
         p.setId(Integer.parseInt(tfid.getText())) ;
         pr.supprimerpromotion(p);
         
        
        
        
    }

    
}
