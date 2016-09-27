/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bytecode.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ahmed
 */
public class RepositoryManager
{
    private static EntityManagerFactory factory;    
    static 
    {
        factory = Persistence.createEntityManagerFactory("BankingCorePU");
    }
    
    public static EntityManager getManager()
    {
        return factory.createEntityManager();
    }
}
