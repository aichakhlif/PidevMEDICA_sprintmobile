/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.plaf.Border;
import com.mycompany.entities.Experience;
import com.mycompany.service.ServiceExperience;
import static java.lang.Math.round;
import java.util.ArrayList;

/**
 *
 * @author Administrateur
 */
public class Listpubform extends BaseForm{
    Form current;
   public Listpubform(Resources res){
     super("Newsfeed",BoxLayout.y());
    Toolbar tb= new Toolbar(true);
    current=this;
            setToolbar(tb);
            getTitleArea().setUIID("Container");
            setTitle("Ajout Publication");
            getContentPane().setScrollVisible(false);
            
            tb.addSearchCommand(e-> {
  
            });
             Tabs swipe=new Tabs();
             Label s1=new Label();
              Label s2=new Label();      
              addTab(swipe,s1,res.getImage("back.png"),"","",res)    ;  
              ///////////////////////
               swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Forum", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajouter publication", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
       ////////////////////////////////////////////////////
       
      
    ArrayList<Experience>list=ServiceExperience.getInstance().getexp();
    for(Experience e : list){
String urlImage="back.png";
        Image placeHolder =Image.createImage(120,90);
        EncodedImage enc=EncodedImage.createFromImage(placeHolder,false);
        URLImage urlim=URLImage.createToStorage(enc, urlImage, urlImage,URLImage.RESIZE_SCALE);
        addButton(urlim,e.getTitre(),e.getDescription(),e);
    ScaleImageLabel image = new ScaleImageLabel(urlim);
    Container containerImg= new Container();
    image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
    }
    }
    
     
    public void bindButtonSelection(Button btn, Label l){    
    btn.addActionListener(e->{
    if(btn.isSelected()){
    
    updateArrowPosition(btn,l);
    }
    });
   }
    public void updateArrowPosition(Button btn,Label l){
    l.getUnselectedStyle().setMargin(LEFT,btn.getX()+btn.getWidth() /2 -l.getWidth()/2);
l.getParent().repaint();    
    }  
     private void addTab(Tabs swipe,Label spacer, Image image, String string, String text, Resources res) {
      int size=Math.min(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight());
    if(image.getHeight()<size){
    image=image.scaledHeight(size);
    }
    if(image.getHeight()>Display.getInstance().getDisplayHeight()/2){
    image=image.scaledHeight(Display.getInstance().getDisplayHeight()/2);
            }
    
    ScaleImageLabel imageScale= new ScaleImageLabel(image);
    imageScale.setUIID("Container");
    imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
    Label overLay=new Label("","ImageOverlay");
    Container page1=
            LayeredLayout.encloseIn(imageScale,overLay,BorderLayout.south(BoxLayout.encloseY(new SpanLabel(text,"LargeWhiteText")
                   
                    ,spacer))
            
            );
            
swipe.addTab("", res.getImage("back-logo.png"),page1);
    
    }

    private void addButton(Image img, String titre,String description, Experience e) {
        int height=Display.getInstance().convertToPixels(11.5f);
        int width=Display.getInstance().convertToPixels(14f);
        Button image= new Button(img.fill(width,height));
        image.setUIID("Label");
        Container cnt=BorderLayout.west(image);
        TextArea ta= new TextArea("titre:"+titre);
        TextArea tb= new TextArea("description:"+description);
        TextArea tc= new TextArea("endroit"+e.getEndroit());
        ta.setUIID("NewsTopLine");
         tb.setUIID("NewsTopLine");
          tc.setUIID("NewsTopLine");
        ta.setEditable(false);
tb.setEditable(false);
tc.setEditable(false);
 Form hi = new Form("Star Slider", new BoxLayout(BoxLayout.Y_AXIS));
    Slider a=createStarRankSlider(e.getNote());
    
    hi.add(FlowLayout.encloseCenter(a));
  
    Button btn = new Button("rate!");
        
        
        
         btn.addActionListener((ActionEvent v)->
         {
         
         hi.add(FlowLayout.encloseCenter(createStarRankSlider((int)round(e.getNote()+a.getProgress()/2)/2)));
        tc.setText(String.valueOf(((int)round(e.getNote()+a.getProgress()/2)/2)));
         hi.show();
         }
         
         
         );
         cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(ta),BoxLayout.encloseX(tb),BoxLayout.encloseX(tc),BoxLayout.encloseX(hi),BoxLayout.encloseX(btn)));
         
          hi.show();
    add(cnt);
    }
private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}

private Slider createStarRankSlider(int a) {
    Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(10);
    starRank.setProgress(a*2);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    System.out.println(starRank.getIncrements()/2);
    return starRank;
}

}
