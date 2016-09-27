/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.makeshift;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.entities.Paccount;
import com.bytecode.entities.Transactions;
import com.bytecode.response.DepositResponse;
import com.bytecode.util.ResponseCode;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Ahmed
 */
@WebServlet(name="WebPortal_FundDeposit", urlPatterns={"/WebPortal/FundDeposit"})
public class FundDeposit extends HttpServlet {
   
    ConManager manager ;
     

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        DepositResponse depResponse = new DepositResponse();  
        String accountNo = null,pin = null,channel=null,
                amount=null,userId=null,narration=null;
        
        try{
             accountNo = request.getParameter("accountNo").trim();
             pin = request.getParameter("pin").trim();
             amount = request.getParameter("amount").trim();
             userId = request.getParameter("userId").trim();
             narration = request.getParameter("narration").trim();
             channel = request.getParameter("channel").trim();
        }
        catch(java.lang.NullPointerException e){
            depResponse.setResponseCode(ResponseCode.FAILED);
            depResponse.setDescription(ResponseCode.Invalid_request);
        }
        
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
        
        Gson gson=new Gson();
        out.println(gson.toJson(depResponse));
        out.flush();
    
    }
    
   @Override
   public void init(){
   manager= new ConManager(new AdminRepositoryImpl());
   }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        DepositResponse depResponse = new DepositResponse();  
        try{
       depResponse.setResponseCode(ResponseCode.METHODNOTALLOWED);
       depResponse.setDescription(ResponseCode.METHOD_NOT_ALLOWED);
       
        Gson gson=new Gson();
        out.println(gson.toJson(depResponse));
           
        }
       finally {
            out.close();
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
