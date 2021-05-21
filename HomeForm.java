/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.linda;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
import com.mycompany.myapp.services.ServiceRendezVous;

import java.util.List;

/**
 *
 * @author bhk
 */
public class HomeForm extends BaseForm {

    Form current;
    private Resources theme;
    
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

    public HomeForm(Resources res) {
        
                


        
        
        current = this; //Récupération de l'interface(Form) en cours
        theme = UIManager.initFirstTheme("/theme_1");
        
      Toolbar tb =new Toolbar();
        setToolbar(tb);
      //  getTitleArea().setUIID("container");
             setTitle("Rendez Vous");
             getContentPane().setScrollVisible(false);
                            super.addSideMenu(res);

             
        Label backimg = new Label(theme.getImage("back.png"));
              add(backimg);
        add(new Label("Choose an option"));



    
        setLayout(BoxLayout.y());
        Button btnAddRes = new Button("Ajouter Rendez-vous");
        Button btnListTasks = new Button("List Des Rendez-vous");
          Button btnStat = new Button("Statistique");
            btnStat.addActionListener(e -> {
//            StatRes sat = new StatRes();
//            sat.execute(theme).show();
        com.mycompany.myapp.gui.linda.StatForm st= new com.mycompany.myapp.gui.linda.StatForm();
           st.createPieChartForm().show();
        });
        
        
        btnAddRes.addActionListener(e -> new AddResForm(res).show());
         btnListTasks.addActionListener(e -> new ShowlistResForm(current).show());
        addAll(btnAddRes, btnListTasks,btnStat);

    }
    

}
