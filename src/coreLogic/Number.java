/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreLogic;

import static java.lang.Math.*;

/**
 *
 * @author Retaliation
 */
public class Number {
    private double numReal;
    private double numIm;
    private double numRadius;
    private double numAngle; // degrees
    
    public Number(){
        numReal=numIm=numRadius=numAngle=0;
    }
    public Number(double real, double im){
        setValueRect(real,im);
    }
    public final void setValueRect(double real,double im){
        numReal=real;
        numIm=im;
        //convert automatically
        numRadius = sqrt(real*real + im*im);
        if(real!=0)numAngle = atan(im/real) * 180/PI;
        else numAngle = 0;
    }
    public void setValuePol(double r, double ang){
        numRadius = r;
        numAngle = ang;
        numReal = r * cos(ang * PI/180); 
        numIm = r * sin(ang * PI/180);
        angleCheck();
    }
    public double getReal(){
        return numReal;
    }
    public double getIm(){
        return numIm;
    }
    public double getRadius(){
        return numRadius;
    }
    public double getAngleDeg(){
        return numAngle;
    }
    public Number add(Number n){
       Number num = new Number();
       num.setValueRect(this.getReal()+n.getReal(),this.getIm()+n.getIm());
       num.angleCheck();
       return num;
    }
    public Number sub(Number n){
       Number num = new Number();
       num.setValueRect(this.getReal()-n.getReal(),this.getIm()-n.getIm());
       num.angleCheck();
       return num;
    }
    public Number mul(double n){
        Number num = new Number();
        double real = this.getReal() * n;
        num.setValueRect(real, this.getIm());
        return num;
    }
    public Number mul(Number n){
        Number num = new Number();
        num.setValuePol(this.getRadius()*n.getRadius(),this.getAngleDeg()+n.getAngleDeg());
        num.angleCheck();
        return num;
    }
    public Number div(double n){
        Number num = new Number();
        double real = this.getReal() / n;
        num.setValueRect(real, this.getIm());
        return num;
    }
    public Number div(Number n){
        Number num = new Number();
        num.setValuePol(this.getRadius()/n.getRadius(),this.getAngleDeg()-n.getAngleDeg());
        num.angleCheck();
        return num;
    }
    public void angleCheck(){
        if(numRadius<0){
            numAngle = 0 - numAngle;
            numRadius = abs(numRadius);
        }
        if(numAngle>180){
            numAngle = numAngle - 360;
        }
        else if(numAngle<-180){
            numAngle = 360 + numAngle;
        }
    }
    public String stringValRect(){
        String str = new String();
        if(numIm>0){
            str =  "[ "+ numReal+"+j"+ numIm+" ]";
        }
        else if(numIm<0) {
            str =  "[ "+numReal + " - j" + abs(numIm)+" ]";
        }
        else if(numIm==0) str = "[ "+numReal + " ]";
        return str;
    }
    public String stringValPol(){
        return numRadius + " < " + numAngle;
    }
    public boolean equals(Number n){
        return (this.getReal()==n.getReal())&(this.getIm()==n.getIm());
    }
    
}
