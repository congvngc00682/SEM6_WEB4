/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Profile;

/**
 * @author CuongDH
 *
 */
public class ProfileDAO {

	public static void insertProfile(Profile account) throws SQLException {
		String sqlQuery = "insert into Profile(firstname,middlename,lastname,dob,accountId) values(?,?,?,?,?)";

		Connection connection = new DataProcess().getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, account.getFirstname());
		statement.setString(2, account.getMiddlename());
		statement.setString(3, account.getLastname());
		statement.setString(4, account.getDob());
		statement.setInt(5, account.getAccountId());

		int affectedRows = statement.executeUpdate();

		if (affectedRows == 0) {
			throw new SQLException("Creating user failed, no rows affected.");
		}

		ResultSet generatedKeys = statement.getGeneratedKeys();
		if (generatedKeys.next()) {
			account.setProfileId(generatedKeys.getInt(1));
			System.out.println("successfully");
		} else {
			throw new SQLException("Creating profile failed, no ID obtained.");
		}
	}

	public static boolean checkExistingProfile(Profile p) throws SQLException {
		String sqlQuery = "select count(1) from [profile] where firstname=? and middlename=? and lastname=? and dob=?;";

		Connection connection = new DataProcess().getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, p.getFirstname());
		statement.setString(2, p.getMiddlename());
		statement.setString(3, p.getLastname());
		statement.setString(4, p.getDob());
		statement.executeQuery();

		ResultSet rs = statement.getResultSet();
		if (rs.next()) {
			if ((rs.getInt(1) > 0))
				return true;
			return false;
		} else {
			throw new SQLException("Creating user failed, no ID obtained.");
		}
	}

	public static boolean checkExistingProfileByName(Profile p) throws SQLException {
		String sqlQuery = "select count(1) from [profile] where firstname=? and middlename=? and lastname=? ;";

		Connection connection = new DataProcess().getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, p.getFirstname());
		statement.setString(2, p.getMiddlename());
		statement.setString(3, p.getLastname());
		statement.executeQuery();

		ResultSet rs = statement.getResultSet();
		if (rs.next()) {
			if ((rs.getInt(1) > 0))
				return true;
			return false;
		} else {
			throw new SQLException("Creating user failed, no ID obtained.");
		}
	}

}
