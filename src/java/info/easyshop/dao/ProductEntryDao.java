/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.easyshop.dao;

import info.easyshop.bean.Product;
import info.easyshop.util.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jahangiralamdiu
 */
public class ProductEntryDao {

    Connection connection = null;

    public void insertProduct(int productId, String productName, String companyName, String groupId, int stockId, double unitCost, double salesPrice, Date addDate, String imageURL,String size,String color) {
        try {
            connection = Database.getConnection();
            PreparedStatement pstm1;
            PreparedStatement pstm2;
            PreparedStatement pstm3;
            String query1 = "insert into product (product_id, product_name, company_name, group_id, imageURL, size, color) values(?, ?, ?, ?, ?,?,?)";
            pstm1 = connection.prepareStatement(query1);
            pstm1.setInt(1, productId);
            pstm1.setString(2, productName);
            pstm1.setString(3, companyName);
            pstm1.setInt(4, Integer.parseInt(groupId));
            pstm1.setString(5, imageURL);
            pstm1.setString(6, size);
            pstm1.setString(7, color);
            int addProductTable = pstm1.executeUpdate();

            String query2 = "insert into stock (stock_id, product_id, unit_cost, sales_price, reorder_date)"
                    + "values (?, ?, ?, ?, ?)";
            pstm2 = connection.prepareStatement(query2);
            pstm2.setInt(1, stockId);
            pstm2.setInt(2, productId);
            pstm2.setDouble(3, unitCost);
            pstm2.setDouble(4, salesPrice);
            pstm2.setDate(5, new java.sql.Date(addDate.getTime()));
            int addStock = pstm2.executeUpdate();

            String query3 = "update product set stock_id=? where product_id=?";

            pstm3 = connection.prepareStatement(query3);

            pstm3.setInt(1, stockId);
            pstm3.setInt(2, productId);

            int updateProduct = pstm3.executeUpdate();
            pstm1.close();
            pstm2.close();
            pstm3.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void updateProduct(int productId, String productName, String companyName, String groupId, double unitCost, double salesPrice) {
        try {
            connection = Database.getConnection();
            PreparedStatement pstm1;
            PreparedStatement pstm2;
            String query1 = "update product set product_name=?, company_name=?, group_id=? where product_id=?";
            pstm1 = connection.prepareStatement(query1);

            pstm1.setString(1, productName);
            pstm1.setString(2, companyName);
            pstm1.setInt(3, Integer.parseInt(groupId));
            pstm1.setInt(4, productId);
            int updateProductTable = pstm1.executeUpdate();

            String query2 = "update stock set unit_cost=?, sales_price=? where product_id=?";

            pstm2 = connection.prepareStatement(query2);

            pstm2.setDouble(1, unitCost);
            pstm2.setDouble(2, salesPrice);
            pstm2.setInt(3, productId);
            int updateStock = pstm2.executeUpdate();
            pstm1.close();
            pstm2.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public int productIDInitiate() {
        int pid = 0;
        try {

            connection = Database.getConnection();

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select MAX(product_id) from product");

            if (rs.next()) {
                pid = rs.getInt("Max(product_id)") + 1;
            }
            rs.close();
            stm.close();
            connection.close();
            return pid;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public int stockIDInitiate() {
        int sid = 0;
        try {

            connection = Database.getConnection();

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select MAX(stock_id) from stock");

            if (rs.next()) {
                sid = rs.getInt("Max(stock_id)") + 1;
            }
            rs.close();
            stm.close();
            connection.close();
            return sid;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    public List<Product> getProductsByCatagory(String gID) {
        ArrayList products = new ArrayList<Product>();
        int i = 0;
        try {
            connection = Database.getConnection();
            String query = "Select p.product_id, p.product_name, p.company_name, p.group_id, p.stock_id, p.imageURL,p.size,p.color, s.unit_cost, s.sales_price, s.avail_stock from product p, stock s where (p.product_id = s.product_id) and (p.group_id=?)";
            PreparedStatement pstm = connection.prepareStatement(query);
            
            pstm.setInt(1, Integer.parseInt(gID));
            
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setProductName(rs.getString("product_name"));
                p.setCompanyName(rs.getString("company_name"));
                p.setGroupId(Integer.toString(rs.getInt("group_id")));
                p.setStockId(rs.getInt("stock_id"));
                p.setImageURL(rs.getString("ImageURL"));
                p.setUnitCost(rs.getDouble("unit_cost"));
                p.setSalesPrice(rs.getDouble("sales_price"));
                p.setStockInHand(rs.getInt("avail_stock"));
                p.setSize(rs.getString("size"));
                p.setColor(rs.getString("color"));
                products.add(p);
            }
            pstm.close();
            rs.close();
            connection.close();
            return products;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public List<String> getProductGoroups() {
        ArrayList productGroup = new ArrayList<String>();
        try {
            connection = Database.getConnection();
            String query = "Select catagory_id from product_catagory";
            PreparedStatement pstm = connection.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                productGroup.add(rs.getInt(1));
            }
            pstm.close();
            connection.close();
            return productGroup;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productGroup;
    }
    
     public List<Integer> getAllProductIds() {
        ArrayList productIDs = new ArrayList<Integer>();
        try {
            connection = Database.getConnection();
            String query = "Select product_id from product";
            PreparedStatement pstm = connection.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                productIDs.add(rs.getInt(1));
            }
            pstm.close();
            connection.close();
            return productIDs;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productIDs;
    }

    public List<Product> getAllProducts() {
        ArrayList products = new ArrayList<Product>();
        int i = 0;
        try {
            connection = Database.getConnection();
            String query = "Select p.product_id, p.product_name, p.company_name, p.group_id, p.stock_id, p.imageURL,p.size,p.color, s.unit_cost, s.sales_price, s.avail_stock from product p, stock s where p.product_id = s.product_id";
            PreparedStatement pstm = connection.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setProductName(rs.getString("product_name"));
                p.setCompanyName(rs.getString("company_name"));
                p.setGroupId(Integer.toString(rs.getInt("group_id")));
                p.setStockId(rs.getInt("stock_id"));
                p.setImageURL(rs.getString("ImageURL"));
                p.setUnitCost(rs.getDouble("unit_cost"));
                p.setSalesPrice(rs.getDouble("sales_price"));
                p.setStockInHand(rs.getInt("avail_stock"));
                p.setSize(rs.getString("size"));
                p.setColor(rs.getString("color"));
                products.add(p);
            }
            pstm.close();
            rs.close();
            connection.close();
            return products;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public Product getProductByID(int productId) {
        Product p = new Product();
        try {
            connection = Database.getConnection();
            String query = "Select p.product_id, p.product_name, p.company_name, p.group_id, p.stock_id, p.imageURL,p.size,p.color, s.unit_cost, s.sales_price, s.avail_stock from product p, stock s where (p.product_id = s.product_id) and (p.product_id=?)";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, productId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {       
                p.setProductId(rs.getInt("product_id"));
                p.setProductName(rs.getString("product_name"));
                p.setCompanyName(rs.getString("company_name"));
                p.setGroupId(Integer.toString(rs.getInt("group_id")));
                p.setStockId(rs.getInt("stock_id"));
                p.setImageURL(rs.getString("ImageURL"));
                p.setUnitCost(rs.getDouble("unit_cost"));
                p.setSalesPrice(rs.getDouble("sales_price"));
                p.setStockInHand(rs.getInt("avail_stock"));
                p.setSize(rs.getString("size"));
                p.setColor(rs.getString("color"));
            }
            pstm.close();
            rs.close();
            connection.close();
            return p;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;

    }

    public void updateImage(String imageURL, int productID) {
        try {
            connection = Database.getConnection();
            String query = "update product set imageURL=? where product_id=?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, imageURL);
            pstm.setInt(2, productID);
            int up = pstm.executeUpdate();
            pstm.close();
            Database.close(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
