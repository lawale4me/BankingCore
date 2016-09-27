/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "guarantor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Guarantor.findAll", query = "SELECT g FROM Guarantor g"),
    @NamedQuery(name = "Guarantor.findById", query = "SELECT g FROM Guarantor g WHERE g.id = :id"),
    @NamedQuery(name = "Guarantor.findByClientRelnCvId", query = "SELECT g FROM Guarantor g WHERE g.clientRelnCvId = :clientRelnCvId"),
    @NamedQuery(name = "Guarantor.findByTypeEnum", query = "SELECT g FROM Guarantor g WHERE g.typeEnum = :typeEnum"),
    @NamedQuery(name = "Guarantor.findByEntityId", query = "SELECT g FROM Guarantor g WHERE g.entityId = :entityId"),
    @NamedQuery(name = "Guarantor.findByFirstname", query = "SELECT g FROM Guarantor g WHERE g.firstname = :firstname"),
    @NamedQuery(name = "Guarantor.findByLastname", query = "SELECT g FROM Guarantor g WHERE g.lastname = :lastname"),
    @NamedQuery(name = "Guarantor.findByDob", query = "SELECT g FROM Guarantor g WHERE g.dob = :dob"),
    @NamedQuery(name = "Guarantor.findByAddressLine1", query = "SELECT g FROM Guarantor g WHERE g.addressLine1 = :addressLine1"),
    @NamedQuery(name = "Guarantor.findByAddressLine2", query = "SELECT g FROM Guarantor g WHERE g.addressLine2 = :addressLine2"),
    @NamedQuery(name = "Guarantor.findByCity", query = "SELECT g FROM Guarantor g WHERE g.city = :city"),
    @NamedQuery(name = "Guarantor.findByState", query = "SELECT g FROM Guarantor g WHERE g.state = :state"),
    @NamedQuery(name = "Guarantor.findByCountry", query = "SELECT g FROM Guarantor g WHERE g.country = :country"),
    @NamedQuery(name = "Guarantor.findByZip", query = "SELECT g FROM Guarantor g WHERE g.zip = :zip"),
    @NamedQuery(name = "Guarantor.findByHousePhoneNumber", query = "SELECT g FROM Guarantor g WHERE g.housePhoneNumber = :housePhoneNumber"),
    @NamedQuery(name = "Guarantor.findByMobileNumber", query = "SELECT g FROM Guarantor g WHERE g.mobileNumber = :mobileNumber"),
    @NamedQuery(name = "Guarantor.findByComment", query = "SELECT g FROM Guarantor g WHERE g.comment = :comment"),
    @NamedQuery(name = "Guarantor.findByIsActive", query = "SELECT g FROM Guarantor g WHERE g.isActive = :isActive")})
public class Guarantor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "client_reln_cv_id")
    private Integer clientRelnCvId;
    @Basic(optional = false)
    @Column(name = "type_enum")
    private short typeEnum;
    @Column(name = "entity_id")
    private BigInteger entityId;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "address_line_1")
    private String addressLine1;
    @Column(name = "address_line_2")
    private String addressLine2;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "zip")
    private String zip;
    @Column(name = "house_phone_number")
    private String housePhoneNumber;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @Column(name = "is_active")
    private boolean isActive;
    @JoinColumn(name = "loan_id", referencedColumnName = "loanid")
    @ManyToOne(optional = false)
    private Loan loanId;

    public Guarantor() {
    }

    public Guarantor(Long id) {
        this.id = id;
    }

    public Guarantor(Long id, short typeEnum, boolean isActive) {
        this.id = id;
        this.typeEnum = typeEnum;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getClientRelnCvId() {
        return clientRelnCvId;
    }

    public void setClientRelnCvId(Integer clientRelnCvId) {
        this.clientRelnCvId = clientRelnCvId;
    }

    public short getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(short typeEnum) {
        this.typeEnum = typeEnum;
    }

    public BigInteger getEntityId() {
        return entityId;
    }

    public void setEntityId(BigInteger entityId) {
        this.entityId = entityId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getHousePhoneNumber() {
        return housePhoneNumber;
    }

    public void setHousePhoneNumber(String housePhoneNumber) {
        this.housePhoneNumber = housePhoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Loan getLoanId() {
        return loanId;
    }

    public void setLoanId(Loan loanId) {
        this.loanId = loanId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Guarantor)) {
            return false;
        }
        Guarantor other = (Guarantor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bytecode.entities.Guarantor[ id=" + id + " ]";
    }
    
}
