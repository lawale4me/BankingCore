/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.dto;

import com.bytecode.entities.Loan;
import com.bytecode.entities.Loanapllication;
import com.bytecode.entities.Paccount;
import com.bytecode.entities.Transactions;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Ahmed
 */
public class AccountDTO {
    
    private Integer accountid;
    private String accountname;    
    private String accountemail;    
    private String accountphone;    
    private Double balance;    
    private String address;   
    private Integer status,pin;        
    private Date dateopened;        
    private Date dob;    
    private String gender;
    private String prodcode;
    private Double ledgerbalance;    
    private Double totalLoss;
    private Double totalProfit;
    private Integer noOfLoans;
    private Collection<Transactions> transactionsCollection;    
    private Collection<Paccount> paccountCollection;    
    private Collection<Loan> loanCollection;
    private Collection<Loanapllication> loans;

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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    public Double getLedgerbalance() {
        return ledgerbalance;
    }

    public void setLedgerbalance(Double ledgerbalance) {
        this.ledgerbalance = ledgerbalance;
    }

    public Collection<Transactions> getTransactionsCollection() {
        return transactionsCollection;
    }

    public void setTransactionsCollection(Collection<Transactions> transactionsCollection) {
        this.transactionsCollection = transactionsCollection;
    }

    public Collection<Paccount> getPaccountCollection() {
        return paccountCollection;
    }

    public void setPaccountCollection(Collection<Paccount> paccountCollection) {
        this.paccountCollection = paccountCollection;
    }

    public Collection<Loan> getLoanCollection() {
        return loanCollection;
    }

    public void setLoanCollection(Collection<Loan> loanCollection) {
        this.loanCollection = loanCollection;
    }

    public String getProdcode() {
        return prodcode;
    }

    public void setProdcode(String prodcode) {
        this.prodcode = prodcode;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public Double getTotalLoss() {
        return totalLoss;
    }

    public void setTotalLoss(Double totalLoss) {
        this.totalLoss = totalLoss;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Integer getNoOfLoans() {
        return noOfLoans;
    }

    public void setNoOfLoans(Integer noOfLoans) {
        this.noOfLoans = noOfLoans;
    }

    public Collection<Loanapllication> getLoans() {
        return loans;
    }

    public void setLoans(Collection<Loanapllication> loans) {
        this.loans = loans;
    }
    
    
    
}
