/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.linda;

import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.myapp.entities.rendez_vous;
import com.mycompany.myapp.services.ServiceRendezVous;


/**
 *
 * @author admin
 */
public class DetailsResForm extends Form {

    Form current;

    public DetailsResForm() {

    }

    public DetailsResForm(Form previous, rendez_vous e) {
   

        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
      


//Message m = new Message("Body of message");
//
//Display.getInstance().sendMessage(new String[] {"aicha.khlif@esprit.tn"}, "hello", m);
        
        setTitle("Details du rendez-vous ");
        
        SpanLabel lbdate = new SpanLabel();
        Label lbheure = new Label();
        Label lbm = new Label();
        


        System.out.println("\n");
        Button btnSuppRes = new Button("Supprimer ce rendez-vous");

        btnSuppRes.addActionListener(p -> ServiceRendezVous.getInstance().SupprimerRes(e));

        addAll(lbdate,lbheure,lbm,btnSuppRes);

        lbdate.setText("date :" + e.getDate());
        lbheure.setText("heure :" + e.getHeure());
        lbm.setText("Dr :" + e.getMed().getNommed());
       


        show();

        getToolbar().addCommandToLeftBar("back", null, ev -> {
            previous.show();
        });

    }

    DetailsResForm(Form current, ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
