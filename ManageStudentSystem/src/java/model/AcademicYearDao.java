/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.AcademicYear;
import entities.Faculty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author f87
 */
public class AcademicYearDao {
    public ArrayList<AcademicYear> retrieveAcademicYears() throws SQLException {
        String sqlQuery = "select * from AcademicYear";

        Connection connection;
        connection = DataProcess.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

        statement.executeQuery();

        ResultSet rs = statement.getResultSet();
        ArrayList<AcademicYear> years = new ArrayList<>();
        AcademicYear year;
        while (rs.next()) {
            year = new AcademicYear();
            year.setId(rs.getInt(1));
            year.setYearName(rs.getString(2));

            years.add(year);
        }

        return years;
    }
}
