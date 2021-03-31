/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oussama
 */
public class TopmoviesController implements Initializable {

    @FXML
    private AnchorPane paneMedia;
    @FXML
    private MediaView mv;
    @FXML
    private Button btnstop;
    @FXML
    private Button btnplay;
MediaPlayer mp;
 Media me;
    @FXML
    private Button btnBack;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String path = new File("C:\\Users\\oussama\\Documents\\NetBeansProjects\\Ticket\\src\\video\\top.mp4").getAbsolutePath();
me = new Media(new File(path).toURI().toString());
mp = new MediaPlayer(me);
mv.setMediaPlayer(mp);
DoubleProperty width = mv.fitWidthProperty();
DoubleProperty Height = mv.fitHeightProperty();
width.bind(Bindings.selectDouble(mv.sceneProperty(),"width"));
Height.bind(Bindings.selectDouble(mv.sceneProperty(),"height"));
mp.setAutoPlay(true);
    }    

    @FXML
    private void afficherlist(MouseEvent event) {
    }

     @FXML
    private void onClick_btnstop(){ 
        mp.stop();
    }
   @FXML
    private void onClick_btnplay(){ 
        mp.stop();
        if(mp.getStatus()==PLAYING){
            mp.stop(); mp.play();
        }
        else{ 
            mp.play();
        }
    }
      @FXML
   public void handlebtnBack() throws IOException {
      //  paneMediaShow();
  Parent root = FXMLLoader.load(getClass().getResource("/gui/film.fxml"));
        
         Stage window =(Stage)btnBack.getScene().getWindow();
         window.setScene(new Scene(root));
                 mp.stop();

    //  mp.setAutoPlay(true);
    }

}
