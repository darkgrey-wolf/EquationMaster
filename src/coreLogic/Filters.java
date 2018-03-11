/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreLogic;

import java.util.*;

/**
 *
 * @author Retaliation
 */
public class Filters {
    public Filters(){
        
    }
    public boolean strIntegerPositive(String test){//receives string argument
        if(test.length()==0) return false;
        if(test.charAt(test.length()-1)=='d') return false;
        try {
            Integer temp = new Integer(test);
            return temp>0;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    public boolean dimSingleCheck(String str){
        if(!countX(str,1)) return false;
        str = this.removeWhiteSpace(str);
        StringTokenizer tokens = new StringTokenizer(str,"x");
        Integer irow;
        Integer icol;
        irow = 0; icol = 0;
        if(tokens.countTokens()!=2) return false;
        String row = new String();
        String col = new String();
        //Double irow,icol;
        while(tokens.hasMoreTokens()){
            row = tokens.nextToken();
            col = tokens.nextToken();
        }
        try{
            if(row.charAt(row.length()-1)=='d'|row.charAt(row.length()-1)=='f') return false;
            if(col.charAt(col.length()-1)=='d'|col.charAt(col.length()-1)=='f') return false;
            irow = new Integer(row);
            icol = new Integer(col);
            return !(irow<0||icol<0);
        }
        catch(NumberFormatException e){
            return false;
        }
        //return false;
    }
    public boolean dimDoubleCheck(String str){
        if(!countX(str,2)) return false;
        str = this.removeWhiteSpace(str);
        StringTokenizer tokens = new StringTokenizer(str,"&");
        if(tokens.countTokens()!=2) return false;
        while(tokens.hasMoreTokens()){
            if(!dimSingleCheck(tokens.nextToken())) return false;
        }
        return true;
    }
    public boolean countX(String str, int match){
        StringTokenizer tokens = new StringTokenizer(str,"x",true);
        int count = 0;
        while(tokens.hasMoreTokens()){
            if("x".equals(tokens.nextToken())) count++;
        }
        return count==match;
    }
    public int[] parseDimSingle(String str){//use after dimSingleCheck
        str = this.removeWhiteSpace(str);
        StringTokenizer tokens = new StringTokenizer(str,"x");
        Integer irow = 0;
        Integer icol = 0;
        int[] dim = new int[2];
        while(tokens.hasMoreTokens()){
            irow = new Integer(tokens.nextToken());
            icol = new Integer(tokens.nextToken());
        }
        dim[0]=irow;
        dim[1]=icol;
        return dim;
    }
    public int[] parseDimDouble(String str){ //use after dimDoubleCheck
        str = this.removeWhiteSpace(str);
        StringTokenizer tokens = new StringTokenizer(str,"x,&");
        Integer temp;
        int[] dim = new int[4];
        int k = 0;
        while(tokens.hasMoreTokens()){
            temp = new Integer(tokens.nextToken());
            dim[k]=temp;
            k++;
        }
        return dim;
    }
    public String removeWhiteSpace(String str){
        ArrayList<Character> stack = new ArrayList<>();
        char[] temp;
        int k;
        for(k=0;k<str.length();k++){
            if(str.charAt(k)==' ') continue;
            stack.add(str.charAt(k));
        }
        temp = new char[stack.size()];
        for(k=0;k<stack.size();k++){
            temp[k]=stack.get(k);
        }
        return new String(temp);
    }
}
