/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.easyshop.dao;

import info.easyshop.util.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

/**
 *
 * @author Riad
 */
public class EmployeeEntryDao {
    
    Connection con=null;
    
    public boolean entryEmployee(String empname,String address,String phone,String email,String designation,Date date,String userid,String password,String role){
    
        try{
           con=Database.getConnection();
           
           String query1="insert into user(user_id,password) values(?,?)";
           PreparedStatement pstm1=con.prepareStatement(query1);
           pstm1.setString(1, userid);
           pstm1.setString(2, password);
           int a=pstm1.executeUpdate();
           
           String query2="insert into employee (employee_name,address,phone,email,designation,join_date,user_id,password) values (?,?,?,?,?,?,?,?)";
           PreparedStatement pstm2=con.prepareStatement(query2);
           pstm2.setString(1, empname);
           pstm2.setString(2, address);
           pstm2.setString(3, phone);
           pstm2.setString(4, email);
           pstm2.setString(5, designation);
           pstm2.setDate(6, new java.sql.Date(date.getTime()));      
           pstm2.setString(7, userid);
           pstm2.setString(8, password);
           int b=pstm2.executeUpdate();
           
           String query3="insert into user_role (role_name,user_id) values(?,?)";
           PreparedStatement pstm3=con.prepareStatement(query3);
           pstm3.setString(1, role);
           pstm3.setString(2, userid);
           int c=pstm3.executeUpdate();
           
           if(a>0 && b>0 && c>0){
             pstm3.close();
             pstm2.close();
             pstm1.close();
             con.close();
             return true;  
           }
           else{
            pstm3.close();
            pstm2.close();
            pstm1.close();
            con.close();
            return false;    
           }
            
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
}
