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
@Table(name = "paccount")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paccount.findAll", query = "SELECT p FROM Paccount p"),
    @NamedQuery(name = "Paccount.findByPaccountid", query = "SELECT p FROM Paccount p WHERE p.paccountid = :paccountid"),
    @NamedQuery(name = "Paccount.findByAccountno", query = "SELECT p FROM Paccount p WHERE p.accountno = :accountno"),
    @NamedQuery(name = "Paccount.findByBalance", query = "SELECT p FROM Paccount p WHERE p.balance = :balance"),
    @NamedQuery(name = "Paccount.findByLedgerbalance", query = "SELECT p FROM Paccount p WHERE p.ledgerbalance = :ledgerbalance"),
    @NamedQuery(name = "Paccount.findByDateopened", query = "SELECT p FROM Paccount p WHERE p.dateopened = :dateopened")})
public class Paccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "paccountid")
    private Integer paccountid;
    @Column(name = "accountno")
    private String accountno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "balance")
    private Double balance;
    @Column(name = "ledgerbalance")
    private Double ledgerbalance;
    @Column(name = "dateopened")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateopened;
    @JoinColumn(name = "prodtype", referencedColumnName = "productid")
    @ManyToOne
    private Product prodtype;
    @JoinColumn(name = "accountid", referencedColumnName = "accountid")
    @ManyToOne
    private Account accountid;

    public Paccount() {
    }

    public Paccount(Integer paccountid) {
        this.paccountid = paccountid;
    }

    public Integer getPaccountid() {
        return paccountid;
    }

    public void setPaccountid(Integer paccountid) {
        this.paccountid = paccountid;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getLedgerbalance() {
        return ledgerbalance;
    }

    public void setLedgerbalance(Double ledgerbalance) {
        this.ledgerbalance = ledgerbalance;
    }

    public Date getDateopened() {
        return dateopened;
    }

    public void setDateopened(Date dateopened) {
        this.dateopened = dateopened;
    }

    public Product getProdtype() {
        return prodtype;
    }

    public void setProdtype(Product prodtype) {
        this.prodtype = prodtype;
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
        hash += (paccountid != null ? paccountid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paccount)) {
            return false;
        }
        Paccount other = (Paccount) object;
        if ((this.paccountid == null && other.paccountid != null) || (this.paccountid != null && !this.paccountid.equals(other.paccountid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bytecode.entities.Paccount[ paccountid=" + paccountid + " ]";
    }
    
}
