/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YumXpress.util;

import java.util.Base64;


//      <===================THIS CLASS USE PASSWORD ENCRYPTED&DECRYPTRD===================>

public class PasswordEncryption {
    public static String getEncryptedPassword(String pwd)
    {
         Base64.Encoder en=Base64.getEncoder();
      String En=  en.encodeToString(pwd.getBytes());
      return En;
   }
    
     public static String getDecryptedPassword(String pwd){
         Base64.Decoder dn=Base64.getDecoder();
       byte[]arr=dn.decode(pwd.getBytes());
       String Dn=new String(arr);
       return Dn;
     }
}
