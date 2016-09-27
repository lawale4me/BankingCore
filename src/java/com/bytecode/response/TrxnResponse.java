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
public class TrxnResponse {
    private int responseCode;
    private String description;

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

    @Override
    public String toString() {
        return "TrxnResponse{" + "responseCode=" + responseCode + ", description=" + description + '}';
    }    
    
    
}
