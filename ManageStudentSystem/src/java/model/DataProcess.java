/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Account;
import entities.ExtenuatingCircumstance;
import java.io.File;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author power
 */
public class DataProcess {

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=ManageStudent";
            String user = "sa";
            String pass = "123456";
            conn = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;

    }

    public Account checkLogin(String username, String password) {

        String sql = "Select * from Account where username=? and [password]=?";
        Account account = new Account();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                account.setId(rs.getInt(1));
                account.setUsername(rs.getString(2));
                account.setPassword(rs.getString(3));
                account.setEmail(rs.getString(4));
                account.setRole(rs.getInt(5));
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
        }
        return account;
    }

    // add new account
    public boolean getAccount(String username, String password, String email, int role, int faculty) {
        boolean result = false;
        String sql = "insert into Account values (?,?,?,?,?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setInt(4, role);
            ps.setInt(5, faculty);
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return true;
    }

    public ExtenuatingCircumstance getExtenuatingCircumstance(ExtenuatingCircumstance ec) {
        String sql = "insert into ExtenuatingCircumstance values(?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, ec.getTitle());
            ps.setString(2, ec.getDescription());
            ps.setString(3, ec.getSubmitted_date());
            ps.setString(4, ec.getProcess_status());
            ps.setString(5, ec.getProcessed_date());
            ps.setInt(6, ec.getAccount());
            ps.setInt(7, ec.getAssignedCoordinator());
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                ec.setId(generatedKeys.getInt(1));
                System.out.println("successfully");
            } else {
                throw new SQLException("Creating profile failed, no ID obtained.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ec;
    }

    public List<ExtenuatingCircumstance> viewAllEC() {
        List<ExtenuatingCircumstance> list = new ArrayList<>();
        String sql = "select * from ExtenuatingCircumstance";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExtenuatingCircumstance ec = new ExtenuatingCircumstance();
                ec.setId(rs.getInt(1));
                ec.setTitle(rs.getString(2));
                ec.setDescription(rs.getString(3));
                ec.setSubmitted_date(rs.getString(4));
                ec.setProcess_status(rs.getString(5));
                ec.setProcessed_date(rs.getString(6));
                ec.setAccount(rs.getInt(7));
                ec.setAssignedCoordinator(rs.getInt(8));
                list.add(ec);
            }
        } catch (Exception e) {
        }

        return list;

    }

    public boolean updateExtenuatingCircumstance(ExtenuatingCircumstance ec) {
        String sql = "update ExtenuatingCircumstance set title=?, [description]=?, submitted_date=?"
                + "process_status=?, processed_date=?, account=?, assignedCoordinator=? where id=?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, ec.getTitle());
            ps.setString(2, ec.getDescription());
            ps.setString(3, ec.getSubmitted_date());
            ps.setString(4, ec.getProcess_status());
            ps.setString(5, ec.getProcessed_date());
            ps.setInt(6, ec.getAccount());
            ps.setInt(7, ec.getAssignedCoordinator());
            ps.setInt(8, ec.getId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Update failed, no rows affected.");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                ec.setId(generatedKeys.getInt(1));
                System.out.println("id: " + ec.getId());
            } else {
                throw new SQLException("Update failed, no ID obtained.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updatePassword(String password, String email) {
        String sql = "Update Account set [password]=? where email=?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (Exception ex) {

        }
        return false;
    }

    public boolean getEvidence(String path, String datetime, int id_EC) {
        boolean result = false;
        String sql = "insert into Evidence values(?,?,?)";

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, path);
            ps.setString(2, datetime);
            ps.setInt(3, id_EC);
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return true;
    }

}
