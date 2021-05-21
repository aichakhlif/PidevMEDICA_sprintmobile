/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.medecin;
import ServiceMedecin.MedecinService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
import com.mycompany.gui.ProfileForm;
import com.mycompany.myapp.gui.HomeForm;
import com.mycompany.myapp.gui.ShowlistResForm;
import com.mycompany.myapp.gui.StatRes;
import tn.esprit.MyApplication;
import java.util.ArrayList;
import java.util.List;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 *
 * @author BENJOU
 */
public class MedecinFormFront extends BaseForm {
 private ContainerList listContainer;
 private TextField chercher;
 private Button chercherbutton;
 Form current;
 Resources theme;
    public MedecinFormFront(Resources res) {
        this.theme=theme;
        this.setLayout(BoxLayout.y());
                    


        addGui(res);
        addAction();

      
    }

    private void addGui(Resources res) {
                     setTitle("Medecin");
                                    super.addSideMenu(res);
           Button btnStatM = new Button("Stats");
            btnStatM.addActionListener(e -> {
      StatForm st= new StatForm();
           st.createPieChartForm().show();
   
        });

addAll(btnStatM);

// getToolbar().addCommandToLeftSideMenu("Stat",null,(evt)->{
//           StatForm st= new StatForm();
//           st.createPieChartForm().show();
//           
//       });     


        chercher = new TextField("","chercher un medecin");
        chercherbutton = new Button("chercher");
        Container chercherContianer = new Container();
        chercherContianer.setLayout(BoxLayout.y());
        chercherContianer.addAll(chercher,chercherbutton);
        this.add(chercherContianer);     
        listContainer = new ContainerList();
        listContainer.setScrollableY(false);
        listContainer.setScrollVisible(true);
            MedecinService ms = new MedecinService();

     ArrayList<medecin> allmedecins = ms.getMedecins();
        for (medecin m : allmedecins){
       //AFFICHER Image
            EncodedImage placeHolder = EncodedImage.createFromImage(MyApplication.theme.getImage("placeholder-image.png"), false);
            String url = "http://127.0.0.1:8000/Uploads/Image/" + m.getPic();
            Image img = URLImage.createToStorage(placeHolder, url, url);
            ImageViewer image = new ImageViewer(img);
       //End Afficher Image
       Container c = new Container();
       c.setLayout(BoxLayout.y());
       c.add(image);
       c.add(new SpanLabel("nom : " + m.getNom()));
       c.add(new SpanLabel("prenom : " + m.getPrenom()));
       c.add(new SpanLabel("numero  : " + m.getNum()));
       c.add(new SpanLabel("Email  : " + m.getEmail()));
       c.add(new SpanLabel("List speicalites : " + m.getListspec()));
       Button contact = new Button("contacter");
       c.add(contact);
       contact.addActionListener((evt)->{
       Message med = new Message("Body of message");
       Display.getInstance().sendMessage(new String[]{m.getEmail()}, "Subject of message", med);    
       });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
               ,(evt)->{
           Form show = new ProfileForm(res);
        show.show();
       });
       listContainer.add(c);
       
   
   }
   this.add(listContainer);
   }

    private void addAction() {
     chercherbutton.addActionListener((evt)->{
         Form f= new  chercherMedecin(theme,chercher.getText());
         f.show();
     });
     
    
    }
    
    
 
}
