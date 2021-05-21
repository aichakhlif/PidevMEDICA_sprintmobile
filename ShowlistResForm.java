/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.linda;

import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.rendez_vous;
import com.mycompany.myapp.services.ServiceRendezVous;
import java.util.List;

/**
 *
 * @author admin
 */
public class ShowlistResForm extends Form {

    Form current;
    private TextField chercher;
        
 private Button chercherbutton;
  Resources theme;
  
 


    public ShowlistResForm(Form previous) {
      
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
getToolbar().addMaterialCommandToSideMenu("Mail", FontImage.MATERIAL_INFO, e -> {
         Message m = new Message("Body of message");
            Display.getInstance().sendMessage(new String[] {"aicha.khlif@esprit.tn"}, "", m);
    
        });
        setTitle("List tasks");
 
        
//                TextField recherche = new TextField();
//        add(recherche);
//        
//        
         

       // Button stat = new Button("statistique ");


        add(new Label("Voici votre liste des rendez-Vous:"));
        
         TextField chercher = new TextField("","chercher rendez-vous");
        Button chercherbutton = new Button("chercher");
        
        Container chercherContianer = new Container();
        chercherContianer.setLayout(BoxLayout.y());
        chercherContianer.addAll(chercher,chercherbutton);
        this.add(chercherContianer);     


        //Affichge de la liste des objectifs
        List<rendez_vous> list = ServiceRendezVous.getInstance().getAllRendezVous();
        for (rendez_vous e : list) {
                    current = this; //Récupération de l'interface(Form) en cours

            System.out.println(e);
            Container cnt1 = new Container(BoxLayout.y());
            Container cnt2 = new Container(BoxLayout.x());;

            SpanLabel lbdate = new SpanLabel("date:" + e.getDate());
            SpanLabel lbheure = new SpanLabel("heure :" + e.getHeure());
            //SpanLabel lbpays = new SpanLabel("pays :" + e.getPays());
            SpanLabel lbmed = new SpanLabel("Dr :" + e.getMed().getNommed());
           
                                Button btnDetails = new Button("Détails");



            cnt1.add(lbdate);
            cnt1.add(lbheure);
            
            cnt1.add(lbmed);
            
            cnt1.add(btnDetails);
            cnt2.add(cnt1);

//          cnt2.getAllStyles().setAlignment(Component.CENTER);
//
//         cnt2.getStyle().setBgColor(0xffffff);
//            
//            cnt2.getStyle().setBgTransparency(255);
//            cnt2.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
//            cnt2.getStyle().setMargin(Component.BOTTOM, 3);
//            
//            Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
//            cnt2.getStyle().setBorder(RoundBorder.create().
//                    rectangle(true).
//                    color(0xffffff).
//                    strokeColor(0).
//                    strokeOpacity(120)
//                  //  stroke(borderStroke)
//                    );
//            
            add(cnt2);
             btnDetails.addActionListener(p -> new DetailsResForm(current, e).show());
//             chercherbutton.addActionListener(p->{
//         Form f= new  chercherRes(theme,chercher.getText());
//         f.show();
//         });

         //   chercherbutton.addActionListener(p->new chercherRes(current,chercher.getText()).show());



        }


    }

    

}
