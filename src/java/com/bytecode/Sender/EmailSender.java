/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.Sender;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.dto.MessageStatus;
import com.bytecode.entities.Outmessages;
import com.bytecode.util.Email;
import com.bytecode.util.SendEmail;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public class EmailSender implements Runnable 
{

    @Override
    public void run()
    {
        ConManager manager=new ConManager(new AdminRepositoryImpl());
        
       List<Outmessages> pendingList = manager.getMessages(MessageStatus.PENDING);

        try {
            int i=0;
            for(Outmessages msg : pendingList){
            
                System.out.println("=============== Started SubcriptionSender Task Runner ================");                
                SendEmail emailSender=new SendEmail();
                Email email=new Email();
                email.setEmailAddress(msg.getEmail());
                email.setMessage(msg.getMessage());
                email.setSubject(msg.getEmailType().toString());
                emailSender.sendSimpleMail(email);
                manager.updateMessage(msg,MessageStatus.SENT);                
                System.out.println("=============== Completed SubcriptionSender Task Runner ================");
              i++;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("The error message is "+ex.getLocalizedMessage());
            System.out.println("The error message is "+ex.getMessage());            
        }
    }
    
}
