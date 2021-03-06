/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineapp;

import Entities.Reservation;
import mail.Mailling;
import Service.ServiceReservation;
import Utils.Maconnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author mouad
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane paneLogin;
    @FXML
    private AnchorPane paneSignUp;
    @FXML
    private AnchorPane paneReservation;
    @FXML
    private AnchorPane paneAjouterRes;
    @FXML
    private AnchorPane paneModifierRes;
    @FXML
    private AnchorPane paneSuppRes;
    @FXML
    private AnchorPane paneAfficherRes;
    @FXML
    private TextField tfUsernameLogin;
    @FXML
    private PasswordField tfPasswordLogin;
    @FXML
    private ComboBox<String> cbxTypeLogin;
    @FXML
    private Button btLogin;
    @FXML
    private ComboBox<String> cbxTypeSignUp;
    @FXML
    private TextField tfUsernameSignUp;
    @FXML
    private TextField tfFamilynameSignup;
    @FXML
    private TextField tfCinSignUp;
    @FXML
    private TextField tfMailSignUp;
    @FXML
    private Button btSignUp;
    @FXML
    private PasswordField tfPasswordSignUp;
    @FXML
    private Button btAfficherRes;
    @FXML
    private ComboBox<String> cbxTicketAjouterRes;
    @FXML
    private ComboBox<String> cbxAlimAjouterRes;
    @FXML
    private Button btAjouterRes;
    @FXML
    private ComboBox<String> cbxTicketModifierRes;
    @FXML
    private ComboBox<String> cbxAlimModifierRes;
    @FXML
    private Button btModifierRes;
    @FXML
    private TextField tfIdResModifierRes;
    @FXML
    private Button btSupprimerRes;
    @FXML
    private TextField tfIdResSupprimerRes;
    @FXML
    private Label labelAfficherRes;
    @FXML
    private Button btImprimer;
    Connection cnx;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    public void paneLoginShow() {
        cbxTypeLogin.getItems().clear();
        cbxTypeLogin.getItems().clear();
        paneLogin.setVisible(true);
        paneSignUp.setVisible(false);
        paneReservation.setVisible(false);
        paneAjouterRes.setVisible(false);
        paneModifierRes.setVisible(false);
        paneSuppRes.setVisible(false);
        paneAfficherRes.setVisible(false);
        cbxTypeLogin.getItems().add("Simple User");
        cbxTypeLogin.getItems().add("Administrateur");
    }
    
    @FXML
    public void paneSignUpShow() {
        cbxTypeSignUp.getItems().clear();
        cbxTypeSignUp.getItems().clear();
        paneLogin.setVisible(false);
        paneSignUp.setVisible(true);
        paneReservation.setVisible(false);
        paneAjouterRes.setVisible(false);
        paneModifierRes.setVisible(false);
        paneSuppRes.setVisible(false);
        paneAfficherRes.setVisible(false);
        cbxTypeSignUp.getItems().add("Simple User");
        cbxTypeSignUp.getItems().add("Administrateur");
    }
    
    @FXML
    public void paneReservationShow() {
        paneLogin.setVisible(false);
        paneSignUp.setVisible(false);
        paneReservation.setVisible(true);
        paneAjouterRes.setVisible(false);
        paneModifierRes.setVisible(false);
        paneSuppRes.setVisible(false);
        paneAfficherRes.setVisible(false);
        cbxTicketModifierRes.getItems().clear();
        cbxAlimModifierRes.getItems().clear();
        cbxTicketAjouterRes.getItems().clear();
        cbxAlimAjouterRes.getItems().clear();
        tfIdResModifierRes.setText("");
        tfIdResSupprimerRes.setText("");

    }
    
    @FXML
    public void paneAjouterResShow() {
        paneLogin.setVisible(false);
        paneSignUp.setVisible(false);
        paneReservation.setVisible(false);
        paneAjouterRes.setVisible(true);
        paneModifierRes.setVisible(false);
        paneSuppRes.setVisible(false);
        paneAfficherRes.setVisible(false);
        remplirCbxAjouterRes();   //call it in login btn
    }
    
    @FXML
    public void paneModifierResShow() {
        paneLogin.setVisible(false);
        paneSignUp.setVisible(false);
        paneReservation.setVisible(false);
        paneAjouterRes.setVisible(false);
        paneModifierRes.setVisible(true);
        paneSuppRes.setVisible(false);
        paneAfficherRes.setVisible(false);
        remplirCbxModifierRes();  //call it in login btn
    }
    
    @FXML
    public void paneSuppResShow() {
        paneLogin.setVisible(false);
        paneSignUp.setVisible(false);
        paneReservation.setVisible(false);
        paneAjouterRes.setVisible(false);
        paneModifierRes.setVisible(false);
        paneSuppRes.setVisible(true);
        paneAfficherRes.setVisible(false);
    }
    
    public void paneAfficherResShow() {
        paneLogin.setVisible(false);
        paneSignUp.setVisible(false);
        paneReservation.setVisible(false);
        paneAjouterRes.setVisible(false);
        paneModifierRes.setVisible(false);
        paneSuppRes.setVisible(false);
        paneAfficherRes.setVisible(true);
    }
    
    private void remplirCbxAjouterRes()
    {
        cnx=Maconnexion.getInstance().getConnection();
        try {
            
            String query ="select * from tickets";
            Statement stm= cnx.createStatement();
            ResultSet res = stm.executeQuery(query);
           
            while(res.next())
            {
             cbxTicketAjouterRes.getItems().add(res.getInt("idTicket")+""); 
            }
           
            System.out.println("combobox1 rempli!");
            
            Statement stm2= cnx.createStatement();
            String query2 ="select * from alimentations";
            ResultSet res2 = stm2.executeQuery(query2);
            
            while(res2.next())
            {
                cbxAlimAjouterRes.getItems().add(res2.getInt("idAlim")+""); 
            }
           
            System.out.println("combobox2 rempli!");
            
        } catch (SQLException e) {
            System.out.println("erreur combobox!");
            System.out.println(e.getMessage());
        }
    }
    
    
    private void remplirCbxModifierRes()
    {    

        cnx=Maconnexion.getInstance().getConnection();
        try {
            
            String query ="select * from tickets";
            Statement stm= cnx.createStatement();
            ResultSet res = stm.executeQuery(query);
           
            while(res.next())
            {
             cbxTicketModifierRes.getItems().add(res.getInt("idTicket")+""); 
            }
           
            System.out.println("combobox1 rempli!");
            
            Statement stm2= cnx.createStatement();
            String query2 ="select * from alimentations";
            ResultSet res2 = stm2.executeQuery(query2);
            
            while(res2.next())
            {
                cbxAlimModifierRes.getItems().add(res2.getInt("idAlim")+""); 
            }
           
            System.out.println("combobox2 rempli!");
            
            
        } catch (SQLException e) {
            System.out.println("erreur combobox!");
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {
        if(cbxTypeLogin.getValue().equals("Simple User"))
        {
            
        }
        else if(cbxTypeLogin.getValue().equals("Administrateur"))
        {
            
        }
        
    }

    @FXML
    private void SignUp(ActionEvent event) {
        if(cbxTypeSignUp.getValue().equals("Simple User"))
        {
            
        }
        else if(cbxTypeSignUp.getValue().equals("Administrateur"))
        {
            
        }
    }

    @FXML
    private void afficherRes(ActionEvent event) {
        paneAfficherResShow();
        ServiceReservation sr=new ServiceReservation();
        Reservation r=new Reservation();
        int id=1;       //to modify after ending login code
        
        try {
            System.out.println(sr.AfficherReservation(id).toString());
            labelAfficherRes.setText(sr.AfficherReservation(id).toString());
        } catch (SQLException ex) {
            System.out.println(ex);
        
        }
    }

    @FXML
    private void ajouterRes(ActionEvent event) {
        ServiceReservation sr=new ServiceReservation();
        Reservation r=new Reservation();
        
        r.setIdUser(1);  //to modify after ending login code
        
        r.setIdTicket(Integer.parseInt(cbxTicketAjouterRes.getValue()));  
        r.setIdAlim(Integer.parseInt(cbxAlimAjouterRes.getValue()));    
        
        cnx=Maconnexion.getInstance().getConnection();
        
        int id=Integer.parseInt(cbxTicketAjouterRes.getValue());
        int id2=Integer.parseInt(cbxAlimAjouterRes.getValue());
        
        try {
            Statement stm= cnx.createStatement();
            String query ="select * from tickets where `idTicket`="+id+" ";
            ResultSet res = stm.executeQuery(query);
            
            if (res.next()) {
            r.setPrix(res.getFloat("prix"));
            r.setDate(res.getString("date"));
            r.setHeure(res.getString("heure"));
                }
            
            String query2 ="select * from alimentations where `idAlim`="+id2+" ";
            ResultSet res2 = stm.executeQuery(query2);
            if (res2.next()) {
            Float p=r.getPrix();
            r.setPrix(p+res2.getFloat("prix"));
                }
            
             
            sr.mail();
            
            sr.AddReservation(r);
            paneReservationShow();
            
         
        } catch (Exception e) {
            System.out.println("Pas d'ajout!"); 
            System.out.println(e.getMessage()); 
       }
        
        
    }

    @FXML
    private void modifierRes(ActionEvent event) {
         ServiceReservation sr=new ServiceReservation();
        Reservation r=new Reservation();
        int id;
        id=Integer.parseInt(tfIdResModifierRes.getText());
        int idUser=1;
        if(idUser==1)   //to modify after ending login code
        {
        r.setIdUser(idUser);
        r.setIdTicket(Integer.parseInt(cbxTicketModifierRes.getValue()));  
        r.setIdAlim(Integer.parseInt(cbxAlimModifierRes.getValue()));    
       
        int id1=Integer.parseInt(cbxTicketModifierRes.getValue());
        int id2=Integer.parseInt(cbxAlimModifierRes.getValue());
        
         try {
            Statement stm= cnx.createStatement();
            String query ="select * from tickets where `idTicket`="+id1+" ";
            ResultSet res = stm.executeQuery(query);
            
            if (res.next()) {
            r.setPrix(res.getFloat("prix"));
            r.setDate(res.getString("date"));
            r.setHeure(res.getString("heure"));
                }
            
            String query2 ="select * from alimentations where `idAlim`="+id2+" ";
            ResultSet res2 = stm.executeQuery(query2);
            if (res2.next()) {
            Float p=r.getPrix();
            r.setPrix(p+res2.getFloat("prix"));
                }
             
            sr.ModifierReservation(id, r);
            paneReservationShow();

            
         
            
        } catch (Exception e) {
            System.out.println("Pas de modification!"); 
       }
       }
        
    }

    @FXML
    private void supprimerRes(ActionEvent event) {
        ServiceReservation sr=new ServiceReservation();
        Reservation r=new Reservation();
        r.setIdRes(Integer.parseInt(tfIdResSupprimerRes.getText()));
        int idUser=1;  //to modify after ending login code
        if(idUser==1){
            sr.SuppReservation(r);
            paneReservationShow();
        }
        
         
    }

    @FXML
    private void imprimerRes(ActionEvent event) {
        
    }
    
    
}
