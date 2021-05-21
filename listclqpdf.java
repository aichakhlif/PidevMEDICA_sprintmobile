/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiyassin;

import Entitiesyassin.clinique;
import ServiceClinique.cliniqueService;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;

import com.codename1.ui.Form;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;




/**
 *
 * @author Asus
 */
public class listclqpdf extends Form {
  
  public listclqpdf() {
        setTitle("Liste des cliniques");
        
        //this.theme=theme;
        SpanLabel sp = new SpanLabel();
        
        sp.setText(cliniqueService.getInstance().getclq().toString());
        add(sp);
            //// f twig 
    Button pdf=new Button("pdf");
 add(pdf);  
  pdf.addActionListener((ActionListener) (ActionEvent evt) -> {
      String path="";
      
      Document document = new Document();
      try
      {
         PdfWriter   writer   = null; 
          
          try {
         writer = PdfWriter.getInstance(document, new FileOutputStream(path+"clinique.pdf"));
          } catch (FileNotFoundException ex) {
          }
          document.open();
          PdfPTable tb1 = new PdfPTable(5);
          tb1.addCell("Adresse");
          tb1.addCell("Numéro");
          tb1.addCell("Nom");
        
          //tb1.addCell("img");
          tb1.addCell("Specialite");
          tb1.addCell("Email");
          
          cliniqueService es = new cliniqueService() ;
          ArrayList<clinique> list = es.getclq();
          for (clinique m : list) {
            
              String adresse= String.valueOf(m.getAdresse());
              String tel= String.valueOf(m.getTel());
              String nom= String.valueOf(m.getNom());
             
              String spec= String.valueOf(m.getSpecialite());
              String email= String.valueOf(m.getEmail());
              
              
              
              tb1.addCell(adresse);
              tb1.addCell(tel);
              tb1.addCell(nom);
              //tb1.addCell(image);
              tb1.addCell(spec);
              tb1.addCell(email);
              
              
              
              
              
          }
            document.add(new Paragraph("Clinique"));
          
          document.add(tb1);
         document.close();
//         writer.close();
          com.codename1.io.File file=new com.codename1.io.File("clinique.pdf");
         
//          desktop.open(file);
      }catch (DocumentException e)
      {
          e.printStackTrace();
      }
      //Logger.getLogger(ListFormation.class.getName()).log(Level.SEVERE, null, ex);
      
      //Logger.getLogger(ListFormation.class.getName()).log(Level.SEVERE, null, ex);
        });
        
        
       //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}

      

