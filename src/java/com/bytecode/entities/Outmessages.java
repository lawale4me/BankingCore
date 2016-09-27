/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmed
 */
@Entity
@Table(name = "outmessages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Outmessages.findAll", query = "SELECT o FROM Outmessages o"),
    @NamedQuery(name = "Outmessages.findByMessageId", query = "SELECT o FROM Outmessages o WHERE o.messageId = :messageId"),
    @NamedQuery(name = "Outmessages.findByEmail", query = "SELECT o FROM Outmessages o WHERE o.email = :email"),
    @NamedQuery(name = "Outmessages.findByStatus", query = "SELECT o FROM Outmessages o WHERE o.status = :status"),
    @NamedQuery(name = "Outmessages.findByEmailType", query = "SELECT o FROM Outmessages o WHERE o.emailType = :emailType"),
    @NamedQuery(name = "Outmessages.findByTrycount", query = "SELECT o FROM Outmessages o WHERE o.trycount = :trycount")})
public class Outmessages implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "messageId")
    private Integer messageId;
    @Lob
    @Column(name = "message")
    private String message;
    @Column(name = "email")
    private String email;
    @Column(name = "status")
    private String status;
    @Column(name = "emailType")
    private Integer emailType;
    @Column(name = "trycount")
    private Integer trycount;

    public Outmessages() {
    }

    public Outmessages(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEmailType() {
        return emailType;
    }

    public void setEmailType(Integer emailType) {
        this.emailType = emailType;
    }

    public Integer getTrycount() {
        return trycount;
    }

    public void setTrycount(Integer trycount) {
        this.trycount = trycount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Outmessages)) {
            return false;
        }
        Outmessages other = (Outmessages) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bytecode.entities.Outmessages[ messageId=" + messageId + " ]";
    }
    
}
