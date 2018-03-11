/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customComponents;

import javax.swing.JLabel;

/**
 *
 * @author Retaliation
 */
public class EmLabel extends JLabel{
    public EmLabel(String str){
        this.setText(str);
        this.setSize(20,str.length()*10);
        this.setVisible(true);
    }
}
