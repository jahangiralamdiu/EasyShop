/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.easyshop.dao;

import info.easyshop.bean.CartItem;
import info.easyshop.bean.Product;
import info.easyshop.bean.Users;
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
public class CheckOutDao {
    Connection connection=null;
    public int orderIDInitiate() {
        int oid=0;
        try {

            connection = Database.getConnection();

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select MAX(rorderid) from requested_order");

            if (rs.next()) {
                oid = rs.getInt("Max(rorderid)") + 1;
            }
            rs.close();
            stm.close();
            connection.close();
            return oid;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    public void placeOrder(int rorderid, String payMethod, String tid, String address) {
        List<Product> product = new ArrayList<Product>();
        product=CartItem.cartDetails();
        int orderLine = 1;
        connection = Database.getConnection();
        PreparedStatement pstm = null;
        PreparedStatement pstm2 = null;
        try {
           String fquery="insert into requested_order (rorderid, rorder_date, total_price, payment_method, transaction_id, delivery_address, client_id) values (?, ?, ?, ?, ?, ?, ?)";
           pstm = connection.prepareStatement(fquery);
           pstm.setInt(1, rorderid);
           pstm.setDate(2, new java.sql.Date(new Date().getTime()));
           pstm.setDouble(3, CartItem.calPrice());
           pstm.setString(4, payMethod);
           pstm.setString(5, tid);
           pstm.setString(6, address);
           pstm.setInt(7, Users.clientDetails().getClientId());
           int rowAdd = pstm.executeUpdate();
   
            for (Product p : product) {
                String query = "insert into requested_orderline (rorder_serial, rorder_id, product_id, quantity, total, date, client_id) values(?,?,?,?,?, ?, ?)";

                pstm2 = connection.prepareStatement(query);
                pstm2.setInt(1, Integer.parseInt(""+rorderid+orderLine));
                pstm2.setInt(2, rorderid);
                pstm2.setInt(3, p.getProductId());
                pstm2.setInt(4, p.getQuantity());
                pstm2.setDouble(5, p.getPrice());
                pstm2.setDate(6, new java.sql.Date(new Date().getTime()));
                pstm2.setInt(7, Users.clientDetails().getClientId());
                int addProduct = pstm2.executeUpdate();
                orderLine++;
            }
            pstm.close();
            pstm2.close();
            Database.close(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        

    }
}
