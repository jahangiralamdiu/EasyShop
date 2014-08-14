/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.easyshop.dao;

import info.easyshop.bean.PurchaseOrder;
import info.easyshop.util.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jahangiralamdiu
 */
public class PurchaseOrderDao {
    Connection connection=null;
    
    public PurchaseOrder getProductByID(int productId) {
        PurchaseOrder p = new PurchaseOrder();
        try {
            connection = Database.getConnection();
            String query = "Select p.product_id, p.product_name, p.company_name, p.group_id, p.stock_id, p.imageURL, s.unit_cost, s.sales_price, s.avail_stock from product p, stock s where (p.product_id = s.product_id) and (p.product_id=?)";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, productId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {       
                p.setProductId(rs.getInt("product_id"));
                p.setProductName(rs.getString("product_name"));
                p.setCompanyName(rs.getString("company_name"));
                p.setGroupId(Integer.toString(rs.getInt("group_id")));
                p.setStockId(rs.getInt("stock_id"));
                p.setUnitCost(rs.getDouble("unit_cost"));
                p.setStockInHand(rs.getInt("avail_stock"));             
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
    
    public void productPurchase(int productId, int quantity, double totalPrice, Date date){
        
        connection = Database.getConnection();
        PreparedStatement pstm1;
        PreparedStatement pstm2;
        
        String query1="insert into purchase_order (product_id, quantity, total_price, date) values (?, ?, ?, ?)";
        try {
            pstm1=connection.prepareStatement(query1);
           pstm1.setInt(1, productId);
            pstm1.setInt(2, quantity);
            pstm1.setDouble(3, totalPrice);
            pstm1.setDate(4, new java.sql.Date(date.getTime()));
            int addPurchase=pstm1.executeUpdate();
            
            String queryStock="update stock set avail_stock=avail_stock+? where product_id=?";
            pstm2=connection.prepareStatement(queryStock);
            pstm2.setInt(1, quantity);
            pstm2.setInt(2, productId);
            
            int upStock=pstm2.executeUpdate();
            
            pstm1.close();
            pstm2.close();
            Database.close(connection);
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
