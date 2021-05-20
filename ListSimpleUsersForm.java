/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import services.ServiceSimpleUser;

/**
 *
 * @author mouad
 */
public class ListSimpleUsersForm extends Form{
    
    private Resources theme;
    public ListSimpleUsersForm(Form previous) {
       
        setTitle("My account");
        setLayout(BoxLayout.y());
       
        SpanLabel lab=new SpanLabel("");
        lab.setText(ServiceSimpleUser.getInstance().ShowMe()); 
        
        addAll(lab);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
}
