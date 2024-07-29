/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YumXpress.util;


public class Validator {
    
    //    <======================THIS METHOD EMAIL VALIDATE======================>
    
     public static boolean isValidateEmail(String emailId)
    {
        org.apache.commons.validator.routines.EmailValidator validate=org.apache.commons.validator.routines.EmailValidator.getInstance();
        return validate.isValid(emailId);
    }
     
     //    <======================THIS METHOD MOBILE NO VALIDATE======================>
    
     public static boolean isValidateMobileNumber(String mobileNo)
    {
        if(mobileNo.length()!=10)
            return false;
        char[] mobArr=mobileNo.toCharArray();
        for(char ch :mobArr)
         if(Character.isDigit(ch)==false)
             return false;
            return  true;
    }
     
}
