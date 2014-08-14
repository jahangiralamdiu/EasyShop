/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.easyshop.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
/**
 *
 * @author jahangiralamdiu
 */
public class CartItem {

    private int productId;
    private String productName;
    private int quantity=1;
    private double price;
    private int productCount;
    private boolean ist=true;
    static List<Product> item = new ArrayList<Product>();
    static Map <Integer, Product> map = new <Integer, Product> HashMap();
    private final int [] quantityList={1,2,3,4,5,6,7,8,9,10};
    

    public CartItem() {

    }

    public CartItem(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
    
    

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
    
    public boolean isIst() {
        return ist;
    }

    public void setIst(boolean ist) {
        this.ist = ist;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addProduct(Product p) {
        map.put(p.getProductId(), p);
        //productCount();
    }
    
    public String removeProduct(Product p) {
        if(map.containsKey(p.getProductId())){
            map.remove(p.getProductId());
        }
        return "cart";
    }

    public static List<Product> cartDetails() {
        item.clear();
        item.addAll(map.values());
        return item;
    }

    public int productCount() {
        return map.size();
    }
    
    public static double calPrice(){
        double totalPrice=0.0d;
        Iterator<Product> ite=item.iterator();
        while(ite.hasNext()){
            totalPrice+=ite.next().initPrice();
        }
        return totalPrice;
    }
    
    public int getProductFromCart(Product p) {
       if(map.containsKey(p.getProductId())){
            return 1;
        }else {            
            return 0;    
        }
    }
   
   
}
