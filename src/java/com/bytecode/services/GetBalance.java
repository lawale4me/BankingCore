/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytecode.services;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.entities.Paccount;
import com.bytecode.response.BalanceResponse;
import com.bytecode.util.ResponseCode;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Ahmed
 */
@Path("/json/getBalance")
public class GetBalance {

    @POST
    @Produces("application/json")
    //@Consumes("application/json")
    public BalanceResponse getBalance(@FormParam("requestParam") String requestParam) {
        //TODO return proper representation object
        ConManager manager = new ConManager(new AdminRepositoryImpl());
        BalanceResponse balRes = new BalanceResponse();

        JSONObject json;
        String accountNo = null,pin = null,channel;
        try {
            json = new JSONObject(requestParam);
            
            accountNo = json.getString("accountNo");
            pin = json.getString("pin");
            channel = json.getString("channel");
            
        } catch (JSONException ex) {
            Logger.getLogger(GetBalance.class.getName()).log(Level.SEVERE, null, ex);
            balRes.setResponseCode(ResponseCode.FAILED);
            balRes.setDescription(ResponseCode.Invalid_request);
            return balRes;
        }      
        catch (Exception ex) {
            Logger.getLogger(GetBalance.class.getName()).log(Level.SEVERE, null, ex);
            balRes.setResponseCode(ResponseCode.FAILED);
            balRes.setDescription(ResponseCode.Invalid_request);
            return balRes;
        } 
        
        if (accountNo != null && pin != null) {
            try {
                Paccount acct = manager.getPaccount(accountNo);

                if (acct != null) {
                    if (acct.getAccountid().getPin().equalsIgnoreCase(pin)) {
                        balRes.setBalance(acct.getBalance().toString());
                        balRes.setLedgerBalance(acct.getLedgerbalance().toString());
                        balRes.setResponseCode(ResponseCode.OK);
                        balRes.setDescription(ResponseCode.BALANCE_SUCCESSFUL);
                    } else {
                        balRes.setResponseCode(ResponseCode.FAILED);
                        balRes.setDescription(ResponseCode.INVALID_PIN);
                    }
                } else {
                    balRes.setResponseCode(ResponseCode.FAILED);
                    balRes.setDescription(ResponseCode.INVALID_ACCOUNTNO);
                }

            } catch (Exception e) {
                System.err.println("Exception:" + e);
                balRes.setResponseCode(ResponseCode.FAILED);
                balRes.setDescription(ResponseCode.Internal_Error_Occurred);
            }
        } else {
            balRes.setResponseCode(ResponseCode.FAILED);
            balRes.setDescription(ResponseCode.Invalid_request);
        }

        return balRes;
    }
}
