    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YumXpress.dao;

import YumXpress.dbutil.DBConnection;
import YumXpress.pojo.CustomerPojo;
import YumXpress.pojo.StaffPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class StaffDAO {
    
     //      <===================THIS METHOD  GENERATE NEW ID FOR STAFFS===================>
        
    public static String getNewID() throws SQLException{
          Connection conn=DBConnection.getConn();
       Statement st=conn.createStatement();
       ResultSet rs=st.executeQuery("Select max(company_id) from staff");
       rs.next();
       String id=rs.getString(1);
        String staffId="";
      if(id!=null)
      {
           id=id.substring(4);
        staffId="STF-"+(Integer.parseInt(id)+1);
      }
      else
          staffId="STF-101";
          return staffId;
      }
    
   //      <===================THIS METHOD  ADD STAFF===================>
    
      public static String addStaff(StaffPojo staff ) throws SQLException{
          Connection conn=DBConnection.getConn();
           PreparedStatement ps=conn.prepareStatement("Insert into staff values(?,?,?,?,?)");
           staff.setStaffId(getNewID());
           ps.setString(1, staff.getStaffId());
           ps.setString(2,staff.getCompanyId());
           ps.setString(3,staff.getEmailId());
           ps.setString(4,staff.getPassword());
           ps.setString(5,staff.getStaffName());
           return (ps.executeUpdate()==1)?staff.getStaffId():null; //using the ternery operator
      
      
      //    <===============THIS METHOD FINE EMAIL ID AND SECURITY KEY===============>
    
//      public static Map<String,String> getEmailCredentailsByCompanyId(String compantId) throws SQLException{
//          Connection conn=DBConnection.getConn();
//           PreparedStatement ps=conn.prepareStatement("select email_id,security_key from companies wherencompant_id=? and status='ACTIVE'");
//           ps.setString(1, compantId);
//           Map<String,String> compCreade=new HashMap<>();
//           ResultSet rs=ps.executeQuery();
//           if(rs.next())
//           {
//             String emailId=rs.getString(1);
//             String securityKey=rs.getString(2);
//             compCreade.put(emailId,securityKey);
//           }
//         return compCreade;
//      }
      }
     
       //      <===================THIS METHOD  GET ALL STAFF_ID===================>
    
      public static List<String> getAllStaffIdByCompanyId(String companyId)throws SQLException{
        Connection conn=DBConnection.getConn();
        PreparedStatement ps=conn.prepareStatement("select staff_id from staff where company_id=?");
        ps.setString(1, companyId);
        List<String>staffList=new ArrayList<>();
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            staffList.add(rs.getString(1));
        }
        return staffList;
        
    }
   
       //      <===================THIS METHOD  GET STAFF DETAILS===================>
    
      public static StaffPojo getStaffDetailsbyId(String staffId)throws SQLException{
        Connection conn=DBConnection.getConn();
        PreparedStatement ps=conn.prepareStatement("select * from staff where staff_id=?");
        ps.setString(1, staffId);
        StaffPojo staff=new StaffPojo();
        ResultSet rs=ps.executeQuery();
        rs.next();
        staff.setStaffName(rs.getString(5));
        staff.setEmailId(rs.getString(3));
        return staff;
            }
   
       //      <===================THIS METHOD  GET RANDOM STAFF DETAILS===================>
    
    public static String getRandomStaffIdFromCompany(String compId)throws SQLException{
        List<String>staffList=StaffDAO.getAllStaffIdByCompanyId(compId);
        Random rand=new Random();
        int index=rand.nextInt(staffList.size());
        return staffList.get(index);
    }
    
   //      <===================THIS METHOD  CHECK EMIAL_ID&PASSWORD===================>
    
     public static StaffPojo validate(String emailId,String password)throws SQLException{
       Connection conn=DBConnection.getConn();
       PreparedStatement ps=conn.prepareStatement("select staff_id,staff_name from staff where email_id=? and password=?");
       ps.setString(1, emailId);
       ps.setString(2,password);
       ResultSet rs=ps.executeQuery();
      StaffPojo staff=null;
       if(rs.next()){
           staff=new StaffPojo();
           staff.setStaffId(rs.getString(1));
           staff.setStaffName(rs.getString(2));
           staff.setEmailId(emailId);
       }
       return staff;     
       
       }
}
