/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.core;

/**
 *
 * @author Ahmed
 */
public class LoanException extends Exception {

    public LoanException(String message) {
        super(message);
    }

    public LoanException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
