/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytecode.mbeans;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.dto.ChequeStatus;
import com.bytecode.dto.LoanStatus;
import com.bytecode.entities.Appuser;
import com.bytecode.entities.Installments;
import com.bytecode.entities.Loanapllication;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Ahmed
 */
@ManagedBean
@RequestScoped
public class InstallmentMBean {

    private List<Installments> installments;
    private List<Installments> filteredInstallments;
    private Installments installment;
    private Installments selectedInstallment;
    String username;
    String chequeNo = "";
    String referenceNo = "";
    String refNum = "";
    Double interest = 0d;
    Double total = 0d;
    Date presentationDate = null, startdate = null, enddate = null;
    Double amount = 0d;
    Appuser user;
    ConManager appManager = new ConManager(new AdminRepositoryImpl());

    /**
     * Creates a new instance of ActivityMBean
     */
    public InstallmentMBean() {
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        username = (String) httpSession.getAttribute("username");
        user = appManager.getUser(username);

        referenceNo = request.getParameter("referenceno");
        if (referenceNo == null) {
            referenceNo = request.getParameter("refNum");
        }
        installments = appManager.getInstallments(referenceNo);
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public Date getPresentationDate() {
        return presentationDate;
    }

    public void setPresentationDate(Date presentationDate) {
        this.presentationDate = presentationDate;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public void onRowEdit(RowEditEvent event) {
        System.out.println("installments:" + installments);
        Installments ins = ((Installments) event.getObject());
        installment = appManager.getInstallment(ins.getInstallmentId());
        if (installment != null) 
        {
            installment.setAmount(amount.toString());
            installment.setChequeNumber(chequeNo);
            installment.setInterest(interest.toString());
            installment.setTotal(total.toString());
            appManager.updateInstallment(installment);
            
            FacesMessage msg = new FacesMessage("Installment Edited", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onRowCancel(RowEditEvent event) {
        Installments ins = ((Installments) event.getObject());
        installment = appManager.getInstallment(ins.getInstallmentId());
        
        FacesMessage msg = new FacesMessage("Edit Cancelled", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public String addInstallment() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        refNum = request.getParameter("refNum");
        System.out.println("ReferenceNo:" + referenceNo);
        System.out.println("refNum:" + refNum);
        if (refNum != null) {
            Installments obj = new Installments();
            obj.setAmount(amount.toString());
            obj.setInterest(interest.toString());
            obj.setTotal(((Double) (amount + interest)).toString());
            obj.setAppuser(user.getId().intValue());
            obj.setChequeNumber(chequeNo);
            obj.setPresentationDate(presentationDate);
            obj.setStatus(ChequeStatus.PENDING.name());

            chequeNo = null;
            amount = null;
            presentationDate = null;
            //********************
            Loanapllication loan = appManager.getLoanByRef(refNum);
            if (loan == null) {
                FacesMessage msg = new FacesMessage("Installment Error", "Reference Number does not exists");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;
            }
            System.out.println("obj:" + obj);
            System.out.println("loan:" + loan);
            obj.setLoanId(loan);

            referenceNo = refNum;

            Installments inst = appManager.addInstallment(obj, refNum);
            installments.add(inst);

            return referenceNo;
        }

        return "Reference is NULL";

    }

    public String getRefNum() {
        return refNum;
    }

    public void setRefNum(String refNum) {
        this.refNum = refNum;
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

}
