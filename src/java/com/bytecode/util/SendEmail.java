/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.util;

import com.bytecode.dto.LoanDTO;
import com.bytecode.entities.Loanapllication;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;


/**
 *
 * @author Ahmed
 */
public class SendEmail {
    
    static boolean sendADEmail(Email msg) {
            boolean sent =false;
           try {
            // Create the email message            
            HtmlEmail email = new HtmlEmail();
            email.setHostName("172.16.10.184");
            email.setSmtpPort(25);
            email.addTo(msg.getEmailAddress());
            email.setFrom("twofactor@unionbankng.com","Union Bank");
            if(msg.getSubject().equalsIgnoreCase("SPECIAL")){
            email.setSubject("MANUAL ACTIVATION");
            }else{email.setSubject("Two Factor Authentication Details");}
            String formattedEmail = formatEmail(msg.getMessage());
            // set the html message
            email.setHtmlMsg(formattedEmail);
            // set the alternative message
            email.setTextMsg("Your email client does not support HTML messages");
            // send the email
            Log.l.infoLog.info("Sending email to "+msg.getEmailAddress());            
            String response = email.send();
            sent=true;
            Log.l.infoLog.info("Email Sent to :"+msg.getEmailAddress()+ "  Response:"+response);
            return sent;
        } catch (EmailException ex) {
            Log.l.infoLog.info(ex);            
            return sent;
        } catch (Exception ex) {
            Log.l.infoLog.info(ex);            
            return sent;
        } 
    }
    private static String formatEmail(String cid) {
        String email ="<html><head><title>Two Factor Authentication</title>" +
                "<style type='text/css'>div{margin:0px;padding:0px;}</style>" +
                "</head><body style='margin:0px'>" +cid+
                "</body></html>";

        return email;
    }

    
    public void sendSimpleMail(Email msg)
    {
    // Create The Email
        MultiPartEmail email = new MultiPartEmail();
        try 
        {               
            email.setHostName("webmail.firmuscap.com");
            email.setSmtpPort(25);
            email.setAuthenticator(new DefaultAuthenticator("alert@firmuscap.com", "Admin100$"));            
            email.setFrom("alert@firmuscap.com");
            email.setSubject(msg.getSubject());
            email.setMsg(msg.getMessage());
            email.addTo(msg.getEmailAddress());
//            email.setTLS(true);        
            System.out.println("**************");
            email.send();                            
            System.out.println("**************");
        }
        catch (EmailException ee) {
        ee.printStackTrace();
        }
    }
    
    
    public static void main(String args[]) {
        try
        {                    
            Email email=new Email();
            email.setEmailAddress("lawale4me@yahoo.com");
            email.setSubject("Your have create a new Case :");
            email.setMessage("Case Body"+"\n");
            System.out.println("about to send");
            new SendEmail().sendSimpleMail(email);
            System.out.println("sent");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public String constructLoanEmail(LoanDTO loan) {
     return "Loan application of "+loan.getLoanAmount() +" Recieved From "+loan.getSurname()+" "+loan.getOtherNames()
             + " who works for "+ loan.getEmployer()+ " as a "+loan.getGrade()+"("+loan.getLengthOfService()+")"             ;   
    }

    public String constructLoanApproval(Loanapllication loan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String constructLoanCANCELED(Loanapllication loan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String constructLoanAUTHORIZED(Loanapllication loan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}