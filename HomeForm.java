/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;


/**
 *
 * @author mouad
 */
public class HomeForm extends Form{
Form current;
    public HomeForm(Form previous) {
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Reservation"));
      //  Button btnAddSU = new Button("Add Simple User");
        Button btnAddRes = new Button("Add Reservation");
     //   Button btnEditRes = new Button("Edit Reservation");
      //  Button btnDelRes = new Button("Delete Reservation");
        Button btnShowRes = new Button("Show Reservation(s)");
        Label L2=new Label("More options");
        Button btnListSU = new Button("My Account");
        Button btnEditSU = new Button("Edit my Account");
        Button btnDelSU = new Button("Delete my Account");
        Button btnMap = new Button("Google Map");
        
        
   //     btnAddSU.addActionListener(e-> new AddSimpleUserForm(current).show());
        btnListSU.addActionListener(e-> new ListSimpleUsersForm(current).show());
        btnEditSU.addActionListener(e-> new EditSimpleUserForm(current).show());
        btnMap.addActionListener(e-> new GoogleMapForm(current).show());
      //  addAll(btnAddRes,btnEditRes,btnDelRes,btnShowRes,L2,btnListSU,btnEditSU,btnDelSU);
      addAll(btnAddRes,btnShowRes,L2,btnListSU,btnEditSU,btnDelSU,btnMap);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());

    }
    
    
}
