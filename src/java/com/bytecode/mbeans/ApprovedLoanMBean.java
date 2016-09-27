/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.mbeans;


import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.entities.Appuser;
import com.bytecode.entities.Loanapllication;
import java.util.Date;
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
public class ApprovedLoanMBean {

    private List<Loanapllication> loans;
    private List<Loanapllication> filteredLoans;
    private Loanapllication loan;
    private Loanapllication selectedLoan;
    String username;
    Appuser user;
    ConManager appManager = new ConManager(new AdminRepositoryImpl());
    
    String Amount=null,chequeNo=null;
    Date presentatioNDate=new Date();
    
    /**
     * Creates a new instance of ActivityMBean
     */
    public ApprovedLoanMBean() {
    }
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
        HttpSession httpSession = request.getSession(false);          
        username=(String) httpSession.getAttribute("username");  
        user=appManager.getUser(username);                        
        loans = appManager.getApprovedLoans();
    }

    public List<Loanapllication> getLoans() {
        return loans;
    }

    public void setLoans(List<Loanapllication> loans) {
        this.loans = loans;
    }

    public List<Loanapllication> getFilteredLoans() {
        return filteredLoans;
    }

    public void setFilteredLoans(List<Loanapllication> filteredLoans) {
        this.filteredLoans = filteredLoans;
    }

    public Loanapllication getLoan() {
        return loan;
    }

    public void setLoan(Loanapllication loan) {
        this.loan = loan;
    }

    public Loanapllication getSelectedLoan() {
        return selectedLoan;
    }

    public void setSelectedLoan(Loanapllication selectedLoan) {
        this.selectedLoan = selectedLoan;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public Date getPresentatioNDate() {
        return presentatioNDate;
    }

    public void setPresentatioNDate(Date presentatioNDate) {
        this.presentatioNDate = presentatioNDate;
    }

             
    
    
    
    
}
