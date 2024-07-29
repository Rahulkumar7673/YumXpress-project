/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YumXpress.pojo;


public class OrderForCustomerPojo {

    public OrderForCustomerPojo(String productName, double productPrice, String customerName, String adress, String ordId) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.customerName = customerName;
        this.adress = adress;
        this.ordId = ordId;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }
     private String productName;
    private double productPrice;
    private String customerName;
    private String adress;
    private String ordId;

    public OrderForCustomerPojo()
    {
        
    }
   
}
