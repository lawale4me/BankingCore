/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Ahmed
 */
@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAccountid", query = "SELECT a FROM Account a WHERE a.accountid = :accountid"),
    @NamedQuery(name = "Account.findByAccountname", query = "SELECT a FROM Account a WHERE a.accountname = :accountname"),
    @NamedQuery(name = "Account.findByAccountemail", query = "SELECT a FROM Account a WHERE a.accountemail = :accountemail"),
    @NamedQuery(name = "Account.findByAccountphone", query = "SELECT a FROM Account a WHERE a.accountphone = :accountphone"),
    @NamedQuery(name = "Account.findByAddress", query = "SELECT a FROM Account a WHERE a.address = :address"),
    @NamedQuery(name = "Account.findByStatus", query = "SELECT a FROM Account a WHERE a.status = :status"),
    @NamedQuery(name = "Account.findByDateopened", query = "SELECT a FROM Account a WHERE a.dateopened = :dateopened"),
    @NamedQuery(name = "Account.findByDob", query = "SELECT a FROM Account a WHERE a.dob = :dob"),
    @NamedQuery(name = "Account.findByGender", query = "SELECT a FROM Account a WHERE a.gender = :gender"),
    @NamedQuery(name = "Account.findByPin", query = "SELECT a FROM Account a WHERE a.pin = :pin"),
    @NamedQuery(name = "Account.findByUserid", query = "SELECT a FROM Account a WHERE a.userid = :userid")})
public class Account implements Serializable {
    @OneToMany(mappedBy = "accountid")
    private Collection<Loan> loanCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "accountid")
    private Integer accountid;
    @Basic(optional = false)
    @Column(name = "accountname")
    private String accountname;
    @Column(name = "accountemail")
    private String accountemail;
    @Basic(optional = false)
    @Column(name = "accountphone")
    private String accountphone;
    @Column(name = "address")
    private String address;
    @Column(name = "status")
    private Integer status;
    @Column(name = "dateopened")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateopened;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "gender")
    private String gender;
    @Column(name = "pin")
    private String pin;
    @Column(name = "userid")
    private String userid;

    public Account() {
    }

    public Account(Integer accountid) {
        this.accountid = accountid;
    }

    public Account(Integer accountid, String accountname, String accountphone) {
        this.accountid = accountid;
        this.accountname = accountname;
        this.accountphone = accountphone;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getAccountemail() {
        return accountemail;
    }

    public void setAccountemail(String accountemail) {
        this.accountemail = accountemail;
    }

    public String getAccountphone() {
        return accountphone;
    }

    public void setAccountphone(String accountphone) {
        this.accountphone = accountphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDateopened() {
        return dateopened;
    }

    public void setDateopened(Date dateopened) {
        this.dateopened = dateopened;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountid != null ? accountid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountid == null && other.accountid != null) || (this.accountid != null && !this.accountid.equals(other.accountid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bytecode.entities.Account[ accountid=" + accountid + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Loan> getLoanCollection() {
        return loanCollection;
    }

    public void setLoanCollection(Collection<Loan> loanCollection) {
        this.loanCollection = loanCollection;
    }
    
}
