/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SessionManager;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.mycompany.entities.User;
import java.util.Vector;

import com.mycompany.myapp.utils.Statics;
import static com.twilio.jwt.taskrouter.UrlUtils.tasks;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class ServiceUtilisateur {
    
    public static ServiceUtilisateur instance = null ; 
    String json;
    public static boolean resultOk = true ; 
          public ArrayList<User> tasks;
           public Map<String, Object> resultatCnx;
    private ConnectionRequest req ; 
    
    
     public static ServiceUtilisateur getInstance(){
  if(instance==null)
      instance =new ServiceUtilisateur();
  return instance;
  
  }
    public ServiceUtilisateur()
    {
    req =new ConnectionRequest();
    }
    
    //////////////////////////////////////////////////////////////////////////////////
    public void  signup ( TextField username , TextField email , TextField age , TextField genre , TextField password ,TextField pays,ComboBox <String> roles , Resources res ){
       
        
        
        
        String url =  Statics.BASE_URL+"/signup?name="+username.getText()+"&email="+email.getText().toString()
                +"&age="+age.getText().toString()+"&pays="+pays.getText().toString()+"&genre="+genre.getText().toString()+"&password="
                +password.getText().toString()+"&roles="+roles.getSelectedItem().toString(); 
        
        req.setUrl(url) ; 
        
        
         
     req.addResponseListener(
    (e)->{
    String str=new String(req.getResponseData());
    System.out.println("data=="+str);
    
    } );    
      
      NetworkManager.getInstance().addToQueueAndWait(req);
    } 
    /////////////////////////////////////////////////////////////////////////////////////
    
    ///////////////////////////////////////////////////////////////////
    public void signin( TextField username , TextField password , Resources rs) {
         String url =  Statics.BASE_URL+"/user/signin?name="+username.getText().toString()+"&password="+password.getText().toString() ; 
         req = new ConnectionRequest(url ,false) ;
                 
         req.setUrl(url) ; 
      req.addResponseListener(
    (e)->{
        JSONParser j = new JSONParser() ; 
        String json = new String(req.getResponseData()) + "" ; 
        
        try {
        if (json.equals("failed") || json.equals("password not found") ){
            Dialog.show("echec d'autentification" , "username ou mot de passe oublie ","ok" , null ) ; 
        } 
        else {
            System.out.println("data ==== "+json);
            Map <String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray())) ; 
            //Session  Service
                //float id = Float.parseFloat(user.get("id").toString());
           //  SessionManager.setId((int)Float.parseFloat(user.get("id").toString()));//jibt id ta3 user ly3ml login w sajltha fi session ta3i
          
                SessionManager.setAge((int)Float.parseFloat(user.get("age").toString()));  
                SessionManager.setPassword(user.get("password").toString());
                SessionManager.setName(user.get("name").toString());
                SessionManager.setEmail(user.get("email").toString());
                 SessionManager.setPays(user.get("pays").toString());
                  SessionManager.setGenre(user.get("genre").toString());
            
                System.out.println("current user==>"+SessionManager.getEmail()+","+SessionManager.getGenre()+","+SessionManager.getName()+","+SessionManager.getPays()+","+SessionManager.getPassword());
                
           if(user.size() > 0) {
               new ProfileForm(rs).show() ; 
             } 
            
            
        }
        
        }
        catch(Exception ex){
        ex.printStackTrace() ; 
        }
        
    });
    
       NetworkManager.getInstance().addToQueueAndWait(req); 
    }
    ////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public String getPasswordByEmail(String email , Resources res){
        
      
        String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url,false);
        req.setUrl(url);
        req.addResponseListener((e)->{
        JSONParser j= new JSONParser();
       json = new String (req.getResponseData()+"");
        try{
        System.out.println("data =="+json);
        Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()) );
        
        }catch(Exception ex)
        {  
            ex.printStackTrace();        }
            
        }
        
        
        
        );
        NetworkManager.getInstance().addToQueueAndWait(req); 
        return json;
    } public ArrayList<User> parseTasks1(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                User f = new User();
//                Date date = new Date();
//               
//              f.setDate((java.sql.Date) date);
                float num = Float.parseFloat(obj.get("id").toString());
                f.setId((int)num);
 float age = Float.parseFloat(obj.get("age").toString());
                f.setAge((int)age);
                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(f);
            } 
        } catch (IOException ex) {
        }
        return tasks;
    }
    ///////////////////////////////////////////////////////////////////////////////:
    
    ///////////////////////////////////////////////////////////////////////////////////
      public ArrayList<User> getAllTasks(){
        String url = Statics.BASE_URL +"/AfficherUsers";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks1(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;

    }
}