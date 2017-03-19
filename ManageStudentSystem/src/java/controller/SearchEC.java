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
import java.io.IOException;
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
@WebServlet(name = "SearchEC", urlPatterns = {"/SearchEC"})
public class SearchEC extends HttpServlet {

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
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            int faculty = Integer.parseInt(request.getParameter("faculty"));
            String title = request.getParameter("title");
            String submittedBy = request.getParameter("studentName");
            String assignedTo = request.getParameter("coordinatorName");
            String submitDate = request.getParameter("submittedDate");
            String status = request.getParameter("status");
            System.out.println("title: " + title + "-submitted: " + submittedBy + "-assign: " + assignedTo + "-date: " + submitDate + "-status: "+ status);
            
            ArrayList<ExtenuatingCircumstance> ecs = new ExtenuatingCircumstanceDAO().retrieveECsByCriteria(faculty, title, submittedBy, assignedTo, submitDate, status);
            Gson gson = new Gson();
            JsonElement jElement = gson.toJsonTree(ecs, new TypeToken<ArrayList<ExtenuatingCircumstance>>(){}.getType());
            JsonArray jArray = jElement.getAsJsonArray();
            System.out.println("json" + jArray.toString());
            response.setContentType("application/json");
            response.getWriter().print(jArray);
        } catch (SQLException ex) {
            Logger.getLogger(SearchEC.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
