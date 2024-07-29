/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YumXpress.pojo;



//      <===================THIS CLASS USE GET&SET CUSTOMER CART DETAILS===================>

public class CustomerAddCartPojo {

    public CustomerAddCartPojo(String productName, double productPrice, String orderId) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.orderId = orderId;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public CustomerAddCartPojo()
    {
        
    }

    private String productName;
    private double productPrice;
    private String orderId;
    
}
