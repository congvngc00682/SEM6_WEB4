/**
 *
 */
package model;

import entities.Faculty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author CuongDH
 *
 */
public class FacultyDAO {

    public ArrayList<Faculty> retrieveFaculties() throws SQLException {
        String sqlQuery = "select * from Faculty";

        Connection connection;
        connection = DataProcess.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

        statement.executeQuery();

        ResultSet rs = statement.getResultSet();
        ArrayList<Faculty> faculties = new ArrayList<>();
        Faculty f;
        while (rs.next()) {
            f = new Faculty();
            f.setId(rs.getInt(1));
            f.setFaculty_name(rs.getString(2));

            faculties.add(f);
        }

        return faculties;
    }
}
