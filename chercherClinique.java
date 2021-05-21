/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiyassin;


import Entitiesyassin.clinique;
import ServiceClinique.cliniqueService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.util.Resources;
import tn.esprit.MyApplication;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class chercherClinique extends Form{
     Resources theme;
   String chercher;
    private ContainerList listContainer;

    public chercherClinique(Resources theme, String chercher) {
        this.theme = theme;
       
        this.chercher=chercher; 
        
        addGui();
        addAction();
    }
       private void addGui() {
       listContainer = new ContainerList();
        listContainer.setScrollableY(false);
        listContainer.setScrollVisible(true);
            cliniqueService ms = new cliniqueService();
ArrayList<clinique> allclq =ms.getclq();

        for (clinique m : allclq){
       //AFFICHER Image
            EncodedImage placeHolder = EncodedImage.createFromImage(MyApplication.theme.getImage("placeholder-image.png"), false);
            String url = "http://127.0.0.1:8000/uploads/" + m.getImg();
            Image imag = URLImage.createToStorage(placeHolder, url, url);
            ImageViewer image = new ImageViewer(imag);
           if (m.getNom().equals(chercher)){  
 Container c = new Container();
       c.setLayout(BoxLayout.y());
       
       c.add(image);
       c.add(new SpanLabel("nom : " + m.getNom()));
       c.add(new SpanLabel("adresse : " + m.getAdresse()));
       c.add(new SpanLabel("numero  : " + m.getTel()));
       c.add(new SpanLabel("Email  : " + m.getEmail()));
       c.add(new SpanLabel("Speicalité : " + m.getSpecialite()));
       
       listContainer.add(c);
       }
   
        } 
   this.add(listContainer);
   }
     private void addAction() {
     
     
  }
    
    
    
}
