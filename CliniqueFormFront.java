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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
import com.mycompany.gui.ProfileForm;
import com.mycompany.myapp.gui.HomeForm;
import tn.esprit.MyApplication;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class CliniqueFormFront extends BaseForm {
    private ContainerList listContainer;
 private TextField chercher;
 private Button chercherbutton;
 Resources theme;
private Button Listpdf;
    public CliniqueFormFront(Resources res) {
        this.theme = theme;
         this.setLayout(BoxLayout.y());
         
           addGui(res);
        addAction();
       pdf();
    } 
   
     private void addGui(Resources res) {
         
         setTitle("Clinique");
                                    super.addSideMenu(res);
        chercher = new TextField("","Veuillez entrer un nom pour chercher une clinique");
        Listpdf = new Button("PDF");
        chercherbutton = new Button("Chercher");
        Container chercherContianer = new Container();
        chercherContianer.setLayout(BoxLayout.y());
        chercherContianer.addAll(chercher,chercherbutton,Listpdf);
        this.add(chercherContianer);     
        listContainer = new ContainerList();
        listContainer.setScrollableY(false);
        listContainer.setScrollVisible(true);
            cliniqueService ms = new cliniqueService();
ArrayList<clinique> allclq = ms.getclq();
        for (clinique m : allclq){
       //AFFICHER Image
            EncodedImage placeHolder = EncodedImage.createFromImage(MyApplication.theme.getImage("placeholder-image.png"), false);
            String url = "http://127.0.0.1:8000/uploads/" + m.getImg();
            Image imag = URLImage.createToStorage(placeHolder, url, url);
            ImageViewer image = new ImageViewer(imag);
 Container c = new Container();
       c.setLayout(BoxLayout.y());
       c.add(image);
       c.add(new SpanLabel("nom : " + m.getNom()));
       c.add(new SpanLabel("adresse : " + m.getAdresse()));
       c.add(new SpanLabel("numero  : " + m.getTel()));
       c.add(new SpanLabel("Email  : " + m.getEmail()));
       c.add(new SpanLabel("Specialité : " + m.getSpecialite()));
       
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
         Form f= new  chercherClinique(theme,chercher.getText());
         f.show();
     });
     }
      private void pdf() {
     Listpdf.addActionListener((evt)->{
         Form f= new  listclqpdf();
         f.show();
     });}
}
