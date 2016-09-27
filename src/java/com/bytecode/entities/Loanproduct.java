/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Ahmed
 */
@Entity
@Table(name = "loanproduct")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Loanproduct.findAll", query = "SELECT l FROM Loanproduct l"),
    @NamedQuery(name = "Loanproduct.findByLoanproductid", query = "SELECT l FROM Loanproduct l WHERE l.loanproductid = :loanproductid"),
    @NamedQuery(name = "Loanproduct.findByLoanname", query = "SELECT l FROM Loanproduct l WHERE l.loanname = :loanname"),
    @NamedQuery(name = "Loanproduct.findByLoancharge", query = "SELECT l FROM Loanproduct l WHERE l.loancharge = :loancharge"),
    @NamedQuery(name = "Loanproduct.findByLoanrepayment", query = "SELECT l FROM Loanproduct l WHERE l.loanrepayment = :loanrepayment")})
public class Loanproduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "loanproductid")
    private Integer loanproductid;
    @Column(name = "loanname")
    private String loanname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "loancharge")
    private Double loancharge;
    @Column(name = "loanrepayment")
    private Double loanrepayment;
    @OneToMany(mappedBy = "loanproductid")
    private Collection<Loan> loanCollection;

    public Loanproduct() {
    }

    public Loanproduct(Integer loanproductid) {
        this.loanproductid = loanproductid;
    }

    public Integer getLoanproductid() {
        return loanproductid;
    }

    public void setLoanproductid(Integer loanproductid) {
        this.loanproductid = loanproductid;
    }

    public String getLoanname() {
        return loanname;
    }

    public void setLoanname(String loanname) {
        this.loanname = loanname;
    }

    public Double getLoancharge() {
        return loancharge;
    }

    public void setLoancharge(Double loancharge) {
        this.loancharge = loancharge;
    }

    public Double getLoanrepayment() {
        return loanrepayment;
    }

    public void setLoanrepayment(Double loanrepayment) {
        this.loanrepayment = loanrepayment;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Loan> getLoanCollection() {
        return loanCollection;
    }

    public void setLoanCollection(Collection<Loan> loanCollection) {
        this.loanCollection = loanCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loanproductid != null ? loanproductid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loanproduct)) {
            return false;
        }
        Loanproduct other = (Loanproduct) object;
        if ((this.loanproductid == null && other.loanproductid != null) || (this.loanproductid != null && !this.loanproductid.equals(other.loanproductid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bytecode.entities.Loanproduct[ loanproductid=" + loanproductid + " ]";
    }
    
}
