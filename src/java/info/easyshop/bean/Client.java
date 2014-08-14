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

/**
 *
 * @author Riad
 */
@ManagedBean
@SessionScoped
public class Client {

    private String type, name, address1, address2, address3, mobile, phone, email, userid, password;
    private String msg, msg2;
    private int clientId;

    public Client() {

    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }    
    

    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
     * @return the msg2
     */
    public String getMsg2() {
        return msg2;
    }

    /**
     * @param msg2 the msg2 to set
     */
    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }

    /**
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
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

    public List<Client> clientInfoById() {
        ClientEntryDao dao = new ClientEntryDao();
        List<Client> ct = dao.clientInfoById();
        return ct;
    }

    public void showClient() {
        ClientEntryDao dao = new ClientEntryDao();
        Client ct = dao.clientDetails();
        if (ct != null) {
            this.name = ct.name;
            this.email = ct.email;
            this.mobile = ct.mobile;
            this.phone = ct.phone;
            this.address1 = ct.address1;
            this.password = ct.password;
        } else {
            this.name = "";
            this.email = "";
            this.mobile = "";
            this.phone = "";
            this.address1 = "";
            this.password = "";
        }
    }

    public String updateCustomer() {
        ClientEntryDao dao = new ClientEntryDao();
        if (dao.updateClient(name, email, mobile, phone, address1, password)) {
            this.msg = "update success";
            return "success";
        } else {
            this.msg = "update fail";
            return "fail";
        }
    }

    public List<String> clientTypeInit() {
        ClientEntryDao dao = new ClientEntryDao();
        return dao.allClientType();
    }

    public String addClientData() {
        ClientEntryDao dao = new ClientEntryDao();
        if (dao.entryClient(name, email, mobile, phone, fullAddress(), userid, password)) {
            clearAll();
            return "success";
        } else {
            this.msg2 = "This User name is already exists . plz try again";
            return "fail";
        }

    }

    public String fullAddress() {
        return this.address1 + "," + this.address2 + "," + this.address3;
    }

    public void clearAll() {
        this.address1 = "";
        this.address2 = "";
        this.address3 = "";
        this.email = "";
        this.mobile = "";
        this.name = "";
        this.password = "";
        this.phone = "";
        this.userid = "";
    }

    public String goLogForm() {
        return "login";
    }
    
    public String goToReg(){
        return "regs";
    }

}
