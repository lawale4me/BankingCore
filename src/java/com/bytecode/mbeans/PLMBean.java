/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.mbeans;


import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.core.UserException;
import com.bytecode.dto.ExpenseDTO;
import com.bytecode.dto.ProfitAndLoss;
import com.bytecode.dto.RepaymentDTO;
import com.bytecode.dto.UserType;
import com.bytecode.entities.Appuser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmed
 */
@ManagedBean
@RequestScoped
public class PLMBean {

    
    Date startdate=null,enddate=null;
    String username,email,phone,name,passwd;
    UserType role;
    String adminUsername;
    Appuser adminUser;
    List<String> branchnames;
    ArrayList<RepaymentDTO> rep;
    ArrayList<RepaymentDTO> filteredrep;
    
    ArrayList<ExpenseDTO> exp;
    ArrayList<ExpenseDTO> filteredexp;
    ProfitAndLoss pl;
    ArrayList<String> pep;
    ConManager appManager = new ConManager(new AdminRepositoryImpl());
    
    
    /**
     * Creates a new instance of UserMBean
     */
    public PLMBean() {
    }

    @PostConstruct
    public void init() {        
        
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
        HttpSession httpSession = request.getSession(false);          
        adminUsername=(String) httpSession.getAttribute("username");  
        adminUser=appManager.getUser(adminUsername);
        Date sD = new Date();
        sD.setMonth(new Date().getMonth()-1) ;   
        pl = (ProfitAndLoss) appManager.searchPL(sD,new Date());  
        if(pl!=null){
            exp=pl.getExpenses();
            rep=pl.getRepayments();
        }
       pep=new ArrayList<String>();
       pep.add("hmmm");
    }
    
    

   

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }      
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }      

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
    
    
    
     public void search(){
        System.out.println("Startdate:"+startdate);
        System.out.println("Enddate:"+enddate);
        pl = (ProfitAndLoss) appManager.searchPL(startdate,enddate);  
        if(pl!=null){
            exp=pl.getExpenses();
            rep=pl.getRepayments();
        }
        System.out.println("Pl Interest:"+pl.getTotalInterest());
    }

    public ProfitAndLoss getPl() {
        return pl;
    }

    public void setPl(ProfitAndLoss pl) {
        this.pl = pl;
    }

    public ArrayList<RepaymentDTO> getRep() {
        return rep;
    }

    public void setRep(ArrayList<RepaymentDTO> rep) {
        this.rep = rep;
    }

    public ArrayList<RepaymentDTO> getFilteredrep() {
        return filteredrep;
    }

    public void setFilteredrep(ArrayList<RepaymentDTO> filteredrep) {
        this.filteredrep = filteredrep;
    }

    public ArrayList<ExpenseDTO> getExp() {
        return exp;
    }

    public void setExp(ArrayList<ExpenseDTO> exp) {
        this.exp = exp;
    }

    public ArrayList<ExpenseDTO> getFilteredexp() {
        return filteredexp;
    }

    public void setFilteredexp(ArrayList<ExpenseDTO> filteredexp) {
        this.filteredexp = filteredexp;
    }

    public ArrayList<String> getPep() {
        return pep;
    }

    public void setPep(ArrayList<String> pep) {
        this.pep = pep;
    }
    

  
    
    

}