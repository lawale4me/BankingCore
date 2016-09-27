/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.mbeans;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.entities.Appuser;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed
 */
@ManagedBean
@ViewScoped
public class NewUserMBean {

    String username;
    Appuser adminUser,selectedUser;
    List<Appuser> adminAppusers,filteredAppusers;
    ConManager appManager = new ConManager(new AdminRepositoryImpl());
    
    /**
     * Creates a new instance of NewUserMBean
     */
    public NewUserMBean() {
    }
    
    @PostConstruct
    public void init() {
        
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
        HttpSession httpSession = request.getSession(false);          
        username=(String) httpSession.getAttribute("username");  
        adminUser=appManager.getUser(username);                
        adminAppusers = appManager.getAllAppusers();
        
    }

    public List<Appuser> getAdminAppusers() {
        return adminAppusers;
    }

    public void setAdminAppusers(List<Appuser> adminAppusers) {
        this.adminAppusers = adminAppusers;
    }

    public Appuser getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Appuser selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<Appuser> getFilteredAppusers() {
        return filteredAppusers;
    }

    public void setFilteredAppusers(List<Appuser> filteredAppusers) {
        this.filteredAppusers = filteredAppusers;
    }
    
    
    public void onRowSelect(){
        
    }
    
    
}
