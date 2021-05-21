/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.SeriesSelection;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.List;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;
import com.mycompany.entities.User;
import com.mycompany.service.ServiceUtilisateur;
import java.util.ArrayList;
import static java.util.Collections.list;





/**
 * Budget demo pie chart.
 */
public class BudgetPieChart extends AbstractDemoChart {
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Merci Pour Votre Confiance"
            + ""
            + "";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "Marge D'age de Nos utilisateurs ";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Form execute() {
      
       double[] values = {20,30,40,50,0,0,0,0,0};
      //  serviceUtilisateur maw shyh !! EYY stana stnaa dhhrli rzinn ama ymchyyy ismaa bch naawd w baaid mtconnecty mtnzlch inty behyy
      ArrayList<User> list= ServiceUtilisateur.getInstance().getAllTasks();
for(User u : list ){
// Cinema f=new Cinema();
//for(int i=0; i<list.size();i++){
   
    values[list.indexOf(u)]=u.getAge();
       
   }
   // for ( User u : list){ values [list.index
    int[] colors = new int[] { ColorUtil.YELLOW, ColorUtil.rgb(178, 2, 56), ColorUtil.rgb(3, 159, 190),ColorUtil.rgb(217, 165, 179), ColorUtil.rgb(55, 241, 225), ColorUtil.rgb(210, 96, 26)};
   final DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextFont(largeFont);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    renderer.setBackgroundColor(0xFF018786);
    renderer.setApplyBackgroundColor(true);
    renderer.setLabelsColor(0xFF000000);
    
    final CategorySeries seriesSet = buildCategoryDataset("statiques", values);
    final PieChart chart = new PieChart(seriesSet, renderer);
    ChartComponent comp = new ChartComponent(chart){

        private boolean inDrag = false;
        
        @Override
        public void pointerPressed(int x, int y) {
            inDrag = false;
            super.pointerPressed(x, y); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void pointerDragged(int x, int y) {
            inDrag = true;
            super.pointerDragged(x, y); //To change body of generated methods, choose Tools | Templates.
        }

        
        
        @Override
        protected void seriesReleased(SeriesSelection sel) {
            
            if ( inDrag ){
                // Don't do this if it was a drag operation
                return;
            }
            
            for ( SimpleSeriesRenderer r : renderer.getSeriesRenderers()){
                r.setHighlighted(false);
            }
            SimpleSeriesRenderer r = renderer.getSeriesRendererAt(sel.getPointIndex());
            r.setHighlighted(true);
            
            Shape seg = chart.getSegmentShape(sel.getPointIndex());
            Rectangle bounds = seg.getBounds();
            bounds = new Rectangle(
                    bounds.getX()-40,
                    bounds.getY()-40,
                    bounds.getWidth()+80,
                    bounds.getHeight()+80
            );
            
            this.zoomToShapeInChartCoords(bounds, 500);
            
            
            
        }
       
        
        
    };
    comp.setZoomEnabled(true);
    comp.setPanEnabled(true);
    comp.getStyle().setBgColor(0xff0000);
    comp.getStyle().setBgTransparency(255);
    
    return wrap("Budget", comp);
    
  }

}
