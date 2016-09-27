/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytecode.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmed
 */
@Entity
@Table(name = "expense")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Expense.findAll", query = "SELECT e FROM Expense e"),
    @NamedQuery(name = "Expense.findByExpenseId", query = "SELECT e FROM Expense e WHERE e.expenseId = :expenseId"),
    @NamedQuery(name = "Expense.findByAmount", query = "SELECT e FROM Expense e WHERE e.amount = :amount"),
    @NamedQuery(name = "Expense.findByPurpose", query = "SELECT e FROM Expense e WHERE e.purpose = :purpose"),
    @NamedQuery(name = "Expense.findByExpenseDate", query = "SELECT e FROM Expense e WHERE e.expenseDate = :expenseDate"),
    @NamedQuery(name = "Expense.findByAppuser", query = "SELECT e FROM Expense e WHERE e.appuser = :appuser")})
public class Expense implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "expenseId")
    private Integer expenseId;
    @Column(name = "amount")
    private String amount;
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "expenseDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expenseDate;
    @Column(name = "appuser")
    private String appuser;

    public Expense() {
    }

    public Expense(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
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

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getAppuser() {
        return appuser;
    }

    public void setAppuser(String appuser) {
        this.appuser = appuser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (expenseId != null ? expenseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expense)) {
            return false;
        }
        Expense other = (Expense) object;
        if ((this.expenseId == null && other.expenseId != null) || (this.expenseId != null && !this.expenseId.equals(other.expenseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bytecode.entities.Expense[ expenseId=" + expenseId + " ]";
    }
    
}
