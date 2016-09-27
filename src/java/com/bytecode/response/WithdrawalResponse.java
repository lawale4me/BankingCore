/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.response;

/**
 *
 * @author Ahmed
 */
public class WithdrawalResponse {
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
        return "WithdrawalResponse{" + "responseCode=" + responseCode + ", description=" + description + '}';
    }

    
    
    
}
