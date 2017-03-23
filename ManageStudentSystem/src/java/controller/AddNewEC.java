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
@WebServlet(name = "AddNewEC", urlPatterns = {"/AddNewEC"})
public class AddNewEC extends HttpServlet {

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
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);
                ArrayList<FileItem> files = new ArrayList<>();
                for (FileItem item : multiparts) {
                    if (item.isFormField()) {
                        if (item.getFieldName().equals("title")) {
                            title = item.getString();
                        }
                        if (item.getFieldName().equals("description")) {
                            desciption = item.getString();
                        }

                    } else {
                        if (StringUtils.isNotEmpty(item.getName())) {
                            files.add(item);
                        }
                    }
                }

                HttpSession session = request.getSession(false);
                Account studentAccount = (Account) session.getAttribute("account");
                LocalDateTime now = WsadUtils.GetCurrentDatetime();

                // insert EC
                ec.setTitle(title);
                ec.setDescription(desciption);
                ec.setProcess_status("submitted");
                ec.setSubmitted_date(now.toString());
                ec.setAccount(studentAccount.getId());

                ExtenuatingCircumstance insertedEC = new ExtenuatingCircumstanceDAO().insertEC(ec);

                //insert assigned coordinator
                Account coordinator = new AccountDAO().getCoordinator(studentAccount.getFaculty());
                insertAssignedCoordinator(coordinator, insertedEC);

                //insert evidence
                insertedEvidence(files, now, insertedEC, studentAccount);
                
                String mailContent = WsadUtils.buildMailContentForNewEC(insertedEC);
                SendMail.sendMail(coordinator.getEmail(), "New EC", mailContent);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }

        request.setAttribute("resultMsg", "inserted");
        request.getRequestDispatcher("AddNewECResult.jsp").forward(request, response);
    }

    

    private void insertAssignedCoordinator(Account coordinator, ExtenuatingCircumstance inserted) throws SQLException {
        
        AssginedCoordinator aCoordinator = new AssginedCoordinator();

        aCoordinator.setEcId(inserted.getId());
        aCoordinator.setCoordinatorId(coordinator.getId());

        AssginedCoordinator insertedAC = new ExtenuatingCircumstanceDAO().insertAssignedCoordinator(aCoordinator);
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
                Logger.getLogger(AddNewEC.class.getName()).log(Level.SEVERE, null, ex);
            }

            evidence.setFiles(destination);
            evidence.setEvidence_date(now.toString());
            evidence.setEcId(inserted.getId());
            
            Evidence insertedE = new EvidenceDAO().insertEvidence(evidence);
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
