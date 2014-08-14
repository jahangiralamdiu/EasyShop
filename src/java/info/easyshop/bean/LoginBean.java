/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.easyshop.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@ViewScoped

/**
 *
 * @author jahangiralamdiu
 */
public class LoginBean implements Serializable {

    String username, password;
    String user;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String requestedURI;

    @PostConstruct
    public void init() {
        requestedURI = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (requestedURI == null) {
            requestedURI = "index.xhtml";
        }
    }

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.login(this.username, this.password);
            this.user = request.getUserPrincipal().getName();
            HttpSession s = request.getSession();
            s.setAttribute("user", this.user);
            Users.setUserName(this.user);
           

        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Login failed."));

        }

        try {
            context.getExternalContext().redirect(requestedURI);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
          Users.setUserName("none");
      
          
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
   
    }

}
