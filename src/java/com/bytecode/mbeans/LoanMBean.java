/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytecode.mbeans;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.dto.LoanStatus;
import com.bytecode.dto.LoanType;
import com.bytecode.entities.Appuser;
import com.bytecode.entities.Installments;
import com.bytecode.entities.Loanapllication;
import com.bytecode.util.Email;
import com.bytecode.util.ResponseCode;
import com.bytecode.util.SendEmail;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
public class LoanMBean {

    private List<Loanapllication> loans;
    private List<Loanapllication> filteredLoans;
    private Loanapllication loan;
    private Loanapllication selectedLoan;
    String username;
    String approvedAmount = "";
    String referenceNo = "";
    String comment = "";
    String tenor = "";
    Appuser user;
    ConManager appManager = new ConManager(new AdminRepositoryImpl());

    /**
     * Creates a new instance of ActivityMBean
     */
    public LoanMBean() {
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        username = (String) httpSession.getAttribute("username");
        user = appManager.getUser(username);
        loans = appManager.getPendingLoans();
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

    public String getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(String approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String approveLoan() {
        System.out.println("comment" + comment);
        System.out.println("approvedAmount:" + approvedAmount);

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String referenceno = params.get("referenceno");
        referenceNo = referenceno;

        System.out.println("referenceno:" + referenceno);

        FacesMessage msg;// = null;
        Loanapllication ploan = appManager.getLoan(LoanStatus.PENDING, referenceno);
        if (ploan == null) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Loan reference Not found", referenceno);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

        try {
            ploan.setComment(comment);
            ploan.setApprovedAmount(approvedAmount);
            ploan.setApprovedby(username);
            ploan.setStatus(LoanStatus.APPROVED.ordinal());
            appManager.updateLoan(ploan);
            FacesContext context = FacesContext.getCurrentInstance();
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Loan approved", referenceno);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            SendEmail sendObject = new SendEmail();
            Email mail = new Email();
            mail.setEmailAddress(loan.getEmail());
            mail.setMessage(sendObject.constructLoanApproval(loan));
            mail.setSubject(ResponseCode.LOAN_APPROVED);
            sendObject.sendSimpleMail(mail);

            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            String logonUser = (String) httpSession.getAttribute("username");

            appManager.audit(logonUser, "Loan " + referenceno + " has been approved  Amount:" + approvedAmount, "IPADDRESS");

            return "pretty:home";
        } catch (Exception ex) {
            ex.printStackTrace();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loan approval Error", ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

    }

    public String cancelLoan() {
        System.out.println("comment" + comment);

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String referenceno = params.get("referenceno");
        referenceNo = referenceno;

        System.out.println("referenceno:" + referenceno);

        FacesMessage msg;// = null;
        Loanapllication ploan = appManager.getLoan(LoanStatus.PENDING, referenceno);
        if (ploan == null) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Loan reference Not found", referenceno);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

        try {
            ploan.setComment(comment);
            ploan.setStatus(LoanStatus.CANCELED.ordinal());
            appManager.updateLoan(ploan);
            FacesContext context = FacesContext.getCurrentInstance();
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Loan Canceled", referenceno);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            SendEmail sendObject = new SendEmail();
            Email mail = new Email();
            mail.setEmailAddress(loan.getEmail());
            mail.setMessage(sendObject.constructLoanCANCELED(loan));
            mail.setSubject(ResponseCode.LOAN_CANCELED);
            sendObject.sendSimpleMail(mail);

            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            String logonUser = (String) httpSession.getAttribute("username");

            appManager.audit(logonUser, "Loan " + referenceno + " has been canceled", "IPADDRESS");

            return "pretty:home";
        } catch (Exception ex) {
            ex.printStackTrace();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loan approval Error", ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    //AUTHORIZATION
    public String authorizeLoan() {
        System.out.println("comment" + comment);
        System.out.println("approvedAmount:" + approvedAmount);

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String referenceno = params.get("referenceno");
        referenceNo = referenceno;

        System.out.println("referenceno:" + referenceno);

        FacesMessage msg;// = null;
        Loanapllication ploan = appManager.getLoan(LoanStatus.APPROVED, referenceno);
        if (ploan == null) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Loan reference Not found", referenceno);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

        if (ploan.getInstallmentsCollection().size() <= 2) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Repayments Not found", referenceno);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

        try {
            ploan.setComment(comment);
            ploan.setApprovedAmount(approvedAmount);
            ploan.setStatus(LoanStatus.COMPLETED.ordinal());
            appManager.updateLoan(ploan);
            FacesContext context = FacesContext.getCurrentInstance();
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Loan authorized", referenceno);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
            SendEmail sendObject = new SendEmail();
            Email mail = new Email();
            mail.setEmailAddress(loan.getEmail());
            mail.setMessage(sendObject.constructLoanAUTHORIZED(loan));
            mail.setSubject(ResponseCode.LOAN_AUTHORIZED);
            sendObject.sendSimpleMail(mail);

            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            String logonUser = (String) httpSession.getAttribute("username");

            appManager.audit(logonUser, "Loan " + referenceno + " has been authorized Amount:" + approvedAmount, "IPADDRESS");

            return "pretty:home";
        } catch (Exception ex) {
            ex.printStackTrace();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loan approval Error", ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

    }

    //END OF AUTHORIZATION
    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String addInstallments() {
        System.out.println("adding Installments");
        return null;
    }

    public String disburseLoan() {
        System.out.println("comment" + comment);

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String referenceno = params.get("referenceno");
        referenceNo = referenceno;

        System.out.println("referenceno:" + referenceno);

        FacesMessage msg;// = null;
        Loanapllication ploan = appManager.getLoan(LoanStatus.COMPLETED, referenceno);
        if (ploan == null) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Loan reference Not found", referenceno);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

        try {
            ploan.setComment(comment);
            ploan.setStatus(LoanStatus.DISBURSRED.ordinal());
            appManager.updateLoan(ploan);
            FacesContext context = FacesContext.getCurrentInstance();
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Loan DISBURSRED", referenceno);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            SendEmail sendObject = new SendEmail();
            Email mail = new Email();
            mail.setEmailAddress(loan.getEmail());
            mail.setMessage(sendObject.constructLoanCANCELED(loan));
            mail.setSubject(ResponseCode.LOAN_DISBURSRED);
            sendObject.sendSimpleMail(mail);

            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            String logonUser = (String) httpSession.getAttribute("username");

            appManager.audit(logonUser, "Loan " + referenceno + " has been DISBURSRED", "IPADDRESS");

            return "pretty:home";
        } catch (Exception ex) {
            ex.printStackTrace();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loan approval Error", ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    public String getType(String value){
        try{
        return LoanType.values()[Integer.parseInt(value.replace("+","").trim())].name();
        }catch(Exception e){
        return LoanType.values()[Integer.parseInt(value)].name();
        }
    }
    
}
