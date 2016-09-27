/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytecode.services;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.core.LoanException;
import com.bytecode.dto.LoanDTO;
import com.bytecode.response.LoanApplicationResponse;
import com.bytecode.util.Email;
import com.bytecode.util.ResponseCode;
import com.bytecode.util.SendEmail;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ahmed
 */
@Path("/json/loanApplication")
public class LoanApplication {
    
    
    @Resource
    private ServletContext context;

    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public LoanApplicationResponse loanApplication(@FormParam("surname") String surname,
            @FormParam("otherNames") String otherNames,
            @FormParam("title") String title,
            @FormParam("email") String email,
            @FormParam("phone") String phone,
            @FormParam("dob") String dob,
            @FormParam("prodCode") String prodCode,
            @FormParam("address") String address,
            @FormParam("kinName") String kinName,
            @FormParam("kinPhone") String kinPhone,
            @FormParam("kinWork") String kinWork,
            @FormParam("kinType") String kinType,
            @FormParam("employer") String employer,
            @FormParam("employerAddress") String employerAddress,
            @FormParam("employerPhone") String employerPhone,
            @FormParam("officeEmail") String officeEmail,
            @FormParam("grade") String grade,
            @FormParam("lengthOfService") String lengthOfService,
            @FormParam("jobDesc") String jobDesc,
            @FormParam("empNo") String empNo,
            @FormParam("dept") String dept,
            @FormParam("annualSalary") String annualSalary,
            @FormParam("payDay") String payDay,
            @FormParam("totalExistingLoan") String totalExistingLoan,
            @FormParam("tenor") String tenor,
            @FormParam("purpose") String purpose,
            @FormParam("loanAmount") String loanAmount,
            @FormParam("signature") String signature,
            @FormParam("bankName") String bankName,
            @FormParam("bankAccountNo") String bankAccountNo,
            @FormParam("bankStatement") String bankStatement,
            @FormParam("idCard") String idCard,
            @FormParam("data_string") String data_string,
            @FormParam("channel") String channel) 
    {
        //TODO return proper representation object
        ConManager manager = new ConManager(new AdminRepositoryImpl());
        LoanApplicationResponse loanResponse = new LoanApplicationResponse();

        if (true) 
        {            
            //CHECK IF EMAIL OR PHONE EXISTS To avoid duplicate  records            
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
                                
                LoanApplicationResponse res =  manager.LoanApplication(loan);
                
                loanResponse.setResponseCode(String.valueOf(ResponseCode.OK));
                loanResponse.setDescription(ResponseCode.LOAN_APPLICATION_SUCCESSFUL);
                loanResponse.setReferenceNo(res.getReferenceNo());
                
                SendEmail sendObject=new SendEmail();
                Email mail=new Email();
                mail.setEmailAddress(loan.getEmail());
                mail.setMessage(sendObject.constructLoanEmail(loan));
                mail.setSubject(ResponseCode.LOAN_APPLICATION_SUBMITTED);
                sendObject.sendSimpleMail(mail);
                
            } catch (LoanException e) {                
                loanResponse.setResponseCode(String.valueOf(ResponseCode.FAILED));
                loanResponse.setDescription(e.getMessage());
            }
//                catch (JSONException ex) {
//                ex.printStackTrace();
//                loanResponse.setResponseCode(String.valueOf(ResponseCode.FAILED));
//                loanResponse.setDescription(ex.getMessage());
//            }
        } else {
            loanResponse.setResponseCode(String.valueOf(ResponseCode.FAILED));
            loanResponse.setDescription("Invalid request:LOAN");
        }

        return loanResponse;
    }


}
