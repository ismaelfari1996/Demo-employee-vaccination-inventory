/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.af.example.Demoemployeevaccinationinventory.utility;

/**
 *
 * @author Ing. Ismael Farinango 2022
 */
public class IdentityCardValidator {
    
    public static boolean isValidIdentityCard(String identityCard){
        int resultDigit=0;
        int resultSum=0;
        if(!isLengthValidate(identityCard))
            return false;
        resultSum=sumOperation(multiplayOperation(identityCard));
        resultDigit=isEvenNumber(resultSum/10)?nextTen(resultSum)-((resultSum/10)*10):
                    nextTen(resultSum)- resultSum ;
        resultDigit=resultDigit!=10?resultDigit:0;
        if(!isValid(identityCard, resultDigit))
            return false;
        return true;
    }
    
    private static int[] multiplayOperation(String identityCard){
        int result[]=new int[9];
        int value=0;
        for(int i=0;i<identityCard.length()-1;i++){
            value=Integer.parseInt(identityCard.charAt(i)+"");
            if(isEvenNumber(i+1)){
                result[i]=value;
            }else{ 
               result[i]=2*value>9?(2*value)-9:2*value;
            }
        }
        return result;
    }
        
    private static int sumOperation(int [] values){
        int sum=0;
        for(Integer x:values){
            sum=sum+x;
        }
        return sum;
    }
        
    private static boolean isLengthValidate(String identityCard){
        if(identityCard.length()<10)
            return false;
        return true;
    }
    
    private static boolean isEvenNumber(int number){
        if((number % 2)==0)
            return true;
        return false;
                    
    }
    
    private static int nextTen(int value){
        value=((value/10)+1)*10;
        return value;
    }
    
    private static boolean isValid(String identityCard, int resultDigit){
        if(Integer.parseInt(identityCard.charAt(9)+"")==resultDigit)
            return true;
        return false;
    }
}
