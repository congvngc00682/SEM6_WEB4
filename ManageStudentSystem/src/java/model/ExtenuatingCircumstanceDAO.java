/**
 *
 */
package model;

import entities.Account;
import entities.AssginedCoordinator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.ExtenuatingCircumstance;
import entities.ReportData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 * @author CuongDH
 *
 */
public class ExtenuatingCircumstanceDAO {

    public ArrayList<ExtenuatingCircumstance> retrieveECsByStudentId(int studentId) throws SQLException {
        String sqlQuery = "select * from ExtenuatingCircumstance ec,\n"
                + "	(select ec.id, count(e.EC_id) as totalEvidence \n"
                + "		from ExtenuatingCircumstance ec left join Evidence e \n"
                + "			on ec.id = e.EC_id group by ec.id) countEvidence,\n"
                + "	Account coordinator, [Profile] cp, AssignedCoordinator ac\n"
                + "   where ec.id = countEvidence.id\n"
                + "	and ac.coordinator = coordinator.id\n"
                + "	and ac.ec_id = ec.id\n"
                + "	and ac.coordinator = cp.accountId and submittedBy = ? order by ec.submitted_date desc";
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
            ec.setIsActive(rs.getBoolean("isActive"));

            ecs.add(ec);
        }
        return ecs;
    }

    public ArrayList<ExtenuatingCircumstance> retrieveECsByCoordinatorId(int coordinatorId) throws SQLException {
        String sqlQuery = "select ec.*,ec.processed_date, ac.coordinator, countEvidence.totalEvidence, p.firstname, p.lastname, p.middlename\n"
                + " from ExtenuatingCircumstance ec,\n"
                + "	(select ec.id, count(e.EC_id) as totalEvidence \n"
                + "		from ExtenuatingCircumstance ec left join Evidence e \n"
                + "			on ec.id = e.EC_id group by ec.id) countEvidence,\n"
                + "	 Account coordinator, Account student, [Profile] p, AssignedCoordinator ac\n"
                + " where ec.id = countEvidence.id and \n"
                + " ac.coordinator = coordinator.id and\n"
                + " ac.ec_id = ec.id and\n"
                + " student.id = ec.submittedBy and \n"
                + " student.id = p.accountId and\n"
                + " ac.coordinator = ? order by ec.submitted_date desc;";
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
            ec.setIsActive(rs.getBoolean("isActive"));

            ecs.add(ec);
        }
        return ecs;
    }

    public ArrayList<ExtenuatingCircumstance> retrieveECsByFacultyId(int faculty) throws SQLException {
        String sqlQuery = "select ec.*, countEvidence.totalEvidence,ec.processed_date, \n"
                + "(p.firstname + ' ' + isnull(p.middlename, '') + ' ' + p.lastname) as studentName,\n"
                + "(cp.firstname + ' ' + isnull(cp.middlename, '') + ' ' + cp.lastname) as coordinatorName\n"
                + " from ExtenuatingCircumstance ec,\n"
                + "	(select ec.id, count(e.EC_id) as totalEvidence \n"
                + "		from ExtenuatingCircumstance ec left join Evidence e \n"
                + "			on ec.id = e.EC_id group by ec.id) countEvidence,\n"
                + "	 Account coordinator, Account student, [Profile] p, [Profile] cp, Faculty f,AssignedCoordinator ac \n"
                + "where ec.id = countEvidence.id \n"
                + " and ac.coordinator = coordinator.id \n"
                + " and ac.ec_id = ec.id\n"
                + " and student.id = ec.submittedBy \n"
                + " and student.id = p.accountId \n"
                + " and ac.coordinator = cp.accountId \n"
                + " and student.faculty = f.id"
                + " and f.id = ? order by ec.submitted_date desc";
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
            ec.setIsActive(rs.getBoolean("isActive"));

            ecs.add(ec);
        }
        return ecs;
    }

    public ArrayList<ExtenuatingCircumstance> retrieveECsByCriteria(int faculty, String title,
            String submitedBy, String assignedTo, String submittedDate, String status) throws SQLException {
        String sqlQuery = "select ec.*, countEvidence.totalEvidence, ec.processed_date, \n"
                + " (p.firstname + ' ' + isnull(p.middlename, '') + ' ' + p.lastname) as studentName,\n"
                + " (cp.firstname + ' ' + isnull(cp.middlename, '') + ' ' + cp.lastname) as coordinatorName\n"
                + " from ExtenuatingCircumstance ec,\n"
                + "	(select ec.id, count(e.EC_id) as totalEvidence \n"
                + "		from ExtenuatingCircumstance ec left join Evidence e \n"
                + "			on ec.id = e.EC_id group by ec.id) countEvidence,\n"
                + "	 Account coordinator, Account student, [Profile] p, [Profile] cp, Faculty f, AssignedCoordinator ac\n"
                + " where ec.id = countEvidence.id \n"
                + " and ac.coordinator = coordinator.id \n"
                + " and ac.ec_id = ec.id\n"
                + " and student.id = ec.submittedBy \n"
                + " and student.id = p.accountId \n"
                + " and ac.coordinator = cp.accountId \n"
                + " and student.faculty = f.id ";
        if (faculty != 0) {
            sqlQuery += " and f.id = " + faculty;
        }
        if (StringUtils.isNotEmpty(title)) {
            sqlQuery += " and ec.title like '%" + title + "%'\n";
        }

        if (StringUtils.isNotEmpty(submitedBy)) {
            sqlQuery += " and p.firstname like '%" + submitedBy + "%' \n";
        }
        if (StringUtils.isNotEmpty(assignedTo)) {
            sqlQuery += " and cp.firstname like '%" + assignedTo + "%' \n";
        }
        if (StringUtils.isNotEmpty(submittedDate)) {
            sqlQuery += " and ec.submitted_date ='" + submittedDate + "'";
        }
        if (StringUtils.isNotEmpty(status)) {
            sqlQuery += " and ec.process_status ='" + status + "'";
        }
        sqlQuery += " order by ec.submitted_date desc";
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
            if (StringUtils.isNotEmpty(rs.getString("submitted_date"))) {
                ec.setSubmitted_date(rs.getString("submitted_date").substring(0, rs.getString("submitted_date").indexOf(" ")));;
            }
            ec.setProcess_status(rs.getString("process_status"));
            if (StringUtils.isNotEmpty(rs.getString("processed_date"))) {
                ec.setProcessedDate(rs.getString("processed_date").substring(0, rs.getString("processed_date").indexOf(" ")));;
            }
            ec.setCoordinatorName(rs.getString("coordinatorName"));
            ec.setStudentName(rs.getString("studentName"));
            ec.setIsActive(rs.getBoolean("isActive"));

            ecs.add(ec);
        }
        return ecs;
    }

    public ExtenuatingCircumstance retrieveECById(int id) throws SQLException {
        String sqlQuery = "select * from ExtenuatingCircumstance ec,\n"
                + "	Account coordinator, [Profile] cp, AssignedCoordinator ac\n"
                + "       where  ac.coordinator = coordinator.id\n"
                + "	and ac.coordinator = cp.accountId\n"
                + "	and ac.ec_id = ec.id and  ec.id = ?;";
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
            ec.setIsActive(rs.getBoolean("isActive"));

        }
        return ec;
    }

    public ExtenuatingCircumstance insertEC(ExtenuatingCircumstance ec) throws SQLException {
        String sqlQuery = "insert into ExtenuatingCircumstance values(?,?,?,?,?,?,?,?)";

        Connection connection = new DataProcess().getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, ec.getTitle());
        statement.setString(2, ec.getDescription());
        statement.setString(3, ec.getSubmitted_date());
        statement.setString(4, ec.getProcess_status());
        statement.setString(5, null);
        statement.setBoolean(6, true);
        statement.setInt(7, ec.getAcademicYear());
        statement.setInt(8, ec.getAccount());

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

    public AssginedCoordinator insertAssignedCoordinator(AssginedCoordinator coordinator) throws SQLException {
        String sqlQuery = "insert into AssignedCoordinator(ec_Id, coordinator) values(?,?)";

        Connection connection = new DataProcess().getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, coordinator.getEcId());
        statement.setInt(2, coordinator.getCoordinatorId());

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

    public void updateEC(ExtenuatingCircumstance ec, String updatedBy) throws SQLException {
        String sqlQuery = "update ExtenuatingCircumstance set title='" + ec.getTitle()
                + "', description='" + ec.getDescription() + "' where id=" + ec.getId();

        if (StringUtils.equals("coordinator", updatedBy) || StringUtils.equals("admin", updatedBy)) {
            sqlQuery = "update ExtenuatingCircumstance set process_status='" + ec.getProcess_status()
                    + "', processed_date='" + ec.getProcessedDate() + "' where id=" + ec.getId();
        }
        System.out.println("Sql: " + sqlQuery);

        Connection connection = new DataProcess().getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

        statement.executeUpdate();

//		if (affectedRows == 0) {
//			throw new SQLException("Update EC failed, no rows affected.");
//		}
//		ResultSet generatedKeys = statement.getGeneratedKeys();
//		if (generatedKeys.next()) {
//			ec.setId(generatedKeys.getInt(1));
//			System.out.println("id: " + ec.getId());
//		} else {
//			throw new SQLException("Update EC failed, no ID obtained.");
//		}
//		return ec;
    }

    public void disableEC() throws SQLException {
        String sqlQuery = "update ExtenuatingCircumstance\n"
                + "set isActive = 0\n"
                + "where isActive = 1 \n"
                + "and  DATEDIFF(day,getdate(),submitted_date) <= -14";

        System.out.println("Sql: " + sqlQuery);

        Connection connection = new DataProcess().getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            System.out.println("no rows affected.");
        } else {
            System.out.println(affectedRows + " rows affected.");
        }
    }

    public ArrayList<ReportData> reportEcByFaculty(int faculty) throws SQLException {
        String sqlQuery = "select count(ec.id) as total, ay.year_name as label \n"
                + "from ExtenuatingCircumstance ec, AcademicYear ay, account a\n"
                + "where ec.AcademicYear = ay.id and ec.submittedBy = a.id \n"
                + "and a.faculty = ? group by ay.year_name;";

        Connection connection = new DataProcess().getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, faculty);
        statement.executeQuery();

        ResultSet rs = statement.getResultSet();
        ArrayList<ReportData> reportDatas = new ArrayList<>();
        ReportData data = null;
        while (rs.next()) {
            data = new ReportData();
            data.setLabel(rs.getString("label"));
            data.setData(rs.getInt("total"));

            reportDatas.add(data);
        }
        return reportDatas;
    }

    public ArrayList<ReportData> reportEcByYear(int year) throws SQLException {
        String sqlQuery = "select count(ec.id) as total, f.name as label \n"
                + "from ExtenuatingCircumstance ec, account a, faculty f\n"
                + "where  ec.submittedBy = a.id and a.faculty = f.id \n"
                + "and ec.AcademicYear = ? group by f.name;";

        Connection connection = new DataProcess().getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, year);
        statement.executeQuery();

        ResultSet rs = statement.getResultSet();
        ArrayList<ReportData> reportDatas = new ArrayList<>();
        ReportData data = null;
        while (rs.next()) {
            data = new ReportData();
            data.setLabel(rs.getString("label"));
            data.setData(rs.getInt("total"));

            reportDatas.add(data);
        }
        return reportDatas;
    }

    public ArrayList<ReportData> reportEcByFacultyAndYear(int faculty, int year) throws SQLException {
        String sqlQuery = "select  count(distinct(ec.submittedBy)) as total, \n"
                + "'Number of students making a claim' as 'label' \n"
                + "from ExtenuatingCircumstance ec, account a, AcademicYear ay \n"
                + "where ec.AcademicYear = ay.id and ec.submittedBy = a.id \n"
                + "and a.faculty = ? and ec.AcademicYear = ?\n"
                + "union\n"
                + "select count(a.id) as total, 'Number of students not making a claim' as 'label' \n"
                + "from account a where a.role = 4 and a.faculty = ? and\n"
                + "a.id not in (select submittedBy from  ExtenuatingCircumstance where AcademicYear = ?) ;";

        Connection connection = new DataProcess().getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, faculty);
        statement.setInt(2, year);
        statement.setInt(3, faculty);
        statement.setInt(4, year);
        statement.executeQuery();

        ResultSet rs = statement.getResultSet();
        ArrayList<ReportData> reportDatas = new ArrayList<>();
        ReportData data = null;
        while (rs.next()) {
            data = new ReportData();
            data.setLabel(rs.getString("label"));
            data.setData(rs.getInt("total"));

            reportDatas.add(data);
        }
        return reportDatas;
    }

    public void main(String[] args) {
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
