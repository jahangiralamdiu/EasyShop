/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.easyshop.bean;

import info.easyshop.dao.EmployeeEntryDao;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Riad
 */
@ManagedBean
@SessionScoped
public class Employee {
private String empname;
private String address;
private String phone;
private String email;
private String designation;
private Date date;
private String userid;
private String password;
private String role;



private String msg;
private String address2;
private String address3;

    /**
     * @return the empname
     */
    public String getEmpname() {
        return empname;
    }

    /**
     * @param empname the empname to set
     */
    public void setEmpname(String empname) {
        this.empname = empname;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
    
    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    
     /**
     * @return the address3
     */
    public String getAddress3() {
        return address3;
    }

    /**
     * @param address3 the address3 to set
     */
    public void setAddress3(String address3) {
        this.address3 = address3;
    }
    
    public String fullAddress(){
    return  this.address+","+this.address2+","+this.address3;
    }
    
    
    public String addEmployeeData(){
    EmployeeEntryDao dao=new EmployeeEntryDao();
    if(dao.entryEmployee(empname, fullAddress(), phone, email, designation, date, userid, password, role)){
      clearAll();  
      return "success";  
    }
    else{
        this.msg="This user name/email is already exists";
        return "unsuccess";
    }
    }
    
    public void clearAll(){
     
        this.address="";
        this.address2="";
        this.address3="";
        this.date=null;
        this.designation="";       
        this.password="";
        this.phone="";
        this.email="";
        this.role="";
        this.userid="";
        this.empname="";
        this.msg="";
       
        
    }

   

    

    

    

}
