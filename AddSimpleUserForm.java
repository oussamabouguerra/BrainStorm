/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.SimpleUser;
import services.ServiceSimpleUser;

/**
 *
 * @author mouad
 */
public class AddSimpleUserForm extends Form{

    public AddSimpleUserForm(Form previous) {
        setTitle("New Simple User");
        setLayout(BoxLayout.y());
        
        TextField tfNom= new TextField("", "Nom");
        TextField tfPrenom= new TextField("", "Prenom");
        TextField tfCin= new TextField("", "CIN");
        TextField tfMail= new TextField("", "Mail");
        TextField tfMdp= new TextField("", "Password");
        
        Button btnValider=new Button("Add Simple User");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(tfNom.getText().length()==0)
                {
                    Dialog.show("Alert","Please fill all the fields",new Command("OK"));
                }
                else{
                    
                      
                       
                         SimpleUser su= new SimpleUser(Integer.parseInt(tfCin.getText()), tfNom.getText(), tfPrenom.getText(), tfMail.getText(), tfMdp.getText());   
                          System.out.println(su);
                         if (ServiceSimpleUser.getInstance().addSimpleUser(su))
                         { Dialog.show("Success","Ajout avec Succès!",new Command("OK"));
                          previous.showBack();}
                         else
                             Dialog.show("Error","Pas d'ajout!",new Command("OK"));
                    tfNom.setText("");
                    tfPrenom.setText("");
                    tfCin.setText("");
                    tfMail.setText("");
                    tfMdp.setText("");
                                 }
               
            }
        });
        addAll(tfNom, tfPrenom, tfCin, tfMail, tfMdp, btnValider);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
}
