/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.util.List;
import com.codename1.ui.events.ActionListener;
import entities.SimpleUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author mouad
 */
public class ServiceSimpleUser {
    
    public boolean resultOK;
    private ConnectionRequest req;
    public static ServiceSimpleUser instance=null;
    public ArrayList<SimpleUser> listall;
    public ArrayList<SimpleUser> tasks;
     
    public ServiceSimpleUser() {
         req = new ConnectionRequest();
    }
        public static ServiceSimpleUser getInstance() {
        if (instance == null) {
            instance = new ServiceSimpleUser();
        }
        return instance;
    }
    public boolean addSimpleUser(SimpleUser su){
        String url=Statics.BASE_URL+"/addSimpleUserMobile?nom="+su.getNom()+"&prenom="+su.getPrenom()+"&cin="+su.getCin()+"&mail="+su.getMail()+"&mdp="+su.getMdp();
      /*ConnectionRequest req= new ConnectionRequest(url);
        req.addResponseCodeListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK=req.getResponseCode()==200;
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        */
      req.setUrl(url);
        System.out.println(url);
        req.addResponseListener((ex) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      
        String str = new String(req.getResponseData());
        if(str.equals("Invalid Email!"))
            return false;
        else
            return true;
    }
    
    
    public ArrayList<SimpleUser> listAll(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               SimpleUser t = new SimpleUser();
         
                t.setId(Integer.parseInt(obj.get("id").toString()));
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
                t.setCin(Integer.parseInt(obj.get("cin").toString()));
                t.setMail(obj.get("mail").toString());
                t.setMdp(obj.get("mdp").toString());
                t.setBlock(obj.get("block").toString());
                t.setCodem(obj.get("codem").toString());
                tasks.add(t);

            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
       public ArrayList<SimpleUser> getListalls(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL +"/AllSimpleUsersMobile");
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceSimpleUser ser = new ServiceSimpleUser();
                listall = ser.listAll(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listall;
    }
      /*public boolean supprimer(String id){
        
        String url = statics.BASE_URL+"/remove/"+id;
        
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        
        return resultOK;
    }*/

        public ArrayList<SimpleUser> findUser(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               SimpleUser t = new SimpleUser();
         
                t.setMail(obj.get("mail").toString());
                t.setMdp(obj.get("mdp").toString());
               
                tasks.add(t);

            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
        
        
       public boolean login(SimpleUser su)
       {
       String url=Statics.BASE_URL+"/loginSimpleUserMobile?mail="+su.getMail()+"&mdp="+su.getMdp();
       req.setUrl(url);
        System.out.println(url);
        req.addResponseListener((ex) -> {
           
            String str = new String(req.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);   
       //SimpleUser test=new SimpleUser();
       //test.setMail(.get("mail"));
          
           String str = new String(req.getResponseData());
        
       if ((str.equals("Blocked Account!"))) {return false;}        
       else if((str.equals("Invalid Password!"))){return false;}
       else if((str.equals("SimpleUser not found!"))){return false;}
       else{return true;}
       }
    
        public String ShowMe()
       {
       String url=Statics.BASE_URL+"/SimpleUserMobile";
       req.setUrl(url);
        System.out.println(url);
        req.addResponseListener((ex) -> {
           
            String str = new String(req.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);   
          String str = new String(req.getResponseData());
           return str;
       }
        
        public boolean editSimpleUser(SimpleUser su){
        String url=Statics.BASE_URL+"/EditSimpleUsersMobile?nom="+su.getNom()+"&prenom="+su.getPrenom()+"&cin="+su.getCin()+"&mail="+su.getMail()+"&mdp="+su.getMdp();
        req.setUrl(url);
        System.out.println(url);
        req.addResponseListener((ex) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        String str = new String(req.getResponseData());
        if(str.equals("Invalid Email!"))
            return false;
        else
            return true;
    }
}
