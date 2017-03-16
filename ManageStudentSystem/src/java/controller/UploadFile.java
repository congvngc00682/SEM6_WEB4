/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.DataProcess;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author power
 */
public class UploadFile extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String Upload_Directory = "E:\\SEM6\\EnterPrise-Mr.Tung\\Project\\TestSaveFile\\src\\java\\FileName";

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

        // convert date to string
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);

        
            if (ServletFileUpload.isMultipartContent(request)) {
                try {
                    String fname = null;
                    String fsize = null;
                    String ftype = null;
                    List<FileItem> multiparts = new ServletFileUpload(
                            new DiskFileItemFactory()).parseRequest(request);
                    for (FileItem item : multiparts) {
                        if (!item.isFormField()) {
                            fname = new File(item.getName()).getName();
                            fsize = new Long(item.getSize()).toString();
                            ftype = item.getContentType();
                            item.write(new File(Upload_Directory + File.separator
                                    + fname));
                        }
                    }
                    // File uploaded successfully
                    request.setAttribute("message", "File Uploaded Successfully");
                    
                    request.setAttribute("size", fsize);
                    request.setAttribute("type", ftype);
                    DataProcess db = new DataProcess();
                    db.getEvidence(reportDate, fname, 1);
                    request.setAttribute("name", Upload_Directory + fname);
                } catch (Exception e) {
                }
            } else {
                request.setAttribute("message",
                        "Sorry this Servlet only handles file upload request");
            }

            request.getRequestDispatcher("/result.jsp").forward(request, response);

        
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
