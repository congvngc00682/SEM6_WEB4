/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author CuongDH
 */
public class EcProcessServletListener implements ServletContextListener {

    private ServletContext context = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.context = sce.getServletContext();
        EcProcessTimerTask taskShe = new EcProcessTimerTask();
        taskShe.runTask();
        System.out.println("Application is deploying");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application is undeploying");
        this.context = null;
    }
}
