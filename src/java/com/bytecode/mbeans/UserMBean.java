/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.mbeans;


import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.core.UserException;
import com.bytecode.dto.Response;
import com.bytecode.dto.UserType;
import com.bytecode.entities.Appuser;
import com.bytecode.util.Email;
import com.bytecode.util.Log1;
import com.bytecode.util.SendEmail;
import com.bytecode.util.Util;
import static com.bytecode.util.Util.hash;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author Ahmed
 */
@ManagedBean
@ViewScoped
public class UserMBean {

    
    private String username,email,phone,name,passwd;
    UserType role;
    String adminUsername;
    Appuser adminUser;
    List<String> branchnames;
    ConManager appManager = new ConManager(new AdminRepositoryImpl());
    
    String newPassword;
    String newPassword1;
    String oldPassword;
    
    
    /**
     * Creates a new instance of UserMBean
     */
    public UserMBean() {
    }

    @PostConstruct
    public void init() {        
        
        FacesContext context = FacesContext.getCurrentInstance();  
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
        HttpSession httpSession = request.getSession(false);          
        adminUsername=(String) httpSession.getAttribute("username");  
        adminUser=appManager.getUser(adminUsername);        
       // branches = (List<Branch>) appManager.getBranches();
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
    
    
    
    public void create() throws UserException
    {
               
               
        Appuser user =new Appuser();
        user.setEmail(email);
        user.setUsername(username);
        user.setName(name);
        user.setRole(role.ordinal());
        try {
            passwd=Util.hash(passwd);
        } catch (Exception ex) {
            Logger.getLogger(UserMBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new UserException(ex.getMessage());
        }
        user.setPassword(passwd);  
        user.setStatus(1);
        
                
        appManager.addUser(user);
        System.out.println("User "+user.getUsername()+" created");
        
        role=null;
        email="";
        username="";
        name="";
        passwd="";
//        
         Email email1=new Email();
         Email email2=new Email();
         email1.setEmailAddress(adminUser.getEmail());
         email1.setSubject("Your have create a new user :" +user.getUsername());
         email1.setMessage("User :" +user.getUsername()+"\n"+user.getEmail()+"\n"+Log1.APPURL);
         
         email2.setEmailAddress(user.getEmail());
         email2.setSubject("Your Banking Core Admin account has been created:" );
         email2.setMessage("User :" +user.getUsername()+"\n"+user.getEmail()+"\n"+Log1.APPURL);
         
         new SendEmail().sendSimpleMail(email1);
         new SendEmail().sendSimpleMail(email2);
        
         appManager.audit(adminUser.getUsername(), "New User created  "+user.getUsername(),"IPADDRESS");
        FacesMessage message = new FacesMessage("Succesful", user.getUsername()+" user has been Created.");
        FacesContext.getCurrentInstance().addMessage(null, message);     
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
    
    
    
    public void changePassword(String username, String oldpass, String newpassword) throws UserException, Exception
    {
                
        String pwPolicy = Util.employPasswordPolicy(newpassword);
    
         if (!pwPolicy.equals("0")) {
             Log1.l.infoLog.info("change password failed "+username +" policy:"+pwPolicy);
            throw new UserException(pwPolicy);
         }
        //ResponseDetails res =authenticate(username, oldpass);  
        Response res = appManager.login(username, oldpass);
        if(res.getStatus()){
            
            Appuser adminUser = appManager.getUser(username);
            if(adminUser!=null)
            {            
            adminUser.setPassword(hash(newpassword));
       //     adminUser.setPwdExpired(false);
//            adminUser.setPwdDate(DateUtils.addDays(new Date(), 30));
            appManager.updateAppuser(adminUser);
                                    
            }
            else
            {
                Log1.l.infoLog.info("Admin User Not Found whilse changing password "+username);
                throw new UserException("User not found");                                        
            }
        }
//        else if(res.getErrorCode()== ResponseCodes.USER_EXPIRED){           
//            AdminUser adminUser = adminrepo.getUser(username);            
//            if(adminUser!=null){
//            adminUser.setPassword(hash(newpassword));
//            adminUser.setPwdExpired(false);
//            adminUser.setPwdDate(DateUtils.addDays(new Date(), 30));
//            adminrepo.update(adminUser);            
//            }
//            else
//            {
//            Log.l.infoLog.info("User Not Found "+username +" while changing password");
//            throw new ProcessingException("User not found");                                        
//            }
//        }
        else
        {            
            Log1.l.infoLog.info("password doesnt match "+username);
            throw new UserException("Password does not match:"+res.getDescription());
        }        
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    


}