/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.mbeans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;


/**
 *
 * @author Ahmed
 */
@ManagedBean
@SessionScoped
public class MenuMBean {

    private MenuModel simpleMenuModel = new DefaultMenuModel();
    DefaultSubMenu submenu = new DefaultSubMenu("kkk");
    
    /**
     * Creates a new instance of MenuMBean
     */
    public MenuMBean() 
    {      
        
        simpleMenuModel.addElement(submenu);
        DefaultMenuItem item=new DefaultMenuItem("Reports");        
        item.setValue("Repayments");        
        item.setUrl("/gerenralreports");
        submenu.addElement(item);
    }
    
    public MenuModel getSimpleMenuModel() {
        return simpleMenuModel;
    }
    
    
}
