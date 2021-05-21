/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.ServiceReservation;
import tn.esprit.MyApplication;
import java.util.ArrayList;

/**
 *
 * @author BENJOU
 */
public class chercherRes extends Form {
    Form current;
    Resources theme;
    String chercher;
    private ContainerList listContainer;


//    public chercherRes(Resources theme, String chercher) {
//       
//       // addGui();
//    
//
//    }

    
    public chercherRes(Form previous,String chercher) {
         this.theme = theme;
        this.setLayout(BoxLayout.y());
        this.chercher = chercher;
        setTitle("Resultat de la recherche : ");

        
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

          listContainer = new ContainerList();
        listContainer.setScrollableY(false);
        listContainer.setScrollVisible(true);
        ServiceReservation ms = new ServiceReservation();

        ArrayList<Reservation> allres = ms.getRes(chercher);
        for (Reservation m : allres) {

            Container c = new Container();
            c.setLayout(BoxLayout.y());

            c.add(new SpanLabel("nom : " + m.getNom()));
            c.add(new SpanLabel("email : " + m.getEmail()));
            c.add(new SpanLabel("pays  : " + m.getPays()));
            c.add(new SpanLabel("medecin  : " + m.getMed().getNommed()));
            c.add(new SpanLabel("intervention  : " + m.getInter().getType()));
            c.add(new SpanLabel("clinique  : " + m.getClinique().getNomclinique()));
            

            listContainer.add(c);

        }
        this.add(listContainer);
    }

        
    
    }

//    private void addGui() {
//
//        listContainer = new ContainerList();
//        listContainer.setScrollableY(false);
//        listContainer.setScrollVisible(true);
//        ServiceReservation ms = new ServiceReservation();
//
//        ArrayList<Reservation> allres = ms.getRes(chercher);
//        for (Reservation m : allres) {
//
//            Container c = new Container();
//            c.setLayout(BoxLayout.y());
//
//            c.add(new SpanLabel("nom : " + m.getNom()));
//            c.add(new SpanLabel("email : " + m.getEmail()));
//            c.add(new SpanLabel("pays  : " + m.getPays()));
//            c.add(new SpanLabel("medecin  : " + m.getMed().getNommed()));
//            c.add(new SpanLabel("intervention  : " + m.getInter().getType()));
//            c.add(new SpanLabel("clinique  : " + m.getClinique().getNomclinique()));
//            
//
//            listContainer.add(c);
//
//        }
//        this.add(listContainer);
//    }


