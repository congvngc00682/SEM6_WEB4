/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author power
 */
public class Faculty {
    private int id;
    private String facultyName;

    public Faculty(String facultyName) {
        this.facultyName = facultyName;
    }

    public Faculty() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFaculty_name(String faculty_name) {
        this.facultyName = faculty_name;
    }
    
    
}
