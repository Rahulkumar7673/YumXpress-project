/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YumXpress.pojo;



//      <===================THIS CLASS USE GET&SET CUSTOMERS DETAILS===================>

public class CustomerPojo {
    private String customerId;
     private String customerName;
     private String emailId;
     private String password;
     private String mobileNumber;
     private String address;
 public CustomerPojo(){
     
 }
    public CustomerPojo(String customerId, String customerName, String emailId, String password, String mobileNumber, String address) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.emailId = emailId;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
