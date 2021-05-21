/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.zied;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.zied.Article;
import com.mycompany.myapp.entities.zied.Reservation;
import com.mycompany.myapp.entities.zied.clinique;
import com.mycompany.myapp.entities.zied.intervention;
import com.mycompany.myapp.entities.zied.medecin;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ZIED
 */
public class ServiceArticle {
    
    public ArrayList<Article> restab;
            private  double[] statvalues ;
    private String[] statTitles;

    public static ServiceArticle instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceArticle() {

        req = new ConnectionRequest();
    }

    public static ServiceArticle getInstance() {
        if (instance == null) {
            instance = new ServiceArticle();
        }
        return instance;
    }

    public ArrayList<Article> parseObjectifs(String jsonText) {
        try {
            restab = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ResListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ResListJson.get("root");
            System.out.println("ziedddd1");
            for (Map<String, Object> obj : list) {
                Article res = new Article();

//                float id = Float.parseFloat(obj.get("id").toString());
//                System.out.println("voici l'id" + id);
//                res.setId((int) id);
System.out.println("ziedddd");
                res.setTitre(obj.get("Titre").toString());
                res.setAuteur(obj.get("Auteur").toString());
                res.setContenu(obj.get("Contenu").toString());
                

                

                restab.add(res);
            }
        } catch (IOException ex) {

        }
        return restab;
    }

    public ArrayList<Article> getAllArticle() {
        String url = Statics.BASE_URL + "/ArticleJson";

        System.out.println("eeeee" + url);
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                restab = parseObjectifs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return restab;
    }
    
     /****/
    
    public double[] parseStatValues(String jsonText) {
        try {
            statvalues = new double[20];
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            int i=0;
            for (Map<String, Object> obj : list) {
              statvalues[i]=(double) Double.parseDouble(obj.get("i").toString());
            i++;
            }

        } catch (IOException ex) {
            ex.getMessage();
        }
        return statvalues;
    }

    public double[] getStatValues() {

        String url = "http://127.0.0.1:8000/API/StatMobile/blog";
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                statvalues = parseStatValues(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
                 System.out.print("values = = " + statvalues.toString());
return statvalues;

    }

    public String[] parseStatTitles(String jsonText) {
        try {
            statTitles = new String[20];
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            int i=0;
            for (Map<String, Object> obj : list) {
              statTitles[i]=obj.get("titre").toString();
            i++;
            }

        } catch (IOException ex) {
            ex.getMessage();
        }
        System.out.print("titre = = " + statTitles.toString());
        return statTitles;
    }

    public String[] getStatTitles() {

        String url = "http://127.0.0.1:8000/API/StatMobile/blog";
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                statTitles = parseStatTitles(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return statTitles;
    }

}
