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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "loan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Loan.findAll", query = "SELECT l FROM Loan l"),
    @NamedQuery(name = "Loan.findByLoanid", query = "SELECT l FROM Loan l WHERE l.loanid = :loanid"),
    @NamedQuery(name = "Loan.findByAmount", query = "SELECT l FROM Loan l WHERE l.amount = :amount"),
    @NamedQuery(name = "Loan.findByApprovedby", query = "SELECT l FROM Loan l WHERE l.approvedby = :approvedby"),
    @NamedQuery(name = "Loan.findByDeniedby", query = "SELECT l FROM Loan l WHERE l.deniedby = :deniedby"),
    @NamedQuery(name = "Loan.findByEnddate", query = "SELECT l FROM Loan l WHERE l.enddate = :enddate"),
    @NamedQuery(name = "Loan.findByInstallments", query = "SELECT l FROM Loan l WHERE l.installments = :installments"),
    @NamedQuery(name = "Loan.findByLoanaccountno", query = "SELECT l FROM Loan l WHERE l.loanaccountno = :loanaccountno"),
    @NamedQuery(name = "Loan.findByLoandate", query = "SELECT l FROM Loan l WHERE l.loandate = :loandate"),
    @NamedQuery(name = "Loan.findByRequestdate", query = "SELECT l FROM Loan l WHERE l.requestdate = :requestdate"),
    @NamedQuery(name = "Loan.findByStatus", query = "SELECT l FROM Loan l WHERE l.status = :status")})
public class Loan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "loanid")
    private Integer loanid;
    @Column(name = "amount")
    private String amount;
    @Column(name = "approvedby")
    private String approvedby;
    @Column(name = "deniedby")
    private String deniedby;
    @Column(name = "enddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    @Column(name = "installments")
    private String installments;
    @Column(name = "loanaccountno")
    private String loanaccountno;
    @Column(name = "loandate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loandate;
    @Column(name = "requestdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestdate;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "loanproductid", referencedColumnName = "loanproductid")
    @ManyToOne
    private Loanproduct loanproductid;
    @JoinColumn(name = "accountid", referencedColumnName = "accountid")
    @ManyToOne
    private Account accountid;

    public Loan() {
    }

    public Loan(Integer loanid) {
        this.loanid = loanid;
    }

    public Integer getLoanid() {
        return loanid;
    }

    public void setLoanid(Integer loanid) {
        this.loanid = loanid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getApprovedby() {
        return approvedby;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public String getDeniedby() {
        return deniedby;
    }

    public void setDeniedby(String deniedby) {
        this.deniedby = deniedby;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getInstallments() {
        return installments;
    }

    public void setInstallments(String installments) {
        this.installments = installments;
    }

    public String getLoanaccountno() {
        return loanaccountno;
    }

    public void setLoanaccountno(String loanaccountno) {
        this.loanaccountno = loanaccountno;
    }

    public Date getLoandate() {
        return loandate;
    }

    public void setLoandate(Date loandate) {
        this.loandate = loandate;
    }

    public Date getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(Date requestdate) {
        this.requestdate = requestdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Loanproduct getLoanproductid() {
        return loanproductid;
    }

    public void setLoanproductid(Loanproduct loanproductid) {
        this.loanproductid = loanproductid;
    }

    public Account getAccountid() {
        return accountid;
    }

    public void setAccountid(Account accountid) {
        this.accountid = accountid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loanid != null ? loanid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loan)) {
            return false;
        }
        Loan other = (Loan) object;
        if ((this.loanid == null && other.loanid != null) || (this.loanid != null && !this.loanid.equals(other.loanid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bytecode.entities.Loan[ loanid=" + loanid + " ]";
    }
    
}
