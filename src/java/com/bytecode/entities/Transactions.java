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
import javax.persistence.Lob;
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
@Table(name = "transactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t"),
    @NamedQuery(name = "Transactions.findByTransactionid", query = "SELECT t FROM Transactions t WHERE t.transactionid = :transactionid"),
    @NamedQuery(name = "Transactions.findByAmount", query = "SELECT t FROM Transactions t WHERE t.amount = :amount"),
    @NamedQuery(name = "Transactions.findByTranxdate", query = "SELECT t FROM Transactions t WHERE t.tranxdate = :tranxdate"),
    @NamedQuery(name = "Transactions.findByStatus", query = "SELECT t FROM Transactions t WHERE t.status = :status"),
    @NamedQuery(name = "Transactions.findByResponsecode", query = "SELECT t FROM Transactions t WHERE t.responsecode = :responsecode"),
    @NamedQuery(name = "Transactions.findByDescription", query = "SELECT t FROM Transactions t WHERE t.description = :description"),
    @NamedQuery(name = "Transactions.findByRefnum", query = "SELECT t FROM Transactions t WHERE t.refnum = :refnum"),
    @NamedQuery(name = "Transactions.findByNarration", query = "SELECT t FROM Transactions t WHERE t.narration = :narration"),
    @NamedQuery(name = "Transactions.findBySourceaccount", query = "SELECT t FROM Transactions t WHERE t.sourceaccount = :sourceaccount"),
    @NamedQuery(name = "Transactions.findByDestaccount", query = "SELECT t FROM Transactions t WHERE t.destaccount = :destaccount")})
public class Transactions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "transactionid")
    private Integer transactionid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "tranxdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tranxdate;
    @Column(name = "status")
    private Integer status;
    @Column(name = "responsecode")
    private String responsecode;
    @Column(name = "description")
    private String description;
    @Lob
    @Column(name = "trxnparam")
    private String trxnparam;
    @Column(name = "refnum")
    private String refnum;
    @Column(name = "narration")
    private String narration;
    @Column(name = "sourceaccount")
    private String sourceaccount;
    @Column(name = "destaccount")
    private String destaccount;
    @JoinColumn(name = "accountid", referencedColumnName = "accountid")
    @ManyToOne
    private Account accountid;

    public Transactions() {
    }

    public Transactions(Integer transactionid) {
        this.transactionid = transactionid;
    }

    public Integer getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(Integer transactionid) {
        this.transactionid = transactionid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTranxdate() {
        return tranxdate;
    }

    public void setTranxdate(Date tranxdate) {
        this.tranxdate = tranxdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrxnparam() {
        return trxnparam;
    }

    public void setTrxnparam(String trxnparam) {
        this.trxnparam = trxnparam;
    }

    public String getRefnum() {
        return refnum;
    }

    public void setRefnum(String refnum) {
        this.refnum = refnum;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getSourceaccount() {
        return sourceaccount;
    }

    public void setSourceaccount(String sourceaccount) {
        this.sourceaccount = sourceaccount;
    }

    public String getDestaccount() {
        return destaccount;
    }

    public void setDestaccount(String destaccount) {
        this.destaccount = destaccount;
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
        hash += (transactionid != null ? transactionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.transactionid == null && other.transactionid != null) || (this.transactionid != null && !this.transactionid.equals(other.transactionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bytecode.entities.Transactions[ transactionid=" + transactionid + " ]";
    }
    
}
