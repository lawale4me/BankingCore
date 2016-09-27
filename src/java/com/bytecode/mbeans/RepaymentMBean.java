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
import com.bytecode.entities.Installments;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Ahmed
 */
@ManagedBean
@RequestScoped
public class RepaymentMBean {

    
    
    
    private List<Installments> installments,returnedCheque;
    private List<Installments> filteredInstallments;
    private Installments installment;
    private Installments selectedInstallment;
    String username;    
    Appuser user;
    ConManager appManager = new ConManager(new AdminRepositoryImpl());
    
    Date startdate=null,enddate=null;
    
    /**
     * Creates a new instance of RepaymentMBean
     */
    public RepaymentMBean() {
    }
    
    
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
        HttpSession httpSession = request.getSession(false);          
        username=(String) httpSession.getAttribute("username");  
        user=appManager.getUser(username);                        
        installments = appManager.getAllInstallments();
        returnedCheque = installments;//appManager.getReturnedCheque();
    }

    public List<Installments> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installments> installments) {
        this.installments = installments;
    }

    public List<Installments> getFilteredInstallments() {
        return filteredInstallments;
    }

    public void setFilteredInstallments(List<Installments> filteredInstallments) {
        this.filteredInstallments = filteredInstallments;
    }

    public Installments getInstallment() {
        return installment;
    }

    public void setInstallment(Installments installment) {
        this.installment = installment;
    }

    public Installments getSelectedInstallment() {
        return selectedInstallment;
    }

    public void setSelectedInstallment(Installments selectedInstallment) {
        this.selectedInstallment = selectedInstallment;
    }
    
 public ChequeStatus[] getChequeStatuses() {
        return ChequeStatus.values();
    }    
    
    
 public void search(){
        System.out.println("Startdate:"+startdate);
        System.out.println("Enddate:"+enddate);
        installments = appManager.search(startdate,enddate);        
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

    public List<Installments> getReturnedCheque() {
        return returnedCheque;
    }

    public void setReturnedCheque(List<Installments> returnedCheque) {
        this.returnedCheque = returnedCheque;
    }
 
    
    
    
    public void onEdit(RowEditEvent event) {  
        Installments ins=(Installments) event.getObject();        
        System.out.println("Cheque Number:"+ins.getChequeNumber());
        FacesMessage msg = new FacesMessage("Cheque Number",ins.getChequeNumber());  
        installment=appManager.getInstallment(ins.getInstallmentId());         
        installment.setStatus(ins.getStatus());
        appManager.updateInstallment(installment);
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
       
    
    
    
    
}
