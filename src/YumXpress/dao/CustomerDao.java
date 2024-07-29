/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YumXpress.dao;

import YumXpress.dbutil.DBConnection;
import YumXpress.pojo.CustomerPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CustomerDao {
    
      //      <===================THIS METHOD  GENERATE NEW ID FOR CUSTOMERS===================>
    
    public static String getNewID() throws SQLException{
          Connection conn=DBConnection.getConn();
       Statement st=conn.createStatement();
       ResultSet rs=st.executeQuery("Select max(customer_id) from customers");
       rs.next();
       String id=rs.getString(1);
        String custId="";
      if(id!=null)
      {
           id=id.substring(4);
        custId="CUS-"+(Integer.parseInt(id)+1);
      }
      else
          custId="CUS-101";
          return custId;
      }
    
//      <===================THIS METHOD  ADD NEW CUSTOMERS===================>
    
    public static String addCustomer(CustomerPojo customer ) throws SQLException{
          Connection conn=DBConnection.getConn();
           PreparedStatement ps=conn.prepareStatement("Insert into customers values(?,?,?,?,?,?)");
           customer.setCustomerId(getNewID());
           ps.setString(1, customer.getCustomerId());
           ps.setString(2,customer.getCustomerName());
           ps.setString(3,customer.getEmailId());
           ps.setString(4,customer.getPassword());
           ps.setString(5,customer.getMobileNumber());
           ps.setString(6,customer.getAddress());
           return (ps.executeUpdate()==1)?customer.getCustomerId():null; 
}
 
    //      <===================THIS METHOD  CHECK ID&PASSWORD===================>
    
    public static CustomerPojo validate(String emailId,String password)throws SQLException{
       Connection conn=DBConnection.getConn();
       PreparedStatement ps=conn.prepareStatement("select * from customers where email_id=? and password=?");
       ps.setString(1, emailId);
       ps.setString(2,password);
       ResultSet rs=ps.executeQuery();
       CustomerPojo cust=null;
       if(rs.next()){
           cust=new CustomerPojo();
           cust.setCustomerId(rs.getString(1));
           cust.setCustomerName(rs.getString(2));
           cust.setEmailId(emailId);
       }
       return cust;     
       
       }
    
       //      <===================THIS METHOD  GET CUSTOMERS DETAILS===================>
    
    public static CustomerPojo getCustomerDetailsById(String custId)throws SQLException{
        Connection conn=DBConnection.getConn();
        PreparedStatement ps=conn.prepareStatement("select * from customers where customer_id=?");
        ps.setString(1, custId);
        ResultSet rs=ps.executeQuery();
       CustomerPojo cust=null;
       if(rs.next()){
           cust=new CustomerPojo();
           cust.setCustomerId(rs.getString(1));
           cust.setCustomerName(rs.getString(2));
           cust.setEmailId(rs.getString("email_id"));
           cust.setMobileNumber(rs.getString("mobile_no"));
           cust.setAddress(rs.getString("address"));
           cust.setPassword(rs.getString("password"));
           
       }
       return cust;  
    }
    
        //      <===================THIS METHOD  UPDATE CUSTOMER DETAILS===================>
    
    public static boolean updateCustomer(CustomerPojo customer)throws SQLException{
        Connection conn=DBConnection.getConn();
        PreparedStatement ps=conn.prepareStatement("update customers set customer_name=?,password=?,mobile_no=?,address=? where customer_id=?");
        ps.setString(5,customer.getCustomerId());
        ps.setString(1,customer.getCustomerName());
        ps.setString(2,customer.getPassword());
        ps.setString(3,customer.getMobileNumber());
        ps.setString(4,customer.getAddress());
        return ps.executeUpdate()==1;
        
    }
}
