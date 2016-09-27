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
public class LoanApplicationResponse {
    private String responseCode;
    private String description;
    private String referenceNo;

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

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String accountNo) {
        this.referenceNo = accountNo;
    }

    @Override
    public String toString() {
        return "LoanApplicationResponse{" + "responseCode=" + responseCode + ", description=" + description + ", referenceNo=" + referenceNo + '}';
    }

    
    
    
}
