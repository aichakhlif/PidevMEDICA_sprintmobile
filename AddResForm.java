/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.linda;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Stroke;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
import com.mycompany.myapp.entities.medecin;
import com.mycompany.myapp.entities.rendez_vous;
import com.mycompany.myapp.services.ServiceRendezVous;

import java.util.ArrayList;
import javafx.scene.control.ToolBar;

/**
 *
 * @author admin
 */
public class AddResForm extends BaseForm {
    
        private Resources theme;
                Form current;

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    
        public AddResForm(Resources res) {
          
              setTitle("Add a new RendezVous");
        setLayout(BoxLayout.y());
          current = this; //Récupération de l'interface(Form) en cours
        theme = UIManager.initFirstTheme("/theme_1");
                Label backimg = new Label(theme.getImage("back2.png"));
                        add(backimg);



        Toolbar tb = new Toolbar();
        setToolbar(tb);
        //  getTitleArea().setUIID("container");
        setTitle("Resevation");
        getContentPane().setScrollVisible(false);
        
               super.addSideMenu(res);

        
        
        
         Label linfo = new Label("Info personnelle :", "Container");
         linfo.getAllStyles().setAlignment(Component.CENTER);


        TextField tfdate = new TextField("","date");
  
        TextField tfheure= new TextField("", "heure");
       
        
        
          Label lmed = new Label("choisir votre medecin :", "Container");
                   lmed.getAllStyles().setAlignment(Component.CENTER);
                  ComboBox  cmb = new ComboBox<>();
                  
      
                  
          ArrayList<medecin> med = new ArrayList<>();
          
                  
 
        ServiceRendezVous ad = new ServiceRendezVous();
        
        med.addAll(ad.getListmed2());
          for (medecin object : med) {
            cmb.addItem(object.getNommed());
        }

          
           
            
                 /**style**/
        Style tfnomStyle = tfdate.getAllStyles();
       Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
       tfnomStyle.setBorder(RoundRectBorder.create().
        strokeColor(0).
        strokeOpacity(120).
        stroke(borderStroke));
tfnomStyle.setBgColor(0xffffff);
tfnomStyle.setBgTransparency(255);
tfnomStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
tfnomStyle.setMargin(Component.BOTTOM, 3);
/**/
   Style tfemailStyle = tfheure.getAllStyles();
       tfemailStyle.setBorder(RoundRectBorder.create().
        strokeColor(0).
        strokeOpacity(120).
        stroke(borderStroke));
tfemailStyle.setBgColor(0xffffff);
tfemailStyle.setBgTransparency(255);
tfemailStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
tfemailStyle.setMargin(Component.BOTTOM, 3);
/**/
  
   Style cmbStyle = cmb.getAllStyles();
       cmbStyle.setBorder(RoundRectBorder.create().
        strokeColor(0).
        strokeOpacity(120).
        stroke(borderStroke));
cmbStyle.setBgColor(0xffffff);
cmbStyle.setBgTransparency(255);
cmbStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
cmbStyle.setMargin(Component.BOTTOM, 3);
        /*********/

        Button btnValider = new Button("Add Rendez-vous");
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfdate.getText().length()==0)||(tfheure.getText().length()==0))
                    Dialog.show("Alerte", "Veuillez remplir tous les champs", new Command("OK"));
                else
                {
                    try {
                        rendez_vous t = new rendez_vous();
                        t.setDate(tfdate.getText());
                        t.setHeure(tfheure.getText());
                       // t.setEtat(0);

                        medecin m = med.get(cmb.getSelectedIndex());
                         System.out.println("aaaaaaaaaaaaaassssss");
                        System.out.println(m);
                        t.setMed(m);
                        



                        if( ServiceRendezVous.getInstance().addRendezVous(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
          
                }
                            
            }

                
        });
        /*********************/
        
         addAll(linfo,tfdate,tfheure,cmb,btnValider);
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
               ,(evt)->{
           Form show = new com.mycompany.myapp.gui.linda.HomeForm(res);
        show.show();
       });
            
        }


        
        
 
}
