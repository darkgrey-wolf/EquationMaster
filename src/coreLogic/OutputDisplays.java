/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreLogic;
import customComponents.NumField;
import customComponents.PanelSpace;
import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author Retaliation
 */
public class OutputDisplays {
    public OutputDisplays(){
        
    }
    public void dispSoln1(double data[][],int intUnknowns, TypeNumber type, JScrollPane scrPaneOutput){
      PanelSpace ps = new PanelSpace();
      NumField temp;
      Double datum;
      for(int k=0;k<intUnknowns;k++){
          for(int l=0;l<intUnknowns+1;l++){
              datum = data[k][l];
              temp = new NumField(type);
              temp.setText(datum.toString());
              temp.setEditable(false);
              if(l==intUnknowns) {
                  temp.setBackground(new Color(75,175,105));
              }
              temp.saveBgk();
              ps.setCmp(temp,l,k);
          }
      }
      scrPaneOutput.setViewportView(ps);
      scrPaneOutput.repaint();
    }
}
