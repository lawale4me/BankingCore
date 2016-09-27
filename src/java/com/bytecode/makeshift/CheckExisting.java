/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.makeshift;

import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.entities.Loanapllication;
import com.bytecode.entities.Paccount;
import com.bytecode.response.BalanceResponse;
import com.bytecode.response.LoanExistingResponse;
import com.bytecode.util.ResponseCode;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Ahmed
 */
@WebServlet(name="WebPortal_CheckExisting", urlPatterns={"/WebPortal/CheckExisting"})
public class CheckExisting extends HttpServlet {
   
    ConManager manager ;
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        LoanExistingResponse existRes = new LoanExistingResponse();

        String email = null,channel=null;
        
        try{
             email = request.getParameter("email").trim();             
             channel = request.getParameter("channel").trim();
        }
        catch(java.lang.NullPointerException e){
             existRes.setResponseCode(String.valueOf(ResponseCode.FAILED));
            existRes.setDescription(ResponseCode.Invalid_request);
        }
         if (email != null ) {
            try {
                Loanapllication loan = manager.getLoanByEmail(email);
                  
                if (loan != null) {
                    
                        
                        existRes.setResponseCode(String.valueOf(ResponseCode.OK));
                        existRes.setDescription("User exists");
                        existRes.setLoanapplication(loan);
                    
                } else {
                    existRes.setResponseCode(String.valueOf(ResponseCode.FAILED));
                    existRes.setDescription(ResponseCode.INVALID_EMAIL);
                }

            } catch (Exception e) {
                System.err.println("Exception:" + e);
                existRes.setResponseCode(String.valueOf(ResponseCode.FAILED));
                existRes.setDescription(ResponseCode.Internal_Error_Occurred);
            }
        } else {
            existRes.setResponseCode(String.valueOf(ResponseCode.FAILED));
            existRes.setDescription(ResponseCode.Invalid_request);
        }

        Gson gson=new Gson();
        out.println(gson.toJson(existRes));
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
        BalanceResponse existRes = new BalanceResponse();
        try{
       existRes.setResponseCode(ResponseCode.METHODNOTALLOWED);
       existRes.setDescription(ResponseCode.METHOD_NOT_ALLOWED);
       
       out.println(existRes);   
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
