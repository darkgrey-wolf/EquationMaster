/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customExceptions;

/**
 *
 * @author Retaliation
 */
public class IllegalMatrixMultiplication extends Exception{
       
    @Override
    public String toString(){
        return "Matrix cannot be multiplied. Row of one matrix must be "
             + "equal with the column of the other. Likewise, the column"
             + " of the same matrix must match the row of the other.";
    }
     
    
}
