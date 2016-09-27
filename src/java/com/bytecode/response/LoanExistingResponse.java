/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.response;

import com.bytecode.entities.Loanapllication;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmed
 */
@XmlRootElement
public class LoanExistingResponse {
    private String responseCode;
    private String description;
    private Loanapllication loanapplication;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Loanapllication getLoanapplication() {
        return loanapplication;
    }

    public void setLoanapplication(Loanapllication loanapplication) {
        this.loanapplication = loanapplication;
    }

  

    @Override
    public String toString() {
        return "LoanApplicationResponse{" + "responseCode=" + responseCode + ", description=" + description + '}';
    }

    
    
    
}
