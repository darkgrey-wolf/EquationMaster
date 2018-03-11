/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Problems: try catch can't catch division by 0;
package coreLogic;

import customComponents.*;
import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 *
 * @author Retaliation
 */
public class GaussJordan {
//    private double data[][];
//    private double soln[][];
    private double data[][];
    private double soln[][];
    //private MyTextField textArr[][];
    private int numVars;
    private boolean hasSolution;
    public double[][] getData(){    return data; }
    public double[][] getSoln() { return soln; }
    public int getNumVars() { return numVars; }
    public boolean solnExist() { return hasSolution; }
    public GaussJordan(ArrayList<NumField> arTextField, int numVars) throws NumberFormatException{
        data = new double[numVars][numVars+1];
        hasSolution = false;
        this.numVars=numVars;
        int k,l,m=0;
        NumField sample;
        Double dsample;
        for(k=0;k<numVars;k++){
            for(l=0;l<numVars+1;l++){
                sample = arTextField.get(m);
                dsample = new Double((sample.getRealField()).getText());
                data[k][l]=dsample;
                m++;
            }
        }
    }
    public void solve(){
        int k,l,m;
        double diagonal;
        double cache;
        soln = data;
        hasSolution = true;
            for(k=0;k<numVars;k++){
                if(soln[k][k]==0){
                    hasSolution = false;
                    //find non zero data in column k
                    for(l=0;l<numVars;l++){
                        if(soln[l][k]!=0){
                            hasSolution = true;
                            //if found add this equation to the current equation k
                            for(m=0;m<numVars+1;m++){
                                soln[k][m] = soln[k][m]+ soln[l][m];
                            }
                            break;
                        }
                    }
                }
                if(!hasSolution) break;
                diagonal=soln[k][k]; // identify the diagonal element
                for(l=0;l<numVars+1;l++){
                   soln[k][l] = soln[k][l]/diagonal;
                }
                for(l=0;l<numVars;l++){
                   if(l==k) continue;
                   cache = soln[l][k];
                   for(m=0;m<numVars+1;m++){
                       soln[l][m] = soln[l][m] - soln[k][m]*cache;
                   }
                }
            }
            //checking if solution is valid
            this.hasSolution = true;
            for(k=0;k<numVars;k++){
                for(l=0;l<numVars;l++){
                    if(l==k&soln[k][l]!=1){
                        hasSolution = false; break;
                    }
                    if(l!=k&soln[k][l]!=0) {
                        hasSolution = false; break;
                    }
                }
            }
        
    }
    
}
