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
public class MatrixDeterminantException extends Exception{
    @Override
    public String toString(){
        return "Non-square matrices has no determinant.";
    }
}
