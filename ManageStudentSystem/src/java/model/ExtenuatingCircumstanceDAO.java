/**
 * 
 */
package model;

import entities.Account;
import entities.AssignedCoordinator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.ExtenuatingCircumstance;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 * @author CuongDH
 *
 */
public class ExtenuatingCircumstanceDAO {
        
        public ArrayList<ExtenuatingCircumstance> retrieveECsByStudentId(int studentId) throws SQLException {
		String sqlQuery = "select * from ExtenuatingCircumstance ec,\n" +
                                "	(select ec.id, count(e.EC_id) as totalEvidence \n" +
                                "		from ExtenuatingCircumstance ec left join Evidence e \n" +
                                "			on ec.id = e.EC_id group by ec.id) countEvidence,\n" +
                                "	Account coordinator, [Profile] cp, AssignedCoordinator ac\n" +
                                "   where ec.id = countEvidence.id\n" +
                                "	and ac.coordinator = coordinator.id\n" +
                                "	and ac.ec_id = ec.id\n" +
                                "	and ac.coordinator = cp.accountId and submittedBy = ? ";
                System.out.println("SQL: " + sqlQuery);
                
		Connection connection = new DataProcess().getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, studentId);
		statement.executeQuery();

		ResultSet rs = statement.getResultSet();
                ExtenuatingCircumstance ec = null;
                ArrayList<ExtenuatingCircumstance> ecs = new ArrayList<>();
		while (rs.next()) {
                    ec = new ExtenuatingCircumstance();
                    ec.setId(rs.getInt("id"));
                    ec.setTitle(rs.getString("title"));
                    ec.setDescription(rs.getString("description"));
                    ec.setSubmitted_date(rs.getString("submitted_date"));;
                    ec.setProcess_status(rs.getString("process_status"));
                    ec.setProcessedDate(rs.getString("processed_date"));
                    ec.setAccount(rs.getInt("submittedBy"));
                    ec.setAssignedCoordinatorId(rs.getInt("coordinator"));
                    ec.setCoordinatorName(rs.getString("firstname") + " " + rs.getString("lastname"));
                    
                    ecs.add(ec);
		}
		return ecs;
	}
        
        public ArrayList<ExtenuatingCircumstance> retrieveECsByCoordinatorId(int coordinatorId) throws SQLException {
		String sqlQuery = "select ec.*,ac.processed_date, ac.coordinator, countEvidence.totalEvidence, p.firstname, p.lastname, p.middlename\n" +
                                " from ExtenuatingCircumstance ec,\n" +
                                "	(select ec.id, count(e.EC_id) as totalEvidence \n" +
                                "		from ExtenuatingCircumstance ec left join Evidence e \n" +
                                "			on ec.id = e.EC_id group by ec.id) countEvidence,\n" +
                                "	 Account coordinator, Account student, [Profile] p, AssignedCoordinator ac\n" +
                                " where ec.id = countEvidence.id and \n" +
                                " ac.coordinator = coordinator.id and\n" +
                                " ac.ec_id = ec.id and\n" +
                                " student.id = ec.submittedBy and \n" +
                                " student.id = p.accountId and\n" +
                                " ac.coordinator = ?;";
                System.out.println("SQL: " + sqlQuery);
		Connection connection = new DataProcess().getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, coordinatorId);
		statement.executeQuery();

		ResultSet rs = statement.getResultSet();
                ExtenuatingCircumstance ec = null;
                ArrayList<ExtenuatingCircumstance> ecs = new ArrayList<>();
		while (rs.next()) {
                    ec = new ExtenuatingCircumstance();
                    ec.setId(rs.getInt("id"));
                    ec.setTitle(rs.getString("title"));
                    ec.setDescription(rs.getString("description"));
                    ec.setSubmitted_date(rs.getString("submitted_date"));;
                    ec.setProcess_status(rs.getString("process_status"));
                    ec.setProcessedDate(rs.getString("processed_date"));
                    ec.setAccount(rs.getInt("submittedBy"));
                    ec.setAssignedCoordinatorId(rs.getInt("coordinator"));
                    ec.setStudentName(rs.getString("firstname") + " " + rs.getString("lastname"));
                    
                    ecs.add(ec);
		}
		return ecs;
	}
        
        public ArrayList<ExtenuatingCircumstance> retrieveECsByFacultyId(int faculty) throws SQLException {
		String sqlQuery = "select ec.*, countEvidence.totalEvidence,ac.processed_date, \n" +
                                    "(p.firstname + ' ' + isnull(p.middlename, '') + ' ' + p.lastname) as studentName,\n" +
                                    "(cp.firstname + ' ' + isnull(cp.middlename, '') + ' ' + cp.lastname) as coordinatorName\n" +
                                    " from ExtenuatingCircumstance ec,\n" +
                                    "	(select ec.id, count(e.EC_id) as totalEvidence \n" +
                                    "		from ExtenuatingCircumstance ec left join Evidence e \n" +
                                    "			on ec.id = e.EC_id group by ec.id) countEvidence,\n" +
                                    "	 Account coordinator, Account student, [Profile] p, [Profile] cp, Faculty f,AssignedCoordinator ac \n" +
                                    "where ec.id = countEvidence.id \n" +
                                    " and ac.coordinator = coordinator.id \n" +
                                    " and ac.ec_id = ec.id\n" +
                                    " and student.id = ec.submittedBy \n" +
                                    " and student.id = p.accountId \n" +
                                    " and ac.coordinator = cp.accountId \n" +
                                    " and student.faculty = f.id" +
                                    " and f.id = ?";
                System.out.println("SQL: " + sqlQuery);
		Connection connection = new DataProcess().getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, faculty);
		statement.executeQuery();

		ResultSet rs = statement.getResultSet();
                ExtenuatingCircumstance ec = null;
                ArrayList<ExtenuatingCircumstance> ecs = new ArrayList<>();
		while (rs.next()) {
                    ec = new ExtenuatingCircumstance();
                    ec.setId(rs.getInt("id"));
                    ec.setTitle(rs.getString("title"));
                    ec.setDescription(rs.getString("description"));
                    ec.setSubmitted_date(rs.getString("submitted_date"));;
                    ec.setProcess_status(rs.getString("process_status"));
                    ec.setProcessedDate(rs.getString("processed_date"));
                    ec.setCoordinatorName(rs.getString("coordinatorName"));
                    ec.setStudentName(rs.getString("studentName"));
                    
                    ecs.add(ec);
		}
		return ecs;
	}
        
        public ArrayList<ExtenuatingCircumstance> retrieveECsByCriteria(int faculty, String title, 
                String submitedBy, String assignedTo, String submittedDate, String status ) throws SQLException {
		String sqlQuery = "select ec.*, countEvidence.totalEvidence, ac.processed_date, \n" +
                                " (p.firstname + ' ' + isnull(p.middlename, '') + ' ' + p.lastname) as studentName,\n" +
                                " (cp.firstname + ' ' + isnull(cp.middlename, '') + ' ' + cp.lastname) as coordinatorName\n" +
                                " from ExtenuatingCircumstance ec,\n" +
                                "	(select ec.id, count(e.EC_id) as totalEvidence \n" +
                                "		from ExtenuatingCircumstance ec left join Evidence e \n" +
                                "			on ec.id = e.EC_id group by ec.id) countEvidence,\n" +
                                "	 Account coordinator, Account student, [Profile] p, [Profile] cp, Faculty f, AssignedCoordinator ac\n" +
                                " where ec.id = countEvidence.id \n" +
                                " and ac.coordinator = coordinator.id \n" +
                                " and ac.ec_id = ec.id\n" +
                                " and student.id = ec.submittedBy \n" +
                                " and student.id = p.accountId \n" +
                                " and ac.coordinator = cp.accountId \n" +
                                " and student.faculty = f.id";
                if(faculty != 0) {
                    sqlQuery += " and f.id = "+ faculty;
                }
                if(StringUtils.isNotEmpty(title)){
                    sqlQuery += " and ec.title like '%" +title+ "%'\n";
                }
                
                if(StringUtils.isNotEmpty(submitedBy)){
                    sqlQuery += " and p.firstname like '%"+submitedBy+"%' \n";
                }
                if(StringUtils.isNotEmpty(assignedTo)){
                    sqlQuery +=  " and cp.firstname like '%"+assignedTo+"%' \n";
                }
                if(StringUtils.isNotEmpty(submittedDate)){
                    sqlQuery += " and ec.submitted_date ='" + submittedDate + "'";
                }
                if(StringUtils.isNotEmpty(status)){
                    sqlQuery +=  " and ec.process_status ='" + status + "'";
                }
                System.out.println("SQL: " + sqlQuery);                    

		Connection connection = new DataProcess().getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
		statement.executeQuery();

		ResultSet rs = statement.getResultSet();
                ExtenuatingCircumstance ec = null;
                ArrayList<ExtenuatingCircumstance> ecs = new ArrayList<>();
		while (rs.next()) {
                    ec = new ExtenuatingCircumstance();
                    ec.setId(rs.getInt("id"));
                    ec.setTitle(rs.getString("title"));
                    ec.setDescription(rs.getString("description"));
                    ec.setSubmitted_date(rs.getString("submitted_date"));;
                    ec.setProcess_status(rs.getString("process_status"));
                    ec.setProcessedDate(StringUtils.isEmpty(rs.getString("processed_date"))?"":rs.getString("processed_date"));
                    ec.setCoordinatorName(rs.getString("coordinatorName"));
                    ec.setStudentName(rs.getString("studentName"));
                    
                    ecs.add(ec);
		}
		return ecs;
	}
        
        public ExtenuatingCircumstance retrieveECById(int id) throws SQLException {
		String sqlQuery = "select * from ExtenuatingCircumstance ec,\n" +
                                "	Account coordinator, [Profile] cp, AssignedCoordinator ac\n" +
                                "       where  ac.coordinator = coordinator.id\n" +
                                "	and ac.coordinator = cp.accountId\n" +
                                "	and ac.ec_id = ec.id and  ec.id = ?;";
                System.out.println("SQL: " + sqlQuery);
                
		Connection connection = new DataProcess().getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, id);
		statement.executeQuery();

		ResultSet rs = statement.getResultSet();
                ExtenuatingCircumstance ec = null;
		while (rs.next()) {
                    ec = new ExtenuatingCircumstance();
                    ec.setId(rs.getInt("id"));
                    ec.setTitle(rs.getString("title"));
                    ec.setDescription(rs.getString("description"));
                    ec.setSubmitted_date(rs.getString("submitted_date"));
                    ec.setProcess_status(rs.getString("process_status"));
                    ec.setProcessedDate(rs.getString("processed_date"));
                    ec.setAccount(rs.getInt("submittedBy"));
                    ec.setAssignedCoordinatorId(rs.getInt("coordinator"));
                    ec.setCoordinatorName(rs.getString("firstname") + " " + rs.getString("lastname"));
                    
		}
		return ec;
	}

        public static ExtenuatingCircumstance insertEC(ExtenuatingCircumstance ec) throws SQLException {
		String sqlQuery = "insert into ExtenuatingCircumstance values(?,?,?,?,?)";

		Connection connection = new DataProcess().getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, ec.getTitle());
		statement.setString(2, ec.getDescription());
		statement.setString(3, ec.getSubmitted_date());
		statement.setString(4, ec.getProcess_status());
		statement.setInt(5, ec.getAccount());

		int affectedRows = statement.executeUpdate();

		if (affectedRows == 0) {
			throw new SQLException("Creating user failed, no rows affected.");
		}

		ResultSet generatedKeys = statement.getGeneratedKeys();
		if (generatedKeys.next()) {
			ec.setId(generatedKeys.getInt(1));
			System.out.println("id: " + ec.getId());
		} else {
			throw new SQLException("Creating EC failed, no ID obtained.");
		}
		return ec;
	}
        
         public static AssignedCoordinator insertAssignedCoordinator(AssignedCoordinator coordinator) throws SQLException {
		String sqlQuery = "insert into AssignedCoordinator(ec_Id, coordinator, processed_date) values(?,?,?)";

		Connection connection = new DataProcess().getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, coordinator.getEcId());
		statement.setInt(2, coordinator.getCoordinatorId());
		statement.setString(3, coordinator.getProcessedDate());

		int affectedRows = statement.executeUpdate();

		if (affectedRows == 0) {
			throw new SQLException("Creating user failed, no rows affected.");
		}

		ResultSet generatedKeys = statement.getGeneratedKeys();
		if (generatedKeys.next()) {
			coordinator.setId(generatedKeys.getInt(1));
			System.out.println("id: " + coordinator.getId());
		} else {
			throw new SQLException("Inserted coordinator failed, no ID obtained.");
		}
		return coordinator;
	}
        
	public static void main(String[] args) {
            try {
                ExtenuatingCircumstance ec = new ExtenuatingCircumstance();
                ec.setTitle(("title"));
                ec.setDescription("description");
                ec.setProcess_status("submitted");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now)); //2016/11/16 12:08:43
                ec.setSubmitted_date(now.toString());
                ec.setAccount(41);
                
                new ExtenuatingCircumstanceDAO().insertEC(ec);
               
            } catch (Exception ex) {
                Logger.getLogger(ExtenuatingCircumstanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
}
