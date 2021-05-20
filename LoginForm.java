/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
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
public class LoginForm extends Form{
   Form current;

    public LoginForm() {
        current=this;
        setTitle("Login");
        setLayout(BoxLayout.y());
        add(new Label("Enter your informations"));
        TextField tfMail= new TextField("", "Mail");
        TextField tfMdp= new TextField("", "Password",20,TextField.PASSWORD);
       // PasswordField tfMdp= new PasswordField(); 
        
        Button btnLogin=new Button("Login");
        Button btnAddSU = new Button("New Simple User");
       
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                       
                         SimpleUser su= new SimpleUser(tfMail.getText(), tfMdp.getText());   
                          System.out.println(su);
                         if (ServiceSimpleUser.getInstance().login(su))
                             new HomeForm(current).show();
                         else
                             Dialog.show("Error","Connexion not found!",new Command("OK"));
        tfMail.setText("");
        tfMdp.setText("");
                                 
               
            }
        });
        btnAddSU.addActionListener(e-> new AddSimpleUserForm(current).show());
      
       // current.add(tfMail).add(tfMdp);
        addAll(tfMail,tfMdp,btnLogin,btnAddSU);
    }
   
}
