/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.entities.clinique;
import com.mycompany.myapp.entities.intervention;

import com.mycompany.myapp.entities.medecin;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class ServiceReservation {

    public ArrayList<Reservation> restab;
        private  double[] statvalues ;
    private String[] statTitles;
    

    public static ServiceReservation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceReservation() {

        req = new ConnectionRequest();
    }

    public static ServiceReservation getInstance() {
        if (instance == null) {
            instance = new ServiceReservation();
        }
        return instance;
    }

    public ArrayList<Reservation> parseObjectifs(String jsonText) {
        try {
            restab = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ResListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ResListJson.get("root");

            for (Map<String, Object> obj : list) {
                Reservation res = new Reservation();

                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println("voici l'id" + id);
                res.setIdres((int) id);

                res.setNom(obj.get("nom").toString());
                res.setEmail(obj.get("email").toString());
                res.setPays(obj.get("pays").toString());

                Map<String, Object> med = (Map<String, Object>) obj.get("medecin");
                medecin mede = new medecin(med.get("nom").toString());
                res.setMed(mede);
                
                Map<String, Object> cli = (Map<String, Object>) obj.get("clinique");
                clinique clin = new clinique(cli.get("nom").toString());
                res.setClinique(clin);

                Map<String, Object> inte = (Map<String, Object>) obj.get("intervention");
                intervention inter = new intervention(inte.get("type").toString());
                res.setInter(inter);

                restab.add(res);
            }
        } catch (IOException ex) {

        }
        return restab;
    }

    public ArrayList<Reservation> getAllReservation() {
        String url = Statics.BASE_URL + "/resliste";

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

    ArrayList<clinique> listecli = new ArrayList<>();

    public ArrayList<clinique> getListcli2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "/cliliste");
        con.addResponseListener((NetworkEvent evt) -> {
            ServiceClinique ser = new ServiceClinique();
            listecli = ser.getListClinique(new String(con.getResponseData()));
            System.out.println("************************");
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listecli;
    }

    ArrayList<intervention> listeinter = new ArrayList<>();

    public ArrayList<intervention> getListinter2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "/interliste");
        con.addResponseListener((NetworkEvent evt) -> {
            ServiceIntervention ser = new ServiceIntervention();
            listeinter = ser.getListInter(new String(con.getResponseData()));
            System.out.println("************************");
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listeinter;
    }

    public boolean addReservation(Reservation t) {
        String url = Statics.BASE_URL + "/resadd?nom=" + t.getNom() + "&email=" + t.getEmail() + "&pays=" + t.getPays() + "&medecin_id=" + t.getMed().getIdmed() + "&clinique_id="
                + t.getClinique().getIdclinique() + "&intervention_id=" + t.getInter().getIdinter();
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

    public boolean SupprimerRes(Reservation t) {
        String url = Statics.BASE_URL + "/ressuppo/" + t.getIdres();
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

    
      ArrayList<Reservation> resss = new ArrayList<>();;

    public ArrayList<Reservation> getRes(String chercher) {
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

    public void SupprimerRes(com.mycompany.myapp.entities.zied.Reservation e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
              statvalues[i]=(double) Double.parseDouble(obj.get("mb").toString());
            i++;
            }

        } catch (IOException ex) {
            ex.getMessage();
        }
        return statvalues;
    }

    public double[] getStatValues() {

        String url = "http://127.0.0.1:8000/API/StatMobile/res";
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

        String url = "http://127.0.0.1:8000/API/StatMobile/res";
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
