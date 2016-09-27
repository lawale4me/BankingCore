/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytecode.makeshift;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.core.LoanException;
import com.bytecode.core.RegisterException;
import com.bytecode.dto.AccountDTO;
import com.bytecode.dto.LoanDTO;
import com.bytecode.encryption.AES256Cipher;
import com.bytecode.response.LoanApplicationResponse;
import com.bytecode.util.ResponseCode;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ahmed
 */
@WebServlet(name = "WebPortal_LoanApplication", urlPatterns = {"/WebPortal/LoanApplication"})
public class LoanApplication extends HttpServlet {

    ConManager manager;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        LoanApplicationResponse loanResponse = new LoanApplicationResponse();

        String surname = null, tenor = null, title = null,
                totalExistingLoan = null, signature = null, purpose = null, prodCode = null, phone = null,
                payDay = null, otherNames = null, officeEmail = null, loanAmount = null,
                lengthOfService = null, kinWork = null, kinType = null,
                kinPhone = null, kinName = null, jobDesc = null, idCard = null,
                grade = null, employerPhone = null, employerAddress = null, employer = null,
                empNo = null, email = null, dob = null, dept = null,
                channel = null, bankStatement = null, bankName = null, bankAccountNo = null,
                lastTurnover = null, businessNature = null, businessYr = null,
                tenancyInvoice = null, landlordName = null, propertyAddres = null, landlordBankName = null, landlordAccountNo = null,
                childName = null, childSchool = null, schoolBankName = null, schoolAccountNo = null, schoolfeeInvoice = null,
                annualSalary = null, address = null, installment = null, loanType = null, xtra = null;
        try {

            phone = request.getParameter("phone");
            surname = request.getParameter("surname");
            tenor = request.getParameter("tenor");
            title = request.getParameter("title");
            totalExistingLoan = request.getParameter("totalExistingLoan");
            signature = request.getParameter("signature");
            purpose = request.getParameter("purpose");
            prodCode = request.getParameter("prodCode");
            payDay = request.getParameter("payDay");
            otherNames = request.getParameter("otherNames");
            officeEmail = request.getParameter("officeEmail");
            loanAmount = request.getParameter("loanAmount");
            lengthOfService = request.getParameter("lengthOfService");
            kinWork = request.getParameter("kinWork");
            kinType = request.getParameter("kinType");
            kinPhone = request.getParameter("kinPhone");
            kinName = request.getParameter("kinName");
            jobDesc = request.getParameter("jobDesc");
            idCard = request.getParameter("idCard");
            grade = request.getParameter("grade");
            employerPhone = request.getParameter("employerPhone");
            employerAddress = request.getParameter("employerAddress");
            employer = request.getParameter("employer");
            empNo = request.getParameter("empNo");
            email = request.getParameter("email");
            dob = request.getParameter("dob");
            dept = request.getParameter("dept");
            bankStatement = request.getParameter("bankStatement");
            bankName = request.getParameter("bankName");
            bankAccountNo = request.getParameter("bankAccountNo");
            annualSalary = request.getParameter("annualSalary");
            address = request.getParameter("address");
            channel = request.getParameter("channel");
            loanType = request.getParameter("loanType");
            installment = request.getParameter("installment");

            lastTurnover = request.getParameter("lastTurnover");
            businessNature = request.getParameter("businessNature");
            businessYr = request.getParameter("businessYr");
            tenancyInvoice = request.getParameter("tenancyInvoice");
            landlordName = request.getParameter("landlordName");
            propertyAddres = request.getParameter("propertyAddres");
            landlordBankName = request.getParameter("landlordBankName");
            landlordAccountNo = request.getParameter("landlordAccountNo");
            childName = request.getParameter("childName");
            childSchool = request.getParameter("childSchool");
            schoolBankName = request.getParameter("schoolBankName");
            schoolAccountNo = request.getParameter("schoolAccountNo");
            schoolfeeInvoice = request.getParameter("schoolfeeInvoice");
            xtra = request.getParameter("xtra");

            System.out.println("loanType:" + loanType);
            //***********DESCRIPTION STARTS**********************
            String key = "05c748a690dcf2673228400e5730b896";
            try {
                email = AES256Cipher.AES_Decode(email, key);
//              loanAmount = AES256Cipher.AES_Decode(loanAmount, key);
//              tenor = AES256Cipher.AES_Decode(tenor, key);
//              bankAccountNo = AES256Cipher.AES_Decode(bankAccountNo, key);
//              installment = AES256Cipher.AES_Decode(installment, key);
//              schoolAccountNo = AES256Cipher.AES_Decode(schoolAccountNo, key);
//              landlordAccountNo = AES256Cipher.AES_Decode(landlordAccountNo, key);
                System.out.println("email_Decode :" + email);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
             ///END OF DESCRIPTION

        } catch (java.lang.NullPointerException e) {
            loanResponse.setResponseCode(String.valueOf(ResponseCode.FAILED));
            loanResponse.setDescription(ResponseCode.Invalid_request);
        }

        System.out.println("loanType :" + loanType);
        //CHECK IF EMAIL EXISTS To avoid duplicate  records            
        System.out.println("Email:" + email);
        if (!manager.checkEmail(email) || xtra != null && xtra.equalsIgnoreCase("dfghj")) {
            try {

                LoanDTO loan = new LoanDTO();
                loan.setSurname(surname);
                loan.setTenor(tenor);
                loan.setTitle(title);
                loan.setTotalExistingLoan(totalExistingLoan);
                loan.setSignature(signature);
                loan.setPurpose(purpose);
                loan.setProdCode(prodCode);
                loan.setPhone(phone);
                loan.setPayDay(payDay);
                loan.setOtherNames(otherNames);
                loan.setOfficeEmail(officeEmail);
                loan.setLoanAmount(loanAmount);
                loan.setLengthOfService(lengthOfService);
                loan.setKinWork(kinWork);
                loan.setKinType(kinType);
                loan.setKinPhone(kinPhone);
                loan.setKinName(kinName);
                loan.setJobDesc(jobDesc);
                loan.setIdCard(idCard);
                loan.setGrade(grade);
                loan.setEmployerPhone(employerPhone);
                loan.setEmployerAddress(employerAddress);
                loan.setEmployer(employer);
                loan.setEmpNo(empNo);
                loan.setEmail(email);
                loan.setDob(dob);
                loan.setDept(dept);
                loan.setChannel(channel);
                loan.setBankStatement(bankStatement);
                loan.setBankName(bankName);
                loan.setBankAccountNo(bankAccountNo);
                loan.setAnnualSalary(annualSalary);
                loan.setAddress(address);
                loan.setLoanDate(new Date());

                loan.setLastTurnover(lastTurnover);
                loan.setBusinessNature(businessNature);
                loan.setBusinessYr(businessYr);
                loan.setTenancyInvoice(tenancyInvoice);
                loan.setLandlordName(landlordName);
                loan.setPropertyAddres(propertyAddres);
                loan.setLandlordBankName(landlordBankName);
                loan.setLandlordAccountNo(landlordAccountNo);
                loan.setChildName(childName);
                loan.setChildSchool(childSchool);
                loan.setSchoolBankName(schoolBankName);
                loan.setSchoolAccountNo(schoolAccountNo);
                loan.setSchoolfeeInvoice(schoolfeeInvoice);
                loan.setLoanType(Integer.parseInt(loanType));

                //REGISTRING ACCOUNTS
                if(xtra == null){
                AccountDTO adto = new AccountDTO();
                adto.setAccountname(loan.getSurname() + " " + loan.getOtherNames());
                adto.setAccountemail(email);
                adto.setAccountphone(phone);
                adto.setAddress(address);
                adto.setProdcode(prodCode);
                DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                Date date = format.parse(dob);
                adto.setDob(date);
                String accountNo = manager.registerAccount(adto);
                }
                //END OF REGISTRATION

                LoanApplicationResponse res = manager.LoanApplication(loan);

                loanResponse.setResponseCode(ResponseCode.sOK);
                loanResponse.setDescription(ResponseCode.LOAN_APPLICATION_SUCCESSFUL);
                loanResponse.setReferenceNo(res.getReferenceNo());

//                SendEmail sendObject=new SendEmail();
//                Email mail=new Email();
//                mail.setEmailAddress(loan.getEmail());
//                mail.setMessage(sendObject.constructLoanEmail(loan));
//                mail.setSubject(ResponseCode.LOAN_APPLICATION_SUBMITTED);
//                sendObject.sendSimpleMail(mail);
            } catch (LoanException e) {
                loanResponse.setResponseCode(String.valueOf(ResponseCode.FAILED));
                loanResponse.setDescription(e.getMessage());
            } catch (ParseException ex) {
                loanResponse.setResponseCode(String.valueOf(ResponseCode.FAILED));
                loanResponse.setDescription(ex.getMessage());
            } catch (RegisterException ex) {
                loanResponse.setResponseCode(String.valueOf(ResponseCode.FAILED));
                loanResponse.setDescription(ex.getMessage());
            }

        } else {
            loanResponse.setResponseCode(String.valueOf(ResponseCode.FAILED));
            loanResponse.setDescription("User Details exist");
        }

        System.out.println(loanResponse);
        Gson gson = new Gson();
        out.println(gson.toJson(loanResponse));
        out.close();

    }

    @Override
    public void init() {
        manager = new ConManager(new AdminRepositoryImpl());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        LoanApplicationResponse loanResponse = new LoanApplicationResponse();
        try {
            loanResponse.setResponseCode(String.valueOf(ResponseCode.METHODNOTALLOWED));
            loanResponse.setDescription(ResponseCode.METHOD_NOT_ALLOWED);

            Gson gson = new Gson();
            out.println(gson.toJson(loanResponse));
        } finally {
            out.close();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Loan Application Service";
    }// </editor-fold>

}
