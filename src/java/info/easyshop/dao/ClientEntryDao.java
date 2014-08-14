/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.easyshop.dao;

import info.easyshop.bean.Client;
import info.easyshop.bean.Users;
import info.easyshop.util.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Riad
 */
public class ClientEntryDao {

    Connection con;

    public boolean entryClient(String name, String email, String mobile, String phone, String address, String userid, String password) {
        try {
            con = Database.getConnection();

            String query1 = "insert into users(user_name,user_pass) values (?,?)";
            PreparedStatement pstm1 = con.prepareStatement(query1);
            pstm1.setString(1, userid);
            pstm1.setString(2, password);
            int a = pstm1.executeUpdate();

            String query2 = "insert into client(client_name,email,mobile,phone,client_address,client_type,user_id) values(?,?,?,?,?,?,?)";
            PreparedStatement pstm2 = con.prepareStatement(query2);
            pstm2.setString(1, name);
            pstm2.setString(2, email);
            pstm2.setString(3, mobile);
            pstm2.setString(4, phone);
            pstm2.setString(5, address);
            pstm2.setString(6, "101");
            pstm2.setString(7, userid);
            int b = pstm2.executeUpdate();

            String query3 = "insert into user_roles(role_name,user_name) values (?,?)";
            PreparedStatement pstm3 = con.prepareStatement(query3);
            pstm3.setString(1, "customer");
            pstm3.setString(2, userid);
            int c = pstm3.executeUpdate();

            if (a > 0 && b > 0 && c > 0) {
                pstm3.close();
                pstm2.close();
                pstm1.close();
                con.close();
                return true;
            } else {
                pstm3.close();
                pstm2.close();
                pstm1.close();
                con.close();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<Client> clientInfoById() {
        ArrayList cdetail = new ArrayList<Client>();
        try {
            con = Database.getConnection();
            String query = "select client_name,email,mobile,phone,client_address,password from client where user_id=?";

            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, Users.getUserName());
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                Client c = new Client();
                c.setName(result.getString("client_name"));
                c.setEmail(result.getString("email"));
                c.setMobile(result.getString("mobile"));
                c.setPhone(result.getString("phone"));
                c.setAddress1(result.getString("client_address"));
                c.setPassword(result.getString("password"));
                cdetail.add(c);
            }
            result.close();
            pstm.close();
            con.close();

            return cdetail;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cdetail;
    }

    public Client clientDetails() {
        Client c = new Client();
        try {
            con = Database.getConnection();
            String query = "select client_name,email,mobile,phone,client_address,password, client_id from client where user_id=?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, Users.getUserName());
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                c.setName(result.getString("client_name"));
                c.setEmail(result.getString("email"));
                c.setMobile(result.getString("mobile"));
                c.setPhone(result.getString("phone"));
                c.setAddress1(result.getString("client_address"));
                c.setPassword(result.getString("password"));
                c.setClientId(result.getInt("client_id"));

            }
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public boolean updateClient(String name, String email, String mobile, String phone, String address, String password) {
        try {
            con = Database.getConnection();
            String query = "update client set client_name=?,email=?,mobile=?,phone=?,client_address=?,password=? where user_id='riad'";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, name);
            pstm.setString(2, email);
            pstm.setString(3, mobile);
            pstm.setString(4, phone);
            pstm.setString(5, address);
            pstm.setString(6, password);
            int a = pstm.executeUpdate();

            if (a > 0) {
                pstm.close();
                con.close();
                return true;
            } else {
                pstm.close();
                con.close();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<String> allClientType() {
        ArrayList<String> clienttypes = new ArrayList<String>();

        try {
            con = Database.getConnection();
            String query = "select client_type from client_type";
            PreparedStatement pstm = con.prepareStatement(query);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                clienttypes.add(result.getString(1));
            }
            return clienttypes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clienttypes;

    }

    public boolean userLogin(String userid, String password) {
        try {
            con = Database.getConnection();
            String query = "select * from client where user_id=? and password=?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, userid);
            pstm.setString(2, password);
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
