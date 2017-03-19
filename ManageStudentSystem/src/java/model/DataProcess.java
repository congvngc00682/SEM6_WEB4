/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author power
 */
public class DataProcess {

    public static Connection getConnection() {
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
                account.setFaculty(rs.getInt("faculty"));
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
        }
        return account;
    }

    // xem lại hàm check tên giống nhau khi add new account
    public boolean checkNameAccount(String username) {

        String sql = "Select * from Account where username=?";
        Account account = new Account();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
        }
        System.out.println("ko the add");
        return false;
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

    public boolean updatePassword(String password, String email){
        String sql = "Update Account set [password]=? where email=?";
        try{
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, email);
            ps.executeUpdate();
        }catch(Exception ex){
            
        }
        return false;
    }

    
    
    
     

}
