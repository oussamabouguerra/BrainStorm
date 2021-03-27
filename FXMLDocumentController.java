/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineapp;

import Entities.Administrateur;
import javafx.print.PrinterJob;
import Entities.Reservation;
import Entities.SimpleUser;
import Entities.User;
import Service.ServiceAdministrateur;
import mail.Mailling;
import Service.ServiceReservation;
import Service.ServiceSimpleUser;
import Utils.Maconnexion;
import com.qoppa.pdf.PDFException;
import com.qoppa.pdf.PDFPermissionException;
import com.qoppa.pdf.PrintSettings;
import com.qoppa.pdfPrint.PDFPrint;
import java.awt.print.PrinterException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

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
    private int idUser,idAdmin;
    private String mailUser, mailAdmin; 
    @FXML
    private TableView<Reservation> tvRes;
    @FXML
    private TableColumn<Reservation, Integer> clmnIdRes;
    @FXML
    private TableColumn<Reservation, Integer> clmnIdUser;
    @FXML
    private TableColumn<Reservation, Integer> clmnIdTicket;
    @FXML
    private TableColumn<Reservation, Integer> clmnIdAlim;
    @FXML
    private TableColumn<Reservation, Float> clmnPrix;
    @FXML
    private TableColumn<Reservation, String> clmnDate;
    @FXML
    private TableColumn<Reservation, String> clmnHeure;
    @FXML
    private Button btConsultations;
    @FXML
    private AnchorPane paneConsultations;
    @FXML
    private Button btConsulterFilms;
    @FXML
    private Button btConsulterActualites;
    @FXML
    private Button btConsulterAlim;
    @FXML
    private AnchorPane paneConsulterFilms;
    @FXML
    private AnchorPane paneConsulterAlim;
    @FXML
    private AnchorPane paneConsulterActualites;
    @FXML
    private AnchorPane paneAdmin;
    @FXML
    private Button btAfficherRes1;
    @FXML
    private Button btConsultations1;
    
    

    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    
    
    @FXML
    public void paneLoginShow() {

        
        idUser=0;
        cbxTypeLogin.getItems().clear();
        cbxTypeLogin.getItems().clear();
        paneLogin.setVisible(true);
        paneSignUp.setVisible(false);
        paneReservation.setVisible(false);
        paneAjouterRes.setVisible(false);
        paneModifierRes.setVisible(false);
        paneSuppRes.setVisible(false);
        paneAfficherRes.setVisible(false);
        paneConsultations.setVisible(false);
        paneConsulterFilms.setVisible(false);
        paneConsulterAlim.setVisible(false);
        paneConsulterActualites.setVisible(false);
        paneAdmin.setVisible(false);
        cbxTypeLogin.getItems().add("Simple User");
        cbxTypeLogin.getItems().add("Administrateur");
        tfUsernameLogin.setText("");
        tfPasswordLogin.setText("");
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
        paneConsultations.setVisible(false);
        paneConsulterFilms.setVisible(false);
        paneConsulterAlim.setVisible(false);
        paneConsulterActualites.setVisible(false);
        paneAdmin.setVisible(false);
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
        paneConsultations.setVisible(false);
        paneConsulterFilms.setVisible(false);
        paneConsulterAlim.setVisible(false);
        paneConsulterActualites.setVisible(false);
        paneAdmin.setVisible(false);
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
        paneConsultations.setVisible(false);
        paneConsulterFilms.setVisible(false);
        paneConsulterAlim.setVisible(false);
        paneConsulterActualites.setVisible(false);
        paneAdmin.setVisible(false);
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
        paneConsultations.setVisible(false);
        paneConsulterFilms.setVisible(false);
        paneConsulterAlim.setVisible(false);
        paneConsulterActualites.setVisible(false);
        paneAdmin.setVisible(false);
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
        paneConsultations.setVisible(false);
        paneConsulterFilms.setVisible(false);
        paneConsulterAlim.setVisible(false);
        paneConsulterActualites.setVisible(false);
        paneAdmin.setVisible(false);
    }
    
    public void paneAfficherResShow() {
        paneLogin.setVisible(false);
        paneSignUp.setVisible(false);
        paneReservation.setVisible(false);
        paneAjouterRes.setVisible(false);
        paneModifierRes.setVisible(false);
        paneSuppRes.setVisible(false);
        paneAfficherRes.setVisible(true);
        paneConsultations.setVisible(false);
        paneConsulterFilms.setVisible(false);
        paneConsulterAlim.setVisible(false);
        paneConsulterActualites.setVisible(false);
        paneAdmin.setVisible(false);
    }
    
    @FXML
    public void paneConsultationsShow() {
        paneLogin.setVisible(false);
        paneSignUp.setVisible(false);
        paneReservation.setVisible(false);
        paneAjouterRes.setVisible(false);
        paneModifierRes.setVisible(false);
        paneSuppRes.setVisible(false);
        paneAfficherRes.setVisible(false);
        paneConsultations.setVisible(true);
        paneConsulterFilms.setVisible(false);
        paneConsulterAlim.setVisible(false);
        paneConsulterActualites.setVisible(false);
        paneAdmin.setVisible(false);
    }
    
    @FXML
    public void paneConsultetFilmsShow() {
        paneLogin.setVisible(false);
        paneSignUp.setVisible(false);
        paneReservation.setVisible(false);
        paneAjouterRes.setVisible(false);
        paneModifierRes.setVisible(false);
        paneSuppRes.setVisible(false);
        paneAfficherRes.setVisible(false);
        paneConsultations.setVisible(false);
        paneConsulterFilms.setVisible(true);
        paneConsulterAlim.setVisible(false);
        paneConsulterActualites.setVisible(false);
        paneAdmin.setVisible(false);
    }
    
    @FXML
    public void paneConsultetAlimShow() {
        paneLogin.setVisible(false);
        paneSignUp.setVisible(false);
        paneReservation.setVisible(false);
        paneAjouterRes.setVisible(false);
        paneModifierRes.setVisible(false);
        paneSuppRes.setVisible(false);
        paneAfficherRes.setVisible(false);
        paneConsultations.setVisible(false);
        paneConsulterFilms.setVisible(false);
        paneConsulterAlim.setVisible(true);
        paneConsulterActualites.setVisible(false);
        paneAdmin.setVisible(false);
    }
    
    @FXML
    public void paneConsultetActualitesShow() {
        paneLogin.setVisible(false);
        paneSignUp.setVisible(false);
        paneReservation.setVisible(false);
        paneAjouterRes.setVisible(false);
        paneModifierRes.setVisible(false);
        paneSuppRes.setVisible(false);
        paneAfficherRes.setVisible(false);
        paneConsultations.setVisible(false);
        paneConsulterFilms.setVisible(false);
        paneConsulterAlim.setVisible(false);
        paneConsulterActualites.setVisible(true);
        paneAdmin.setVisible(false);
    }
    
    public void paneAdminShow() {
        paneLogin.setVisible(false);
        paneSignUp.setVisible(false);
        paneReservation.setVisible(false);
        paneAjouterRes.setVisible(false);
        paneModifierRes.setVisible(false);
        paneSuppRes.setVisible(false);
        paneAfficherRes.setVisible(false);
        paneConsultations.setVisible(false);
        paneConsulterFilms.setVisible(false);
        paneConsulterAlim.setVisible(false);
        paneConsulterActualites.setVisible(false);
        paneAdmin.setVisible(true);
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
             //cbxTicketAjouterRes.getItems().add(res.getInt("idTicket")+"");  //à remplacer par nomFilm
             cbxTicketAjouterRes.getItems().add(res.getString("nomFilm"));
            }
           
            System.out.println("combobox1 rempli!");
            
            Statement stm2= cnx.createStatement();
            String query2 ="select * from alimentations";
            ResultSet res2 = stm2.executeQuery(query2);
            
            while(res2.next())
            {
                //cbxAlimAjouterRes.getItems().add(res2.getInt("idAlim")+"");    // à remplacer par la marque de l'alim
                cbxAlimAjouterRes.getItems().add(res2.getString("marque"));
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
             //cbxTicketModifierRes.getItems().add(res.getInt("idTicket")+"");    //à remplacer par nomFilm
                cbxTicketModifierRes.getItems().add(res.getString("nomFilm")); 
            }
           
            System.out.println("combobox1 rempli!");
            
            Statement stm2= cnx.createStatement();
            String query2 ="select * from alimentations";
            ResultSet res2 = stm2.executeQuery(query2);
            
            while(res2.next())
            {
                //cbxAlimModifierRes.getItems().add(res2.getInt("idAlim")+"");    // à remplacer par la marque de l'alim
                cbxAlimModifierRes.getItems().add(res2.getString("marque"));
            }
           
            System.out.println("combobox2 rempli!");
            
            
        } catch (SQLException e) {
            System.out.println("erreur combobox!");
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxTypeLogin.getItems().add("Simple User");
        cbxTypeLogin.getItems().add("Administrateur");

    }    

    @FXML
    private void login(ActionEvent event) {
        cnx=Maconnexion.getInstance().getConnection();
        if(cbxTypeLogin.getValue().equals("Simple User"))
        {
            try {
                
                String query ="select * from simpleusers where `cin`="+tfUsernameLogin.getText()+" and `mdp`="+tfPasswordLogin.getText()+" ";
                Statement stm=cnx.createStatement();
                ResultSet res= stm.executeQuery(query);
                if(res.next())
                {
                    JOptionPane.showMessageDialog(null, "Informations correctes!");
                    paneReservationShow();
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "CIN ou mot de passe incorrectes!");
                    tfUsernameLogin.setText("");
                    tfPasswordLogin.setText("");
                }
                String query1 ="select * from simpleusers where `cin`="+tfUsernameLogin.getText()+" and `mdp`="+tfPasswordLogin.getText()+" ";
                Statement stm1=cnx.createStatement();
                ResultSet res1= stm1.executeQuery(query1);
                if(res1.next()){
                    idUser=res1.getInt("idUser");
                    mailUser=res1.getString("mail");
                }
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
        }
        else if(cbxTypeLogin.getValue().equals("Administrateur"))
        {
            try {
                
                String query2 ="select * from administrateurs where `cin`="+tfUsernameLogin.getText()+" and `mdp`='"+tfPasswordLogin.getText()+"' ";
                Statement stm2=cnx.createStatement();
                ResultSet res2= stm2.executeQuery(query2);
                if(res2.next())
                {
                    JOptionPane.showMessageDialog(null, "Informations correctes!");
                    paneAdminShow();
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "CIN ou mot de passe incorrectes!");
                    tfUsernameLogin.setText("");
                    tfPasswordLogin.setText("");
                }
                String query3 ="select * from administrateurs where `cin`="+tfUsernameLogin.getText()+" and `mdp`='"+tfPasswordLogin.getText()+"' ";
                Statement stm3=cnx.createStatement();
                ResultSet res3= stm3.executeQuery(query3);
                if(res3.next()){
                    idAdmin=res3.getInt("idAdmin");
                    mailAdmin=res3.getString("mail");
                }
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
       
        
    }

    @FXML
    private void SignUp(ActionEvent event) {
        boolean test=true;
        if(cbxTypeSignUp.getValue().equals("Simple User"))
        {
            ServiceSimpleUser ssu=new ServiceSimpleUser();
            SimpleUser su=new SimpleUser();
            if((tfUsernameSignUp.getText()).length()<20)
                su.setNom(tfUsernameSignUp.getText());
            else
            {  
              test=false;  
              JOptionPane.showMessageDialog(null, "Username ne doit pas dépasser les 20 caractères!");
              tfUsernameSignUp.setText("");
            }
            if((tfFamilynameSignup.getText()).length()<20)
                su.setPrenom(tfFamilynameSignup.getText());
            else
            {  
              test=false;  
              JOptionPane.showMessageDialog(null, "Familyname  ne doit pas dépasser les 20 caractères!");
              tfFamilynameSignup.setText("");
            }
            if((tfCinSignUp.getText()).length()==8)
                su.setCin(Integer.parseInt(tfCinSignUp.getText()));
            else
            {  
              test=false;  
              JOptionPane.showMessageDialog(null, "Une carte CIN est composée de 8 nombres!");
              tfCinSignUp.setText("");
            }
            if((tfMailSignUp.getText()).contains("@esprit.tn"))
                su.setMail(tfMailSignUp.getText());
            else
            {  
              test=false;  
              JOptionPane.showMessageDialog(null, "Vous devez taper votre mail de esprit!");
              tfMailSignUp.setText("");
            }
            
            if ((tfPasswordSignUp.getText()).length()<8)
            {  
              test=false;  
              JOptionPane.showMessageDialog(null, "Le mot de passe doit être composé au minimum de 8 caractères!");
              tfPasswordSignUp.setText("");
            }
            else if ((tfPasswordSignUp.getText()).length()>20)
            {  
              test=false;  
              JOptionPane.showMessageDialog(null, "Le mot de passe ne doit pas dépasser les 20 caractères!");
              tfPasswordSignUp.setText("");
            }
            else if((tfPasswordSignUp.getText()).length()>8 || (tfPasswordSignUp.getText()).length()<20 )
                su.setMdp(tfPasswordSignUp.getText());
            
            if(test==true)
            {
            ssu.inscription(su);
            JOptionPane.showMessageDialog(null, "Simple User ajouté!");
            paneLoginShow();}
        }
        else if(cbxTypeSignUp.getValue().equals("Administrateur"))
        {
            ServiceAdministrateur ssu=new ServiceAdministrateur();
            Administrateur su=new Administrateur();
            if((tfUsernameSignUp.getText()).length()<20)
                su.setNom(tfUsernameSignUp.getText());
            else
            {  
              test=false;  
              JOptionPane.showMessageDialog(null, "Username ne doit pas dépasser les 20 caractères!");
              tfUsernameSignUp.setText("");
            }
            if((tfFamilynameSignup.getText()).length()<20)
                su.setPrenom(tfFamilynameSignup.getText());
            else
            {  
              test=false;  
              JOptionPane.showMessageDialog(null, "Familyname  ne doit pas dépasser les 20 caractères!");
              tfFamilynameSignup.setText("");
            }
            if((tfCinSignUp.getText()).length()==8)
                su.setCin(Integer.parseInt(tfCinSignUp.getText()));
            else
            {  
              test=false;  
              JOptionPane.showMessageDialog(null, "Une carte CIN est composée de 8 nombres!");
              tfCinSignUp.setText("");
            }
            if((tfMailSignUp.getText()).contains("@esprit.tn"))
                su.setMail(tfMailSignUp.getText());
            else
            {  
              test=false;  
              JOptionPane.showMessageDialog(null, "Vous devez tapez votre mail de esprit!");
              tfMailSignUp.setText("");
            }
            
            if ((tfPasswordSignUp.getText()).length()<8)
            {  
              test=false;  
              JOptionPane.showMessageDialog(null, "Le mot de passe doit être composé au minimum de 8 caractères!");
              tfPasswordSignUp.setText("");
            }
            else if ((tfPasswordSignUp.getText()).length()>20)
            {  
              test=false;  
              JOptionPane.showMessageDialog(null, "Le mot de passe ne doit pas dépasser les 20 caractères!");
              tfPasswordSignUp.setText("");
            }
            else if(!(tfPasswordSignUp.getText()).contains("root"))
            {  
              test=false;  
              JOptionPane.showMessageDialog(null, "Vous n'avez pas le droit de créer un compte administrateur!");
              tfPasswordSignUp.setText("");
            }
            else if((tfPasswordSignUp.getText()).length()>8 || (tfPasswordSignUp.getText()).length()<20 && (tfPasswordSignUp.getText()).contains("root"))
                su.setMdp(tfPasswordSignUp.getText());
            if(test==true)
            {
            ssu.inscription(su);
            JOptionPane.showMessageDialog(null, "Administrateur ajouté!");
            paneLoginShow();}
        }
    }
    
    
    ObservableList<Reservation> ResListe = FXCollections.observableArrayList();
     public void afficherliste(){
          ResListe.clear();
          try{
          Connection cnx=Maconnexion.getInstance().getConnection();
          String query = "SELECT * FROM reservations where `idUser`="+idUser+"";
          Statement st;
          ResultSet rs;
              st = cnx.createStatement();
              rs = st.executeQuery(query);
              Reservation res;
              while(rs.next()){ // il faut ajouter la marque de l'alim dans la table de reservation
                  res = new Reservation(rs.getInt("idRes"), rs.getInt("idUser"),rs.getInt("idTicket"),rs.getInt("idAlim"),rs.getFloat("prix"),rs.getString("date"),rs.getString("heure"));
                 ResListe.add(res);
              }
              
          }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error on Building Data");
          }
            clmnIdUser.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("idUser"));
            clmnIdRes.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("idRes"));
            clmnIdAlim.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("idAlim"));  // à remplacer par la marque de l'alim
            clmnIdTicket.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("idTicket"));    //à remplacer par nomFilm
            clmnPrix.setCellValueFactory(new PropertyValueFactory<Reservation,Float>("prix"));
            clmnDate.setCellValueFactory(new PropertyValueFactory<Reservation,String>("date"));
            clmnHeure.setCellValueFactory(new PropertyValueFactory<Reservation,String>("heure"));
       
      tvRes.setItems(ResListe);
      
    }
    
    @FXML
    private void afficherRes(ActionEvent event) {
        paneAfficherResShow();
        ServiceReservation sr=new ServiceReservation();
        Reservation r=new Reservation();

        int id=idUser;
        
        try {
            System.out.println(sr.AfficherReservation(id).toString());
            labelAfficherRes.setText(sr.AfficherReservation(id).toString());
            //remplir table view
            afficherliste();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
        }
    }

    @FXML
    private void ajouterRes(ActionEvent event) {
        ServiceReservation sr=new ServiceReservation();
        Reservation r=new Reservation();
       
        r.setIdUser(idUser);  
        
        //r.setIdTicket(Integer.parseInt(cbxTicketAjouterRes.getValue()));  
        //r.setIdAlim(Integer.parseInt(cbxAlimAjouterRes.getValue()));    
        
        cnx=Maconnexion.getInstance().getConnection();
        int id1=0;
        int id2=0;
        
        
        //int id=Integer.parseInt(cbxTicketAjouterRes.getValue()); //prendre id à partir du nomFilm
        //int id2=Integer.parseInt(cbxAlimAjouterRes.getValue());// prendre id2 à partir de la marque de l'alim
        
        try {
            String test1=cbxTicketAjouterRes.getValue();
            Statement stm1= cnx.createStatement();
            String query1 ="select * from tickets where `nomFilm`='"+test1+"' ";
            ResultSet res1 = stm1.executeQuery(query1);
            if(res1.next())
                id1= res1.getInt("idTicket");
            
            String test2=cbxAlimAjouterRes.getValue();
            Statement stm3= cnx.createStatement();
            String query3 ="select * from alimentations where `marque`='"+test2+"' ";
            ResultSet res3 = stm3.executeQuery(query3);
            if(res3.next())
                id2= res3.getInt("idAlim");
            
            Statement stm= cnx.createStatement();
            String query ="select * from tickets where `idTicket`="+id1+" ";
            ResultSet res = stm.executeQuery(query);
            
            if (res.next()) {
            r.setIdTicket(id1);  
            r.setIdAlim(id2);    
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
            
             
            sr.mail(mailUser);
            
            sr.AddReservation(r);
            JOptionPane.showMessageDialog(null, "Reservation ajouté! \n Un mail de confirmation est envoyé. ");
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
        int id,idUser1= 0, id1=0, id2=0;
        id=Integer.parseInt(tfIdResModifierRes.getText());
      
        try {           
            String query1 ="select * from reservations where `idRes`="+id+" ";
            Statement stm1=cnx.createStatement();
            ResultSet res1= stm1.executeQuery(query1);
            if(res1.next())
                idUser1=res1.getInt("idUser");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(idUser==idUser1)   
        {
        r.setIdUser(idUser);
        //r.setIdTicket(Integer.parseInt(cbxTicketModifierRes.getValue()));  
        //r.setIdAlim(Integer.parseInt(cbxAlimModifierRes.getValue()));    
       
         //id1=Integer.parseInt(cbxTicketModifierRes.getValue());  //prendre id1 à partir du nomFilm
         //id2=Integer.parseInt(cbxAlimModifierRes.getValue());  //prendre id2 à partir de la marque de l'alim
        
         try {
             
            String test1=cbxTicketModifierRes.getValue();
            Statement stm1= cnx.createStatement();
            String query1 ="select * from tickets where `nomFilm`='"+test1+"' ";
            ResultSet res1 = stm1.executeQuery(query1);
            if(res1.next())
                id1= res1.getInt("idTicket"); 
            
            String test2=cbxAlimModifierRes.getValue();
            Statement stm3= cnx.createStatement();
            String query3 ="select * from alimentations where `marque`='"+test2+"' ";
            ResultSet res3 = stm3.executeQuery(query3);
            if(res3.next())
                id2= res3.getInt("idAlim");

            Statement stm= cnx.createStatement();
            String query ="select * from tickets where `idTicket`="+id1+" ";
            ResultSet res = stm.executeQuery(query);
            
            if (res.next()) {
            r.setIdTicket(id1);  
            r.setIdAlim(id2); 
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
            JOptionPane.showMessageDialog(null, "Reservation Modifiée!");
            paneReservationShow();

            
         
            
        } catch (Exception e) {
            System.out.println("Pas de modification!");
            System.out.println(e.getMessage());
       }
       }
        else
        {
            JOptionPane.showMessageDialog(null, "Vous ne pouvez modifier que votre réservation!");
        }
        
    }

    @FXML
    private void supprimerRes(ActionEvent event) {
        ServiceReservation sr=new ServiceReservation();
        Reservation r=new Reservation();
        r.setIdRes(Integer.parseInt(tfIdResSupprimerRes.getText()));
        int id,idUser1 = 0;
        id=Integer.parseInt(tfIdResSupprimerRes.getText());
        try {
            String query1 ="select * from reservations where `idRes`="+id+" ";
            Statement stm1=cnx.createStatement();
            ResultSet res1= stm1.executeQuery(query1);
            if(res1.next())
                idUser1=res1.getInt("idUser");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(idUser==idUser1){
            sr.SuppReservation(r);
            JOptionPane.showMessageDialog(null, "Réservation supprimée!");
            paneReservationShow();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Vous ne pouvez supprimer que votre réservation!");
        }
        
        
         
    }

    @FXML
    private void imprimerRes(ActionEvent event) {
        try {
            PrinterJob job=PrinterJob.createPrinterJob();
            if(job != null)
            {
            job.printPage(labelAfficherRes);
            job.endJob();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    
}
