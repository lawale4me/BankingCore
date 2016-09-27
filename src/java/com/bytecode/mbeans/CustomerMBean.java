/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytecode.mbeans;


import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.dto.AccountDTO;
import com.bytecode.entities.Account;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed
 */
@ManagedBean
@RequestScoped
public class CustomerMBean {

    /**
     * Creates a new instance of ProductMBean
     */
    public CustomerMBean() {
    }

    AccountDTO customer;
    private List<AccountDTO> customers;     
    String username;



    ConManager appManager = new ConManager(new AdminRepositoryImpl());

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        username=(String) httpSession.getAttribute("username");  
        //user=appManager.getUser(username);                        
        customers = appManager.getCustomers();
        
    }


    public AccountDTO getCustomer() {
        return customer;
    }

    public void setCustomer(AccountDTO customer) {
        this.customer = customer;
    }

    public List<AccountDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<AccountDTO> customers) {
        this.customers = customers;
    }           

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    
    

}
