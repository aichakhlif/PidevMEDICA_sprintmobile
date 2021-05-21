/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

/**
 *
 * @author hp
 */
import com.mycompany.entities.InformationsSupplementaires;
import com.mycompany.myapp.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.Map;
public class InformationsSupplementairesService {
    InformationsSupplementaires ProfilInfo;
      public InformationsSupplementaires parseInfo(String jsonText) {
         InformationsSupplementaires info = new InformationsSupplementaires();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            info.setId((int) Float.parseFloat(tasksListJson.get("id").toString()));
                        info.setUser_id((int) Float.parseFloat(tasksListJson.get("user_id").toString()));
                        info.setName(tasksListJson.get("name").toString());
                        info.setPays(tasksListJson.get("pays").toString());
                           info.setAge((int) Float.parseFloat(tasksListJson.get("age").toString()));
                        info.setEmail(tasksListJson.get("email").toString());

            return info;

        } catch (IOException ex) {

        }
        return null;
    }
       public InformationsSupplementaires getProfil(int id) {
        String url = Statics.BASE_URL + "/detailuser/" + id;
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
               //Users = parseUsers(new String(req.getResponseData()));
                ProfilInfo = parseInfo(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ProfilInfo;
    }
}
