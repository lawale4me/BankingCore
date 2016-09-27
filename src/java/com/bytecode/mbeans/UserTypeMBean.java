/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.mbeans;

import com.bytecode.dto.UserType;
import com.bytecode.entities.Appuser;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Ahmed
 */
@ManagedBean
@ApplicationScoped
public class UserTypeMBean {
    
        private List<Appuser> adminUsers =new ArrayList();


    /**
     * Creates a new instance of UserTypeMBean
     */
    public UserTypeMBean() {
    }
    
    public UserType[] getUserTypes() {
        return UserType.values();
    }
    
    public UserType[] getUserOtherTypes() {
        UserType[] u = {UserType.Admin,UserType.RegularUser};
        return  u;
    }

    public List<Appuser> getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(List<Appuser> adminUsers) {
        this.adminUsers = adminUsers;
    }
    
    
    
    
}
