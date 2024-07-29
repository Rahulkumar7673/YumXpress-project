/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YumXpress.pojo;

public class OrderListPojo {

    public OrderListPojo(){
        
    }
    public OrderListPojo(String productName, double productPrice, String customerName, String staffName, String address, String status) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.customerName = customerName;
        this.staffName = staffName;
        this.address = address;
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private String productName;
    private double productPrice;
    private String customerName;
    private String staffName;
    private String address;
    private String status;
    
}
