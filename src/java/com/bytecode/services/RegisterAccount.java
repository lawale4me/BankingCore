/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.services;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.dto.AccountDTO;
import com.bytecode.response.RegisterResponse;
import com.bytecode.util.ResponseCode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/**
 *
 * @author Ahmed
 */
@Path("/json/registerAccount")
public class RegisterAccount 
{

    @POST
    @Produces("application/json")
    public RegisterResponse registerAccount(@FormParam("accountName")String accountname,
            @FormParam("email")String email, 
            @FormParam("phone")String phone,            
            @FormParam("dob")String dob,
            @FormParam("prodCode")String prodCode,
            @FormParam("channel")String channel,
            @FormParam("pin")String pin,
            @FormParam("userId")String userId,
            @FormParam("address")String address) 
    {
        //TODO return proper representation object
        ConManager manager = new ConManager(new AdminRepositoryImpl());                
        RegisterResponse regResponse = new RegisterResponse();      
        
        if(accountname!=null&&prodCode!=null&&phone!=null&&email!=null){
        try
        {                          
           AccountDTO adto=new AccountDTO();
           adto.setAccountname(accountname);
           adto.setAccountemail(email);
           adto.setAccountphone(phone);
           adto.setAddress(address);
           adto.setProdcode(prodCode);               
           DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
           Date date = format.parse(dob);           
           adto.setDob(date);
           
           
          String accountNo = manager.registerAccount(adto);           
           regResponse.setResponseCode(String.valueOf(ResponseCode.OK));
           regResponse.setDescription(ResponseCode.REGISTRATION_SUCCESSFUL);    
           regResponse.setAccountNo(accountNo);
        }catch(Exception e){            
            e.printStackTrace();
            regResponse.setResponseCode(String.valueOf(ResponseCode.FAILED));
            regResponse.setDescription(ResponseCode.Internal_Error_Occurred);
        }
        }else{
            regResponse.setResponseCode(String.valueOf(ResponseCode.FAILED));
            regResponse.setDescription(ResponseCode.Invalid_request);
        }
        
        return regResponse;
    }
    
}
