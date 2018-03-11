/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreLogic;

import customComponents.NumField;
import customComponents.PanelSpace;
import java.awt.*;
import java.util.Formatter;
import javax.swing.*;


/**
 *
 * @author Retaliation
 */
public class DisplayHandler {
    public DisplayHandler(){}
    public void dispData(Double[][] data, JScrollPane scr){
        this.dispData(data,"",scr);
    }
    public void dispData(Double[][] data, String msg, JScrollPane scr){
        PanelSpace ps = new PanelSpace();
        JTextPane txp = new JTextPane();
        Formatter ft;
        NumField textField;
        int k,l=0;
        int row = data.length;
        int col = data[0].length;
        for(k=0;k<row;k++){
            for(l=0;l<col;l++){
                textField = new NumField(TypeNumber.Real);
                ft = new Formatter();
                ft.format("%5.2f", data[k][l]);
                textField.setText(ft.toString());
                //textField.setEditable(false);
                textField.setDisplayMode(true);
                ps.setCmp(textField,l,k);
            }
        }
        if(!msg.isEmpty()){
            txp.setText(msg);
            txp.setEditable(false);
            txp.setOpaque(false);
            txp.setFont(new Font("Verdana",0,12));
            ps.setCmp(txp,0,k,col,2);
        }
        ps.setPreferredSize(new Dimension(scr.getWidth(),scr.getHeight()));
        scr.setViewportView(ps);
        scr.repaint();
    }
    public void dispMessage(String msg, JScrollPane scr){
        PanelSpace ps = new PanelSpace();
        JTextPane txp = new JTextPane();
        txp.setText(msg);
        ps.setCmp(txp, 0,0 );
        ps.setPreferredSize(new Dimension(scr.getWidth(),scr.getHeight()));
        scr.setViewportView(ps);
        scr.repaint();
    }
}
