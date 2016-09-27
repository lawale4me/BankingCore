/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytecode.mbeans;


import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.entities.Expense;
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
public class ExpenseMBean {

    /**
     * Creates a new instance of ProductMBean
     */
    public ExpenseMBean() {
    }

    private String message,appuser,amount,purpose;
    private Integer messageId;
    
    Expense expense;
    private List<Expense> expenses;        



    ConManager appManager = new ConManager(new AdminRepositoryImpl());

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        appuser=(String) httpSession.getAttribute("username");  
        //user=appManager.getUser(username);                        
        expenses = appManager.getExpenses();
        
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getAppuser() {
        return appuser;
    }

    public void setAppuser(String appuser) {
        this.appuser = appuser;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }                

    public String addAction() {
        Expense exp = new Expense();
        exp.setAppuser(appuser);
        exp.setAmount(amount);
        exp.setExpenseDate(new Date());
        exp.setPurpose(purpose);        
        
        exp = appManager.addExpense(exp);
        expenses.add(exp);

        message = "";
        messageId = 0;
        messageId = null;
        return null;
    }

 

    public void onEdit(RowEditEvent event) {
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Expense Deleted");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Expense exp = (Expense) event.getObject();
        exp = appManager.getExpense(exp.getExpenseId());
        appManager.removeExpense(exp);
        expenses.remove(exp);
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }


    
    
    

}
