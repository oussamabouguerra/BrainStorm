/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_aliment;
import Entities.Aliment;
import Service.ServiceAliment;
import Utils.Maconnexion;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import static java.sql.DriverManager.println;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @selimgharbi 21624
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
    private TextField tfquantite;
   // private TextField tfidp;
    @FXML
    private TableView<Aliment> table;
    @FXML
    private TableColumn<Aliment, Integer> id;
    @FXML
    private TableColumn<Aliment, Integer> quantite;
    @FXML
    private TableColumn<Aliment, Integer> prix;
    @FXML
    private TableColumn<Aliment, Integer> idp;
    @FXML
    private TableColumn<Aliment, Integer> type;
    @FXML
    private TableColumn<Aliment, Integer> marque;
    @FXML
    private ImageView imglogo;
    @FXML
    private Button Btn_suivant;
    @FXML
    private AnchorPane parent;
    @FXML
    private ImageView bg;
    @FXML
    private TextField tfidaliment;
    @FXML
    private TextField tfmarquealiment;
    @FXML
    private TextField tftypealiment;
    @FXML
    private TextField tfprixaliment;
    @FXML
    private JFXComboBox<String> idpromotion_aliment;
ServiceAliment serv=new ServiceAliment();
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");

    }
    ObservableList<Aliment> alimentsList = FXCollections.observableArrayList();
    ObservableList<Aliment> alimentsList2;
    ObservableList<String> data2;
    
    
    
   
    ObservableList<String> data = FXCollections.observableArrayList("0","10", "20", "30", "40", "50", "60", "70", "80");
    public void showaliment() {
        try {
            Connection cnx = Maconnexion.getInstance().getConnection();
            String query = "SELECT * FROM aliment";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Aliment aliment;
            while (rs.next()) {
                aliment = new Aliment(rs.getInt("id"), rs.getInt("quantite"), rs.getInt("prix"), rs.getInt("idpromo"), rs.getString("type"), rs.getString("marque"));
                alimentsList.add(aliment);
            }

        } catch (Exception ex) {
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            
            showaliment();
            List<Integer>  list =new ArrayList<>();
            for(Aliment a : serv.afficherAliment())
            {
                idpromotion_aliment.getItems().addAll(String.valueOf(a.getId()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      

    }
    private void handleColse(ActionEvent event) {
       System.exit(0);
    }
  
    @FXML
    private void ajouter_aliment(ActionEvent event) throws SQLException {
        ServiceAliment aa = new ServiceAliment();
        Scanner sc = new Scanner(System.in);
        Connection cnx = Maconnexion.getInstance().getConnection();
        Statement st;
        ResultSet rs;
        st = cnx.createStatement();
        Statement stm = cnx.createStatement();
        String SQL = "SELECT * FROM aliment WHERE id ='" + tfidaliment.getText() + "'";
        rs = stm.executeQuery(SQL);
        if (!rs.next()) {
            if ((!tfquantite.getText().matches("[A-Za-z]") && !tfquantite.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+"))
                    && (!tfprixaliment.getText().matches("[A-Za-z]") && !tfprixaliment.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+"))) {
                String req = "insert into aliment (id,quantite,prix,idpromo,type,marque)values('" + tfidaliment.getText() + "','" + tfquantite.getText() + "','" + tfprixaliment.getText() + "" + idpromotion_aliment.getValue() + "','" + tftypealiment.getText() + "','" + tfmarquealiment.getText() + "','')";
               
               try {
                    st = cnx.createStatement();
                    st.executeQuery(req);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                    showaliment2();
                

            } 

            Aliment a = new Aliment();
            a.setId(Integer.parseInt(tfidaliment.getText()));
            a.setQuantite(Integer.parseInt(tfquantite.getText()));
            a.setPrix(Integer.parseInt(tfprixaliment.getText()));
            a.setIdpromo(Integer.parseInt(idpromotion_aliment.getValue()));
            a.setType(tftypealiment.getText());
            a.setMarque(tfmarquealiment.getText());
            try {
                    
                aa.AddAliment(a);
                showaliment2();
                tfidaliment.clear();
                 tfquantite.clear();
                  tfprixaliment.clear();
                   //tfidp.clear();
                    tftypealiment.clear();
                     tfmarquealiment.clear();
                
            } catch (SQLException ex) {

            }
        }
       
    }
    
public void showaliment2() {
       alimentsList.removeAll(alimentsList);
        try {
            Connection cnx = Maconnexion.getInstance().getConnection();
            String query = "SELECT * FROM aliment";
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Aliment aliment;
            while (rs.next()) {
                aliment = new Aliment(rs.getInt("id"), rs.getInt("quantite"), rs.getInt("prix"), rs.getInt("idpromo"), rs.getString("type"), rs.getString("marque"));
                alimentsList.add(aliment);
            }

        } catch (Exception ex) {
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
    private void supprimer_aliment(ActionEvent event) {
         alimentsList2=table.getSelectionModel().getSelectedItems();
         Connection cnx = Maconnexion.getInstance().getConnection();
            int id;
            id=alimentsList2.get(0).getId();
            System.out.println(id);
             
        try {
            
           String query = "delete from aliment where id = ?";
      PreparedStatement preparedStmt = cnx.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
       tfidaliment.clear();
                 
      
     
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         showaliment2();
    }

    @FXML
    private void modifier_aliment(ActionEvent event) throws SQLException {
        ServiceAliment sr = new ServiceAliment();
        Aliment a = new Aliment();
        int id;
        id = Integer.parseInt(tfidaliment.getText());
        a.setQuantite(Integer.parseInt(tfquantite.getText()));
        a.setPrix(Integer.parseInt(tfprixaliment.getText()));
        a.setIdpromo(Integer.parseInt(idpromotion_aliment.getValue()));
        a.setType(tftypealiment.getText());
        a.setMarque(tfmarquealiment.getText());

        sr.ModifierAliment(id, a);
        showaliment2();

    }

    private void afficher_aliment(ActionEvent event) {
        ServiceAliment aa = new ServiceAliment();
        Aliment a = new Aliment();
        try {

            System.out.println(aa.afficherAliment().toString());
            Affiche.setText(aa.afficherAliment().toString());
             tfidaliment.clear();
                 tfquantite.clear();
                  tfprixaliment.clear();
                //   tfidp.clear();
                    tftypealiment.clear();
                     tfmarquealiment.clear();
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
    
    @FXML
    private void Suivant(ActionEvent event) throws IOException {
      /*  Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("metiers.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();*/
      AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_aliment/metiers.fxml"));
         parent.getChildren().setAll(pane);
        
    }

    @FXML
    private void handleColse(MouseEvent event) {
    }

}
