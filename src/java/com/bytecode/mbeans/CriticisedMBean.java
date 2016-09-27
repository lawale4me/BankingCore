/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.mbeans;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.dto.ChequeStatus;
import com.bytecode.entities.Appuser;
import com.bytecode.entities.Loanapllication;
import java.util.Date;
import java.util.HashSet;
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
public class CriticisedMBean {

    
    
    
    private HashSet<Loanapllication> critisedAsset;
    private List<Loanapllication> filteredAssets;
    private Loanapllication asset;
    private Loanapllication selectedAsset;
    String username;    
    Appuser user;
    ConManager appManager = new ConManager(new AdminRepositoryImpl());
    
    Date startdate=null,enddate=null;
    
    /**
     * Creates a new instance of RepaymentMBean
     */
    public CriticisedMBean() {
    }
    
    
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
        HttpSession httpSession = request.getSession(false);          
        username=(String) httpSession.getAttribute("username");  
        user=appManager.getUser(username);                        
        critisedAsset = appManager.getCritisedAssets();        
    }
    
 public ChequeStatus[] getChequeStatuses() {
        return ChequeStatus.values();
    }    
    
    
 public void search(){
        System.out.println("Startdate:"+startdate);
        System.out.println("Enddate:"+enddate);
        critisedAsset = appManager.searchCriticizedAssets(startdate,enddate);        
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }


    
    

    public HashSet<Loanapllication> getCritisedAsset() {
        return critisedAsset;
    }

    public void setCritisedAsset(HashSet<Loanapllication> critisedAsset) {
        this.critisedAsset = critisedAsset;
    }

    public List<Loanapllication> getFilteredAssets() {
        return filteredAssets;
    }

    public void setFilteredAssets(List<Loanapllication> filteredAssets) {
        this.filteredAssets = filteredAssets;
    }

    public Loanapllication getAsset() {
        return asset;
    }

    public void setAsset(Loanapllication asset) {
        this.asset = asset;
    }

    public Loanapllication getSelectedAsset() {
        return selectedAsset;
    }

    public void setSelectedAsset(Loanapllication selectedAsset) {
        this.selectedAsset = selectedAsset;
    }
       
    


    
    
    
}
