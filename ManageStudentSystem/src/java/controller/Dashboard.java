/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Account;
import entities.ExtenuatingCircumstance;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "Dashboard", urlPatterns = {"/Dashboard"})
public class Dashboard extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        Account account = (Account) request.getSession().getAttribute("account");
        try {

            if (account.getRole() == 1) {
                response.sendRedirect("AdminManager.jsp");
            }
            if (account.getRole() == 2) {
                System.out.println("faculty id " + account.getFaculty());
                ArrayList<ExtenuatingCircumstance> ecs = new ExtenuatingCircumstanceDAO().retrieveECsByFacultyId(account.getFaculty());
                for (ExtenuatingCircumstance ec : ecs) {
                    System.out.println("title" + ec.getTitle());
                }
                request.setAttribute("ecs", ecs);
                request.getRequestDispatcher("ECManager.jsp").forward(request, response);
            }
            if (account.getRole() == 3) {
                ArrayList<ExtenuatingCircumstance> ecs = new ExtenuatingCircumstanceDAO().retrieveECsByCoordinatorId(account.getId());
                request.setAttribute("ecs", ecs);
                request.getRequestDispatcher("ECCoordinatorManager.jsp").forward(request, response);
            }
            if (account.getRole() == 4) {
                ArrayList<ExtenuatingCircumstance> ecs = new ExtenuatingCircumstanceDAO().retrieveECsByStudentId(account.getId());

                request.setAttribute("ecs", ecs);
                request.getRequestDispatcher("StudentManager.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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