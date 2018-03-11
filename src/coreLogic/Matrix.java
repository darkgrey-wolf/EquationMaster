/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Do not forget to move all long texts to resource bundle.
package coreLogic;

import customComponents.EmLabel;
import customComponents.NumField;
import customComponents.PanelSpace;
import customExceptions.*;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Formatter;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author Retaliation
 */
public class Matrix {
    private Double[][] data;
    private String details;
    private Double det;
    private boolean hasDeterminant;
    public Matrix(ArrayList<NumField> inputs, int dimRow , int dimCol, TypeNumber type){
        data = new Double[dimRow][dimCol];
        det=0.0; 
        details = "";
        NumField temp;
        for(int k=0,m=0;k<dimRow;k++){
            for(int l=0;l<dimCol;l++){
                temp = inputs.get(m); m++;
                data[k][l]= new Double((temp.getRealField()).getText());
            }
        }
    }
    public Matrix(Double[][] d){
        this.data = d;
    }
    public final void solveDeterminant() throws MatrixDeterminantException{
        double diagonal,dmultiplier;
        int row = data.length;
        int col = data[0].length;
        if(row!=col) {
            throw new MatrixDeterminantException();
        }
        for(int k=0;k<row;k++){//For each diagonal element
            if(data[k][k]==0) break;//If diagonal is 0 determinant is 0 skip all
            diagonal = data[k][k];
            for(int l=k;l<row;l++){ //For each element below the diagonal
                if(l==k) continue;
                if(data[l][k]==0) continue; //Skip if element is 0, next..
                dmultiplier = data[l][k]/diagonal; // get the multiplier
                for(int m=0;m<row;m++){ // (L) = (L) - (K)*dmultiplier
                   data[l][m] = data[l][m] - data[k][m] * dmultiplier;
                }
            }
        }
        details = "";
        Formatter ft;
        for(int k=0;k<row;k++){
            if(k==0) {
                det = data[k][k];
                ft = new Formatter();
                ft.format("%.5f",det);
                details = ft.toString();
            }
            else {
                det *= data[k][k];
                ft = new Formatter();
                ft.format("%.5f",data[k][k]);
                details = details+ft.toString();
            }
            if(k!=row-1){
                details = details + " x ";
            }
        }
    }
    public void add(Matrix m) throws MatrixOperationException{
        Double[][] mdata = m.getData();
        int mrow = mdata.length;
        int mcol = mdata[0].length;
        int row = data.length;
        int col = data[0].length;
        if(mrow!=row||mcol!=col) throw new MatrixOperationException();
        else{
            for(int k=0;k<row;k++){
                for(int l=0;l<col;l++){
                    data[k][l] += mdata[k][l];
                }
            }
        }
    }
    public void sub(Matrix m) throws MatrixOperationException{
        Double[][] mdata = m.getData();
        int mrow = mdata.length;
        int mcol = mdata[0].length;
        int row = data.length;
        int col = data[0].length;
        if(mrow!=row||mcol!=col) throw new MatrixOperationException();
        else{
            for(int k=0;k<row;k++){
                for(int l=0;l<col;l++){
                    data[k][l]-=mdata[k][l];
                }
            }
        }
    }
    public void scMul(double multiplier){
        int row = data.length;
        int col = data[0].length;
        for(int k=0;k<row;k++){
            for(int l=0;l<col;l++){
                data[k][l]*=multiplier;
            }
        }
    }
    public void vMul(Matrix mat) throws IllegalMatrixMultiplication{
        Double[][] mdata = mat.getData();
        Double[][] temp;
        int row = this.data.length;
        int col = this.data[0].length;
        int mrow = mdata.length;
        int mcol = mdata[0].length;
        double cache;
        if(row!=mcol||col!=mrow) {
            throw new IllegalMatrixMultiplication();
        }
        else {
            temp = new Double[row][mcol];
            for(int k=0;k<row;k++){
                for(int l=0;l<mcol;l++){
                    cache = 0;
                    for(int m=0;m<col;m++){
                        cache += this.data[k][m] * mdata[m][l];
                    }
                    temp[k][l] = cache;
                }
            }
            this.data = temp;
        }
    }
    public boolean transpose(){
        int row = data.length;
        int col = data[0].length;
        Double[][] temp;
        temp = data;
        data = new Double[col][row];
        for(int k=0;k<row;k++){
            for(int l=0;l<col;l++){
                data[l][k] = temp[k][l];
            }
        }
        return true;
    }
    public void dispDataPlain(JScrollPane scr){
        PanelSpace ps = new PanelSpace();
        Formatter ft;
        NumField textField;
        int k,l;
        int row = data.length;
        int col = data[0].length;
        for(k=0;k<row;k++){
            for(l=0;l<col;l++){
                textField = new NumField(TypeNumber.Real);
                ft = new Formatter();
                ft.format("%5.2f", data[k][l]);
                textField.setText(ft.toString());
                textField.setEditable(false);
                ps.setCmp(textField, l, k);
            }
        }
        scr.setViewportView(ps);
        scr.repaint();
    }
    public void dispDataDet(JScrollPane scr){
        PanelSpace ps = new PanelSpace();
        if(hasDeterminant){
                Formatter ft;
                NumField textField;
                EmLabel emL1;
                EmLabel emL2;
                int k,l;
                int row = data.length;
                int col = data[0].length;
                for(k=0;k<row;k++){
                    for(l=0;l<col;l++){
                        textField = new NumField(TypeNumber.Real);
                        textField.setText((data[k][l]).toString());
                        textField.setEditable(false);
                        ps.setCmp(textField, l, k);
                    }
                }
                ft = new Formatter();
                ft.format("%5.2f",det);
                emL1 = new EmLabel("Sol'n: " + details);
                emL2 = new EmLabel("Determinant: " + ft.toString());
                ft.close();
                ps.setCmp(emL1, 0,k,col,1);
                ps.setCmp(emL2,0,k+1,col,1);
            }
        else{
            EmLabel labelNoDet = new EmLabel("The matrix is not a square matrix, it has no determinant.");
            ps.setCmp(labelNoDet, 1,1);
        }
        scr.setViewportView(ps);
        scr.repaint();
    }
    public Double[][] getData(){
        return data;
    }
    public String getDetMsg(){
        Formatter ft = new Formatter();
        ft.format("%5.2f",det);
        return "Soln: " + details +"\nDeterminant: " + ft.toString();
    }
}
