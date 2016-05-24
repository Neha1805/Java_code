
package com.walmart.levdetail;

/**
 *
 * @author Neha
 */
//public class serv {
 

//THIS WILL BE YOUR JAVA MIDDLEWARE TO GET THE VALUE
//package servletPackage;

/*
 * This is the Servlet page for the application. Its doPost method gets the values from the survey form
 * and sends to the database for save. It also calculates the mean and standard deviation of provided
 * numbers. Its doGet methos retrieves the value of the student id clicked
 * after the acknowledgment. 
 */
//import student.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sewan Shrestha (sshres18)
 */
@WebServlet(urlPatterns = {"/mainServlet"})
public class mainServlet extends HttpServlet {

    protected void doTest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
            String Level_Id = request.getParameter("Level_Id");
            
            
            //HttpSession hs = request.getSession();
            /*hs.setAttribute("studentinfo", studentResult);
            //request.setAttribute("stdinfo", studentResult);
            String url = "";
            if(studentResult.getStudentid()==""){
                url = "/webpages/NoSuchStudentJSP.jsp";
            }
            else{
                url = "/webpages/StudentJSP.jsp";
            }
            con.close();
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);*/
            System.out.println("doPost()"+Level_Id);
             try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet mainServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mainServlet at " + Level_Id + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
            
    } 
        
        
        //processRequest(request, response);
     
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet mainServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mainServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        
        
    }
 }