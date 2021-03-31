/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Aliment;
import Entities.Film;
import Entities.Ticket;
 import Service.ServiceTicket;
import Utils.Maconnexion;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import static java.sql.DriverManager.println;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import static javafx.beans.property.DoubleProperty.doubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
 


/**
 * FXML Controller class
 *
 * @author oussama
 */
public class FilmController implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scroll;
    @FXML
    private AnchorPane paneTicket;
    private Label YNWA;
    @FXML
    private Button btnhome;
    @FXML
    private Button btnhome1;
    @FXML
    private Button fb;
    @FXML
    private Button insta;
    Rating ratingbarr;
    MediaView mv;
           MediaPlayer mp;
 Media me;
    @FXML
    private AnchorPane panek;
    @FXML
    private Button btnalimentShow;
    @FXML
    private AnchorPane paneAliments;
    @FXML
    private ScrollPane scroll1;
    @FXML
    private GridPane grid1;
    @FXML
    private Button btnToWatch;
    @FXML
    private Label labelcontact;
    @FXML
    private Button btnTn;
    @FXML
    private Label choselangue;
    @FXML
    private Button btnfr;
    @FXML
    private Button btneng;
    @FXML
    private Button fb1;
    @FXML
    private Button btnToWatch1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             // TODO

            afficherlist();
         
      afficheraliment();
/* */
     }    
   ObservableList<Ticket> TicketList = FXCollections.observableArrayList();
   ObservableList<Film> FilmListe = FXCollections.observableArrayList();
      ObservableList<Aliment> AlimentListe = FXCollections.observableArrayList();
    public void afficherlist()  {
   try{
  Connection cnx=Maconnexion.getInstance().getConnection();
            
            String query ="SELECT * FROM film  INNER JOIN ticket ON ID = IDTicket " ;
         
          Statement st;
          ResultSet rs;
              st = cnx.createStatement();
              rs = st.executeQuery(query);
              Film film;
              Ticket ticket;
              while(rs.next())  {   
   File file = new File(rs.getString(6));
                Image imgg = new Image(file.toURI().toString());
                ImageView imgView = new ImageView(imgg);
                imgView.setFitWidth(150);          
               imgView.setFitHeight(200);
/**/
   film = new Film (rs.getInt("ID"),rs.getString("Nom"),rs.getString("Categorie"),rs.getString("Duree"),rs.getString("Photo"),rs.getFloat("Prix"),rs.getString("Synopsis"),rs.getString("Actor"),rs.getString("trailer"));
      
   film.setPhoto(imgView);
  film.setTrailers(mp);
   ticket = new Ticket(rs.getInt("IDTicket"),rs.getString("PrixTicket"),rs.getString("NbPlace"),rs.getString("Salle"),rs.getString("Heurs"),rs.getString("Date"),rs.getString("Film"),rs.getString("Promo"));
                 TicketList.add(ticket);
   FilmListe.add(film);
               }
              
  }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error on Building Data");
          }
   

    int x=0;
  int k=0;
      for(int i=0 ;i<FilmListe.size()&&i<TicketList.size();i++){
           
              
            Label lb=new Label("");
            Label lbp=new Label(""); 
            Label lbd=new Label("");
            Label lbc=new Label("");
            Label lbdt= new Label("DT");
            Rating ratingbarr= new Rating();
            Label lbb=new Label("");
      lb.setStyle("-fx-text-fill: white; -fx-font-size: 15pt; -fx-font-weight: bold;");
    lbp.setStyle("-fx-text-fill: white; -fx-font-size: 10pt; -fx-font-weight: bold;"); 
    lbd.setStyle("-fx-text-fill: white; -fx-font-size: 10pt; -fx-font-weight: bold;");
        lbc.setStyle("-fx-text-fill: white; -fx-font-size: 10pt; -fx-font-weight: bold;");
      lbdt.setStyle("-fx-text-fill: white; -fx-font-size: 10pt; -fx-font-weight: bold;");

      lbb.setStyle("-fx-text-fill: white; -fx-font-size: 10pt; -fx-font-weight: bold;");
   
            lb.setText(FilmListe.get(i).getActor());
            lb.setLayoutX(10);
            lb.setLayoutY(10);
            lbd.setText(TicketList.get(i).getDate());
            lbd.setLayoutX(10);
            lbd.setLayoutY(10);
            lbc.setText(FilmListe.get(i).getCategorie());
            lbc.setLayoutX(10);
            lbc.setLayoutY(10);
            lbp.setText(TicketList.get(i).getPrix().concat("DT")); 
            lbp.setLayoutX(10);
            lbp.setLayoutY(10);
             lbb.setText(FilmListe.get(i).getSynopsis());
            lbb.setLayoutX(20);
            lbb.setLayoutY(20);
             lbdt.setLayoutX(10);
            lbdt.setLayoutY(10);
            Button btnn=new Button("Trailer");
              {
 Font font = Font.font("Courier New", FontWeight.BOLD, 20);
btnn.setFont(font);                     
btnn.setStyle("-fx-background-color: #00ff00");

//      sc.addProduitPanier(idd, 1, activ_user_id);
                   
                
             //mp.setAutoPlay(true);
               
              
             {
                  
    btnn.setOnAction((ActionEvent event) -> {
               
                   });
             }
             
        //refrechTableCart(activ_user_id);
       //    Double t=0.0;
      // refrech_tot_price(t, activ_user_id);
                            
    
                    }  
           
            
            VBox h1;
             h1 = new VBox(FilmListe.get(i).getPhoto());
            h1.getChildren().add(lb);
            h1.getChildren().add(lbb);
             h1.getChildren().add((lbp)); 

            h1.getChildren().add(lbd);
            h1.getChildren().add(ratingbarr);
            h1.getChildren().add(lbc);
            h1.getChildren().add(btnn); 
                      

           grid.add(h1,k,x);
           //grid.add(h1, k+1, x+1);  
           grid.setHgap(150);
           grid.setVgap(100);
          
           k++;
         if(k>2)
         { 
           x=x+1; 
           k=0;
         }
           }
          
       }
    
 
    public void afficheraliment(){
   try{

            Connection cnx=Maconnexion.getInstance().getConnection();
            
            String query1 ="SELECT * FROM aliment" ;
          
          Statement stm;
          ResultSet res;
              stm = cnx.createStatement();
              res = stm.executeQuery(query1);
              Aliment aliment;
               while(res.next())  {   
   File file = new File(res.getString(7));
                Image imgg = new Image(file.toURI().toString());
                ImageView imgView = new ImageView(imgg);
                imgView.setFitWidth(150);
                imgView.setFitHeight(200); 
  aliment = new Aliment (res.getInt("quantite"),res.getInt("prix"),res.getInt("idpromo"),res.getString("type"),res.getString("marque"),res.getString("photo"));    
   aliment.setPhotos(imgView);
    
   AlimentListe.add(aliment);
              }
              
          }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error on Building Data");
          }
    int x=0;
  int k=0;
      for(int i=0 ;i<AlimentListe.size();i++){
           
              
            Label lb1=new Label("");
            Label lbp1=new Label("");
            // Rating ratingbarr= new Rating();
           // Label lbb=new Label("");
    lb1.setStyle("-fx-text-fill: white; -fx-font-size: 15pt; -fx-font-weight: bold;");
    lbp1.setStyle("-fx-text-fill: white; -fx-font-size: 10pt; -fx-font-weight: bold;"); 
       //lbb.setStyle("-fx-text-fill: white; -fx-font-size: 10pt; -fx-font-weight: bold;");
   
            lb1.setText(AlimentListe.get(i).getType());
            lb1.setLayoutX(10);
            lb1.setLayoutY(10);
            lbp1.setText(AlimentListe.get(i).getMarque());
            lbp1.setLayoutX(10);
            lbp1.setLayoutY(10);
            
           
            
            Button btnn1=new Button("reserver");
              {
 Font font = Font.font("Courier New", FontWeight.BOLD, 20);
btnn1.setFont(font);                     
btnn1.setStyle("-fx-background-color: #00ff00");

//      sc.addProduitPanier(idd, 1, activ_user_id);
                   
                    btnn1.setOnAction((ActionEvent event) -> {
                        
 
        //refrechTableCart(activ_user_id);
                        //    Double t=0.0;
                           // refrech_tot_price(t, activ_user_id);
                        });
                    }  
           
            
            VBox h1;
           h1 = new VBox(AlimentListe.get(i).getPhotos());
           h1.getChildren().add(lb1);
            h1.getChildren().add(lbp1);
             h1.getChildren().add(btnn1); 
          
           grid1.add(h1,k,x);
           //grid.add(h1, k+1, x+1);  
           grid1.setHgap(150);
           grid1.setVgap(100);
           k++;
         if(k>2)
         { 
           x=x+1; 
           k=0;
         }
           }
          
       }

    private void OnMouseExit(MouseEvent event) {
         YNWA.setScaleX(1);
        YNWA.setScaleY(1);
    }

    private void OnMouseEnter(MouseEvent event) {
         YNWA.setScaleX(1.5);
        YNWA.setScaleY(1.5);
    }
  
    @FXML
    private void facebookload(ActionEvent event) throws URISyntaxException, IOException {
        Desktop d = Desktop.getDesktop();
        d.browse(new URI("https://www.facebook.com/bouguerra.oussama.589/"));
    }

    @FXML
    private void instaload(ActionEvent event) throws URISyntaxException, IOException {
         Desktop d = Desktop.getDesktop();
        d.browse(new URI("https://www.instagram.com/oussamaaa_bouguerra__03/?hl=fr"));
    }
  
    
  private ResourceBundle bundle;
  private Locale locale;

    @FXML
    private void aimentShow(ActionEvent event) {
        paneTicket.setVisible(false);
        paneAliments.setVisible(true);
    }

    @FXML
   public void handlebtnToWatch() throws IOException {
      //  paneMediaShow();
  Parent root = FXMLLoader.load(getClass().getResource("/gui/topmovies.fxml"));
        
         Stage window =(Stage)btnToWatch.getScene().getWindow();
         window.setScene(new Scene(root));
    //  mp.setAutoPlay(true);
    }

    @FXML
    private void btnlantn(ActionEvent event) {
        loadLang("tn");
    }
private void loadLang(String lang){
locale = new Locale(lang);
bundle = ResourceBundle.getBundle("resources.lang",locale);
labelcontact.setText(bundle.getString("labelcontact"));
btnToWatch.setText(bundle.getString("btnToWatch"));
btnhome.setText(bundle.getString("btnhome"));
btnhome1.setText(bundle.getString("btnhome1"));
btnalimentShow.setText(bundle.getString("btnalimentShow"));
insta.setText(bundle.getString("insta"));
fb.setText(bundle.getString("fb"));
choselangue.setText(bundle.getString("choselangue"));
}

    @FXML
    private void btnlanfr(ActionEvent event) {
        loadLangfr("fr");
    }
 
private void loadLangfr(String lang){
locale = new Locale(lang);
bundle = ResourceBundle.getBundle("resources.lang",locale);
labelcontact.setText(bundle.getString("labelcontact"));
btnToWatch.setText(bundle.getString("btnToWatch"));
btnhome.setText(bundle.getString("btnhome"));
btnhome1.setText(bundle.getString("btnhome1"));
btnalimentShow.setText(bundle.getString("btnalimentShow"));
insta.setText(bundle.getString("insta"));
fb.setText(bundle.getString("fb"));
choselangue.setText(bundle.getString("choselangue"));
}

    @FXML
    private void btnlaneng(ActionEvent event) {
        loadLangeng("eng");
    }
     private void loadLangeng(String lang){
locale = new Locale(lang);
bundle = ResourceBundle.getBundle("resources.lang",locale);
labelcontact.setText(bundle.getString("labelcontact"));
btnToWatch.setText(bundle.getString("btnToWatch"));
btnhome.setText(bundle.getString("btnhome"));
btnhome1.setText(bundle.getString("btnhome1"));
btnalimentShow.setText(bundle.getString("btnalimentShow"));
insta.setText(bundle.getString("insta"));
fb.setText(bundle.getString("fb"));
choselangue.setText(bundle.getString("choselangue"));
}
}
