/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customComponents;
import java.awt.*;
import javax.swing.*;
import coreLogic.Number;
import coreLogic.TypeNumber;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 *
 * @author Retaliation
 */
public class NumField extends JPanel implements FocusListener{
    public NumField(TypeNumber type){
        this.selection = true;
        this.realField = new JTextField("0");  realField.setHorizontalAlignment(JTextField.CENTER);
        this.exField = new JTextField("0"); exField.setHorizontalAlignment(JTextField.CENTER);
        this.exLabel = new JLabel(); exLabel.setHorizontalAlignment(JTextField.CENTER);
        //this.setPreferredSize(new Dimension(20,50));
        this.type = type;
        //this.setLayout( new GridLayout(1,3));
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridwidth=4;
        cons.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(realField, cons);
        layout.setConstraints(exField, cons);
        layout.setConstraints(exLabel, cons);
        this.setLayout(layout);
        this.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        switch(type){
            case Real://normal
                realField.setColumns(7);
                this.add(realField);
                break;
            case Rect://complex rectangular
                exLabel.setText(" + j ");
                realField.setColumns(3);
                exField.setColumns(3);
                this.add(realField); this.add(exLabel); this.add(exField);
                break;
            case Polar: // complex angular
                exLabel.setText(" < ");
                realField.setColumns(3);
                exField.setColumns(3);
                this.add(realField); this.add(exLabel); this.add(exField);
                break;
        }
        //this.setSize(20,550);
        //this.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        realField.addFocusListener(this);
    }
    public void setText(String real, String im){
        realField.setText(real);
        exField.setText(im);
    }
    public void setText(String real){
        realField.setText(real);
        exField.setText("0");
    }
    public void setTextRect(Number n){
        Double temp1 = n.getReal();
        Double temp2 = n.getIm();
        realField.setText(temp1.toString());
        exField.setText(temp2.toString());
    }
    public void setTextPol(Number n){
        Double temp1 = n.getRadius();
        Double temp2 = n.getAngleDeg();
        realField.setText(temp1.toString());
        exField.setText(temp2.toString());
    }
    public void setEditable(boolean b){
        realField.setEditable(b);
        exField.setEditable(b);
    }
    public JTextField getRealField(){
        return realField;
    }
    public JTextField getExField(){
        return exField;
    }
    public TypeNumber getType() {return type;}
    public Number getNum(){
        Number n = new Number();
        Double real = new Double(realField.getText());
        Double im = new Double(exField.getText());
        n.setValueRect(real, im);
        n.angleCheck();
        return n;
    }
    public void saveBgk(){
        savedBgk=this.getBackground();
    }
    public void setDisplayMode(boolean mode){
        this.selection = !mode;
        this.setEditable(!mode);
    }
    public boolean checkValue(){
        Double num;
        String sample1 = realField.getText();
        String sample2 = exField.getText();
        try {
            num = new Double(sample1);
            num = new Double(sample2);
            if(sample1.charAt(sample1.length()-1)=='d'|sample1.charAt(sample1.length()-1)=='f'){
                throw new NumberFormatException();
            }//Note no sample2 as of this moment
            this.setBackground(savedBgk);
            return true;
        }
        catch(NumberFormatException e){
            this.setBackground(new Color(200,100,100));
            return false;
        }
    }
    private JTextField realField,exField;
    private JLabel exLabel; // contains either + or <
    private TypeNumber type;
    private Color savedBgk;
    private boolean selection;

    @Override
    public void focusGained(FocusEvent fe) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(selection){
            if((realField.getText()).equals("0")){
                realField.setText("");
            }
            else{
                realField.setSelectionStart(0);
                realField.setSelectionEnd((realField.getText()).length());
            }
        }
    }

    @Override
    public void focusLost(FocusEvent fe) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(selection){
            if((realField.getText()).equals("")){
                realField.setText("?");
            }
        }
    }
}
