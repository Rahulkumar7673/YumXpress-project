/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YumXpress.dao;

import YumXpress.dbutil.DBConnection;
import YumXpress.pojo.CompanyPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class CompanyDAO {
    
//      <===================THIS METHOD  GENERATE NEW ID FOR COMPANYS===================>
    
      public static String getNewID() throws SQLException{
       Connection conn=DBConnection.getConn();
       Statement st=conn.createStatement();
       ResultSet rs=st.executeQuery("Select max(company_id) from companies");
       rs.next();
       String id=rs.getString(1);
        String cmpId="";
      if(id!=null)
      {
           id=id.substring(4);
        cmpId="CMP-"+(Integer.parseInt(id)+1);
      }
      else
          cmpId="CMP-101";
          return cmpId;
      }
      
//      <===================THIS METHOD  ADD SELLER IN DB===================>
       
      public static boolean addSeller(CompanyPojo comp)  throws SQLException{
           Connection conn=DBConnection.getConn();
           PreparedStatement ps=conn.prepareStatement("Insert into companies values(?,?,?,?,?,?,?)");
           ps.setString(1, getNewID());
           ps.setString(2,comp.getCompanyName());
           ps.setString(3,comp.getOwnerName());
           ps.setString(4,comp.getPassword());
           ps.setString(5,"ACTIVE");
           ps.setString(6,comp.getEmailId());
           ps.setString(7,comp.getSecurityKey());
           return ps.executeUpdate()==1;
      }
      
//      <===================THIS METHOD  CHECK ID&PASSWORD===================>
      
      public static CompanyPojo validate(String compName,String password ) throws SQLException
      {
        Connection conn=DBConnection.getConn();
           PreparedStatement ps=conn.prepareStatement("select * from companies where company_name=? and password=? and status='ACTIVE'");
           ps.setString(1, compName);
           ps.setString(2, password);
           ResultSet rs=ps.executeQuery();
           CompanyPojo comp=null;
           if(rs.next())
           {
               comp=new CompanyPojo();
               comp.setCompanyId(rs.getString(1));
               comp.setOwnerName(rs.getString(3));
               comp.setCompanyName(rs.getString(2));
           }
           
           return comp;
      }
       
      //      <===================THIS METHOD  GET EMAIL_ID BY COMPANY ID===================>
      
      public static Map<String,String> getEmailCredentailsByCompanyId(String companyId)throws SQLException{
       Connection conn=DBConnection.getConn();
       PreparedStatement ps=conn.prepareStatement("select email_id,security_key from companies where company_id=? and status='ACTIVE'");
       ps.setString(1, companyId);
       Map<String,String> companyCredentials=new HashMap<>();
       ResultSet rs=ps.executeQuery();
       if(rs.next()){
           String emailId=rs.getString(1);
           String secKey=rs.getString(2);
           companyCredentials.put("EmailId",emailId);
           companyCredentials.put("Password", secKey);
       }
       return companyCredentials;
   }

      //      <===================THIS METHOD GET COMPANY_ID&NAME===================>
      
      public static Map<String,String> getAllCompanyIdAndName()throws SQLException { 
       Connection conn=DBConnection.getConn(); 
       PreparedStatement ps=conn.prepareStatement("select company_id,company_name from companies where status='ACTIVE' and company_id in (select company_id from products)"); 
       ResultSet rs=ps.executeQuery(); 
       Map<String,String> compList=new HashMap<>(); 
       while(rs.next()){ 
           String c_id=rs.getString(1); 
           String c_name=rs.getString(2); 
           compList.put(c_name,c_id); 
            
       } 
       return compList; 
   }   
      }

