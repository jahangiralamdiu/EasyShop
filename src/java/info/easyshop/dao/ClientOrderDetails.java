/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.easyshop.dao;

import info.easyshop.bean.RequestCheck;
import info.easyshop.util.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SHOHUG
 */
public class ClientOrderDetails {
    Connection connection = null;

    public List<RequestCheck> getClientRequest(int clientId) throws SQLException {
        ArrayList arrayList = new ArrayList<RequestCheck>();
        connection = Database.getConnection();
        String query = "select * from requested_order where client_id="+1001+"";
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
}
