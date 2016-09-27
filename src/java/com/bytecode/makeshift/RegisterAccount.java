/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.makeshift;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.dto.AccountDTO;
import com.bytecode.entities.Paccount;
import com.bytecode.response.RegisterResponse;
import com.bytecode.util.ResponseCode;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ahmed
 */
@WebServlet(name = "RegisterAccount", urlPatterns = {"/WebPortal/RegisterAccount"})
public class RegisterAccount extends HttpServlet {

    ConManager manager;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");        
        PrintWriter out = response.getWriter();

        RegisterResponse regResponse = new RegisterResponse(); 
          
        String accountname = null,email = null,phone=null,address = null,
               prodCode = null,dob=null,userId=null,pin=null;
        
        try{
             accountname = request.getParameter("accountName").trim();
             email = request.getParameter("email").trim();
             phone = request.getParameter("phone").trim();
             address = request.getParameter("address").trim();
             prodCode = request.getParameter("prodCode").trim();
             dob = request.getParameter("dob").trim();
             pin = request.getParameter("pin").trim();
             userId = request.getParameter("userId").trim();
            
        }
        catch(java.lang.NullPointerException e){
            regResponse.setResponseCode(String.valueOf(ResponseCode.FAILED));
            regResponse.setDescription(ResponseCode.Invalid_request);
        }
         
        
           
        
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
           adto.setPin(Integer.parseInt(pin));
           
           
          String accountNo = manager.registerAccount(adto);           
           regResponse.setResponseCode(String.valueOf(ResponseCode.OK));
           regResponse.setDescription(ResponseCode.REGISTRATION_SUCCESSFUL);    
           regResponse.setAccountNo(accountNo);
        }catch(Exception e){            
            e.printStackTrace();
            regResponse.setResponseCode(String.valueOf(ResponseCode.FAILED));
            regResponse.setDescription(e.getMessage());
        }
        }else{
            regResponse.setResponseCode(String.valueOf(ResponseCode.FAILED));
            regResponse.setDescription(ResponseCode.Invalid_request);
        }
        
        
         Gson gson=new Gson();
       out.println(gson.toJson(regResponse));
        out.close();
    
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
        RegisterResponse regResponse = new RegisterResponse(); 
        try{
       regResponse.setResponseCode(String.valueOf(ResponseCode.METHODNOTALLOWED));
       regResponse.setDescription(ResponseCode.METHOD_NOT_ALLOWED);
       
        Gson gson=new Gson();
       out.println(gson.toJson(regResponse)); 
        }
       finally {
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
        return "Short description";
    }// </editor-fold>
    
    
     @Override
   public void init(){
   manager= new ConManager(new AdminRepositoryImpl());
   }

}
