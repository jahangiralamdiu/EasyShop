/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.easyshop.bean;

import info.easyshop.dao.ClientEntryDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

/**
 *
 * @author jahangiralamdiu
 */
public class Users {
    
    private static String userName="none", password;
    
    private int clientId;
    
    private String clientName;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Users.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Users.password = password;
    }   
    
    public static int check(){
        if(userName.equals("none")){
            return 0;
        }
        else{
            return 1;
        }
        
    }       
    
 public static Client clientDetails(){
     
     ClientEntryDao dao = new ClientEntryDao();
     return dao.clientDetails();
     
     
 }
    
    
    
}
