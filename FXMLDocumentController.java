/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_aliment;

import Entities.Aliment;
import Service.ServiceAliment;
import Utils.Maconnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author 21624
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button Btn_ajouter;
    @FXML
    private Button Btn_supprimer;
    @FXML
    private Button Btn_modifier;
    private Label Affiche;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfmarque;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfquantite;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfidp;
    @FXML
    private TableView<Aliment> table;
    @FXML
    private TableColumn<Aliment,Integer> id;
    @FXML
    private TableColumn<Aliment,Integer> quantite;
    @FXML
    private TableColumn<Aliment,Integer> prix;
    @FXML
    private TableColumn<Aliment,Integer> idp;
    @FXML
    private TableColumn<Aliment,Integer> type;
    @FXML
    private TableColumn<Aliment,Integer> marque;
    @FXML
    private ImageView imglogo;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
       
    }
    ObservableList<Aliment> alimentsList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
        
          
          try{
          Connection cnx=Maconnexion.getInstance().getConnection();
          String query = "SELECT * FROM aliment";
          Statement st;
          ResultSet rs;
              st = cnx.createStatement();
              rs = st.executeQuery(query);
              Aliment aliment;
              while(rs.next()){
                  aliment = new Aliment(rs.getInt("id"),rs.getInt("quantite"),rs.getInt("prix"),rs.getInt("idpromo"),rs.getString("type"),rs.getString("marque"));
                 alimentsList.add(aliment);
              }
              
          }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error on Building Data");
          }
      id.setCellValueFactory(new PropertyValueFactory<>("id"));
      quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
      prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
      idp.setCellValueFactory(new PropertyValueFactory<>("idpromo"));
      type.setCellValueFactory(new PropertyValueFactory<>("type"));
      marque.setCellValueFactory(new PropertyValueFactory<>("marque")); 
      
      table.setItems(alimentsList);
        
    }    
    
    

    @FXML
    private void ajouter_aliment(ActionEvent event) {
        ServiceAliment aa=new ServiceAliment();
     Aliment a=new Aliment();
     a.setId(Integer.parseInt(tfid.getText()));
     a.setQuantite(Integer.parseInt(tfquantite.getText()));
     a.setPrix(Integer.parseInt(tfprix.getText()));
     a.setIdpromo(Integer.parseInt(tfidp.getText()));
     a.setType(tftype.getText());
     a.setMarque(tfmarque.getText());
try{
     
aa.AddAliment(a);
    } catch(SQLException ex){
   
    }
    }

    @FXML
    private void supprimer_aliment(ActionEvent event) {
        ServiceAliment st=new ServiceAliment();
       Aliment a=new Aliment();
       a.setId(Integer.parseInt(tfid.getText()));
       st.supprimerAliment(a);
    }

    @FXML
    private void modifier_aliment(ActionEvent event)throws SQLException {
         ServiceAliment sr=new ServiceAliment();
        Aliment a=new Aliment();
        int id;
        id=Integer.parseInt(tfid.getText());
        a.setQuantite(Integer.parseInt(tfquantite.getText()));
        a.setPrix(Integer.parseInt(tfprix.getText()));
         a.setIdpromo(Integer.parseInt(tfidp.getText()));
         a.setType(tftype.getText());
        a.setMarque(tfmarque.getText());
       
        sr.ModifierAliment(id,a);
        
    }

    private void afficher_aliment(ActionEvent event) {
        ServiceAliment  aa=new ServiceAliment();
            Aliment a =new Aliment();
        try {
            
            System.out.println(aa.afficherAliment().toString());
            Affiche.setText(aa.afficherAliment().toString());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private String String(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void handlelogoimg(MouseEvent event) {
       label.setText("");
    }
    
}
