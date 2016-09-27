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
@Table(name = "installments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Installments.findAll", query = "SELECT i FROM Installments i"),
    @NamedQuery(name = "Installments.findByInstallmentId", query = "SELECT i FROM Installments i WHERE i.installmentId = :installmentId"),
    @NamedQuery(name = "Installments.findByAmount", query = "SELECT i FROM Installments i WHERE i.amount = :amount"),
    @NamedQuery(name = "Installments.findByChequeNumber", query = "SELECT i FROM Installments i WHERE i.chequeNumber = :chequeNumber"),
    @NamedQuery(name = "Installments.findByPresentationDate", query = "SELECT i FROM Installments i WHERE i.presentationDate = :presentationDate"),    
    @NamedQuery(name = "Installments.findByAppuser", query = "SELECT i FROM Installments i WHERE i.appuser = :appuser"),
    @NamedQuery(name = "Installments.findByInterest", query = "SELECT i FROM Installments i WHERE i.interest = :interest"),
    @NamedQuery(name = "Installments.findByTotal", query = "SELECT i FROM Installments i WHERE i.total = :total"),
    @NamedQuery(name = "Installments.findByStatus", query = "SELECT i FROM Installments i WHERE i.status = :status")})
public class Installments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "installmentId")
    private Integer installmentId;
    @Column(name = "amount")
    private String amount;
    @Column(name = "chequeNumber")
    private String chequeNumber;
    @Column(name = "presentationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date presentationDate;
    @Column(name = "appuser")
    private Integer appuser;
    @Column(name = "interest")
    private String interest;
    @Column(name = "total")
    private String total;
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "loanId", referencedColumnName = "loanid")
    @ManyToOne
    private Loanapllication loanId;

    public Installments() {
    }

    public Installments(Integer installmentId) {
        this.installmentId = installmentId;
    }

    public Integer getInstallmentId() {
        return installmentId;
    }

    public void setInstallmentId(Integer installmentId) {
        this.installmentId = installmentId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public Date getPresentationDate() {
        return presentationDate;
    }

    public void setPresentationDate(Date presentationDate) {
        this.presentationDate = presentationDate;
    }

    public Integer getAppuser() {
        return appuser;
    }

    public void setAppuser(Integer appuser) {
        this.appuser = appuser;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Loanapllication getLoanId() {
        return loanId;
    }

    public void setLoanId(Loanapllication loanId) {
        this.loanId = loanId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (installmentId != null ? installmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Installments)) {
            return false;
        }
        Installments other = (Installments) object;
        if ((this.installmentId == null && other.installmentId != null) || (this.installmentId != null && !this.installmentId.equals(other.installmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bytecode.entities.Installments[ installmentId=" + installmentId + " ]";
    }
    
}
