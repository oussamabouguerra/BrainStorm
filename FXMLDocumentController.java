/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_promotion;

import Entities.Promotion;
import static Entities.Promotion.pdp;
import Service.ServicePromotion;
import Utils.Maconnexion;
import static com.sun.deploy.config.JREInfo.clear;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.PKIXRevocationChecker.Option;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Med Aziz
 */
public class FXMLDocumentController implements Initializable {
    Connection cnx ;
    String codopromo;
    int index=-1 ;
    @FXML
    private Label label;
    private TextField tftype;
    
    @FXML
    private TextField tfdescription;
    private TextField tfpourcentage;
    private TextField tfid;
    @FXML
    private Button btnajout;
    private Button btnmod;
    @FXML
    private TableView<Promotion> Affiche;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TableColumn<Promotion, String> colnom;
    @FXML
    private TableColumn<Promotion, String> coltype;
    @FXML
    private TableColumn<Promotion, String> coldescription;
    @FXML
    private TableColumn<Promotion, String> colpourcentage;
    @FXML 
    private Button fermerbutton ;
    
    @FXML
    private Button btnajouterShow;
    @FXML
    private Button btnmodifierShow;
   
    @FXML
    private ComboBox<String> cbType;
    
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnRetour;
    private TextField tfDescription;
    @FXML
    private Button btnafficher;
    @FXML
    private ComboBox<String> cbxtype;
    private TextField tfidModifier;
    private TextField tfidmodifier;
    private TextField tfnommod;
    @FXML
    private TextField tfdescriptionmod;
    @FXML
    private Button imprimerpromotion;
    private TextField tfchercher;
    @FXML
    private TextField tfrecherche;
    @FXML
    private ComboBox<?> combo;
    @FXML
    private AnchorPane PaneLogin;
    @FXML
    private AnchorPane paneSignUp;
    @FXML
    private ComboBox<String> ComboSignUp;
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfmdp;
    @FXML
    private Button btnlogin;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfcin;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfpwd;
    @FXML
    private Button btnsign;
    @FXML
    private ComboBox<String> cbxcategorie;
    @FXML
    private TableColumn<Promotion, String> colcat;
    private ComboBox<String> cbxId;
    @FXML
    private ComboBox<String> cbxcategorie1;
    @FXML
    private AnchorPane paneQuiz;
    @FXML
    private Button btnconfirmer;
    private ComboBox<String> cbxcode;
    @FXML
    private TableColumn<?, ?> colcode;
    @FXML
    private TextField tfcode;
    int index1=-1 ;
    @FXML
    private TextField tfcode2;
    @FXML
    private TextField tfid2;
    @FXML
    private Button btnspiderman;
    @FXML
    private Button btnjoker;
    @FXML
    private Button btntitanic;
    @FXML
    private AnchorPane paneQuizSpiderman;
    @FXML
    private RadioButton choix112;
    @FXML
    private RadioButton choix212;
    @FXML
    private RadioButton choix113;
    @FXML
    private AnchorPane paneQuizJoker;
    private RadioButton choix300;
    private RadioButton choix301;
    private RadioButton choix400;
    private RadioButton choix401;
    @FXML
    private RadioButton choix114;
    @FXML
    private Button btnconfirmer6;
    @FXML
    private RadioButton choix700;
    @FXML
    private RadioButton choix701;
    @FXML
    private RadioButton choix800;
    @FXML
    private RadioButton choix801;
    @FXML
    private Button btnconfirmer5;
    @FXML
    private AnchorPane paneQuizTitanic;
    @FXML
    private AnchorPane paneQuizStarWars;
    @FXML
    private AnchorPane panespiderman2;
    @FXML
    private Button btnconfirmerspiderman;
    @FXML
    private Button btnStarWars;
    @FXML
    private AnchorPane paneStarWars2;
    @FXML
    private Button btnsuivantstarwars;
    @FXML
    private Button btnsuivanttitanic;
    @FXML
    private AnchorPane panetitanicshow2;
    @FXML
    private AnchorPane paneAfficherpromo;
    @FXML
    private AnchorPane paneAjouterpromo;
    @FXML
    private AnchorPane paneModifierpromo;
    @FXML
    private TextField tfnompromo;
    @FXML
    private TextField tfnompromo1;
    @FXML
    private TableColumn<Promotion, Integer> colidpromo;
    @FXML
    private RadioButton choix1000;
    @FXML
    private RadioButton choix1100;
    @FXML
    private RadioButton choix900;
    @FXML
    private RadioButton choix901;
    @FXML
    private RadioButton radioJoaquinJoker;
    @FXML
    private RadioButton radioCesarJoker;
    @FXML
    private Button btnsuivantJoker;
    @FXML
    private RadioButton radioToddJoker;
    @FXML
    private RadioButton radioScottJoker;
    @FXML
    private Button btnconfirmerjoker;
    @FXML
    private AnchorPane paneQuizJoker2;
    @FXML
            private void fermer () {
                Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez vous fermer la fenêtre?") ;
                Optional <ButtonType> result=alert.showAndWait();
                if (result.get()==ButtonType.OK)
                {
                    Stage stage =(Stage) fermerbutton.getScene().getWindow() ;
                    stage.close() ;
                    
                }
            }
            
        ObservableList<Promotion> PromotionListe = FXCollections.observableArrayList();

    ObservableList<Promotion> PromotionList = FXCollections.observableArrayList();
        //////// methode select users ///////
  
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        afficherlistepromo() ;
    
    }/*
    public static void playMusic (String filepath){
        InputStream music ;
        try{
            music=new FileInputStream(new File(filepath));
            AudioStream audios=new AudioStream(music);
            AudioPlayer.player.start(audios);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error") ;
        }
    }*/
    @FXML
    private void login(ActionEvent event) {
        cnx=Maconnexion.getInstance().getConnection();
                         int iduser;

            try {
                
                String query ="select * from simpleusers where `iduser`="+tfusername.getText()+" and `mdp`="+tfmdp.getText()+" ";
                Statement stm=cnx.createStatement();
                ResultSet res= stm.executeQuery(query);
                if(res.next())
                {
                    JOptionPane.showMessageDialog(null, "Mail et mdp correctes!");
                    paneAfficherShow();
                    
                }
               
                
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
        
       
        
    }
   
    public void paneAfficherShow() {
        PaneLogin.setVisible(false);
      //  paneSignUp.setVisible(false);
        //paneReservation.setVisible(true);
        paneAjouterpromo.setVisible(false);
        paneModifierpromo.setVisible(false);
         paneAfficherpromo.setVisible(true);
      //  cbxTicketModifierRes.getItems().clear();
        //cbxAlimModifierRes.getItems().clear();
        //cbxTicketAjouterRes.getItems().clear();
        //cbxAlimAjouterRes.getItems().clear();
        //tfIdResModifierRes.setText("");
        //tfIdResSupprimerRes.setText("");

    }/*
    public void paneLoginShow() {
        combo.getItems().clear();
        combo.getItems().clear();
        paneLogin.setVisible(true);
        paneSignUp.setVisible(false);
        paneAfficher.setVisible(false);
        paneAjouterRes.setVisible(false);
        paneModifierRes.setVisible(false);
        paneSuppRes.setVisible(false);
        paneAfficherRes.setVisible(false);
        cbxTypeLogin.getItems().add("Simple User");
        cbxTypeLogin.getItems().add("Administrateur");
        tfUsernameLogin.setText("");
        tfPasswordLogin.setText("");
    }
    
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
   
    }*/
    public void afficherlistepromo(){
          try{
          Connection cnx=Maconnexion.getInstance().getConnection();
          String query = "SELECT * FROM promotion";
          Statement st;
          ResultSet rs;
              st = cnx.createStatement();
              rs = st.executeQuery(query);
              Promotion promotion;
              while(rs.next()){
                  promotion = new Promotion(rs.getInt("id"),rs.getString("nom"),rs.getString("type"),rs.getString("description"),rs.getString("pourcentage"),rs.getString("categorie"),rs.getString("code"));
                 PromotionList.add(promotion);
              }
              
          }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error on Building Data");
          }
      colidpromo.setCellValueFactory(new PropertyValueFactory<Promotion,Integer>("id"));
      colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
      coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
      coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
      colpourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
      colcat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
                    colcode.setCellValueFactory(new PropertyValueFactory<>("code"));

 
      
      Affiche.setItems(PromotionList);
    }
    public void afficherliste2promo(){
        PromotionList.removeAll(PromotionList);

               try{
          Connection cnx=Maconnexion.getInstance().getConnection();
          String query = "SELECT * FROM promotion";
          Statement st;
          ResultSet rs;
              st = cnx.createStatement();
              rs = st.executeQuery(query);
              Promotion promotion;
              while(rs.next()){
                  promotion = new Promotion(rs.getInt("id"),rs.getString("nom"),rs.getString("type"),rs.getString("description"),rs.getString("pourcentage"),rs.getString("categorie"),rs.getString("code"));
                 PromotionList.add(promotion);
              }
              
          }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error on Building Data");
          }
      colidpromo.setCellValueFactory(new PropertyValueFactory<Promotion,Integer>("id"));
      colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
      coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
      coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
      colpourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
       colcat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
              colcode.setCellValueFactory(new PropertyValueFactory<>("code"));

      
      Affiche.setItems(PromotionList);
        
    }
    
@FXML
     private void ajouterpromotion(ActionEvent event) {
         
          Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez-vous Ajouter une promotion?") ;
        ServicePromotion sr=new ServicePromotion();
        Promotion r=new Promotion();
       // int idUser=1;  //to modify after ending login code
         
        r.setCategorie((cbxcategorie.getValue()));  
       // r.setPromo((cbxpromo.getValue()));    
        
        cnx=Maconnexion.getInstance().getConnection();
         
     //   String nomm=(cbxcat.getValue());
     //   String typee= (cbxpromo.getValue());
        
        try {
            Statement stm= cnx.createStatement();
            String query ="select * from film  ";
            ResultSet res = stm.executeQuery(query);

            if (res.next()) {
                    r.setCategorie(cbxcategorie.getValue()) ;
             r.setNom(tfnompromo.getText());
             
             if((cbType.getValue().equals("Jeudi"))&&(tfcode.getText().equals("Monoprix")))
            {r.setPourcentage("35");
             r.setType(cbType.getValue());}
             else if((cbType.getValue().equals("Jeudi"))&&(tfcode.getText().equals("Plan b"))){
                 r.setPourcentage("35");
             r.setType(cbType.getValue());
             }
             else if 
                 ((cbType.getValue().equals("Jeudi"))&&(tfcode.getText().equals("Arena gym"))){
                 r.setPourcentage("35");
             r.setType(cbType.getValue());
                 
             }
             else if 
                     ((cbType.getValue().equals("Jeudi"))&&(tfcode.getText().equals("Carrefour"))){
                 r.setPourcentage("35");
             r.setType(cbType.getValue());
                 
             }
             else if (cbType.getValue().equals("Jeudi")){
                 r.setPourcentage("20");
             r.setType(cbType.getValue());
             }
          
          else if ((cbType.getValue().equals("Aid"))&&(tfcode.getText().equals("Carrefour"))){
              r.setPourcentage("45");
             r.setType(cbType.getValue());
          }
          else if ((cbType.getValue().equals("Aid"))&&(tfcode.getText().equals("Monoprix"))){
              r.setPourcentage("45");
             r.setType(cbType.getValue());
          }
           else if ((cbType.getValue().equals("Aid"))&&(tfcode.getText().equals("Plan b"))){
              r.setPourcentage("45");
             r.setType(cbType.getValue());
          }
           else if ((cbType.getValue().equals("Aid"))&&(tfcode.getText().equals("Arena gym"))){
              r.setPourcentage("45");
             r.setType(cbType.getValue());
          }
           else if(cbType.getValue().equals("Aid"))
              {r.setPourcentage("30");
             r.setType(cbType.getValue());}
           else if((cbType.getValue().equals("Semaine du reveillon"))&&(tfcode.getText().equals("Arena gym")))
              {r.setPourcentage("30");
             r.setType(cbType.getValue());}
           else if((cbType.getValue().equals("Semaine du reveillon"))&&(tfcode.getText().equals("Plan b")))
              {r.setPourcentage("30");
             r.setType(cbType.getValue());}
            else if((cbType.getValue().equals("Semaine du reveillon"))&&(tfcode.getText().equals("Carrefour")))
              {r.setPourcentage("30");
             r.setType(cbType.getValue());}
            else if((cbType.getValue().equals("Semaine du reveillon"))&&(tfcode.getText().equals("Monoprix")))
              {r.setPourcentage("30");
             r.setType(cbType.getValue());}
          else if(cbType.getValue().equals("Semaine du reveillon"))
              {r.setPourcentage("15");
             r.setType(cbType.getValue());}
           else if((cbType.getValue().equals("Etudiant"))&&(tfcode.getText().equals("Monoprix")))
              {r.setPourcentage("25");
             r.setType(cbType.getValue());}
           else if((cbType.getValue().equals("Etudiant"))&&(tfcode.getText().equals("Carrefour")))
              {r.setPourcentage("25");
             r.setType(cbType.getValue());}
           else if((cbType.getValue().equals("Etudiant"))&&(tfcode.getText().equals("Plan b")))
              {r.setPourcentage("25");
             r.setType(cbType.getValue());}
           else if((cbType.getValue().equals("Etudiant"))&&(tfcode.getText().equals("Arena gym")))
              {r.setPourcentage("25");
             r.setType(cbType.getValue());}
           else if(cbType.getValue().equals("Etudiant"))
              {r.setPourcentage("10");
             r.setType(cbType.getValue());}
             r.setDescription((tfdescription.getText()));
             r.setCode((tfcode.getText()));

            // r.setPourcentage((tfpourcentage.getText()));
            
                }
               Optional <ButtonType> result=alert.showAndWait();
                if (result.get()==ButtonType.OK)
                {
                    Stage stage =(Stage) btnajout.getScene().getWindow() ;
                      JOptionPane.showMessageDialog(null,"promotion ajoutée") ;
                 
             
            sr.AddPromotion(r);
                }
         
        } catch (Exception e) {
            System.out.println("Pas d'ajout!"); 
            System.out.println(e.getMessage()); 
       }    
    }
    @FXML
   private void modifierpromotion (ActionEvent event) throws SQLException {
       
 
          
        Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation") ;
         alert.setHeaderText("Voulez-vous modifier cette promotion?") ;
         ServicePromotion sr=new ServicePromotion();
         Promotion f=new Promotion();
        //Promotion f=new Promotion(Promotion.getPdp(),tfnom1.getText(),tfdescriptionmod.getText(),);
        //Produit p = new Produit(parseInt(fid_produitfxid.getText()),ftitrefxid.getText(),11, fdescriptionfxid.getText(),pathimage, Float.parseFloat(fprixfxid.getText()));
           // ServiceProduit pdao = ServiceProduit.getInstance();
            //pdao.update(p);
        



      Affiche.setItems(PromotionList);
                 PromotionListe=Affiche.getSelectionModel().getSelectedItems();
                
            String query ="select * from promotion ";
            Statement stm= cnx.createStatement();
            ResultSet res = stm.executeQuery(query);
           
            while(res.next())
            {
f.setId(Integer.parseInt(tfid2.getText()));             
            }
        int id=Integer.parseInt(tfid2.getText());
               
             f.setNom(tfnompromo1.getText());

 
       if((cbxtype.getValue().equals("Jeudi"))&&(tfcode2.getText().equals("Monoprix")))
            {f.setPourcentage("35");
             f.setType(cbxtype.getValue());}
              if((cbxtype.getValue().equals("Jeudi"))&&(tfcode2.getText().equals("Plan b"))){
                 f.setPourcentage("35");
                                           f.setType(cbxtype.getValue());

             System.out.println("pourcentage modifié");

             }

              
             else if 
                 ((cbxtype.getValue().equals("Jeudi"))&&(tfcode2.getText().equals("Arena gym"))){
                 f.setPourcentage("35");
             f.setType(cbxtype.getValue());
                 
             }
             else if 
                     ((cbxtype.getValue().equals("Jeudi"))&&(tfcode2.getText().equals("Carrefour"))){
                 f.setPourcentage("35");
             f.setType(cbxtype.getValue());
                 
             }
             else if (cbxtype.getValue().equals("Jeudi")){
                 f.setPourcentage("20");
             f.setType(cbxtype.getValue());
             }
          
          else if ((cbxtype.getValue().equals("Aid"))&&(tfcode2.getText().equals("Carrefour"))){
              f.setPourcentage("45");
             f.setType(cbxtype.getValue());
          }
          else if ((cbxtype.getValue().equals("Aid"))&&(tfcode2.getText().equals("Monoprix"))){
              f.setPourcentage("45");
             f.setType(cbxtype.getValue());
          }
           else if ((cbxtype.getValue().equals("Aid"))&&(tfcode2.getText().equals("Plan b"))){
              f.setPourcentage("45");
             f.setType(cbxtype.getValue());
          }
           else if ((cbxtype.getValue().equals("Aid"))&&(tfcode2.getText().equals("Arena gym"))){
              f.setPourcentage("45");
             f.setType(cbxtype.getValue());
          }
           else if(cbxtype.getValue().equals("Aid"))
              {f.setPourcentage("30");
             f.setType(cbxtype.getValue());}
           else if((cbxtype.getValue().equals("Semaine du reveillon"))&&(tfcode2.getText().equals("Arena gym")))
              {f.setPourcentage("30");
             f.setType(cbxtype.getValue());}
           else if((cbxtype.getValue().equals("Semaine du reveillon"))&&(tfcode2.getText().equals("Plan b")))
              {f.setPourcentage("30");
             f.setType(cbxtype.getValue());}
            else if((cbxtype.getValue().equals("Semaine du reveillon"))&&(tfcode2.getText().equals("Carrefour")))
              {f.setPourcentage("30");
             f.setType(cbxtype.getValue());}
            else if((cbxtype.getValue().equals("Semaine du reveillon"))&&(tfcode2.getText().equals("Monoprix")))
              {f.setPourcentage("30");
             f.setType(cbxtype.getValue());}
          else if(cbxtype.getValue().equals("Semaine du reveillon"))
              {f.setPourcentage("15");
             f.setType(cbxtype.getValue());}
           else if((cbxtype.getValue().equals("Etudiant"))&&(tfcode2.getText().equals("Monoprix")))
              {f.setPourcentage("25");
             f.setType(cbxtype.getValue());}
           else if((cbxtype.getValue().equals("Etudiant"))&&(tfcode2.getText().equals("Carrefour")))
              {f.setPourcentage("25");
             f.setType(cbxtype.getValue());}
           else if((cbxtype.getValue().equals("Etudiant"))&&(tfcode2.getText().equals("Plan b")))
              {f.setPourcentage("25");
             f.setType(cbxtype.getValue());}
           else if((cbxtype.getValue().equals("Etudiant"))&&(tfcode2.getText().equals("Arena gym")))
              {f.setPourcentage("25");
             f.setType(cbxtype.getValue());}
           else if(cbxtype.getValue().equals("Etudiant"))
              {f.setPourcentage("10");
             f.setType(cbxtype.getValue());}
              f.setDescription((tfdescriptionmod.getText()));
        f.setCategorie((cbxcategorie1.getValue()));
                  f.setCode((tfcode2.getText()));


 
         try {
            // FilmList.add(f);
             Optional <ButtonType> result=alert.showAndWait();
                if (result.get()==ButtonType.OK)
                {
                    Stage stage =(Stage) btnmodifier.getScene().getWindow() ;
                                JOptionPane.showMessageDialog(null,"promotion modifiée") ;

                     sr.ModifierPromotion(id,f);
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
       }
        
    }
    @FXML
       private void getSelected(){
          index1=Affiche.getSelectionModel().getSelectedIndex();
        if(index1<=-1){
            return;
        }
        Promotion.setPdp(colidpromo.getCellData(index1));
        System.out.println("Promotionaaaaaaaaaaaaaaaaaa"+Promotion.getPdp());   
    
        
    }
   
@FXML
   private void supprimerpromotion(ActionEvent event) {
         Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez vous supprimer la promotion?") ;//problem
         ServicePromotion pr=new ServicePromotion();
         Promotion p=new Promotion() ;
         PromotionListe=Affiche.getSelectionModel().getSelectedItems();
         Connection cnx = Maconnexion.getInstance().getConnection();
            int id;
            id=PromotionListe.get(0).getId();
            System.out.println(id);
             
        try {
            
           String query = "delete from promotion where id = ?";
      PreparedStatement preparedStmt = cnx.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
       Optional <ButtonType> result=alert.showAndWait();
                if (result.get()==ButtonType.OK)
                {
                    Stage stage =(Stage) btnsupprimer.getScene().getWindow() ;
                    
                          preparedStmt.execute();
                                   pr.Supprimerpromotion(p);

         afficherliste2promo() ;
                              JOptionPane.showMessageDialog(null, "Promotion supprimée! \n ");


                }

     
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @FXML
    private void ajouterShowpromo(ActionEvent event) {
        // paneLogin.setVisible(false);
        //paneSignUp.setVisible(false);
        paneAfficherpromo.setVisible(false);
        paneAjouterpromo.setVisible(true);
        paneModifierpromo.setVisible(false);
        cbType.getItems().add("Etudiant");
        cbType.getItems().add("Jeudi");
        cbType.getItems().add("Aid");
        cbType.getItems().add("Semaine du reveillon");
        remplirCbxAjouter();
/*         cbxcode.getItems().add("Monoprix");
        cbxcode.getItems().add("Arena Gym");
        cbxcode.getItems().add("Plan b");
        cbxcode.getItems().add("Batbout");*/
        //remplirCbxAjouterCode();
        
        


      //  remplirCbxAjouterRes();   //call it in login btn
    }

    @FXML
    private void modifierShowpromo(ActionEvent event) throws SQLException {
      // paneLogin.setVisible(false);
       // paneSignUp.setVisible(false);
       
        paneAfficherpromo.setVisible(false);
        paneAjouterpromo.setVisible(false);
        paneModifierpromo.setVisible(true);
        
        /*index1=Affiche.getSelectionModel().getSelectedIndex();
        if(index1<=-1){
            return;
        }
       tfid.setText( String.valueOf(colid.getCellData(index1)));
       tfnom1.setText(colnom.getCellData(index1));
       tfdescriptionmod.setText(coldescription.getCellData(index1));
       tfcode2.setText(colcode.getCellData(index1).toString());

       //imageviewfxid.setText(titrefxid.getCellData(index));
      // fprixfxid.setText(prixfxid.getCellData(index).toString());
        Promotion.setPdp(colid.getCellData(index1));*/
     
         cbxtype.getItems().add("Etudiant");
        cbxtype.getItems().add("Jeudi");
        cbxtype.getItems().add("Aid");
        cbxtype.getItems().add("Semaine du reveillon");
        remplirCbxModifier();
         
        ServicePromotion sr=new ServicePromotion();
        Promotion f;        
        f=sr.displayById(Promotion.getPdp()) ;
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        System.out.println(f);
        tfid2.setText(String.valueOf(pdp));
        tfnompromo1.setText(f.getNom());
        cbxtype.setValue(f.getType());
        cbxcategorie1.setValue(f.getCategorie());
        tfdescriptionmod.setText(f.getDescription());
        tfcode2.setText(f.getCode());
      //  remplirCbxModifierRes();  //call it in login btn
       
    }

    @FXML
    private void paneAfficher(ActionEvent event) {
        paneAfficherpromo.setVisible(true);
        paneAjouterpromo.setVisible(false);
        paneModifierpromo.setVisible(false);
        PromotionList.clear();
        
        afficherlistepromo();
        
    }
   public void recherchepromo()throws SQLException{
    ServicePromotion re= new ServicePromotion() ;
    List<Promotion>results = new ArrayList<>();
    results = re.AfficherPromotion();
     FilteredList<Promotion> filteredData = new FilteredList<>(PromotionList , b -> true);
		Promotion r = new Promotion();
		// 2. Set the filter Predicate whenever the filter changes.
		tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Promotion -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
 
				   if (Promotion.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                  else if (Promotion.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} 
				else if (String.valueOf(r.getNom()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Promotion> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(Affiche.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		Affiche.setItems(sortedData);
    }

    @FXML
    private void imprimerRes(ActionEvent event) {
        ServicePromotion pp = new ServicePromotion();
        
         try {
             label.setText(pp.AfficherPromotion().toString());
            PrinterJob job=PrinterJob.createPrinterJob();
            if(job != null)
            {
            job.printPage(label);//affiche
            job.endJob();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
    }

    @FXML
    private void SignUp(ActionEvent event) {
          if(ComboSignUp.getValue().equals("Simple User"))
        {
            
        }
        else if(ComboSignUp.getValue().equals("Administrateur"))
        {
            
        }
    }

    @FXML
    private void SignUpShow(ActionEvent event) {
         
    
       ComboSignUp.getItems().clear();
        ComboSignUp.getItems().clear();
        PaneLogin.setVisible(false);
        paneSignUp.setVisible(true);
        paneAfficherpromo.setVisible(false);
        paneAjouterpromo.setVisible(false);
        paneModifierpromo.setVisible(false);
      //  paneSupprimer.setVisible(false);
        paneAfficherpromo.setVisible(false);
        ComboSignUp.getItems().add("Simple User");
       // SignUp.getItems().add("Administrateur");
   
    }
    
private void remplirCbxAjouter()
    {
        cnx=Maconnexion.getInstance().getConnection();
        try {
            
            String query ="select Categorie from film";
            Statement stm= cnx.createStatement();
            ResultSet res = stm.executeQuery(query);
           
            while(res.next())
            {
             cbxcategorie.getItems().add(res.getString("Categorie")+"");
            }
           
            System.out.println("combobox1 rempli!");
            
         
        } catch (SQLException e) {
            System.out.println("erreur combobox!");
            System.out.println(e.getMessage());
        }
    }/*
private void remplirCbxAjouterCode()
    {
        cnx=Maconnexion.getInstance().getConnection();
        try {
            
            String query ="select idCode from code";
            Statement stm= cnx.createStatement();
            ResultSet res = stm.executeQuery(query);
           
            while(res.next())
            {
             tfcode.getText().add(res.getString("idCode")+"");
            }
           
            System.out.println("combobox1 rempli!");
            
         
        } catch (SQLException e) {
            System.out.println("erreur combobox!");
            System.out.println(e.getMessage());
        }
    }*/
private void remplirCbxModifier()
    {    
    
        cnx=Maconnexion.getInstance().getConnection();
        
       
        try{
            String query ="select categorie from film";
            Statement stm= cnx.createStatement();
            ResultSet res = stm.executeQuery(query);
           
            while(res.next())
            {
             cbxcategorie1.getItems().add(res.getString("categorie")+"");
            }
           
            System.out.println("combobox1 rempli!");
            
            
            
        } catch (SQLException e) {
            System.out.println("erreur combobox!");
            System.out.println(e.getMessage());
        }
    }



    @FXML
    private void confirmer(ActionEvent event) {
        if (choix112.isSelected()&&(choix113.isSelected())){
           if(choix112.isSelected()&&(choix212.isSelected())||(choix113.isSelected())&&(choix114.isSelected())){
                         JOptionPane.showMessageDialog(null, "Vous devez choisir une seule réponse! \n ");    

           }
           else
           {
                                                    playsound();

          JOptionPane.showMessageDialog(null, "TRUE ANSWER! CHECK YOUR EMAIL FOR YOUR FREE TICKET "); 
                                    
                                     }
         btnconfirmer.getScene().getWindow().hide();
                                     try{
                                         Stage stage= new Stage() ;
                                        Parent root =FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                                        Scene scene =new Scene(root) ;
                                        stage.setScene(scene);
                                        stage.show() ;
                                     }catch (IOException ex){
                                         System.out.println(ex);

        }}
        else{
        playsound2();
         JOptionPane.showMessageDialog(null, "Vous avez perdu! à la prochaine! \n ");
        }

    }

    @FXML
    private void paneQuizShow(ActionEvent event) {
        paneAfficherpromo.setVisible(false);
        paneAjouterpromo.setVisible(false);
        paneModifierpromo.setVisible(false);
        paneQuiz.setVisible(true);
        PromotionList.clear();
        
        afficherlistepromo();
        
    }

    @FXML
    private void showQuizSpiderman(ActionEvent event) {
        paneAfficherpromo.setVisible(false);
        paneAjouterpromo.setVisible(false);
        paneModifierpromo.setVisible(false);
        paneQuiz.setVisible(false);
        paneQuizSpiderman.setVisible(true);

        PromotionList.clear();
        
        afficherlistepromo();
        
    }

    @FXML
    private void showQuizJoker(ActionEvent event) {
          paneAfficherpromo.setVisible(false);
        paneAjouterpromo.setVisible(false);
        paneModifierpromo.setVisible(false);
        paneQuiz.setVisible(false);
        paneQuizSpiderman.setVisible(false);
      paneQuizJoker.setVisible(true);

        PromotionList.clear();
        
        afficherlistepromo();
    }

    @FXML
    private void showQuizTitanic(ActionEvent event) {
        paneAfficherpromo.setVisible(false);
        paneAjouterpromo.setVisible(false);
        paneModifierpromo.setVisible(false);
        paneQuiz.setVisible(false);
        paneQuizSpiderman.setVisible(false);
//                paneQuizJoker.setVisible(false);
                paneQuizTitanic.setVisible(true);

        PromotionList.clear();
        
        afficherlistepromo();
    }

    @FXML
    private void quizShowStarWars(ActionEvent event) {
         paneAfficherpromo.setVisible(false);
        paneAjouterpromo.setVisible(false);
        paneModifierpromo.setVisible(false);
        paneQuiz.setVisible(false);
        paneQuizSpiderman.setVisible(false);
             //   paneQuizJoker.setVisible(false);
                paneQuizTitanic.setVisible(false);
                                paneQuizStarWars.setVisible(true);

        PromotionList.clear();
        
        afficherlistepromo();
    }

    private void confirmer2(ActionEvent event) {
         if (choix300.isSelected()&&(choix401.isSelected())){
           if(choix300.isSelected()&&(choix301.isSelected())||(choix400.isSelected())&&(choix401.isSelected())){
                         JOptionPane.showMessageDialog(null, "Vous devez choisir une seule réponse! \n ");    

           }
           else
          JOptionPane.showMessageDialog(null, "Réponse correcte! vous aurez une promotion de 10%! \n ");    
        }
        else  JOptionPane.showMessageDialog(null, "Vous avez perdu! à la prochaine! \n ");
    }

    @FXML
    private void confimer6(ActionEvent event) {
         if (choix1000.isSelected()&&(choix900.isSelected())){
           if(choix1000.isSelected()&&(choix1100.isSelected())||(choix900.isSelected())&&(choix901.isSelected())){
                         JOptionPane.showMessageDialog(null, "Vous devez choisir une seule réponse! \n "); 
                         

           }
           else
          JOptionPane.showMessageDialog(null, "TRUE ANSWER!CHECK YOUR EMAIL FOR YOUR FREE TICKET ! \n ");  
           playsound();
           
        }
        else { JOptionPane.showMessageDialog(null, "Vous avez perdu! à la prochaine! \n ");
         playsound2();}
    }
String path =("C:\\Users\\Lenovo\\Desktop\\Gestion_Promotion\\dist\\Gestion_promo\\src\\music\\m.mp3"); 
          Media media = new Media(new File(path).toURI().toString()); 
          MediaPlayer mediaPlayer = new MediaPlayer(media); 
          String paths =("C:\\Users\\Lenovo\\Desktop\\Gestion_Promotion\\dist\\Gestion_promo\\src\\music\\loser2.mp3"); 
          Media medias = new Media(new File(paths).toURI().toString()); 
          MediaPlayer mediaPlayers = new MediaPlayer(medias);
    private void playsound(){
          mediaPlayer.play(); 
    }
    
 private void playsound2(){
          mediaPlayers.play(); 
    }
    @FXML
    private void confirmer5(ActionEvent event) {
         if (choix700.isSelected()&&(choix800.isSelected())){
           if(choix700.isSelected()&&(choix701.isSelected())||(choix800.isSelected())&&(choix801.isSelected())){
                         JOptionPane.showMessageDialog(null, "Vous devez choisir une seule réponse! \n ");    

           }
           else
          JOptionPane.showMessageDialog(null, "Réponse correcte! Vous avez gagné un ticket gratuit pour le film STAR WARS\n ");
                          playsound();

        }
        else  {JOptionPane.showMessageDialog(null, "Vous avez perdu! à la prochaine! \n ");
         playsound2();}
    }

    @FXML
    private void panespiderman2Show(ActionEvent event) {
          paneAfficherpromo.setVisible(false);
        paneAjouterpromo.setVisible(false);
        paneModifierpromo.setVisible(false);
        paneQuiz.setVisible(false);
        paneQuizSpiderman.setVisible(false);
               // paneQuizJoker.setVisible(false);
                paneQuizTitanic.setVisible(false);
                                paneQuizStarWars.setVisible(false);
                                paneQuizSpiderman.setVisible(false);
                                panespiderman2.setVisible(true);

    }

    @FXML
    private void showQuizStarWars(ActionEvent event) {
        paneAfficherpromo.setVisible(false);
        paneAjouterpromo.setVisible(false);
        paneModifierpromo.setVisible(false);
        paneQuiz.setVisible(false);
        paneQuizSpiderman.setVisible(false);
//                paneQuizJoker.setVisible(false);
                paneQuizTitanic.setVisible(false);
                                paneQuizStarWars.setVisible(false);
                                paneStarWars2.setVisible(true);

        PromotionList.clear();
        
        afficherlistepromo();
    }

    @FXML
    private void panetitanic2(ActionEvent event) {
        paneAfficherpromo.setVisible(false);
        paneAjouterpromo.setVisible(false);
        paneModifierpromo.setVisible(false);
        paneQuiz.setVisible(false);
        paneQuizSpiderman.setVisible(false);
               // paneQuizJoker.setVisible(false);
                paneQuizTitanic.setVisible(false);
                                paneQuizStarWars.setVisible(false);
                                paneQuizSpiderman.setVisible(false);
                                panespiderman2.setVisible(false);
                                panetitanicshow2.setVisible(true);
    }

    @FXML
    private void recherche(KeyEvent event) {
    }

    @FXML
    private void showQuizJoker2(ActionEvent event) {
        paneAfficherpromo.setVisible(false);
        paneAjouterpromo.setVisible(false);
        paneModifierpromo.setVisible(false);
        paneQuiz.setVisible(false);
        paneQuizSpiderman.setVisible(false);
      paneQuizJoker.setVisible(false);
       paneQuizJoker2.setVisible(true);

        PromotionList.clear();
        
        afficherlistepromo();
    }

    @FXML
    private void confirmerjoker(ActionEvent event) {
        if (radioJoaquinJoker.isSelected()&&(radioToddJoker.isSelected())){
           if(radioJoaquinJoker.isSelected()&&(radioCesarJoker.isSelected())||(radioToddJoker.isSelected())&&(radioScottJoker.isSelected())){
                         JOptionPane.showMessageDialog(null, "Vous devez choisir une seule réponse! \n ");    

           }
           else
           {
                                                    playsound();

          JOptionPane.showMessageDialog(null, "TRUE ANSWER! CHECK YOUR EMAIL FOR YOUR FREE TICKET "); 
                                    
                                     }
         btnconfirmer.getScene().getWindow().hide();
                                     try{
                                         Stage stage= new Stage() ;
                                        Parent root =FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                                        Scene scene =new Scene(root) ;
                                        stage.setScene(scene);
                                        stage.show() ;
                                     }catch (IOException ex){
                                         System.out.println(ex);

        }}
        else{
        playsound2();
         JOptionPane.showMessageDialog(null, "Vous avez perdu! à la prochaine! \n ");
        }
    }

     
  
    } 
 
    
    

    

