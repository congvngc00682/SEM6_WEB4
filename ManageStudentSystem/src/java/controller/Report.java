/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import entities.ExtenuatingCircumstance;
import entities.ReportData;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ExtenuatingCircumstanceDAO;

/**
 *
 * @author f87
 */
@WebServlet(name = "Report", urlPatterns = {"/Report"})
public class Report extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<ReportData> reportDatas = new ArrayList<>();
        int report = Integer.parseInt(request.getParameter("report"));
        System.out.println("report: " + report);
        
        
        if(report == 1){
            int faculty = Integer.parseInt(request.getParameter("faculty"));
            reportDatas = new ExtenuatingCircumstanceDAO().reportEcByFaculty(faculty);
        } else if(report == 2) {
            int year = Integer.parseInt(request.getParameter("year"));
            reportDatas = new ExtenuatingCircumstanceDAO().reportEcByYear(year);
        } else if(report == 3){
            int faculty = Integer.parseInt(request.getParameter("faculty"));
            int year = Integer.parseInt(request.getParameter("year"));
            reportDatas = new ExtenuatingCircumstanceDAO().reportEcByFacultyAndYear(faculty, year);
        }

        Gson gson = new Gson();
        JsonElement jElement = gson.toJsonTree(reportDatas, new TypeToken<ArrayList<ExtenuatingCircumstance>>() {
        }.getType());
        JsonArray jArray = jElement.getAsJsonArray();
        System.out.println("json" + jArray.toString());
        response.setContentType("application/json");
        response.getWriter().print(jArray);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
