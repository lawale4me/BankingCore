/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmed
 */
@XmlRootElement
public class BalanceResponse {
    private int responseCode;
    private String description;
    private String balance;
    private String ledgerBalance;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getLedgerBalance() {
        return ledgerBalance;
    }

    public void setLedgerBalance(String ledgerBalance) {
        this.ledgerBalance = ledgerBalance;
    }

    
    //{"balance": 1000.21, "num":100, "is_vip":true, "name":"foo"}
    @Override
    public String toString() {
        return "{" + "'responseCode':" +
                responseCode + ", 'description':'" + 
                description + "', 'balance':" + 
                balance + ", 'ledgerBalance':'" + 
                ledgerBalance + "'}";
    }
    
    
    
}
