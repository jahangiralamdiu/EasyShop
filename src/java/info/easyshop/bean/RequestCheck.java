/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.easyshop.bean;

import info.easyshop.dao.ClientOrderDetails;
import info.easyshop.dao.RequestCheckDao;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author SHOHUG
 */

@ManagedBean
@RequestScoped
public class RequestCheck implements Serializable{
    private int rorderId;
    private Date rorderDate;
    private int clientId;
    private double totalPrice;
    private String paymentMethod;
    private String transactionId;
    private String pinNumber;
    private String address;
    private String or_status;
    private int rorderSerial;
    
    private int productId;
    private int qunatity;
    
    private String orderType;
    
     static List<RequestCheck> item = new ArrayList<RequestCheck>();

    public RequestCheck() {
    }

    public RequestCheck(int rorderId, Date rorderDate, int clientId, double totalPrice, String paymentMethod, String transactionId, String pinNumber, String address, String or_status, int rorderSerial, int productId, int qunatity, String orderType) {
        this.rorderId = rorderId;
        this.rorderDate = rorderDate;
        this.clientId = clientId;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.pinNumber = pinNumber;
        this.address = address;
        this.or_status = or_status;
        this.rorderSerial = rorderSerial;
        this.productId = productId;
        this.qunatity = qunatity;
        this.orderType = orderType;
    }
    
    public int getRorderId() {
        return rorderId;
    }

    public void setRorderId(int rorderId) {
        this.rorderId = rorderId;
    }

    public Date getRorderDate() {
        return rorderDate;
    }

    public void setRorderDate(Date rorderDate) {
        this.rorderDate = rorderDate;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOr_status() {
        return or_status;
    }

    public void setOr_status(String or_status) {
        this.or_status = or_status;
    }

    

    public int getRorderSerial() {
        return rorderSerial;
    }

    public void setRorderSerial(int rorderSerial) {
        this.rorderSerial = rorderSerial;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQunatity() {
        return qunatity;
    }

    public void setQunatity(int qunatity) {
        this.qunatity = qunatity;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }


    public List<RequestCheck> sq() throws SQLException{
        RequestCheckDao dao= new RequestCheckDao();
        return dao.getViewRequest();
        
    }
    public List<RequestCheck> clientOrderDetails() throws SQLException{
        ClientOrderDetails dao= new ClientOrderDetails();
        return dao.getClientRequest(Users.clientDetails().getClientId());
        
    }
    
    public String acceptRequest() throws SQLException{
        RequestCheckDao dao=new RequestCheckDao();
        dao.insertOrdertable(rorderId, rorderDate, clientId, totalPrice, paymentMethod, transactionId, pinNumber, address);
      
        return null;
    }
    public String cancelRequest() throws SQLException{
        RequestCheckDao dao=new RequestCheckDao();
        dao.cancelRequest(rorderId);
        return null;
    }
}
