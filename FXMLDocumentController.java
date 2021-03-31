/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import Entities.Ticket;
import static Entities.Ticket.Pdp;
import static Entities.Ticket.dates;
import Service.ServiceTicket;
import Utils.Maconnexion;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static java.util.Locale.filter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeArray.some;
//import static sun.misc.MessageUtils.where;

/**
 *
 * @author oussama
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    private TextField tfID;
    @FXML
    private TextField tfPlace;
    @FXML
    private TextField tfSalle;
     
 
    @FXML
    private TableColumn<Ticket, Integer> colplace;
    @FXML
   private TableColumn<Ticket, Integer>colsalle;
    @FXML
    private TableColumn<Ticket, Integer> colfilm;
    @FXML
   private TableColumn<Ticket, Integer> colpromo;
    @FXML
    private Button fermerbutton;
     
    @FXML
    private TableColumn<Ticket, String> colDate;
    @FXML
    private TableColumn<Ticket, String> colheurs;
    @FXML
    private ComboBox<String> cbxFilm;
    @FXML
    private ComboBox<String> cbxpromo;
    @FXML
    private DatePicker dtdate;
    @FXML
    private TextField tfheurs;
       Connection cnx;
    @FXML
    private TextField tfprix1;
    @FXML
    private TextField tfPlace1;
    @FXML
    private DatePicker dtdate1;
    @FXML
    private TextField tfheurs1;
    @FXML
    private TextField tfsalle1;
    @FXML
    private ComboBox<String> cbxFilm1;
    @FXML
    private ComboBox<String> cbxpromo1;
    private ComboBox<String> cbxId;
    @FXML
    private Button btnretour;
    @FXML
    private Button btnretour1;
    @FXML
    private AnchorPane paneLogin;
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfmdp;
    @FXML
    private Button btnlogin;
    @FXML
    private Button tfsignup;
    @FXML
    private ComboBox<String> cbxctype;
    @FXML
    private ComboBox<String> cbxtup;
    @FXML
    private AnchorPane paneSignUp;
    @FXML
    private Button btndeco;
     @FXML
    private TextField tfid12;

    int indexticket=-1;
    @FXML
    private AnchorPane paneAjouterTicket;
    @FXML
    private TextField tfprixT;
    @FXML
    private AnchorPane paneAfficherTicket;
    @FXML
    private AnchorPane paneModifierTicket;
    @FXML
    private Button btnAjouterTicket;
    @FXML
    private Button btnModifierTicket;
    @FXML
    private Button btnajouterTicketShow;
    @FXML
    private Button btnmodifierTicketShow;
    @FXML
    private Button btnSupprimerT;
    @FXML
    private TableColumn<Ticket, Integer> colIDTicket;
    @FXML
    private TableColumn<Ticket, String> colprixTicket;
    @FXML
    private TableView<Ticket> AfficheTicket;
    @FXML
    private TextField tfchercherticket;
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
            ObservableList<Ticket> TicketListe = FXCollections.observableArrayList();
    ObservableList<Ticket> TicketList = FXCollections.observableArrayList();
  int index =-1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      afficherlistTicket();
    
      
 
          
    }    
public void afficherlistTicket(){
   try{

            Connection cnx=Maconnexion.getInstance().getConnection();
            
          String query = "SELECT * FROM ticket";
          Statement st;
          ResultSet rs;
              st = cnx.createStatement();
              rs = st.executeQuery(query);
              Ticket ticket;
              while(rs.next()){
ticket = new Ticket(rs.getInt("IDTicket"),rs.getString("PrixTicket"),rs.getString("NbPlace"),rs.getString("Salle"),rs.getString("Heurs"),rs.getString("Date"),rs.getString("Film"),rs.getString("Promo"));
                 TicketList.add(ticket);

              }
              
          }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error on Building Data");
          }
      colIDTicket.setCellValueFactory(new PropertyValueFactory<>("ID"));
      colprixTicket.setCellValueFactory(new PropertyValueFactory<>("Prix" ));
      colplace.setCellValueFactory(new PropertyValueFactory<>("NbPlace"));
      colsalle.setCellValueFactory(new PropertyValueFactory<>("Salle"));
      colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
      colheurs.setCellValueFactory(new PropertyValueFactory<>("Heurs"));
      colfilm.setCellValueFactory(new PropertyValueFactory<>("Film"));
      colpromo.setCellValueFactory(new PropertyValueFactory<>("Promo")); 
      AfficheTicket.setItems(TicketList);

}
public void afficherlistTicket2()
{
            TicketList.removeAll(TicketList);
   try{

            Connection cnx=Maconnexion.getInstance().getConnection();
            
          String query = "SELECT * FROM ticket";
          Statement st;
          ResultSet rs;
              st = cnx.createStatement();
              rs = st.executeQuery(query);
              Ticket ticket;
              while(rs.next()){
ticket = new Ticket(rs.getInt("IDTicket"),rs.getString("PrixTicket"),rs.getString("NbPlace"),rs.getString("Salle"),rs.getString("Heurs"),rs.getString("Date"),rs.getString("Film"),rs.getString("Promo"));
                 TicketList.add(ticket);

              }
              
          }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error on Building Data");
          }
      colIDTicket.setCellValueFactory(new PropertyValueFactory<>("ID"));
      colprixTicket.setCellValueFactory(new PropertyValueFactory<>("Prix"));
      colplace.setCellValueFactory(new PropertyValueFactory<>("NbPlace"));
      colsalle.setCellValueFactory(new PropertyValueFactory<>("Salle"));
      colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
      colheurs.setCellValueFactory(new PropertyValueFactory<>("Heurs"));
      colfilm.setCellValueFactory(new PropertyValueFactory<>("Film"));
      colpromo.setCellValueFactory(new PropertyValueFactory<>("Promo")); 

      AfficheTicket.setItems(TicketList);
}
 
    @FXML
     private void AjouterTicket(ActionEvent event) {
         
          Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez-vous Ajouter Ticket?") ;
        ServiceTicket sr=new ServiceTicket();
        Ticket r=new Ticket();
       // int idUser=1;  //to modify after ending login code
         
        r.setFilm((cbxFilm.getValue()));  
        r.setPromo((cbxpromo.getValue()));    
            //    r.setPourcentage((cbxpromo.getValue()));    

        cnx=Maconnexion.getInstance().getConnection();
         
        String nomm=(cbxFilm.getValue());
        String typee= (cbxpromo.getValue());
        
        try {
            Statement stm= cnx.createStatement();
            String query ="select * from film  ";
            ResultSet res = stm.executeQuery(query);

            if (res.next()) {
 
             r.setDate(dtdate.getValue().toString());
             r.setHeurs(tfheurs.getText());
           /*  String query2 ="select type from promotion   ";
                String FN = res.getString("type");*/

             int p=Integer.parseInt(tfprixT.getText());
             int k=Integer.parseInt(cbxpromo.getValue());
String prixf =String.valueOf(p*k/100);
int h=Integer.parseInt(prixf);
String prixfinal =String.valueOf(p-h);
             
             r.setPrix(prixfinal);
             r.setNbPlace((tfPlace.getText()));
             r.setSalle(tfSalle.getText());
                }
            
            String query2 ="select * from promotion   ";
            ResultSet res2 = stm.executeQuery(query2);
            if (res2.next()) {
              
            
           
                }
          
               Optional <ButtonType> result=alert.showAndWait();
                if (result.get()==ButtonType.OK)
                {
                    Stage stage =(Stage) btnAjouterTicket.getScene().getWindow() ;
                 
             
            sr.AddTicket(r);
                }
         
        } catch (Exception e) {
            System.out.println("Pas d'ajout!"); 
            System.out.println(e.getMessage()); 
       }
        
        
    }
  private void remplirCbxAjouterTicket()
    {
        cnx=Maconnexion.getInstance().getConnection();
        try {
            
            String query ="select * from film";
            Statement stm= cnx.createStatement();
            ResultSet res = stm.executeQuery(query);
           
            while(res.next())
            {
             cbxFilm.getItems().add(res.getString("nom")+"");
            }
           
            System.out.println("combobox2 rempli!");
            
            Statement stm2= cnx.createStatement();
            String query2 ="select Pourcentage from promotion";
            ResultSet res2 = stm2.executeQuery(query2);
            
            while(res2.next())
            {
                cbxpromo.getItems().add(res2.getString("Pourcentage")+""); 
            }
           
            System.out.println("combobox2 rempli!");
            
        } catch (SQLException e) {
            System.out.println("erreur combobox!");
            System.out.println(e.getMessage());
        }
    } 
  /*private void remplirtfAjouter()
    {
        cnx=Maconnexion.getInstance().getConnection();
        try {
            
            String query ="SELECT * FROM film INNER JOIN ticket ON Film = Nom ";
            Statement stm= cnx.createStatement();
            ResultSet res = stm.executeQuery(query);
           
            while(res.next())
            {
 String FN = res.getString("Prix");
                            tfprix.setText(FN);            }
           
            System.out.println("tf prix rempli!");
            
       /*     Statement stm2= cnx.createStatement();
            String query2 ="select type from promotion";
            ResultSet res2 = stm2.executeQuery(query2);
            
            while(res2.next())
            {
                cbxpromo.getItems().add(res2.getString("type")+""); 
            }
           
            System.out.println("combobox2 rempli!");
            
        } catch (SQLException e) {
            System.out.println("erreur combobox!");
            System.out.println(e.getMessage());
            }
    } 
*/
  private void remplirCbxModifierTicket()
    {    
    
        cnx=Maconnexion.getInstance().getConnection();
        
        try{
            String query ="select nom from film";
            Statement stm= cnx.createStatement();
            ResultSet res = stm.executeQuery(query);
           
            while(res.next())
            {
             cbxFilm1.getItems().add(res.getString("nom")+"");
            }
           
            System.out.println("combobox1 rempli!");
            
            Statement stm2= cnx.createStatement();
            String query2 ="select Pourcentage from promotion";
            ResultSet res2 = stm2.executeQuery(query2);
            
            while(res2.next())
            {
                cbxpromo1.getItems().add(res2.getString("Pourcentage")+""); 
            }
           
            System.out.println("combobox2 rempli!");
            
        } catch (SQLException e) {
            System.out.println("erreur combobox!");
            System.out.println(e.getMessage());
        }
    }
 
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
                }}

   /* private void SupprimerTicket(ActionEvent event) {
                    Ticket t =new Ticket();

         try {
            String req = "DELETE FROM `ticket` WHERE `id`.`id` =?";
            PreparedStatement stm = null;
            stm.setInt(1, t.getID());
            stm.executeUpdate();
            System.out.println("element supprimer");

        } catch (SQLException ex) {
         }
    }*/
   private void supprimerticket(ActionEvent event) {
         Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez vous supprimer la promotion?") ;//problem
         ServiceTicket pr=new ServiceTicket();
         Ticket p=new Ticket() ;
         
          Optional <ButtonType> result=alert.showAndWait();
                if (result.get()==ButtonType.OK)
                {
                    Stage stage =(Stage) btnSupprimerT.getScene().getWindow() ;
                    
                    
                }


         TicketListe=AfficheTicket.getSelectionModel().getSelectedItems();
         Connection cnx = Maconnexion.getInstance().getConnection();
            int id;
            id=TicketListe.get(0).getID();
            System.out.println(id);
             
        try {
            
           String query = "delete from ticket where ID = ?";
      PreparedStatement preparedStmt = cnx.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
      
     
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         pr.supprimer(p);
         afficherlistTicket2() ;
        
         
         
        
        
        
    }
  @FXML
private void ModifierTicket(ActionEvent event) throws SQLException {
        Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez-vous modifier Ticket?") ;
         ServiceTicket sr=new ServiceTicket();
        Ticket f=new Ticket();
            String query ="select * from ticket ";
            Statement stm= cnx.createStatement();
            ResultSet res = stm.executeQuery(query);
           while(res.next()){
           f.setID(Integer.parseInt(tfid12.getText()));

           }
           
        int id=Integer.parseInt(tfid12.getText());
int p=Integer.parseInt(tfprix1.getText());
int k=Integer.parseInt(cbxpromo1.getValue());
String prixf =String.valueOf(p*k/100);
int h=Integer.parseInt(prixf);
String prixfinal =String.valueOf(p-h);
             
             f.setPrix(prixfinal);
//r.setIdTicket(Integer.parseInt(cbIdTicket.getValue()));  //error
//r.setIdAlim(Integer.parseInt(cbIdAliment.getValue()));    //erro
          f.setNbPlace((tfPlace1.getText()));
         f.setSalle((tfsalle1.getText()));
         f.setDate(dtdate1.getValue().toString());
         f.setHeurs(tfheurs1.getText());
        f.setFilm((cbxFilm1.getValue()));
        f.setPromo((cbxpromo1.getValue()));  

 
         try {
           
            // FilmList.add(f);
             Optional <ButtonType> result=alert.showAndWait();
                if (result.get()==ButtonType.OK)
                {
                    Stage stage =(Stage) btnModifierTicket.getScene().getWindow() ;
                     sr.modifierTicket(f,id);
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
       }
   
       
    }
/*
@FXML
private void ModifierTicket(ActionEvent event) throws SQLException {
       
 
          Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez-vous modifier Ticket?") ;
         ServiceTicket sr=new ServiceTicket();
        Ticket f=new Ticket();
            String query ="select * from ticket ";
            Statement stm= cnx.createStatement();
            ResultSet res = stm.executeQuery(query);
           while(res.next()){
           f.setID(Integer.parseInt(tfid12.getText()));

           }
            
        int id=Integer.parseInt(tfid12.getText());
int p=Integer.parseInt(tfprix1.getText());
int k=Integer.parseInt(cbxpromo1.getValue());
String prixf =String.valueOf(p*k/100);
int h=Integer.parseInt(prixf);
String prixfinal =String.valueOf(p-h);
             
             f.setPrix(prixfinal); 
//r.setIdTicket(Integer.parseInt(cbIdTicket.getValue()));  //error
//r.setIdAlim(Integer.parseInt(cbIdAliment.getValue()));    //erro
          f.setNbPlace((tfPlace1.getText()));
         f.setSalle((tfsalle1.getText()));
         f.setDate(dtdate1.getValue().toString());
         f.setHeurs(tfheurs1.getText());
        f.setFilm((cbxFilm1.getValue())); 
        f.setPromo((cbxpromo1.getValue()));  

 
         try {
            
            // FilmList.add(f);
             Optional <ButtonType> result=alert.showAndWait();
                if (result.get()==ButtonType.OK)
                {
                    Stage stage =(Stage) btnModifierTicket.getScene().getWindow() ;
                     sr.modifierTicket(f,id);
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
       }
        
    
        
    }
*/
  
  

    @FXML 
    private void ajouterShowTicket(ActionEvent event) throws SQLException {
           // paneLogin.setVisible(false);
        //paneSignUp.setVisible(false);
        paneAfficherTicket.setVisible(false);
        paneAjouterTicket.setVisible(true);
        paneModifierTicket.setVisible(false);
       
      remplirCbxAjouterTicket();   //call it in login btn
              //remplirtfAjouter();
     
        
    }
        
    @FXML
    public void paneAfficherShowTicket() {
        paneLogin.setVisible(false);
      //  paneSignUp.setVisible(false);
        //paneReservation.setVisible(true);
        paneAjouterTicket.setVisible(false);
        paneModifierTicket.setVisible(false);
         paneAfficherTicket.setVisible(true);
      //  cbxTicketModifierRes.getItems().clear();
        //cbxAlimModifierRes.getItems().clear();
        //cbxTicketAjouterRes.getItems().clear();
        //cbxAlimAjouterRes.getItems().clear();
        //tfIdResModifierRes.setText("");
        //tfIdResSupprimerRes.setText("");
TicketList.clear();
        afficherlistTicket2();
    }
    
    public void paneLoginShow() {
        cbxctype.getItems().clear();
        cbxctype.getItems().clear();
        paneLogin.setVisible(true);
      //  paneSignUp.setVisible(false);
         paneAjouterTicket.setVisible(false);
        paneModifierTicket.setVisible(false);
         paneAfficherTicket.setVisible(false);
 
      //  cbxctype.getItems().add(("Simple User"));
        //cbxctype.getItems().add("Administrateur");
        tfusername.setText("");
        tfmdp.setText("");
    }
    @FXML
    public void paneSignUpShow() {
        cbxtup.getItems().clear();
        cbxtup.getItems().clear();
        paneLogin.setVisible(false);
        paneSignUp.setVisible(true);
         paneAjouterTicket.setVisible(false);
        paneModifierTicket.setVisible(false);
         paneAfficherTicket.setVisible(false);
        
    }
      @FXML
    private void login(ActionEvent event) {
        cnx=Maconnexion.getInstance().getConnection();
                         int idUser;

            try {
                
                String query ="select * from simpleusers where `idUser`="+tfusername.getText()+" and `mdp`="+tfmdp.getText()+" ";
                Statement stm=cnx.createStatement();
                ResultSet res= stm.executeQuery(query);
                if(res.next())
                {
                    JOptionPane.showMessageDialog(null, "ID et mdp correctes!");
                    paneAfficherShowTicket();
                    
                }
               
                
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
        
       
        
    }

    @FXML
    private void modifierShowTicket(ActionEvent event) throws SQLException {
          // paneLogin.setVisible(false);
        //paneSignUp.setVisible(false);
        paneAfficherTicket.setVisible(false);
        paneAjouterTicket.setVisible(false);
        paneModifierTicket.setVisible(true);
              remplirCbxModifierTicket();

        ServiceTicket sr=new ServiceTicket();
        Ticket f;   
        
      f=sr.displayById(Ticket.getPdp());
      //  System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

       tfid12.setText(String.valueOf(Pdp));
       tfprix1.setText(f.getPrix());
       tfsalle1.setText(f.getSalle());
       tfPlace1.setText(f.getNbPlace());
              tfheurs1.setText(f.getHeurs());
            //  dtdate.setDate(f.getDate());
        // f.setDate(dtdate1.getValue().toString());

      //  remplirCbxAjouterRes();   //call it in login btn
      dtdate1.setValue(LocalDate.now());
      cbxFilm1.setValue(f.getFilm());
      cbxpromo1.setValue(f.getPromo());
    }

    @FXML
    private void SupprimerTicket(ActionEvent event) {
               Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez vous supprimer la promotion?") ;//problem
         ServiceTicket pr=new ServiceTicket();
         Ticket p=new Ticket() ;
         
          Optional <ButtonType> result=alert.showAndWait();
                if (result.get()==ButtonType.OK)
                {
                    Stage stage =(Stage) btnSupprimerT.getScene().getWindow() ;
                    
                    
                }


         TicketListe=AfficheTicket.getSelectionModel().getSelectedItems();
         Connection cnx = Maconnexion.getInstance().getConnection();
            int id;
            id=TicketListe.get(0).getID();
            System.out.println(id);
             
        try {
            
           String query = "delete from ticket where IDTicket = ?";
      PreparedStatement preparedStmt = cnx.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
      
     
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         pr.supprimer(p);
         afficherlistTicket2() ;
        
         
    } 

    private void SignUp(ActionEvent event) {
        if(cbxtup.getValue().equals("Simple User"))
        {
            
        }
        else if(cbxtup.getValue().equals("Administrateur"))
        {
            
        }
    }
   public void rechercheTicket() throws SQLException{
    ServiceTicket re= new ServiceTicket() ;
    List<Ticket>results = new ArrayList<>();
    results = re.AfficherTicket();
     FilteredList<Ticket> filteredData = new FilteredList<>(TicketList , b -> true);
		Ticket r = new Ticket();
		// 2. Set the filter Predicate whenever the filter changes.
		tfchercherticket.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Ticket -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
 
				   if (Ticket.getPromo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (String.valueOf(Ticket.getPromo()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                   
                                 else   if (Ticket.getFilm().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} 
                                  else if (String.valueOf(Ticket.getFilm()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
				

				     else  
				    	 return false; // Does not match.
                                   
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Ticket> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(AfficheTicket.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		AfficheTicket.setItems(sortedData);
               
        
    }
    @FXML
    private void deconnecter(ActionEvent event) {
          btndeco.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
  @FXML
       private void getSelectedTicket(){
          indexticket=AfficheTicket.getSelectionModel().getSelectedIndex();
        if(indexticket<=-1){
            return;
        }
        Ticket.setPdp(colIDTicket.getCellData(indexticket));
        Ticket.setDates(colDate.getCellData(indexticket));

        System.out.println(""+Ticket.getPdp());   
      LocalDate Date = dtdate.getValue();
         System.err.println("Selected date: " +Ticket.getDates());
        
    }

    @FXML
    private void recherche(KeyEvent event) {
    }

}




    
    

