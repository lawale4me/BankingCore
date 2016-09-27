package com.bytecode.Sender;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Ahmed
 */
@WebListener()
public class EmailServletListener implements ServletContextListener {

    private ScheduledExecutorService executorService;
    private EmailSender sender;
    
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sender = new EmailSender();
        executorService = Executors.newSingleThreadScheduledExecutor();
       // executorService.scheduleWithFixedDelay(sender, 0, 20, TimeUnit.SECONDS);
        sce.getServletContext().setAttribute("EmailSender", sender);
        executorService.scheduleAtFixedRate(sender, 1, 1, java.util.concurrent.TimeUnit.HOURS);
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        executorService.shutdown();
    }
}
