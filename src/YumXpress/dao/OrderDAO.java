/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YumXpress.dao;

import YumXpress.dbutil.DBConnection;
import YumXpress.pojo.CustomerAddCartPojo;
import YumXpress.pojo.OrderForCustomerPojo;
import YumXpress.pojo.OrderForStaffPojo;
import YumXpress.pojo.OrderListPojo;
import YumXpress.pojo.PlaceOrderPojo;
import YumXpress.pojo.ShowHistoryPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class OrderDAO {
    
     //      <===================THIS METHOD  GENERATE NEW ID FOR ORDERS===================>
    
    public static String getNewID() throws SQLException{
          Connection conn=DBConnection.getConn();
       Statement st=conn.createStatement();
       ResultSet rs=st.executeQuery("Select max(order_id) from orders");
       rs.next();
       String id=rs.getString(1);
        String ordId="";
      if(id!=null)
      {
           id=id.substring(4);
        ordId="ORD-"+(Integer.parseInt(id)+1);
      }
      else
          ordId="ORD-101";
          return ordId;
      }
    
     //      <===================THIS METHOD  PLACE ORDERS===================>
    
    public static String placeOrder(PlaceOrderPojo placeOrder) throws SQLException
      {
            Connection conn=DBConnection.getConn();
          PreparedStatement ps = conn.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?)");
      placeOrder.setOrderId(getNewID());
           ps.setString(1, placeOrder.getOrderId());
           ps.setString(2,placeOrder.getProductId());
           ps.setString(3,placeOrder.getCustomerId());
           ps.setString(4,placeOrder.getDeliveryStaffId());
           ps.setString(5," ");
           ps.setString(6,"ORDERED");
            ps.setString(7,placeOrder.getCompanyId());
            Random rand =new Random();
           int otp= rand.nextInt(10000);
            ps.setInt(8,otp);
            
           if (ps.executeUpdate() == 1) {
            return placeOrder.getOrderId();
        }
        return null;
    
      }
      
    //      <===================THIS METHOD  GET ORDER DETAILS BY ORDER_ID===================>
    
    public static OrderForStaffPojo getOrderDetailsByOrderId(String orderId) throws SQLException {
        Connection conn = DBConnection.getConn();
        String qry = "SELECT c.customer_name, c.address, s.staff_name, c.mobile_no, co.company_name,co.email_id, p.product_name, p.product_price, o.otp "
                + "FROM orders o "
                + "JOIN products p ON o.product_id = p.product_id "
                + "JOIN companies co ON o.company_id = co.company_id "
                + "JOIN customers c ON o.customer_id = c.customer_id "
                + "JOIN staff s ON o.staff_id = s.staff_id "
                + "WHERE o.order_id = ?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, orderId);
        ResultSet rs = ps.executeQuery();
        OrderForStaffPojo order = null;
        if (rs.next()) {
            order = new OrderForStaffPojo();
            order.setOrderId(orderId);
            order.setCustomerName(rs.getString("customer_name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setDeliveryStaffName(rs.getString("staff_name"));
            order.setCustomerPhoneNo(rs.getString("mobile_no"));
            order.setCompanyName(rs.getString("company_name"));
            order.setCompanyEmailId(rs.getString("email_id"));
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setOtp(rs.getInt("otp"));

        }
        return order;
    }
 
     //      <===================THIS METHOD  GET NEW ORDER FOR STAFF===================>
    
               public static List<OrderForStaffPojo> getNewOrdersForStaff(String staffId) throws SQLException 
    {
        Connection conn = DBConnection.getConn();
        String qry = "SELECT o.order_id, o.otp, p.product_name, p.product_price, c.customer_name, c.address, c.mobile_no "
                + "FROM orders o "
                + "JOIN products p ON o.product_id = p.product_id "
                + "JOIN customers c ON o.customer_id = c.customer_id "
                + "WHERE o.staff_id = ? "
                + "  AND o.status = 'ORDERED' "
                + "ORDER BY o.order_id DESC";

        PreparedStatement ps = conn.prepareStatement(qry); //DELIVERED
        ps.setString(1, staffId);
        ResultSet rs = ps.executeQuery();
        List<OrderForStaffPojo> orderList = new ArrayList<>();
        OrderForStaffPojo order=null;
        while (rs.next()) {
            order = new OrderForStaffPojo();
            order.setOrderId(rs.getString("order_id"));
            order.setProductName(rs.getString("product_name"));
            order.setProductPrice(rs.getDouble("product_price"));
            order.setCustomerName(rs.getString("customer_name"));
            order.setCustomerAddress(rs.getString("address"));
            order.setCustomerPhoneNo(rs.getString("mobile_no"));
            order.setOtp(rs.getInt("otp"));
            orderList.add(order);

        }
        return orderList;
    }
       
                //      <===================THIS METHOD  CONFORN ORDERS===================>
           
   public static boolean  confornOrder(String orderId) throws SQLException{
       
       Connection conn=DBConnection.getConn();
       PreparedStatement ps=conn.prepareStatement("update orders set status='DELIVERED' where order_id=?");
    ps.setString(1, orderId);
    return ps.executeUpdate()==1;
   }
   
    //      <===================THIS METHOD  GET ORDER HISTORY DETAILS===================>
    
   public static List<ShowHistoryPojo>  getOrederHistoryDetails(String staffId) throws SQLException 
   {
        Connection conn = DBConnection.getConn();
        
        String qry1 = "SELECT o.order_id, o.otp, p.product_name, p.product_price, c.customer_name, c.address, c.mobile_no "
                + "FROM orders o "
                + "JOIN products p ON o.product_id = p.product_id "
                + "JOIN customers c ON o.customer_id = c.customer_id "
                + "WHERE o.staff_id = ? "
                + "  AND o.status = 'DELIVERED' "
                + "ORDER BY o.order_id DESC";
                          PreparedStatement ps = conn.prepareStatement(qry1);
                          
                         
                 ps.setString(1, staffId);
                 ResultSet rs=ps.executeQuery();
           ArrayList<ShowHistoryPojo> ordList = new ArrayList<>();
        ShowHistoryPojo shp =null;
         while(rs.next()) {
            shp = new ShowHistoryPojo();
            shp.setProductName(rs.getString("product_name"));
            shp.setProductPrice(rs.getDouble("product_price"));
            shp.setCustomerName(rs.getString("customer_name"));
            shp.setAdress(rs.getString("address")); 
            ordList.add(shp);
        }
        return  ordList;
   }
    //      <===================THIS METHOD  ADD TO CART===================>
    
       public static String placeOrderAddCart(PlaceOrderPojo placeOrder) throws SQLException
      {
            Connection conn=DBConnection.getConn();
          PreparedStatement ps = conn.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?)");
          placeOrder.setOrderId(getNewID());
           ps.setString(1, placeOrder.getOrderId());
           ps.setString(2,placeOrder.getProductId());
           ps.setString(3,placeOrder.getCustomerId());
           ps.setString(4,placeOrder.getDeliveryStaffId());
           ps.setString(5," ");
           ps.setString(6,"UNDELIVERED");
            ps.setString(7,placeOrder.getCompanyId());
            ps.setInt(8, 0000 );
            
           if (ps.executeUpdate() == 1) {
            return placeOrder.getOrderId();
        }
        return null;
    
      }
   
       //      <===================THIS METHOD  GET PRODUCT BY ADD CART===================>
    
      public static List<CustomerAddCartPojo>  getProductAddCart(String custId) throws SQLException 
   {
        Connection conn = DBConnection.getConn();
        
         String qry2 = "SELECT  *"
                + "FROM orders o "
                + "JOIN products p ON o.product_id = p.product_id "
                + "JOIN customers c ON o.customer_id = c.customer_id "
                +"JOIN staff s ON o.staff_id = s.staff_id "
                + "WHERE o.customer_id =? "
                + "  AND o.status = 'UNDELIVERED' "
                + "ORDER BY o.order_id DESC";
                 
              PreparedStatement ps = conn.prepareStatement(qry2);
                ps.setString(1, custId);
                 ResultSet rs=ps.executeQuery();
           ArrayList<CustomerAddCartPojo> cartList = new ArrayList<>();
        CustomerAddCartPojo cacp =null;
         while(rs.next()) {
            cacp = new CustomerAddCartPojo();
            cacp.setProductName(rs.getString("product_name"));
            cacp.setProductPrice(rs.getDouble("product_price"));
            cacp.setOrderId(rs.getString("order_id"));
             cartList.add(cacp);
        }
        return  cartList;
   }
  
      //      <===================THIS METHOD  CONFORN ORDERS TO CART===================>
           
   public static boolean  placeOrdersToCart(String orderId) throws SQLException{
        
       Connection conn=DBConnection.getConn();
       Random rand =new Random();
           int otp= rand.nextInt(10000);
       PreparedStatement ps=conn.prepareStatement("update orders set status='ORDERED',otp=? where order_id=? and status='UNDELIVERED'");
       ps.setInt(1, otp);
       ps.setString(2, orderId);
      return ps.executeUpdate()==1;
   }

   //      <===================THIS METHOD GET COMPANY_ID ===================>
   public static String getcompanyId(String ordId) throws SQLException
   {
       Connection conn=DBConnection.getConn();
       PreparedStatement ps=conn.prepareStatement("select company_id from orders where order_id=?");
       ps.setString(1, ordId);
      ResultSet rs=ps.executeQuery();
      while(rs.next()){
      return rs.getString("company_id");
      }
      return null;
    
   }
   
   //      <===================THIS METHOD DELETE PRODUCT FROM CART ===================>
   public static boolean deleteProductFromCart(String ordId) throws SQLException
   {
       Connection conn=DBConnection.getConn();
       PreparedStatement ps=conn.prepareStatement("delete from orders where order_id=?");
       ps.setString(1, ordId);
       return ps.executeUpdate()==1;
   }
   
    //      <===================THIS METHOD CANCEL ORDERS===================>
    public static boolean  cancelOrdres(String orderId) throws SQLException{
        
       Connection conn=DBConnection.getConn();
       PreparedStatement ps=conn.prepareStatement("update orders set status='CANCEL'where order_id=? and status='ORDERED'");
       ps.setString(1, orderId);
      return ps.executeUpdate()==1;
   }

    //      <===================THIS METHOD  GET ORDER HISTORY FOR CUSTOMERS DETAILS===================>
    
   public static List<OrderForCustomerPojo>  getOrederHistoryforCustDetails(String custId) throws SQLException 
   {
        Connection conn = DBConnection.getConn();
        
        String qry1 ="SELECT  *"
                + "FROM orders o "
                + "JOIN products p ON o.product_id = p.product_id "
                + "JOIN customers c ON o.customer_id = c.customer_id "
                +"JOIN staff s ON o.staff_id = s.staff_id "
                + "WHERE o.customer_id =? "
                + "  AND o.status = 'ORDERED' "
                + "ORDER BY o.order_id DESC";
                          PreparedStatement ps = conn.prepareStatement(qry1);
                          
                         
                 ps.setString(1, custId);
                 ResultSet rs=ps.executeQuery();
           ArrayList<OrderForCustomerPojo> ordList = new ArrayList<>();
        OrderForCustomerPojo ofcp =null;
         while(rs.next()) {
            ofcp = new OrderForCustomerPojo();
            ofcp.setProductName(rs.getString("product_name"));
            ofcp.setProductPrice(rs.getDouble("product_price"));
            ofcp.setCustomerName(rs.getString("customer_name"));
            ofcp.setAdress(rs.getString("address")); 
            ofcp.setOrdId(rs.getString("order_id"));
            ordList.add(ofcp);
        }
        return  ordList;
   }
  
  //      <===================THIS METHOD CANCEL ORDERS===================>
    public static boolean  addToCartFromCancelOrder(String orderId) throws SQLException{
        
       Connection conn=DBConnection.getConn();
       PreparedStatement ps=conn.prepareStatement("update orders set status='UNDELIVERED'where order_id=? and status='ORDERED'");
       ps.setString(1, orderId);
      return ps.executeUpdate()==1;
   }
  //      <===================THIS METHOD GET ALL ORDER DETAILS ===================>
 public static List<OrderListPojo>  getOrederHistoryforCompanyDetails(String cmpId) throws SQLException 
   {
        Connection conn = DBConnection.getConn();
        
        String qry1 ="SELECT  *"
                + "FROM orders o "
                + "JOIN products p ON o.product_id = p.product_id "
                + "JOIN customers c ON o.customer_id = c.customer_id "
                +"JOIN staff s ON o.staff_id = s.staff_id "
                + "WHERE o.company_id =? "
                + "ORDER BY o.order_id DESC";
                 PreparedStatement ps = conn.prepareStatement(qry1);       
                 ps.setString(1, cmpId);
                 ResultSet rs=ps.executeQuery();
           ArrayList<OrderListPojo> ordList = new ArrayList<>();
        OrderListPojo olp =null;
         while(rs.next()) {
            olp = new OrderListPojo();
            olp.setProductName(rs.getString("product_name"));
            olp.setProductPrice(rs.getDouble("product_price"));
            olp.setCustomerName(rs.getString("customer_name"));
            olp.setAddress(rs.getString("address"));
            olp.setStaffName(rs.getString("staff_name"));
            olp.setStatus(rs.getString("status"));
            ordList.add(olp);
        }
        return  ordList;
   }
  

}