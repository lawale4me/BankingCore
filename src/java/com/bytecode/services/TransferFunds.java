/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytecode.services;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.core.TransferException;
import com.bytecode.entities.Paccount;
import com.bytecode.entities.Transactions;
import com.bytecode.response.TrxnResponse;
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
@Path("/json/transferFunds")
public class TransferFunds {

    @POST
    @Produces("application/json")
    public TrxnResponse transferFunds(@FormParam("accountNo") String accountNo,
            @FormParam("userId") String userId,
            @FormParam("pin") String pin,
            @FormParam("amount") String amount,
            @FormParam("narration") String narration,
            @FormParam("destAccount") String destAccount,
            @FormParam("channel") String channel) {
        //TODO return proper representation object
        ConManager manager = new ConManager(new AdminRepositoryImpl());
        TrxnResponse regResponse = new TrxnResponse();

        if (accountNo != null && pin != null && amount != null) 
        {                        
            Paccount acct = manager.getPaccount(accountNo);
            if (acct != null) {
                try {
                    if (acct.getAccountid().getPin().equalsIgnoreCase(pin)) {
                    Transactions trxn = new Transactions();
                    trxn.setTranxdate(new Date());
                    trxn.setAccountid(acct.getAccountid());
                    trxn.setAmount(Double.parseDouble(amount));
                    trxn.setDescription(narration);
                    trxn.setNarration(narration);
                    String refNum = String.valueOf(System.currentTimeMillis() + (new java.security.SecureRandom().nextInt(999) + 1));
                    trxn.setRefnum(refNum);
                    trxn.setSourceaccount(accountNo);
                    trxn.setTrxnparam(channel+"|"+amount+"|"+accountNo+"|"+destAccount);
                    trxn.setDestaccount(destAccount);

                    manager.transferFunds(trxn);
                    regResponse.setResponseCode(ResponseCode.OK);
                    regResponse.setDescription(ResponseCode.TRANSFER_SUCCESSFUL);       
                    } else {
                        regResponse.setResponseCode(ResponseCode.FAILED);
                        regResponse.setDescription(ResponseCode.INVALID_PIN);
                    }
                } catch (TransferException te) {
                    System.err.println("Exception:" + te);
                    regResponse.setResponseCode(ResponseCode.FAILED);
                    regResponse.setDescription(te.getMessage());
                }
                catch (Exception e) {
                    System.err.println("Exception:" + e);
                    regResponse.setResponseCode(ResponseCode.FAILED);
                    regResponse.setDescription(ResponseCode.Internal_Error_Occurred);
                }
            }
        } else {
            regResponse.setResponseCode(ResponseCode.FAILED);
            regResponse.setDescription(ResponseCode.Invalid_request);
        }

        return regResponse;
    }

}
