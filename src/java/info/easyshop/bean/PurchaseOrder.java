/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.easyshop.bean;

import info.easyshop.dao.CheckOutDao;
import info.easyshop.dao.PurchaseOrderDao;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped

/**
 *
 * @author jahangiralamdiu
 */
public class PurchaseOrder {

    private int productId;
    private String productName;
    private String groupId;
    private String companyName;
    private int stockId;
    private double unitCost;
    private Date purchaeDate;
    private int stockInHand;
    private int quantity;
    private double totalPrice;
    private int orderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public Date getPurchaeDate() {
        return purchaeDate;
    }

    public void setPurchaeDate(Date purchaeDate) {
        this.purchaeDate = purchaeDate;
    }

    public int getStockInHand() {
        return stockInHand;
    }

    public void setStockInHand(int stockInHand) {
        this.stockInHand = stockInHand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void getProductByID() {
        PurchaseOrderDao dao = new PurchaseOrderDao();
        PurchaseOrder pc = dao.getProductByID(this.productId);
        this.productName = pc.productName;
        this.companyName = pc.companyName;
        this.groupId = pc.groupId;
        this.unitCost = pc.unitCost;
        this.stockInHand = pc.stockInHand;
        this.quantity = pc.quantity;
        this.totalPrice = 0;
    }

    public double calTotalPrice() {
        this.totalPrice = (this.quantity * this.unitCost);
        return this.totalPrice;
    }

    public int rorderIdInitiate() {
        CheckOutDao dao = new CheckOutDao();
        this.orderId = dao.orderIDInitiate();
        return this.orderId;

    }

    public void addPurchase() {
        PurchaseOrderDao dao = new PurchaseOrderDao();
        dao.productPurchase(productId, quantity, totalPrice, purchaeDate);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", this.quantity +" "+ this.productName + " added to Stock"));
        this.stockInHand=this.stockInHand+this.quantity;
        this.quantity=0;
        this.totalPrice=0;
        
    }
    
    public String resetAll(){
    this.productId=0;
    this.productName=null;
    this.groupId=null;
    this.companyName=null;
    this.unitCost=0;
    this.stockInHand=0;
    this.quantity=0;
    this.totalPrice=0;
    this.purchaeDate=null;
    return "purchaseForm";
    }
    
    public void reset() {  
        RequestContext.getCurrentInstance().reset("form:panel");  
    } 

}
