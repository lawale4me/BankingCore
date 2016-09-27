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
@Table(name = "auditreport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auditreport.findAll", query = "SELECT a FROM Auditreport a"),
    @NamedQuery(name = "Auditreport.findById", query = "SELECT a FROM Auditreport a WHERE a.id = :id"),
    @NamedQuery(name = "Auditreport.findByAction", query = "SELECT a FROM Auditreport a WHERE a.action = :action"),
    @NamedQuery(name = "Auditreport.findByActiondate", query = "SELECT a FROM Auditreport a WHERE a.actiondate = :actiondate"),
    @NamedQuery(name = "Auditreport.findByIpaddress", query = "SELECT a FROM Auditreport a WHERE a.ipaddress = :ipaddress"),
    @NamedQuery(name = "Auditreport.findByUsername", query = "SELECT a FROM Auditreport a WHERE a.username = :username")})
public class Auditreport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ACTION")
    private String action;
    @Column(name = "ACTIONDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actiondate;
    @Column(name = "IPADDRESS")
    private String ipaddress;
    @Column(name = "USERNAME")
    private String username;

    public Auditreport() {
    }

    public Auditreport(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getActiondate() {
        return actiondate;
    }

    public void setActiondate(Date actiondate) {
        this.actiondate = actiondate;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        if (!(object instanceof Auditreport)) {
            return false;
        }
        Auditreport other = (Auditreport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bytecode.entities.Auditreport[ id=" + id + " ]";
    }
    
}
