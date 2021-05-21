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
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Experience;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceExperience {
    public ArrayList<Experience> tasks;
    private ConnectionRequest req;
  public static ServiceExperience instance =null;
  public static ServiceExperience getInstance(){
  if(instance==null)
      instance =new ServiceExperience();
  return instance;
  
  }
    public ServiceExperience()
    {
    req =new ConnectionRequest();
    }
    public void ajoutexperience(Experience exp){
    String url=Statics.BASE_URL+"/AddExpJson?titre="+exp.getTitre()+"&description="+exp.getDescription()+"&endroit="+exp.getEndroit()+"&note="+exp.getNote();
    req.setUrl(url);
    req.addResponseListener(
    (e)->{
    String str=new String(req.getResponseData());
    System.out.println("data=="+str);
    
    } );
     NetworkManager.getInstance().addToQueueAndWait(req);
 }

public ArrayList<Experience> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Experience t = new Experience();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
               t.setEndroit(obj.get("endroit").toString());
               t.setTitre(obj.get("Titre").toString());
              t.setDescription(obj.get("Description").toString());
               t.setPoints((int) Float.parseFloat(obj.get("Points").toString()));
                 t.setNote((int) Float.parseFloat(obj.get("Note").toString()));
              t.setImage(obj.get("Image").toString());
                
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return tasks;
    }
 public ArrayList<Experience> getexp(){
        String url = Statics.BASE_URL+"/DisplayAll";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
}
