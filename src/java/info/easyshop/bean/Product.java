/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.easyshop.bean;

import info.easyshop.dao.ProductEntryDao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

@ManagedBean
@RequestScoped

/**
 *
 * @author jahangiralamdiu
 */
public class Product {

    private int productId;
    private String productName;
    private String groupId;
    private String companyName;
    private int stockId;
    private double unitCost;
    private double salesPrice;
    private Date addDate;
    private String imageURL;
    private UploadedFile uploadedFile;
    private int stockInHand;
    private int quantity=1;
    private double price;
    private double totalPrice;
    private String message;
    private String size;
    private String color;
  
    
    public Product() {
        productIDInitiate();
        stockIDInitiate();
    }
    
    public double calTotalPrice(){
        this.totalPrice=(this.quantity * this.unitCost);
        return this.totalPrice;
    }

    public double initPrice() {
        this.price = this.salesPrice * this.quantity;
        return this.price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }   
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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
   

    public int getStockInHand() {
        return stockInHand;
    }

    public void setStockInHand(int stockInHand) {
        this.stockInHand = stockInHand;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the stockId
     */
    public int getStockId() {
        return stockId;
    }

    /**
     * @param stockId the stockId to set
     */
    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    /**
     * @return the unitCost
     */
    public double getUnitCost() {
        return unitCost;
    }

    /**
     * @param unitCost the unitCost to set
     */
    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    /**
     * @return the salesPrice
     */
    public double getSalesPrice() {
        return salesPrice;
    }

    /**
     * @param salesPrice the salesPrice to set
     */
    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    /**
     * @return the addDate
     */
    public Date getAddDate() {
        return addDate;
    }

    /**
     * @param addDate the addDate to set
     */
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    /**
     * @return the imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * @param imageURL the imageURL to set
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
        public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void productIDInitiate() {
        ProductEntryDao dao = new ProductEntryDao();
        this.productId = dao.productIDInitiate();
    }

    public void stockIDInitiate() {
        ProductEntryDao dao = new ProductEntryDao();
        this.stockId = dao.stockIDInitiate();
    }

    public List<Integer> getAllProductIds() {
        ProductEntryDao dao = new ProductEntryDao();
        return dao.getAllProductIds();
    }

    public void getProductByID() {
        ProductEntryDao dao = new ProductEntryDao();
        Product pc = dao.getProductByID(this.productId);
        this.productName = pc.productName;
        this.companyName = pc.companyName;
        this.groupId = pc.groupId;
        this.unitCost=pc.unitCost;
        this.stockInHand=pc.stockInHand;
        this.quantity=pc.quantity;
        this.size=pc.size;
        this.color=pc.color;


}

public String addProduct() {
        ProductEntryDao dao = new ProductEntryDao();
        try {
            saveImg();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        dao.insertProduct(productId, productName, companyName, groupId, stockId, unitCost, salesPrice, addDate, imageURL,size,color);
        productIDInitiate();
        stockIDInitiate();
        this.message=this.getProductName()+" has been saved successfully";
        resetAll();
        return "productEntryForm";

    }
    
    public String updateProduct(){
        ProductEntryDao dao = new ProductEntryDao();
        dao.updateProduct(productId, productName, companyName, groupId, unitCost, salesPrice);
        return "productListView";
    }

    public List<String> getProductGoroups() {
        ProductEntryDao dao = new ProductEntryDao();
        return dao.getProductGoroups();
    }

    public void saveImg() throws IOException {
        String filename = FilenameUtils.getName(uploadedFile.getFileName());
        String basename = FilenameUtils.getBaseName(filename) + "_";
        String extension = "." + FilenameUtils.getExtension(filename);
        File file = File.createTempFile(basename, extension, new File("D:\\Easyshop\\web\\images"));
        FileOutputStream output = new FileOutputStream(file);
        this.imageURL = file.getName();
        InputStream input = uploadedFile.getInputstream();
        //OutputStream output = new FileOutputStream(new File("D:\\Easyshop\\web\\images", filename));
        try {
            IOUtils.copy(input, output);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
    }
    
    public String changeImg() throws IOException {
        String filename = FilenameUtils.getName(uploadedFile.getFileName());
        String basename = FilenameUtils.getBaseName(filename) + "_";
        String extension = "." + FilenameUtils.getExtension(filename);
        File file = File.createTempFile(basename, extension, new File("D:\\Easyshop\\web\\images"));
        this.imageURL = file.getName();
        ProductEntryDao dao = new ProductEntryDao();
        dao.updateImage(imageURL, productId);
        FileOutputStream output = new FileOutputStream(file);    
        InputStream input = uploadedFile.getInputstream();
        //OutputStream output = new FileOutputStream(new File("D:\\Easyshop\\web\\images", filename));
        try {
            IOUtils.copy(input, output);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
            return "productListView";
        }
    }
    
    public List<Product> getAllProducts(){
        ProductEntryDao dao = new ProductEntryDao();
        return dao.getAllProducts();
    }
    
      public List<Product> getProductByIds(String gid){
        ProductEntryDao dao = new ProductEntryDao();
        return dao.getProductsByCatagory(gid);
    }
    
    public void resetAll(){
    this.productName=null;
    this.groupId=null;
    this.companyName=null;
    this.unitCost=0;
    this.salesPrice=0;
    this.imageURL=null;
    this.uploadedFile=null;
    this.stockInHand=0;
    this.totalPrice=0;
    }
    
    public String gotoEditpage(){
        return "update";
    }
    
    public String gotoShowpage(){
    return "show";
}

    
}
