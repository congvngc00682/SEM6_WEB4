/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Account;
import entities.AssginedCoordinator;
import entities.Evidence;
import entities.ExtenuatingCircumstance;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccountDAO;
import model.EvidenceDAO;
import model.ExtenuatingCircumstanceDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import utils.SendMail;
import utils.WsadUtils;

/**
 *
 * @author CuongDH1
 */
@WebServlet(name = "UpdateEC", urlPatterns = {"/UpdateEC"})
public class UpdateEC extends HttpServlet {

    private final String Upload_Directory = "C:/UploadFolder";

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
        ExtenuatingCircumstance ec = new ExtenuatingCircumstance();
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                String fname = StringUtils.EMPTY;
                String title = StringUtils.EMPTY;
                String desciption = StringUtils.EMPTY;
                String status = StringUtils.EMPTY;
                int id = 0;
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);
                ArrayList<FileItem> files = new ArrayList<>();
                for (FileItem item : multiparts) {
                    if (item.isFormField()) {
                        if (item.getFieldName().equals("id")) {
                            id = Integer.parseInt(item.getString());
                            System.out.println("id: " + id);
                        }
                        if (item.getFieldName().equals("title")) {
                            title = item.getString();
                        }
                        if (item.getFieldName().equals("description")) {
                            desciption = item.getString();
                        }
                        if (item.getFieldName().equals("status")) {
                            status = item.getString();
                            System.out.println("status: " + status);
                        }

                    } else {
                        if (StringUtils.isNotEmpty(item.getName())) {
                            files.add(item);
                        }
                    }
                }

                HttpSession session = request.getSession(false);
                Account studentAccount = (Account) session.getAttribute("account");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                // insert EC
                ec.setId(id);
                ec.setTitle(title);
                ec.setDescription(desciption);
                ec.setProcess_status(status);
                //ec.setSubmitted_date(now.toString());
                ec.setAccount(studentAccount.getId());

                new ExtenuatingCircumstanceDAO().updateEC(ec, "student");

                //insert additional evident evidence
                if(files.size() > 0) {
                    insertedEvidence(files, now, ec, studentAccount);
                }
                
                request.setAttribute("resultMsg", "updated");
                request.getRequestDispatcher("AddNewECResult.jsp").forward(request, response);
        
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }
    }

    private void insertedEvidence(ArrayList<FileItem> files, LocalDateTime now, ExtenuatingCircumstance inserted, Account studentAccount) throws SQLException {
        String fname;
        Evidence evidence = null;
        String destination = StringUtils.EMPTY;
        for (FileItem file : files) {
            try {
                evidence = new Evidence();
                fname = new File(file.getName()).getName();
                String ext = FilenameUtils.getExtension(fname);
                String newFilename = studentAccount.getUsername() + System.currentTimeMillis() + "." + ext;
                destination = Upload_Directory + File.separator + newFilename;
                System.out.println("destination: " + destination);
                file.write(new File(destination));

                
            } catch (Exception ex) {
                Logger.getLogger(UpdateEC.class.getName()).log(Level.SEVERE, null, ex);
            }

            evidence.setFiles(destination);
            evidence.setEvidence_date(now.toString());
            evidence.setEcId(inserted.getId());
            
            new EvidenceDAO().insertEvidence(evidence);
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
