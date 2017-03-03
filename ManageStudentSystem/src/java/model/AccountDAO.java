/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Account;

/**
 * @author f87
 *
 */
public class AccountDAO {

	public static Account insertAccount(Account account) throws SQLException {
		String SQL_INSERT = "insert into Account(username,password,email,role,faculty) values(?,?,?,?,?)";

		Connection connection = new DataProcess().getConnection();
		PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, account.getUsername());
		statement.setString(2, account.getPassword());
		statement.setString(3, account.getEmail());
		statement.setInt(4, account.getRole());
		statement.setInt(5, account.getFaculty());

		int affectedRows = statement.executeUpdate();

		if (affectedRows == 0) {
			throw new SQLException("Creating user failed, no rows affected.");
		}

		ResultSet generatedKeys = statement.getGeneratedKeys();
		if (generatedKeys.next()) {
			account.setId(generatedKeys.getInt(1));
			System.out.println("id: " + account.getId());
		} else {
			throw new SQLException("Creating user failed, no ID obtained.");
		}
		return account;
	}

	public static Account getSimilarUsername(String username) throws SQLException {
		String SQL_INSERT = "select top(1)* from Account where username like '" + username
				+ "[0-9]%' order by id desc";

		Connection connection = new DataProcess().getConnection();
		PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
		statement.executeQuery();

		ResultSet rs = statement.getResultSet();
		Account similarAccount = new Account();
		if (rs.next()) {
			similarAccount.setId(rs.getInt(1));
			similarAccount.setUsername(rs.getString(2));
		}
		return similarAccount;
	}
        
        public String getUsernameByEmail(String email) throws SQLException {
		String SQL_INSERT = "select top(1)* from Account where email =?";

		Connection connection = new DataProcess().getConnection();
		PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, email);
		statement.executeQuery();

		ResultSet rs = statement.getResultSet();
                String username = "";
		if (rs.next()) {
                    username = 	rs.getString(2);
		}
		return username;
	}

	public static void main(String[] args) {
		Account acc = new Account();
		acc.setUsername("cuongdh");
		acc.setPassword("sdfsdf");
		acc.setEmail("mail");
		acc.setRole(4);
		acc.setFaculty(1);

		/*
		 * try { // reate(acc); //Account acc2 =
		 * getAccountByUsername(acc.getUsername()); String username =
		 * acc2.getUsername(); if (username.length() >
		 * acc.getUsername().length()) { String number =
		 * username.substring(acc.getUsername().length(), username.length());
		 * System.out.println("current highest number: "+ number); } } catch
		 * (SQLException e) { e.printStackTrace(); }
		 */
	}
}
