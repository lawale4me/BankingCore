/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytecode.dto;

import java.util.ArrayList;

/**
 *
 * @author Ahmed
 */
public class ProfitAndLoss 
{
    
    ArrayList<ExpenseDTO> expenses=new ArrayList<ExpenseDTO>();
    ArrayList<RepaymentDTO> repayments=new ArrayList<RepaymentDTO>();        
    
    private Double totalExpenses;
    private Double totalProfit;
    private Double totalLoss;
    private Double totalInterest=0d;
    private Double totalPrincipal=0d;

    

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Double getTotalLoss() {
        return totalLoss;
    }

    public void setTotalLoss(Double totalLoss) {
        this.totalLoss = totalLoss;
    }

    public Double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public ArrayList<ExpenseDTO> getExpenses() {
        return expenses;
    }

    public void setExpenses(ArrayList<ExpenseDTO> expenses) {
        this.expenses = expenses;
    }

    public ArrayList<RepaymentDTO> getRepayments() {
        return repayments;
    }

    public void setRepayments(ArrayList<RepaymentDTO> repayments) {
        this.repayments = repayments;
    }

    public Double getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(Double totalInterest) {
        this.totalInterest = totalInterest;
    }

    public Double getTotalPrincipal() {
        return totalPrincipal;
    }

    public void setTotalPrincipal(Double totalPrincipal) {
        this.totalPrincipal = totalPrincipal;
    }
                
    
    
    
}
