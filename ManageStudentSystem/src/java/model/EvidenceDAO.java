/**
 * 
 */
package model;

import entities.Evidence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.ExtenuatingCircumstance;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author CuongDH
 *
 */
public class EvidenceDAO {
        
        
        public ArrayList<Evidence> retrieveEvidenceByEcId(int ecId) throws SQLException {
		String sqlQuery = "select * from Evidence where ec_id = ?;";

		Connection connection = new DataProcess().getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, ecId);
		statement.executeQuery();

		ResultSet rs = statement.getResultSet();
                Evidence evidence = null;
                ArrayList<Evidence> evidences = new ArrayList<>();
		while (rs.next()) {
                    evidence = new Evidence();
                    evidence.setId(rs.getInt("id"));
                    evidence.setFiles(rs.getString("filepath"));
                    evidence.setEvidence_date("uploaded_date");
                    
                    evidences.add(evidence);
		}
		return evidences;
	}
        
        public static Evidence insertEvidence(Evidence evidence) throws SQLException {
		String sqlQuery = "insert into Evidence values(?,?,?)";

		Connection connection = new DataProcess().getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, evidence.getFiles());
		statement.setString(2, evidence.getEvidence_date());
		statement.setInt(3, evidence.getEcId());

		int affectedRows = statement.executeUpdate();

		if (affectedRows == 0) {
			throw new SQLException("Creating user failed, no rows affected.");
		}

		ResultSet generatedKeys = statement.getGeneratedKeys();
		if (generatedKeys.next()) {
			evidence.setId(generatedKeys.getInt(1));
			System.out.println("id: " + evidence.getId());
		} else {
			throw new SQLException("Insert Evidence failed, no ID obtained.");
		}
		return evidence;
	}
        
	public static void main(String[] args) {
            try {
                ArrayList<Evidence> ecs = new EvidenceDAO().retrieveEvidenceByEcId(1);
                
                for (Evidence ec : ecs) {
                    System.out.println("EC: " + ec.getFiles());
                }
            } catch (SQLException ex) {
                Logger.getLogger(EvidenceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
}
