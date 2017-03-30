/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ExtenuatingCircumstanceDAO;

/**
 *
 * @author CuongDH
 */
public class JobScheduler extends TimerTask {

    public JobScheduler() {
    }

    @Override
    public void run() {
        try {
            System.out.println("disabling EC");
            new ExtenuatingCircumstanceDAO().disableEC();
        } catch (SQLException ex) {
            Logger.getLogger(JobScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
