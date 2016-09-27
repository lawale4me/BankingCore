/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytecode.core;

import com.bytecode.dto.ChequeStatus;
import com.bytecode.dto.LoanStatus;
import com.bytecode.dto.MessageStatus;
import com.bytecode.dto.ProfitAndLoss;
import com.bytecode.entities.Account;
import com.bytecode.entities.Appuser;
import com.bytecode.entities.Auditreport;
import com.bytecode.entities.Expense;
import com.bytecode.entities.Installments;
import com.bytecode.entities.Loan;
import com.bytecode.entities.Loanapllication;
import com.bytecode.entities.Outmessages;
import com.bytecode.entities.Paccount;
import com.bytecode.entities.Product;
import com.bytecode.entities.Transactions;
import com.bytecode.util.AdminRepository;
import com.bytecode.util.RepositoryBase;
import com.bytecode.util.RepositoryManager;
import com.bytecode.util.ResponseCode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author Ahmed
 */
public class AdminRepositoryImpl extends RepositoryBase implements AdminRepository {

    public AdminRepositoryImpl() {
    }

    @Override
    public void registerAccount(Account account) {
        EntityManager manager = RepositoryManager.getManager();
        manager.getTransaction().begin();
        manager.persist(account);
        manager.getTransaction().commit();
    }

    @Override
    public void registerPAccount(Paccount pacct) {
        EntityManager manager = RepositoryManager.getManager();
        manager.getTransaction().begin();
        manager.persist(pacct);
        manager.getTransaction().commit();
    }

    @Override
    public Product getProduct(String prodcode) {
        if (session != null && session.isActive()) {
            List<Product> prod = manager.createNamedQuery("Product.findByProductcode", Product.class).setParameter("productcode", prodcode).getResultList();
            return prod.isEmpty() ? null : prod.get(0);
        } else {
            EntityManager manage = RepositoryManager.getManager();
            List<Product> prod = manager.createNamedQuery("Product.findByProductcode", Product.class).setParameter("productcode", prodcode).getResultList();
            manage.close();
            return prod.isEmpty() ? null : prod.get(0);
        }
    }

    @Override
    public Paccount getPaccount(String accountNo) {
        if (session != null && session.isActive()) {
            List<Paccount> account = manager.createNamedQuery("Paccount.findByAccountno", Paccount.class).setParameter("accountno", accountNo).getResultList();
            return account.isEmpty() ? null : account.get(0);
        } else {
            EntityManager manage = RepositoryManager.getManager();
            List<Paccount> account = manage.createNamedQuery("Paccount.findByAccountno", Paccount.class).setParameter("accountno", accountNo).getResultList();
            manage.close();
            return account.isEmpty() ? null : account.get(0);
        }
    }

    public Product updateProduct(Product prod) {
        EntityManager manager = RepositoryManager.getManager();
        manager.getTransaction().begin();
        prod = manager.merge(prod);
        manager.getTransaction().commit();
        return prod;
    }

    public Paccount updatePaccount(Paccount pacct) {
        EntityManager manager = RepositoryManager.getManager();
        manager.getTransaction().begin();
        pacct = manager.merge(pacct);
        manager.getTransaction().commit();
        return pacct;
    }

    public Transactions updateTrxn(Transactions trxn) {
        EntityManager manager = RepositoryManager.getManager();
        manager.getTransaction().begin();
        trxn = manager.merge(trxn);
        manager.getTransaction().commit();
        return trxn;
    }

    @Override
    public void applyForLoan(Loan loan) {
        EntityManager manager = RepositoryManager.getManager();
        manager.getTransaction().begin();
        manager.persist(loan);
        manager.getTransaction().commit();
    }

    public boolean checkEmailInApplictaion(String email) {
        if (session != null && session.isActive()) {
            List<Loanapllication> loan = manager.createNamedQuery("Loanapllication.findByEmail", Loanapllication.class).setParameter("email", email).getResultList();
            return loan.isEmpty() ? false : true;
        } else {
            EntityManager manage = RepositoryManager.getManager();
            List<Loanapllication> loan = manager.createNamedQuery("Loanapllication.findByEmail", Loanapllication.class).setParameter("email", email).getResultList();
            manage.close();
            return loan.isEmpty() ? false : true;
        }
    }

    public boolean checkEmailInAccount(String email) {
        if (session != null && session.isActive()) {
            List<Account> account = manager.createNamedQuery("Account.findByAccountemail", Account.class).setParameter("accountemail", email).getResultList();
            return account.isEmpty() ? false : true;
        } else {
            EntityManager manage = RepositoryManager.getManager();
            List<Account> account = manager.createNamedQuery("Account.findByAccountemail", Account.class).setParameter("accountemail", email).getResultList();
            manage.close();
            return account.isEmpty() ? false : true;
        }
    }

    public void loanApplictaion(Loanapllication loanApplication) {
        EntityManager manager = RepositoryManager.getManager();
        manager.getTransaction().begin();
        manager.persist(loanApplication);
        manager.getTransaction().commit();
    }

    public Appuser getUser(String userID) {
        if (session != null && session.isActive()) {
            List<Appuser> user = manager.createNamedQuery("Appuser.findByUsername", Appuser.class).setParameter("username", userID).getResultList();
            return user.isEmpty() ? null : user.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Appuser> user = manager.createNamedQuery("Appuser.findByUsername", Appuser.class).setParameter("username", userID).getResultList();
            manager.close();
            return user.isEmpty() ? null : user.get(0);
        }

    }

    public Appuser validate(String userid, String password) {
        EntityManager manager = RepositoryManager.getManager();
        String query = "SELECT u FROM Appuser u WHERE u.username = :username and u.password = :password";
        List<Appuser> adminUser = manager.createQuery(query, Appuser.class).setParameter("username", userid).setParameter("password", password).getResultList();
        return adminUser.isEmpty() ? null : adminUser.get(0);
    }

    public List<Loanapllication> getAllLoans() {
        if (session != null && session.isActive()) {
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByLatest", Loanapllication.class).getResultList();
            return loans.isEmpty() ? null : loans;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByLatest", Loanapllication.class).getResultList();
            manager.close();
            return loans.isEmpty() ? null : loans;
        }
    }

    public List<Loanapllication> getPendingLoans() {
        if (session != null && session.isActive()) {
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByStatus", Loanapllication.class).setParameter("status", LoanStatus.PENDING.ordinal()).getResultList();
            return loans.isEmpty() ? null : loans;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByStatus", Loanapllication.class).setParameter("status", LoanStatus.PENDING.ordinal()).getResultList();
            manager.close();
            return loans.isEmpty() ? null : loans;
        }
    }

    public List<Loanapllication> getApprovedLoans() {
        if (session != null && session.isActive()) {
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByStatus", Loanapllication.class).setParameter("status", LoanStatus.APPROVED.ordinal()).getResultList();
            return loans.isEmpty() ? null : loans;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByStatus", Loanapllication.class).setParameter("status", LoanStatus.APPROVED.ordinal()).getResultList();
            manager.close();
            return loans.isEmpty() ? null : loans;
        }
    }

    public List<Loanapllication> getLoansByStatus(LoanStatus status) {
        if (session != null && session.isActive()) {
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByStatus", Loanapllication.class).setParameter("status", status.ordinal()).getResultList();
            return loans.isEmpty() ? null : loans;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByStatus", Loanapllication.class).setParameter("status", status.ordinal()).getResultList();
            manager.close();
            return loans.isEmpty() ? null : loans;
        }
    }

    public List<Appuser> getAllUsers() {
        if (session != null && session.isActive()) {
            List<Appuser> users = manager.createNamedQuery("Appuser.findAll", Appuser.class).getResultList();
            return users.isEmpty() ? null : users;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Appuser> users = manager.createNamedQuery("Appuser.findAll", Appuser.class).getResultList();
            manager.close();
            return users.isEmpty() ? null : users;
        }
    }

    public void addUser(Appuser user) {
        if (session != null && session.isActive()) {
            manager.persist(user);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            manager.persist(user);
            manager.getTransaction().commit();
        }
    }

    public void audit(Auditreport ar) {
        if (session != null && session.isActive()) {
            manager.persist(ar);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            manager.persist(ar);
            manager.getTransaction().commit();
        }
    }

    public List<Auditreport> getAllAuditReports() {
        if (session != null && session.isActive()) {
            List<Auditreport> report = manager.createNamedQuery("Auditreport.findAll", Auditreport.class).getResultList();
            return report.isEmpty() ? null : report;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Auditreport> report = manager.createNamedQuery("AuditReport.findAll", Auditreport.class).getResultList();
            manager.close();
            return report.isEmpty() ? null : report;
        }
    }

    public Appuser getUser(Integer userID) {
        if (session != null && session.isActive()) {
            List<Appuser> user = manager.createNamedQuery("Appuser.findById", Appuser.class).setParameter("id", userID).getResultList();
            return user.isEmpty() ? null : user.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Appuser> user = manager.createNamedQuery("Appuser.findById", Appuser.class).setParameter("id", userID).getResultList();
            manager.close();
            return user.isEmpty() ? null : user.get(0);
        }
    }

    public ResponseDetails createUser(Appuser adminUser) {
        ResponseDetails resp = new ResponseDetails();
        try {
            if (session != null && session.isActive()) {
                manager.persist(adminUser);
            } else {
                EntityManager manager = RepositoryManager.getManager();
                manager.persist(adminUser);
                manager.close();
            }
            resp.setErrorCode(ResponseCode.OK);
            resp.setErrorDescription("user created");
            return resp;
        } catch (NoResultException e) {
            resp.setErrorCode(ResponseCode.USER_NOT_FOUND);
            resp.setErrorDescription("invalid username or pass");
            return resp;
        }
    }

    public List<Loanapllication> getAuthorizedLoans() {
        if (session != null && session.isActive()) {
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByStatus", Loanapllication.class).setParameter("status", 2).getResultList();
            return loans.isEmpty() ? null : loans;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByStatus", Loanapllication.class).setParameter("status", 2).getResultList();
            manager.close();
            return loans.isEmpty() ? null : loans;
        }
    }

    public List<Loanapllication> getDisbursedLoans() {
        if (session != null && session.isActive()) {
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByStatus", Loanapllication.class).setParameter("status", 3).getResultList();
            return loans.isEmpty() ? null : loans;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByStatus", Loanapllication.class).setParameter("status", 3).getResultList();
            manager.close();
            return loans.isEmpty() ? null : loans;
        }
    }

    public void updateLoan(Loanapllication selectedLoan) {
        if (session != null && session.isActive()) {
            manager.merge(selectedLoan);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            manager.merge(selectedLoan);
            manager.getTransaction().commit();
        }
    }

    public Loanapllication getPendingLoan(String referenceno) {
        if (session != null && session.isActive()) {
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.getPendingLoan", Loanapllication.class).setParameter("status", 1).setParameter("referenceno", referenceno).getResultList();
            return loans.isEmpty() ? null : loans.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.getPendingLoan", Loanapllication.class).setParameter("status", 1).setParameter("referenceno", referenceno).getResultList();
            manager.close();
            return loans.isEmpty() ? null : loans.get(0);
        }
    }

    public Loanapllication getApprovedLoan(String referenceno) {
        if (session != null && session.isActive()) {
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.getApprovedLoan", Loanapllication.class).setParameter("status", 2).setParameter("referenceno", referenceno).getResultList();
            return loans.isEmpty() ? null : loans.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.getApprovedLoan", Loanapllication.class).setParameter("status", 2).setParameter("referenceno", referenceno).getResultList();
            manager.close();
            return loans.isEmpty() ? null : loans.get(0);
        }
    }

    public Loanapllication getCompletedLoan(String referenceno) {
        if (session != null && session.isActive()) {
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.getDisbursedLoan", Loanapllication.class).setParameter("status", 3).setParameter("referenceno", referenceno).getResultList();
            return loans.isEmpty() ? null : loans.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.getDisbursedLoan", Loanapllication.class).setParameter("status", 3).setParameter("referenceno", referenceno).getResultList();
            manager.close();
            return loans.isEmpty() ? null : loans.get(0);
        }
    }

    public Loanapllication getDisbursedLoan(String referenceno) {
        if (session != null && session.isActive()) {
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.getDisbursedLoan", Loanapllication.class).setParameter("status", 4).setParameter("referenceno", referenceno).getResultList();
            return loans.isEmpty() ? null : loans.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.getDisbursedLoan", Loanapllication.class).setParameter("status", 4).setParameter("referenceno", referenceno).getResultList();
            manager.close();
            return loans.isEmpty() ? null : loans.get(0);
        }

    }

    public Loanapllication getLoan(LoanStatus loanStatus, String referenceno) {
        if (session != null && session.isActive()) {
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.getDisbursedLoan", Loanapllication.class).setParameter("status", loanStatus.ordinal()).setParameter("referenceno", referenceno).getResultList();
            return loans.isEmpty() ? null : loans.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.getDisbursedLoan", Loanapllication.class).setParameter("status", loanStatus.ordinal()).setParameter("referenceno", referenceno).getResultList();
            manager.close();
            return loans.isEmpty() ? null : loans.get(0);
        }
    }

    public Loanapllication getLoanByEmail(String email) {
        if (session != null && session.isActive()) {
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByEmail", Loanapllication.class).setParameter("email", email).getResultList();
            return loans.isEmpty() ? null : loans.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByEmail", Loanapllication.class).setParameter("email", email).getResultList();
            manager.close();
            return loans.isEmpty() ? null : loans.get(0);
        }
    }

    public Loanapllication getLoanByRef(String refNum) {
        if (session != null && session.isActive()) {
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByReferenceno", Loanapllication.class).setParameter("referenceno", refNum).getResultList();
            return loans.isEmpty() ? null : loans.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByReferenceno", Loanapllication.class).setParameter("referenceno", refNum).getResultList();
            manager.close();
            return loans.isEmpty() ? null : loans.get(0);
        }
    }

    public Installments addInstallment(Installments installment) {
        if (session != null && session.isActive()) {
            manager.persist(installment);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            manager.persist(installment);
            manager.getTransaction().commit();
        }
        return installment;
    }

    public void updateInstallment(Installments installment) {
        if (session != null && session.isActive()) {
            manager.merge(installment);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            manager.merge(installment);
            manager.getTransaction().commit();
        }
    }

    public List<Installments> getAllInstallments() {
        if (session != null && session.isActive()) {
            List<Installments> installments = manager.createNamedQuery("Installments.findAll", Installments.class).getResultList();
            return installments.isEmpty() ? null : installments;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Installments> installments = manager.createNamedQuery("Installments.findAll", Installments.class).getResultList();
            manager.close();
            return installments.isEmpty() ? null : installments;
        }
    }

    public List<Outmessages> getMessages(MessageStatus messageStatus) {
        if (session != null && session.isActive()) {
            List<Outmessages> msgs = manager.createNamedQuery("Outmessages.findByStatus", Outmessages.class).setParameter("status", messageStatus).getResultList();
            return msgs.isEmpty() ? null : msgs;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Outmessages> msgs = manager.createNamedQuery("Outmessages.findByStatus", Outmessages.class).setParameter("status", messageStatus).getResultList();
            manager.close();
            return msgs.isEmpty() ? null : msgs;
        }

    }

    public void updateMessage(Outmessages msg) {
        EntityManager manager = RepositoryManager.getManager();
        manager.getTransaction().begin();
        manager.merge(msg);
        manager.getTransaction().commit();
    }

    public List<Installments> searchInstallments(Date startdate, Date enddate) {
        if (session != null && session.isActive()) {
            List<Installments> ins = manager.createQuery("SELECT i FROM Installments i WHERE i.presentationDate BETWEEN :startdate AND :enddate", Installments.class).setParameter("startdate", startdate).setParameter("enddate", enddate).getResultList();
            return ins.isEmpty() ? null : ins;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Installments> ins = manager.createQuery("SELECT i FROM Installments i WHERE i.presentationDate BETWEEN :startdate AND :enddate", Installments.class).setParameter("startdate", startdate).setParameter("enddate", enddate).getResultList();
            manager.close();
            return ins.isEmpty() ? null : ins;
        }
    }

    public List<Installments> getReturnedCheque() {
        if (session != null && session.isActive()) {
            List<Installments> installments = manager.createNamedQuery("Installments.findByStatus", Installments.class).setParameter("status", ChequeStatus.RETURNED.name()).getResultList();
            return installments.isEmpty() ? null : installments;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Installments> installments = manager.createNamedQuery("Installments.findByStatus", Installments.class).setParameter("status", ChequeStatus.RETURNED.name()).getResultList();
            manager.close();
            return installments.isEmpty() ? null : installments;
        }

    }
    
    public List<Installments> getInstallmentsByStatus(String status) {
        if (session != null && session.isActive()) {
            List<Installments> installments = manager.createNamedQuery("Installments.findByStatus", Installments.class).setParameter("status", status).getResultList();
            return installments.isEmpty() ? null : installments;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Installments> installments = manager.createNamedQuery("Installments.findByStatus", Installments.class).setParameter("status", status).getResultList();
            manager.close();
            return installments.isEmpty() ? null : installments;
        }

    }

    public Installments getInstallment(Integer installmentId) {
        if (session != null && session.isActive()) {
            List<Installments> product = manager.createNamedQuery("Installments.findByInstallmentId", Installments.class).setParameter("installmentId", installmentId).getResultList();
            return product.isEmpty() ? null : product.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Installments> product = manager.createNamedQuery("Installments.findByInstallmentId", Installments.class).setParameter("installmentId", installmentId).getResultList();
            manager.close();
            return product.isEmpty() ? null : product.get(0);
        }
    }

    public List<Expense> getExpenses() {
        if (session != null && session.isActive()) {
            List<Expense> expenses = manager.createNamedQuery("Expense.findAll", Expense.class).getResultList();
            return expenses.isEmpty() ? null : expenses;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Expense> expenses = manager.createNamedQuery("Expense.findAll", Expense.class).getResultList();
            manager.close();
            return expenses.isEmpty() ? null : expenses;
        }
    }

    public Expense addExpense(Expense exp) 
    {
        if (session != null && session.isActive()) {
            manager.persist(exp);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            manager.persist(exp);
            manager.getTransaction().commit();
        }
        return exp;
    }

    public Expense getExpense(Integer expenseId) {
        if (session != null && session.isActive()) {
            List<Expense> expense = manager.createNamedQuery("Expense.findByExpenseId", Expense.class).setParameter("expenseId", expenseId).getResultList();
            return expense.isEmpty() ? null : expense.get(0);
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Expense> expense = manager.createNamedQuery("Expense.findByExpenseId", Expense.class).setParameter("expenseId", expenseId).getResultList();
            manager.close();
            return expense.isEmpty() ? null : expense.get(0);
        } 
    }

    public void removeExpense(Expense exp) {
     if (session != null && session.isActive()) {
            exp = manager.merge(exp);
            manager.remove(exp);
        } else {            
            EntityManager manager = RepositoryManager.getManager();
            manager.getTransaction().begin();
            exp = manager.merge(exp);
            manager.remove(exp);
            manager.getTransaction().commit();
        }   
    }

    public List<ProfitAndLoss> searchPL(Date startdate, Date enddate) {
             return null;         
    }

    public List<Expense> getExpenses(Date startdate, Date enddate) {
            if (session != null && session.isActive()) {
            List<Expense> ins = manager.createQuery("SELECT i FROM Expense i WHERE i.expenseDate BETWEEN :startdate AND :enddate", Expense.class).setParameter("startdate", startdate).setParameter("enddate", enddate).getResultList();
            return ins.isEmpty() ? null : ins;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Expense> ins = manager.createQuery("SELECT i FROM Expense i WHERE i.expenseDate BETWEEN :startdate AND :enddate", Expense.class).setParameter("startdate", startdate).setParameter("enddate", enddate).getResultList();
            manager.close();
            return ins.isEmpty() ? null : ins;
        }

    }

    public HashSet<Loanapllication> searchCriticizedAssets(Date startdate, Date enddate) {                   
        
      if (session != null && session.isActive()) {
            HashSet<Installments> ins = (HashSet<Installments>) manager.createQuery("SELECT i FROM Installments i WHERE i.status = :status AND i.presentationDate BETWEEN :startdate AND :enddate", Installments.class).setParameter("status", ChequeStatus.MISSED.name()).setParameter("startdate", startdate).setParameter("enddate", enddate).getResultList();
            HashSet<Loanapllication> criticizedAssets=new HashSet();
        HashSet<Integer> ca=new HashSet();
        if(ins!=null){
            for(Installments i:ins){
                if(ca.contains(i.getLoanId().getLoanid())){                    
                   criticizedAssets.add(i.getLoanId());
                }else{
                ca.add(i.getLoanId().getLoanid());
                }
            }
        }
        return criticizedAssets;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            HashSet<Installments> ins = (HashSet<Installments>) manager.createQuery("SELECT i FROM Installments i WHERE i.status = :status AND i.presentationDate BETWEEN :startdate AND :enddate", Installments.class).setParameter("status", ChequeStatus.MISSED.name()).setParameter("startdate", startdate).setParameter("enddate", enddate).getResultList();
            manager.close();
             HashSet<Loanapllication> criticizedAssets=new HashSet();
        HashSet<Integer> ca=new HashSet();
        if(ins!=null){
            for(Installments i:ins){
                if(ca.contains(i.getLoanId().getLoanid())){                    
                   criticizedAssets.add(i.getLoanId());
                }else{
                ca.add(i.getLoanId().getLoanid());
                }
            }
        }
        return criticizedAssets;
        }  
    }

    public HashSet<Loanapllication> getCritisedAssets() {
        List<Installments> missedRepayments= getInstallmentsByStatus(ChequeStatus.MISSED.name());
        HashSet<Loanapllication> criticizedAssets=new HashSet();
        HashSet<Integer> ca=new HashSet();
        if(missedRepayments!=null){
            for(Installments i:missedRepayments){
                if(ca.contains(i.getLoanId().getLoanid())){                    
                   criticizedAssets.add(i.getLoanId());
                }else{
                ca.add(i.getLoanId().getLoanid());
                }
            }
        }
        return criticizedAssets;
    }
    
    public HashSet<Loanapllication> getNonPerformingLoans() {
        List<Installments> missedRepayments= getInstallmentsByStatus(ChequeStatus.MISSED.name());
        HashSet<Loanapllication> npl=new HashSet();
        HashSet<Integer> ca=new HashSet();
        if(missedRepayments!=null){
            for(Installments i:missedRepayments){
                if(ca.contains(i.getLoanId().getLoanid())){                    
                   npl.remove(i.getLoanId());
                }else{
                npl.add(i.getLoanId());
                ca.add(i.getLoanId().getLoanid());
                }
            }
        }
        return npl;
    }

    public HashSet<Loanapllication> searchNPL(Date startdate, Date enddate) {
        if (session != null && session.isActive()) {
            HashSet<Installments> ins = (HashSet<Installments>) manager.createQuery("SELECT i FROM Installments i WHERE i.status = :status AND i.presentationDate BETWEEN :startdate AND :enddate", Installments.class).setParameter("status", ChequeStatus.MISSED.name()).setParameter("startdate", startdate).setParameter("enddate", enddate).getResultList();
          
        HashSet<Loanapllication> npl=new HashSet();
        HashSet<Integer> ca=new HashSet();
        if(ins!=null){
            for(Installments i:ins){
                if(ca.contains(i.getLoanId().getLoanid())){                    
                   npl.remove(i.getLoanId());
                }else{
                npl.add(i.getLoanId());
                ca.add(i.getLoanId().getLoanid());
                }
            }
        }
        return npl;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            HashSet<Installments> ins = (HashSet<Installments>) manager.createQuery("SELECT i FROM Installments i WHERE i.status = :status AND i.presentationDate BETWEEN :startdate AND :enddate", Installments.class).setParameter("status", ChequeStatus.MISSED.name()).setParameter("startdate", startdate).setParameter("enddate", enddate).getResultList();
            manager.close();
             
        HashSet<Loanapllication> npl=new HashSet();
        HashSet<Integer> ca=new HashSet();
        if(ins!=null){
            for(Installments i:ins){
                if(ca.contains(i.getLoanId().getLoanid())){                    
                   npl.remove(i.getLoanId());
                }else{
                npl.add(i.getLoanId());
                ca.add(i.getLoanId().getLoanid());
                }
            }
        }
        return npl;
        }  
    }

    public List<Account> getCustomers() {
        if (session != null && session.isActive()) {
            List<Account> cust = manager.createNamedQuery("Account.findAll", Account.class).getResultList();
            return cust.isEmpty() ? null : cust;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Account> cust = manager.createNamedQuery("Account.findAll", Account.class).getResultList();
            manager.close();
            return cust.isEmpty() ? null : cust;
        }
    }

    public List<Loanapllication> getLoansByEmail(String accountemail) {
       if (session != null && session.isActive()) {
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByEmail", Loanapllication.class).setParameter("email", accountemail).getResultList();
            return loans.isEmpty() ? null : loans;
        } else {
            EntityManager manager = RepositoryManager.getManager();
            List<Loanapllication> loans = manager.createNamedQuery("Loanapllication.findByEmail", Loanapllication.class).setParameter("email", accountemail).getResultList();
            manager.close();
            return loans.isEmpty() ? null : loans;
        } 
    }

    public Appuser updateAppuser(Appuser adminUser) {
        EntityManager manager = RepositoryManager.getManager();
        manager.getTransaction().begin();
        adminUser = manager.merge(adminUser);
        manager.getTransaction().commit();
        return adminUser;
    }

}
