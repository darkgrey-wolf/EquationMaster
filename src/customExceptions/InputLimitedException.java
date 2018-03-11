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
public class InputLimitedException extends Exception{
    private final Double limit2;
    private final Double limit1;
    public InputLimitedException(Double limit2, Double limit1){
        this.limit2=limit2;
        this.limit1=limit1;
    }
    public InputLimitedException(Integer limit2, Integer limit1){
        this.limit2 = limit2.doubleValue();
        this.limit1 = limit1.doubleValue();
    }
    @Override
    public String toString(){
        Integer n2 = this.limit2.intValue();
        Integer n1 = this.limit1.intValue();
        return "Input is limited from " + n1.toString() + " to " +n2.toString() + " inclusive.";
    }
    
}
