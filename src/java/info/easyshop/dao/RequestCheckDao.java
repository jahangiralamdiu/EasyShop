/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.easyshop.dao;

import info.easyshop.bean.RequestCheck;
import info.easyshop.util.Database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SHOHUG
 */
public class RequestCheckDao {

    Connection connection = null;

    public List<RequestCheck> getViewRequest() throws SQLException {
        ArrayList arrayList = new ArrayList<RequestCheck>();
        connection = Database.getConnection();
        String query = "select * from requested_order where order_status='pending'";
        PreparedStatement pstm = connection.prepareStatement(query);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            RequestCheck rc = new RequestCheck();
            rc.setRorderId(rs.getInt("rorderid"));
            rc.setClientId(rs.getInt("client_id"));
            rc.setRorderDate(rs.getDate("rorder_date"));
            rc.setTotalPrice(rs.getDouble("total_price"));
            rc.setTransactionId(rs.getString("transaction_id"));
            rc.setPinNumber(rs.getString("pin_number"));
            rc.setAddress(rs.getString("delivery_address"));
            rc.setPaymentMethod(rs.getString("payment_method"));
            rc.setOr_status(rs.getString("order_status"));
            arrayList.add(rc);
        }
        pstm.close();
        rs.close();
        connection.close();
        return arrayList;
    }
/////  Data Insert into order_table , Order_line

    public void insertOrdertable(int rorderId, java.util.Date rorderDate, int clientId, double totalPrice, String paymentMethod, String transactionId, String pinNumber, String address) throws SQLException {

        connection = Database.getConnection();
        String queryRo = "insert into order_table (order_id, order_date, client_id, total_price, payment_method, transaction_id, pin_number, delivery_address, order_status)"
                + " values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm1 = connection.prepareStatement(queryRo);
        pstm1.setInt(1, rorderId);
        pstm1.setDate(2, (Date) rorderDate);
        pstm1.setInt(3, clientId);
        pstm1.setDouble(4, totalPrice);
        pstm1.setString(5, paymentMethod);
        pstm1.setString(6, transactionId);
        pstm1.setString(7, pinNumber);
        pstm1.setString(8, address);
        pstm1.setString(9, "Accepted");

        int addRo = pstm1.executeUpdate();

        String qureyRol = "INSERT INTO order_line (order_serial, order_id, client_id, product_id, quantity, total, date )"
                + "SELECT rorder_serial, rorder_id, client_id, product_id, quantity, total, date \n"
                + "FROM requested_orderline\n"
                + "WHERE (rorder_id=" + rorderId + ")";
        PreparedStatement pstm2 = connection.prepareStatement(qureyRol);
        int q = pstm2.executeUpdate();

        String update = "update requested_order set order_status='Accepted' where rorderid="+rorderId+"";
        PreparedStatement pstm3 = connection.prepareStatement(update);
        int u = pstm3.executeUpdate();

        pstm1.close();
        pstm2.close();
        pstm3.close();

        Database.close(connection);
        //return arr2;
    }
    
    public void cancelRequest(int rorderId) throws SQLException{
        connection = Database.getConnection();
        String update = "update requested_order set order_status='Canceled' where rorderid="+rorderId+"";
        PreparedStatement pstm4 = connection.prepareStatement(update);
        int u = pstm4.executeUpdate();
        pstm4.close();
        connection.close();
    }

}
