/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YumXpress.pojo;



//      <===================THIS CLASS USE GET&SET SHOW ORDERS HISTORY DETAILS===================>

public class ShowHistoryPojo {

    public ShowHistoryPojo(){
        
    }
    public ShowHistoryPojo(String productName, double productPrice, String customerName, String adress) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.customerName = customerName;
        this.adress = adress;
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
    private String productName;
    private double productPrice;
    private String customerName;
    private String adress;
    
    
}
