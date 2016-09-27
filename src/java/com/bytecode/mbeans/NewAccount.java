/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.mbeans;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.dto.AccountDTO;
import com.bytecode.response.RegisterResponse;
import com.bytecode.services.RegisterAccount;
import com.bytecode.util.ResponseCode;
import com.bytecode.util.Util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;

/**
 *
 * @author Ahmed
 */
@ManagedBean
@RequestScoped
public class NewAccount {

    
    String pin,address,userid,phone,accountName,email;
    
    /**
     * Creates a new instance of NewAccount
     */
    public NewAccount() {
    }
    
    
    public String register() {
        FacesMessage msg;// = null;
        try {                
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, Object> params = fc.getExternalContext().getSessionMap();                

                                                                          
        System.out.println(accountName);
        System.out.println(email);
        System.out.println(phone);
        System.out.println(pin);
        System.out.println(userid);
        System.out.println(address);
        
        RegisterResponse result = registerAccount(accountName, email, phone, "16-02-1986", "SA22", "admin portal", pin, userid, address);        
        if (result!=null&&result.getResponseCode().equalsIgnoreCase(String.valueOf(ResponseCode.OK))) {                    
            System.out.println(result.getResponseCode());
            System.out.println(result.getAccountNo());
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your Account Opening was successful", userid+" "+result.getAccountNo());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
            // get Http Session and store username
            HttpSession session = Util.getSession();
            session.setAttribute("username", userid);
            
            
            accountName=null;
            email=null;
            phone=null;
            pin=null;
            userid=null;
            
            
            return null;
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Account Openning Error Error", result.getDescription());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
            } catch (Exception ex) {
                ex.printStackTrace();
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Account Openning Error Error", ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    public RegisterResponse registerAccount(String accountname,
           String email, 
            String phone,            
            String dob,
            String prodCode,
            String channel,
            String pin,
            String userId,
            String address) 
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
