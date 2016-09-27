/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytecode.core;

import com.bytecode.dto.AccountDTO;
import com.bytecode.dto.AuditReportDTO;
import com.bytecode.dto.ChequeStatus;
import com.bytecode.dto.ExpenseDTO;
import com.bytecode.dto.LoanDTO;
import com.bytecode.dto.LoanStatus;
import com.bytecode.dto.LoanType;
import com.bytecode.dto.MessageStatus;
import com.bytecode.dto.ProfitAndLoss;
import com.bytecode.dto.RepaymentDTO;
import com.bytecode.dto.Response;
import com.bytecode.entities.Account;
import com.bytecode.entities.Appuser;
import com.bytecode.entities.Auditreport;
import com.bytecode.entities.Expense;
import com.bytecode.entities.Installments;
import com.bytecode.entities.Loanapllication;
import com.bytecode.entities.Outmessages;
import com.bytecode.entities.Paccount;
import com.bytecode.entities.Product;
import com.bytecode.entities.Transactions;
import com.bytecode.makeshift.LoanApplication;
import com.bytecode.response.LoanApplicationResponse;
import com.bytecode.util.GenerateNumbers;
import com.bytecode.util.ResponseCode;
import com.bytecode.util.UnitOfWorkSession;
import com.bytecode.util.Util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Ahmed
 */
public class ConManager {

    AdminRepositoryImpl adminrepo;

    public ConManager(AdminRepositoryImpl adminRepositoryImpl) {
        this.adminrepo = adminRepositoryImpl;
    }

    public String registerAccount(AccountDTO accountdto) throws RegisterException {
        UnitOfWorkSession ses = adminrepo.begin();
        Long acctno = null;

        Product prod = adminrepo.getProduct(accountdto.getProdcode());
        if (prod != null) {
            /**
             * ***ACCOUNT DETAILS******
             */
            Account acct = new Account();
            acct.setAccountname(accountdto.getAccountname());
            acct.setAccountphone(accountdto.getAccountphone());
            acct.setAccountemail(accountdto.getAccountemail());
            acct.setAddress(accountdto.getAddress());
            acct.setDateopened(new Date());
            acct.setDob(accountdto.getDob());
            acct.setStatus(ResponseCode.ACTIVE);
            acct.setAddress(accountdto.getAddress());
            acct.setPin(accountdto.getPin() == null ? String.valueOf(GenerateNumbers.generateRandom(4)) : accountdto.getPin().toString());
            adminrepo.registerAccount(acct);
            //CRITICAL SECTION
            synchronized (this) {
                acctno = prod.getProductlastno() + prod.getProductnextvalue();
                prod.setProductlastno(acctno);
                adminrepo.updateProduct(prod);
            }
            //END OF CRITICAL SECTION

            /**
             * ***PRODUCT DETAILS******
             */
            Paccount pacct = new Paccount();
            pacct.setAccountid(acct);
            pacct.setAccountno(acctno.toString());//DYNAMIC ACCOUNTNUMBER
            pacct.setBalance(ResponseCode.EMPTY_BALANCE);
            pacct.setLedgerbalance(ResponseCode.EMPTY_BALANCE);
            pacct.setDateopened(new Date());
            pacct.setProdtype(prod);
            adminrepo.registerPAccount(pacct);
            System.out.println("Generated Accountnumber:" + acctno);
        } else {
            throw new RegisterException("Invalid Product");
        }
        ses.commit();

        return acctno.toString();
    }

    public void transferFunds(Transactions trxn) throws TransferException {
        UnitOfWorkSession ses = adminrepo.begin();
        if (trxn.getSourceaccount().equalsIgnoreCase(trxn.getDestaccount())) {
            throw new TransferException(ResponseCode.SAME_ACCOUNT_TRANSFER_NOT_POSSIBLE);
        }
        synchronized (this) {
            Paccount pacct = getPaccount(trxn.getSourceaccount());
            if (pacct != null) {
                Paccount destPacct = adminrepo.getPaccount(trxn.getDestaccount());
                if (destPacct != null) {
                    if (pacct.getBalance() >= trxn.getAmount()) {
                        pacct.setBalance(pacct.getBalance() - trxn.getAmount());
                        destPacct.setBalance(destPacct.getBalance() + trxn.getAmount());
                        trxn.setStatus(ResponseCode.OK);
                        trxn.setDescription(ResponseCode.SUCCESSFUL);
                        adminrepo.updatePaccount(pacct);
                        adminrepo.updatePaccount(destPacct);
                        adminrepo.updateTrxn(trxn);
                    } else {
                        trxn.setStatus(ResponseCode.FAILED);
                        trxn.setDescription(ResponseCode.SFAILED);
                        adminrepo.updateTrxn(trxn);
                        throw new TransferException(ResponseCode.INSUFFICIENT_BALANCE);
                    }
                } else {
                    trxn.setStatus(ResponseCode.FAILED);
                    trxn.setDescription(ResponseCode.SFAILED);
                    adminrepo.updateTrxn(trxn);
                    throw new TransferException(ResponseCode.DEST_ACCOUNT_DOES_NOT_EXIST);
                }

            }
        }
        ses.commit();
    }

    public Paccount getPaccount(String accountNo) {
        UnitOfWorkSession ses = adminrepo.begin();
        Paccount acct = adminrepo.getPaccount(accountNo);
        ses.commit();
        return acct;
    }

    public Double getBalance(String accountNo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updatePaccount(Paccount acct) {
        UnitOfWorkSession ses = adminrepo.begin();
        adminrepo.updatePaccount(acct);
        ses.commit();
    }

    public void fundWithrawal(Transactions trxn) throws WithdrawalException {
        UnitOfWorkSession ses = adminrepo.begin();
        Paccount pacct = getPaccount(trxn.getSourceaccount());
        if (pacct != null) {
            System.out.println("GOT HERE");
            if (pacct.getBalance() >= trxn.getAmount()) {
                System.out.println("Balance:" + pacct.getBalance());
                pacct.setBalance(pacct.getBalance() - trxn.getAmount());
                System.out.println("Balance:" + pacct.getBalance());
                trxn.setStatus(ResponseCode.OK);
                trxn.setDescription(ResponseCode.SUCCESSFUL);
                adminrepo.updatePaccount(pacct);
                adminrepo.updateTrxn(trxn);
                System.out.println("Balance:" + pacct.getBalance());
            } else {
                System.out.println("GOT HERE ELSE");
                trxn.setStatus(ResponseCode.FAILED);
                trxn.setDescription(ResponseCode.SFAILED);
                adminrepo.updateTrxn(trxn);
                throw new WithdrawalException(ResponseCode.INSUFFICIENT_BALANCE);
            }
        } else {
            throw new WithdrawalException(ResponseCode.INVALID_ACCOUNTNO);
        }
        ses.commit();
    }

    public void fundDeposit(Transactions trxn) throws WithdrawalException {
        UnitOfWorkSession ses = adminrepo.begin();
        Paccount pacct = getPaccount(trxn.getSourceaccount());
        if (pacct != null) {
            if (true) //check product max deposit
            {
                pacct.setBalance(pacct.getBalance() + trxn.getAmount());
                trxn.setStatus(ResponseCode.OK);
                trxn.setDescription(ResponseCode.SUCCESSFUL);
                adminrepo.updatePaccount(pacct);
                adminrepo.updateTrxn(trxn);
            } else {
                trxn.setStatus(ResponseCode.FAILED);
                trxn.setDescription(ResponseCode.SFAILED);
                adminrepo.updateTrxn(trxn);
                throw new WithdrawalException(ResponseCode.INSUFFICIENT_BALANCE);
            }
        }
        ses.commit();
    }

    public LoanApplicationResponse LoanApplication(LoanDTO loan) throws LoanException {
        UnitOfWorkSession ses = adminrepo.begin();
        LoanApplicationResponse lres = new LoanApplicationResponse();

        Loanapllication loanApplication = new Loanapllication();
        loanApplication.setSurname(loan.getSurname());
        loanApplication.setPhone(loan.getPhone());
        loanApplication.setEmail(loan.getEmail());
        loanApplication.setAddress(loan.getAddress());
        loanApplication.setRequestdate(new Date());
        loanApplication.setStatus(LoanStatus.PENDING.ordinal());

        String refNum = String.valueOf(System.currentTimeMillis() + (new java.security.SecureRandom().nextInt(999) + 1));
        loanApplication.setReferenceno(refNum);
        loanApplication.setAnnualSalary(loan.getAnnualSalary());
        loanApplication.setBankAccountNo(loan.getBankAccountNo());
        loanApplication.setBankName(loan.getBankName());
        loanApplication.setBankStatement(loan.getBankStatement());
        loanApplication.setChannel(loan.getChannel());
        loanApplication.setDept(loan.getDept());
        loanApplication.setDob(loan.getDob());
        loanApplication.setEmpNo(loan.getEmpNo());
        loanApplication.setEmployer(loan.getEmployer());
        loanApplication.setEmployerAddress(loan.getEmployerAddress());
        loanApplication.setEmployerPhone(loan.getEmployerPhone());
        loanApplication.setGrade(loan.getGrade());
        loanApplication.setIdCard(loan.getIdCard());
        loanApplication.setJobDesc(loan.getJobDesc());
        loanApplication.setKinName(loan.getKinName());
        loanApplication.setKinPhone(loan.getKinPhone());
        loanApplication.setKinType(loan.getKinType());
        loanApplication.setKinWork(loan.getKinWork());
        loanApplication.setLengthOfService(loan.getLengthOfService());
        loanApplication.setLoanAmount(loan.getLoanAmount());
        loanApplication.setOfficeEmail(loan.getOfficeEmail());
        loanApplication.setOtherNames(loan.getOtherNames());
        loanApplication.setPayDay(loan.getPayDay());
        loanApplication.setPurpose(loan.getPurpose());
        loanApplication.setSignature(loan.getSignature());
        loanApplication.setTenor(loan.getTenor());
        loanApplication.setTitle(loan.getTitle());
        loanApplication.setTotalExistingLoan(loan.getTotalExistingLoan());
        loanApplication.setProdCode(loan.getProdCode());

        loanApplication.setLastTurnover(loan.getLastTurnover());
        loanApplication.setBusinessNature(loan.getBusinessNature());
        loanApplication.setBusinessYr(loan.getBusinessYr());
        loanApplication.setTenancyInvoice(loan.getTenancyInvoice());
        loanApplication.setLandlordName(loan.getLandlordName());
        loanApplication.setPropertyAddress(loan.getPropertyAddres());
        loanApplication.setLandlordBankName(loan.getLandlordBankName());
        loanApplication.setLandlordAccountNo(loan.getLandlordAccountNo());
        loanApplication.setChildName(loan.getChildName());
        loanApplication.setChildSchool(loan.getChildSchool());
        loanApplication.setSchoolBankName(loan.getSchoolBankName());
        loanApplication.setSchoolAccountNo(loan.getSchoolAccountNo());
        loanApplication.setSchoolfeeInvoice(loan.getSchoolfeeInvoice());
        loanApplication.setLoanType(loan.getLoanType());

        try {
            adminrepo.loanApplictaion(loanApplication);
            lres.setReferenceNo(refNum);
        } catch (Exception le) {
            le.printStackTrace();
            throw new LoanException("LOAN APPLICATION Error");
        }
        ses.commit();
        return lres;
    }

    public boolean checkEmail(String email) {
        boolean status = false;
        UnitOfWorkSession ses = adminrepo.begin();
        status = adminrepo.checkEmailInApplictaion(email);
        if (!status) {
            status = adminrepo.checkEmailInAccount(email);
        }
        System.out.println("Email " + email + " Status:" + status);
        ses.commit();
        return status;
    }

    public Response login(String userid, String password) throws Exception {
        System.out.println("Attempt to logon to the application " + userid);
        Response authres = new Response();
        UnitOfWorkSession ses = adminrepo.begin();
        try {
              password = Util.hash(password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Password hashing issue " + userid);
            throw new Exception("Password hashing issue");
        }

        Appuser adminUser = adminrepo.validate(userid, password);
        if (adminUser != null) {
            if (true) {
                if (adminUser.getStatus() == 1) {
                    authres.setStatus(true);
                    authres.setStatuscode(ResponseCode.ACTIVE);
                    authres.setDescription("User exists");
                    System.out.println("cleared to logon");
                } else {
                    authres.setStatuscode(ResponseCode.USER_DISABLED);
                    authres.setDescription("User is disabled");
                }
            } else {
                authres.setStatuscode(ResponseCode.USER_EXPIRED);
                authres.setDescription("User password has expired");
            }
        } else {
            authres.setStatuscode(ResponseCode.USER_NOT_FOUND);
            authres.setDescription("Invalid username or password");
        }

        ses.commit();
        System.out.println("Login:" + authres.getDescription());
        return authres;
    }

    public Appuser getUser(String userID) {
        UnitOfWorkSession ses = adminrepo.begin();
        Appuser mprofile = adminrepo.getUser(userID);
        ses.commit();
        return mprofile;
    }

    public Appuser getUser(Integer userID) {
        UnitOfWorkSession ses = adminrepo.begin();
        Appuser mprofile = adminrepo.getUser(userID);
        ses.commit();
        return mprofile;
    }

    public List<Loanapllication> getLoans() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Loanapllication> loans = adminrepo.getAllLoans();
        ses.commit();
        return loans;
    }

    public List<Loanapllication> getPendingLoans() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Loanapllication> loans = adminrepo.getPendingLoans();
        ses.commit();
        return loans;
    }

    public List<Loanapllication> getApprovedLoans() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Loanapllication> loans = adminrepo.getLoansByStatus(LoanStatus.APPROVED);
        ses.commit();
        return loans;
    }

    public List<Loanapllication> getCompletedLoans() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Loanapllication> loans = adminrepo.getLoansByStatus(LoanStatus.COMPLETED);
        ses.commit();
        return loans;
    }

    public List<Loanapllication> getDisbursedLoans() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Loanapllication> loans = adminrepo.getLoansByStatus(LoanStatus.DISBURSRED);
        ses.commit();
        return loans;
    }

    public List<Appuser> getAllAppusers() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Appuser> users = adminrepo.getAllUsers();
        ses.commit();
        return users;
    }

    public void addUser(Appuser user) {
        UnitOfWorkSession ses = adminrepo.begin();
        adminrepo.addUser(user);
        ses.commit();
    }

    public void audit(String username, String action, String ipaddress) {
        UnitOfWorkSession ses = adminrepo.begin();
        Auditreport ar = new Auditreport();
        ar.setAction(action);
        ar.setActiondate(new Date());
        ar.setIpaddress(ipaddress);
        ar.setUsername(username);
        adminrepo.audit(ar);
        ses.commit();
    }

    public List<AuditReportDTO> getAllAuditReports() {
        List<AuditReportDTO> ardtos = new ArrayList<AuditReportDTO>();
        UnitOfWorkSession ses = adminrepo.begin();
        List<Auditreport> ar = adminrepo.getAllAuditReports();
        if (ar != null) {
            for (Auditreport a : ar) {
                AuditReportDTO ardto = new AuditReportDTO(a);
                ardtos.add(ardto);
            }
        }
        ses.commit();
        return ardtos;
    }

    public ResponseDetails createUser(Appuser user) throws ProcessingException {
        UnitOfWorkSession ses = adminrepo.begin();
        try {
            String pass = genPass();
            String actualpass = pass;
            pass = hash(pass);

            Appuser exist = adminrepo.getUser(user.getUsername());

            if (exist != null) {
                System.out.println("Admin user already exists " + user.getUsername());
                throw new ProcessingException("Admin User Already exists");
            }

            //String username, String firstname, String lastname, String password, String email, Staff staffId        
//        adminUser.setPwdExpired(true);
            user.setPassword(pass);
            ResponseDetails authres = adminrepo.createUser(user);
//        OutMessages sms =new OutMessages();
//        sms.setDestination(phone);
//        sms.setSource(Log.OTPHEADER);
//        sms.setMessage(Log.ADMINUSERMESSAGE+actualpass);        
//        tokenRepo.insertSMS(sms);
            ses.commit();

            return authres;
        } catch (ProcessingException ex) {
            ses.rollback();
            System.out.println(ex.getMessage());
            throw new ProcessingException(ex.getMessage());
        }

//        return null;
    }

    private String hash(String password) throws ProcessingException {
        try {
            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            byte[] passwordBytes = password.getBytes();
            byte[] digest = sha512.digest(passwordBytes);
            StringBuilder stringBuilder = new StringBuilder();
            String s;
            for (byte b : digest) {
                s = Integer.toHexString((int) b & 0xff).toUpperCase();
                if (s.length() == 1) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ConManager.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new ProcessingException("NoSuchAlgorithmException");
        }
    }

    private String genPass() {
        final String sChar = "*+&$%&*+&&$%";
        String alpha = randomString(5);//RandomStringUtils.randomAlphanumeric(6);
        String lower = randomString(2).toLowerCase();//RandomStringUtils.randomAlphabetic(1).toLowerCase();
        int spot = Integer.parseInt(RandomStringUtils.randomNumeric(1));
        char special = sChar.charAt(spot);
        return alpha + lower + spot + special;
    }

    String randomString(int len) {
        final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public List<Loanapllication> getAuthorizedLoans() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Loanapllication> loans = adminrepo.getAuthorizedLoans();
        ses.commit();
        return loans;

    }

    public void updateLoan(Loanapllication selectedLoan) {
        UnitOfWorkSession ses = adminrepo.begin();
        adminrepo.updateLoan(selectedLoan);
        ses.commit();
    }

    public Loanapllication getPendingLoan(String referenceno) {
        UnitOfWorkSession ses = adminrepo.begin();
        Loanapllication loan = adminrepo.getPendingLoan(referenceno);
        ses.commit();
        return loan;
    }

    public Loanapllication getApprovedLoan(String referenceno) {
        UnitOfWorkSession ses = adminrepo.begin();
        Loanapllication loan = adminrepo.getApprovedLoan(referenceno);
        ses.commit();
        return loan;
    }

    public Loanapllication getDisbursedLoan(String referenceno) {
        UnitOfWorkSession ses = adminrepo.begin();
        Loanapllication loan = adminrepo.getDisbursedLoan(referenceno);
        ses.commit();
        return loan;
    }

    public Loanapllication getLoan(LoanStatus loanStatus, String referenceno) {
        UnitOfWorkSession ses = adminrepo.begin();
        Loanapllication loan = adminrepo.getLoan(loanStatus, referenceno);
        ses.commit();
        return loan;
    }

    public Loanapllication getLoanByEmail(String email) {
        UnitOfWorkSession ses = adminrepo.begin();
        Loanapllication loan = adminrepo.getLoanByEmail(email);
        ses.commit();
        return loan;
    }

    public List<Loanapllication> getLoansByStatus(LoanStatus loanStatus) {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Loanapllication> loans = adminrepo.getLoansByStatus(loanStatus);
        ses.commit();
        return loans;
    }

    public List<Installments> getInstallments(String refNum) {
        Loanapllication lp = getLoanByRef(refNum);
        if (lp != null) {
            Collection installments = lp.getInstallmentsCollection();
            return (List<Installments>) installments;
        } else {
            return new ArrayList<Installments>();
        }
    }

    public Loanapllication getLoanByRef(String refNum) {
        UnitOfWorkSession ses = adminrepo.begin();
        Loanapllication loan = adminrepo.getLoanByRef(refNum);
        ses.commit();
        return loan;
    }

    public Installments addInstallment(Installments installment, String referenceNo) {
        UnitOfWorkSession ses = adminrepo.begin();
        Loanapllication loan = adminrepo.getLoanByRef(referenceNo);
        installment.setLoanId(loan);
        Installments ins = adminrepo.addInstallment(installment);
        ses.commit();
        return ins;
    }

    public void updateInstallment(Installments installment) {
        UnitOfWorkSession ses = adminrepo.begin();
        adminrepo.updateInstallment(installment);
        ses.commit();
    }

    public List<Installments> getAllInstallments() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Installments> installments = adminrepo.getAllInstallments();
        ses.commit();
        return installments;
    }

    public List<Outmessages> getMessages(MessageStatus messageStatus) {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Outmessages> msgs = adminrepo.getMessages(messageStatus);
        ses.commit();
        return msgs;
    }

    public void updateMessage(Outmessages msg, MessageStatus status) {
        UnitOfWorkSession ses = adminrepo.begin();
        msg.setStatus(status.name());
        adminrepo.updateMessage(msg);
        ses.commit();
    }

    public HashSet<Loanapllication> searchCriticizedAssets(Date startdate, Date enddate) {
        UnitOfWorkSession ses = adminrepo.begin();
        HashSet<Loanapllication> ins = adminrepo.searchCriticizedAssets(startdate, enddate);
        ses.commit();
        return ins;
    }

    public ProfitAndLoss searchPL(Date startdate, Date enddate) {
        UnitOfWorkSession ses = adminrepo.begin();
        ProfitAndLoss pl = new ProfitAndLoss();
        List<Expense> exps = adminrepo.getExpenses(startdate, enddate);
        List<Installments> ins = adminrepo.searchInstallments(startdate, enddate);
        ArrayList<RepaymentDTO> repDto = new ArrayList<RepaymentDTO>();
        ArrayList<ExpenseDTO> expDtos = new ArrayList<ExpenseDTO>();

        Double totalexp = 0d;
        Double totalinterest = 0d;
        Double totalprincipal = 0d;
        if (exps != null) {
            for (Expense e : exps) {
                ExpenseDTO expDto = new ExpenseDTO();
                expDto.setAmount(e.getAmount());
                expDto.setAppuser(e.getAppuser());
                expDto.setExpenseDate(e.getExpenseDate());
                expDto.setExpenseId(e.getExpenseId());
                expDto.setPurpose(e.getPurpose());
                expDtos.add(expDto);
                totalexp += Double.parseDouble(e.getAmount());
            }
        }
        System.out.println("totalexp:" + totalexp);

        pl.setTotalExpenses(totalexp);
        pl.setExpenses(expDtos);

        if (ins != null) {

            for (Installments s : ins) {
                RepaymentDTO rep = new RepaymentDTO();

                rep.setAmount(s.getAmount());
                rep.setInterest(s.getInterest());
                rep.setPrincipal(s.getLoanId().getApprovedAmount());
                rep.setCustomerName(s.getLoanId().getSurname() + " " + s.getLoanId().getOtherNames());
                rep.setPresentationDate(s.getPresentationDate());
                rep.setAppuser(s.getAppuser());
                rep.setChequeNumber(s.getChequeNumber());
                rep.setCustomerType((LoanType.values())[s.getLoanId().getLoanType()].name());
                rep.setStatus(s.getStatus());
                totalprincipal += Double.parseDouble(rep.getPrincipal());
                totalinterest += Double.parseDouble(rep.getInterest());
                repDto.add(rep);
            }
        }
        pl.setTotalInterest(totalinterest);
        pl.setTotalPrincipal(totalprincipal);
        pl.setRepayments(repDto);
        pl.setTotalProfit(totalinterest - totalexp);

        System.out.println("Totalinterest:" + totalinterest);
        System.out.println("Totalprincipal:" + totalprincipal);
        System.out.println("TotalProfit:" + pl.getTotalProfit());

        ses.commit();
        return pl;
    }

    public List<Installments> getReturnedCheque() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Installments> installments = adminrepo.getReturnedCheque();
        ses.commit();
        return installments;
    }

    public Installments getInstallment(Integer installmentId) {
        UnitOfWorkSession ses = adminrepo.begin();
        Installments prod = adminrepo.getInstallment(installmentId);
        ses.commit();
        return prod;
    }

    public List<Expense> getExpenses() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Expense> expenses = adminrepo.getExpenses();
        ses.commit();
        return expenses;
    }

    public Expense addExpense(Expense exp) {
        UnitOfWorkSession ses = adminrepo.begin();
        exp = adminrepo.addExpense(exp);
        ses.commit();
        return exp;
    }

    public Expense getExpense(Integer expenseId) {
        UnitOfWorkSession ses = adminrepo.begin();
        Expense exp = adminrepo.getExpense(expenseId);
        ses.commit();
        return exp;
    }

    public void removeExpense(Expense exp) {
        UnitOfWorkSession ses = adminrepo.begin();
        adminrepo.removeExpense(exp);
        ses.commit();
    }

    public HashSet<Loanapllication> getCritisedAssets() {
        UnitOfWorkSession ses = adminrepo.begin();
        HashSet<Loanapllication> ca = adminrepo.getCritisedAssets();
        ses.commit();
        return ca;
    }

    public HashSet<Loanapllication> getNonPerformingLoans() {
        UnitOfWorkSession ses = adminrepo.begin();
        HashSet<Loanapllication> ca = adminrepo.getNonPerformingLoans();
        ses.commit();
        return ca;
    }

    public List<Installments> search(Date startdate, Date enddate) {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Installments> ins = adminrepo.searchInstallments(startdate, enddate);
        ses.commit();
        return ins;
    }

    public HashSet<Loanapllication> searchNPL(Date startdate, Date enddate) {
        UnitOfWorkSession ses = adminrepo.begin();
        HashSet<Loanapllication> ins = adminrepo.searchNPL(startdate, enddate);
        ses.commit();
        return ins;
    }

    public List<AccountDTO> getCustomers() {
        UnitOfWorkSession ses = adminrepo.begin();
        List<Account> loans = adminrepo.getCustomers();
        List<AccountDTO> accts = new ArrayList();
        if(loans!=null){
        for (Account a : loans) {
            AccountDTO ad = new AccountDTO();
            ad.setAccountemail(a.getAccountemail());
            ad.setAccountid(a.getAccountid());
            ad.setAccountname(a.getAccountname());
            ad.setAccountphone(a.getAccountphone());
            ad.setAddress(a.getAddress());
            ad.setDob(a.getDob());
            ad.setGender(a.getGender());

            Double tprofit = Double.NaN;
            tprofit = getTotalInterestByEmail(a.getAccountemail());
            ad.setTotalProfit(tprofit);
            List<Loanapllication> lloans=adminrepo.getLoansByEmail(a.getAccountemail());
            if(lloans!=null){
            ad.setNoOfLoans(lloans.size());
            ad.setLoans(lloans);
            }
            accts.add(ad);
        }
        }
        ses.commit();
        return accts;
    }

    private Double getTotalInterestByEmail(String accountemail) {
        UnitOfWorkSession ses = adminrepo.begin();
        Double tprofit = 0d;
        List<Loanapllication> loans = adminrepo.getLoansByEmail(accountemail);
        if (loans != null) {
            for (Loanapllication l : loans) {
                Iterator<Installments> iter = l.getInstallmentsCollection().iterator();
                while (iter.hasNext()) {
                    Installments ins = iter.next();
                    if(ins.getStatus().equalsIgnoreCase(ChequeStatus.APPROVED.name())){
                    tprofit += Double.parseDouble(ins.getInterest());
                    }
                }
            }
        }
        ses.commit();
        return tprofit;
    }

    public void updateAppuser(Appuser adminUser) {
      UnitOfWorkSession ses = adminrepo.begin();
        adminrepo.updateAppuser(adminUser);
        ses.commit();  
    }

}
