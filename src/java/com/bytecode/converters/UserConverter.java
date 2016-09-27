/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.converters;


import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.dto.UserDTO;
import com.bytecode.entities.Appuser;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Ahmed
 */
@ManagedBean
@FacesConverter("userConverter")
public class UserConverter implements Converter{
 
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {            
		
 		System.out.println("getAsObject:"+value);
		if(value.isEmpty()){
                    return null;
		}		 
                Appuser userr= new ConManager(new AdminRepositoryImpl()).getUser(Integer.parseInt(value));
                //status,userID,username,email,fullname,role,branchid
		UserDTO userdto = new UserDTO(1,userr.getId().intValue(),userr.getUsername(),userr.getEmail(),userr.getName(),"Admin",101);
                if (userdto == null) {
                    throw new ConverterException(new FacesMessage("Unknown User ID: " + value));
                }                
                System.out.println("getAsString:"+userdto);
		return userdto;
        }
        
        
        
        
	@Override
	public String getAsString(FacesContext context, UIComponent component,Object value) 
        {
            System.out.println("getAsString:"+value);
          return value.toString(); 	
	}
        
        

}
