/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.services;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.entities.Paccount;
import com.bytecode.entities.Transactions;
import com.bytecode.response.DepositResponse;
import com.bytecode.util.ResponseCode;
import java.util.Date;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Ahmed
 */
@Path("/json/fundDeposit")
public class FundDeposit {
    
    
     @POST
    @Produces("application/json")
    public DepositResponse fundDeposit(@FormParam("accountNo")String accountNo,
            @FormParam("userId")String userId, 
            @FormParam("amount")String amount,
            @FormParam("pin")String pin,
            @FormParam("narration")String narration,
            @FormParam("channel")String channel) 
    {
        //TODO return proper representation object
        ConManager manager = new ConManager(new AdminRepositoryImpl());                
        DepositResponse depResponse = new DepositResponse();      
        
       if(accountNo!=null&&amount!=null&&userId!=null)
        {
        try
        {                                    
             Paccount acct = manager.getPaccount(accountNo);
           if (acct != null) 
           {
                try {
                    if (acct.getAccountid().getPin().equalsIgnoreCase(pin)) 
                    {
                    Transactions trxn = new Transactions();
                    trxn.setTranxdate(new Date());
                    trxn.setAccountid(acct.getAccountid());
                    trxn.setAmount(Double.parseDouble(amount));
                    trxn.setDescription(narration);
                    trxn.setNarration(narration);
                    trxn.setSourceaccount(accountNo);
                    String refNum = String.valueOf(System.currentTimeMillis() + (new java.security.SecureRandom().nextInt(999) + 1));
                    trxn.setRefnum(refNum);
                                      
                    manager.fundDeposit(trxn);                                        
                    depResponse.setResponseCode(ResponseCode.OK);
                    depResponse.setDescription(ResponseCode.DEPOSIT_SUCCESSFUL);       
                    } else {
                        depResponse.setResponseCode(ResponseCode.FAILED);
                        depResponse.setDescription(ResponseCode.INVALID_PIN);
                    }
                } 
                catch (Exception e) {
                    System.err.println("Exception:" + e);
                    depResponse.setResponseCode(ResponseCode.FAILED);
                    depResponse.setDescription(ResponseCode.Internal_Error_Occurred);
                }
            }       
        }catch(Exception e){
            System.err.println("Exception:"+e);
            depResponse.setResponseCode(ResponseCode.FAILED);
            depResponse.setDescription("Internal Error Occurred");
        }
        }else{
            depResponse.setResponseCode(ResponseCode.FAILED);
            depResponse.setDescription("Invalid request");
        }
        return depResponse;
    }
}
