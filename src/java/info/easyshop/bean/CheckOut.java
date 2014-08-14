/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.easyshop.bean;

import info.easyshop.dao.CheckOutDao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped

/**
 *
 * @author jahangiralamdiu
 */
public class CheckOut {

    private String paymentMethod;
    private String message;
    private String transactionId;
    private String pinNumber;
    private List<Product> itemList = new ArrayList<Product>();
    private CartItem item;
    private int rorderId;
    private double totalPrice;
    private String address;
    private String address2="";
    private String city;

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }  
    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }   
    

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getRorderId() {
        return rorderId;
    }

    public void setRorderId(int rorderId) {
        this.rorderId = rorderId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void payMethod() {
        if (paymentMethod.equals("bkash") || paymentMethod.equals("db mbl") || paymentMethod.equals("ukash")) {
            this.message = "Please send money to 01923774442 and provide the transaction id bellow :";
        } else if (paymentMethod.equals("card")) {
            this.message = "Enter your CID Pin Number bellow :";
        } else if (paymentMethod.equals("bank")) {
            this.message = "Please send money to DBBL 14374847 and provide transaction id bellow :";
        } else {
            this.message = "Payment method is not selected";
        }

    }

    public String placeOrder() {
        CheckOutDao dao = new CheckOutDao();
        dao.placeOrder(rorderId, paymentMethod, transactionId, createAddress());
        CartItem.map.clear();
        return "confirmation";
    }

    public int rorderIdInitiate() {
        CheckOutDao dao = new CheckOutDao();
        this.rorderId = dao.orderIDInitiate();
        return this.rorderId;

    }
    
    public String createAddress(){
        String dAddress=this.address+" , "+this.address2+" , "+this.city;
        return dAddress;
    }
    public void saveTransaction(ActionEvent actionEvent) {  
        FacesContext.getCurrentInstance();
        if(transactionId==null){
            this.message="Please provide Transaction ID";
        }
    }  
}
