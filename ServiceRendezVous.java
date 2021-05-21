/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;

import com.mycompany.myapp.entities.medecin;
import com.mycompany.myapp.entities.rendez_vous;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class ServiceRendezVous {

    public ArrayList<rendez_vous> restab;
              private  double[] statvalues ;
    private String[] statTitles;

    public static ServiceRendezVous instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceRendezVous() {

        req = new ConnectionRequest();
    }

    public static ServiceRendezVous getInstance() {
        if (instance == null) {
            instance = new ServiceRendezVous();
        }
        return instance;
    }

    public ArrayList<rendez_vous> parseObjectifs(String jsonText) {
        try {
            restab = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ResListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ResListJson.get("root");

            for (Map<String, Object> obj : list) {
                rendez_vous res = new rendez_vous();

                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println("voici l'id" + id);
                res.setId((int) id);

                res.setDate(obj.get("date").toString());
                res.setHeure(obj.get("heure").toString());
                //res.setPays(obj.get("pays").toString());

                Map<String, Object> med = (Map<String, Object>) obj.get("Medecin");
                medecin mede = new medecin(med.get("nom").toString());
                res.setMed(mede);
                
              

                restab.add(res);
            }
        } catch (IOException ex) {

        }
        return restab;
    }

    public ArrayList<rendez_vous> getAllRendezVous() {
        String url = Statics.BASE_URL + "/listerv";

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

    ArrayList<medecin> listmed = new ArrayList<>();

    public ArrayList<medecin> getListmed2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "/medliste");
        con.addResponseListener((NetworkEvent evt) -> {
            ServiceMedecin ser = new ServiceMedecin();
            listmed = ser.getListMed(new String(con.getResponseData()));
            System.out.println("************************");
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listmed;
    }

   

    

    public boolean addRendezVous(rendez_vous t) {
        String url = Statics.BASE_URL + "/renadd?date=" + t.getDate() + "&heure=" + t.getHeure()+ "&medecin_id=" + t.getMed().getIdmed()  ;
        System.out.println(url);
                
                
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
    }

    public boolean SupprimerRes(rendez_vous t) {
        String url = Statics.BASE_URL + "/ressupp/" + t.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
                ToastBar.showMessage("Votre rendez-vous a été supprimé", FontImage.MATERIAL_ACCESS_TIME);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    
      ArrayList<rendez_vous> resss = new ArrayList<>();;

    public ArrayList<rendez_vous> getRes(String chercher) {
        String url = Statics.BASE_URL + "/recherche/" + chercher;
        System.out.println(url);
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                resss = parseObjectifs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resss;

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
              statvalues[i]=(double) Double.parseDouble(obj.get("o").toString());
            i++;
            }

        } catch (IOException ex) {
            ex.getMessage();
        }
        return statvalues;
    }

    public double[] getStatValues() {

        String url = "http://127.0.0.1:8000/API/StatMobile/rv";
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
              statTitles[i]=obj.get("nom").toString();
            i++;
            }

        } catch (IOException ex) {
            ex.getMessage();
        }
        System.out.print("nom = = " + statTitles.toString());
        return statTitles;
    }

    public String[] getStatTitles() {

        String url = "http://127.0.0.1:8000/API/StatMobile/rv";
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
