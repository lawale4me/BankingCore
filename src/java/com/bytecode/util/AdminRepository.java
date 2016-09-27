/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytecode.util;



import com.bytecode.entities.Account;
import com.bytecode.entities.Loan;
import com.bytecode.entities.Paccount;
import com.bytecode.entities.Product;




/**
 *
 * @author Ahmed
 */
public interface AdminRepository extends UnitOfWork
{            
    public void registerAccount(Account account);
    public  Paccount getPaccount(String accountNo);
    public void registerPAccount(Paccount pacct);
    Product getProduct(String prodcode);
    public void applyForLoan(Loan loan);
    
}
