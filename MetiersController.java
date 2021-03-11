/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_aliment;

import Entities.Aliment;
import Utils.Maconnexion;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author 21624
 */
public class MetiersController implements Initializable {

    @FXML
    private BarChart<String,Integer> stat;
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
    private TableColumn<Aliment,Integer> marque;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button precedent;
    @FXML
    private AnchorPane fils;
    ObservableList<Aliment> alimentsList = FXCollections.observableArrayList();
     ObservableList<Aliment> alimentsList2;
    @FXML
    private Button Btn_imprimer;
    @FXML
    private Button Btn_supprimer;
    
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
        // TODO
        showaliment();
        barchar();
        
    }    

    @FXML
    private void precedent(ActionEvent event) throws IOException {
       /* Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();*/
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestion_aliment/FXMLDocument.fxml"));
         fils.getChildren().setAll(pane);
         
    }
    public void barchar()
    {
        String query="select marque,quantite FROM aliment ORDER BY   quantite";
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
try 
{
Connection cnx = Maconnexion.getInstance().getConnection();
 ResultSet rss=cnx.createStatement().executeQuery(query);
while (rss.next())
{
series.getData().add(new XYChart.Data<>(rss.getString(1), rss.getInt(2)));
}

stat.getData().add(series);
}
catch (SQLException ex) {
            Logger.getLogger(MetiersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
public void pdf()
     {
 
try {

         

            
        String file_nname="E:\\Test.pdf";
        Document document = new Document();
 
        



         

            PdfWriter.getInstance(document, new FileOutputStream(file_nname));


            Paragraph para=new Paragraph("test test");
            document.add(para);
            document.close();


        } catch (Exception e) {


            e.printStackTrace();


     }
}
   
		  public   String getPrix() throws SQLException
    {
        String x="";
         Connection cnx = Maconnexion.getInstance().getConnection();     
            Statement st;
            st = cnx.createStatement();
        String SQL2 = "SELECT prix FROM aliment";
          ResultSet rs2 = st.executeQuery(SQL2);
          
          if(rs2.next())
          {
              x = rs2.getString(1);
              
    }
          return x;   
          
}
    private void imprimer(ActionEvent event) throws IOException  {
        pdf();
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
       
                 
      
     
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         showaliment2();
    }
    
        
    }
    

