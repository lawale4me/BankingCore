/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.makeshift;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.core.TransferException;
import com.bytecode.entities.Paccount;
import com.bytecode.entities.Transactions;
import com.bytecode.response.BalanceResponse;
import com.bytecode.response.TrxnResponse;
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
@WebServlet(name="WebPortal_TransferFunds", urlPatterns={"/WebPortal/TransferFunds"})
public class TransferFunds extends HttpServlet {
   
    ConManager manager ;
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        TrxnResponse regResponse = new TrxnResponse();
        String accountNo = null,pin = null,channel=null,
                amount=null,narration=null,destAccount=null;
        
        try{
             accountNo = request.getParameter("accountNo").trim();
             pin = request.getParameter("pin").trim();
             amount = request.getParameter("amount").trim();
             narration = request.getParameter("narration").trim();
             destAccount = request.getParameter("destAccount").trim();
             channel = request.getParameter("channel").trim();
        }
        catch(java.lang.NullPointerException e){
            regResponse.setResponseCode(ResponseCode.FAILED);
            regResponse.setDescription(ResponseCode.Invalid_request);
        }
        

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

         Gson gson=new Gson();
       out.println(gson.toJson(regResponse));
        out.close();
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
       TrxnResponse regResponse = new TrxnResponse();
        try{
       regResponse.setResponseCode(ResponseCode.METHODNOTALLOWED);
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
