/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceClinique;

import Entitiesyassin.clinique;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author Asus
 */
public class cliniqueService {
      public static cliniqueService instance;
     private ArrayList <clinique> cliniques ;
    public ArrayList<clinique> parseclq(String jsonText) {
        try {
            cliniques = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                clinique mdata = new clinique();
                mdata.setId((int) Float.parseFloat(obj.get("id").toString()));
                mdata.setAdresse(obj.get("adresse").toString());
                mdata.setTel((int)Float.parseFloat(obj.get("tel").toString()));
                mdata.setNom(obj.get("nom").toString());
                 mdata.setImg(obj.get("image").toString());
                  mdata.setSpecialite(obj.get("specialite").toString());
                mdata.setEmail(obj.get("email").toString());
               
              
               cliniques.add(mdata);
            }

        } catch (IOException ex) {
            ex.getMessage();
        }
        return cliniques;
    }
    public ArrayList<clinique> getclq() {

        String url = Statics.BASE_URL_Clinique + "/Afficherclq";
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cliniques = parseclq(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cliniques;
    }
    public ArrayList<clinique> getclq(String chercher) {
 String url = Statics.BASE_URL_Clinique + "/Afficherclq/"+chercher;
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cliniques = parseclq(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cliniques;

    }
     public static cliniqueService getInstance()
    {
        if(instance == null)
            instance= new cliniqueService();
        return   instance;
    }
}
