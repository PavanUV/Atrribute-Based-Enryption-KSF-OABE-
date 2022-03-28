/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DLK 1
 */
public class login extends HttpServlet 
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String name,pass;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        {
            try {
                name=request.getParameter("name");
                pass=request.getParameter("pass");
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/utility","root","root");
                String sql="select * from dreg where name='"+name+"' and pass='"+pass+"'";
                PreparedStatement pss=con.prepareStatement(sql);
                ResultSet rss = pss.executeQuery();
                String sql1="select * from ureg where name='"+name+"' and pass='"+pass+"'";
                PreparedStatement pss1=con.prepareStatement(sql1);
                ResultSet rss1 = pss1.executeQuery();
                HttpSession session = request.getSession();
                if(rss.next())
                {
                     session.setAttribute("name", name);
                     out.println("<script>"
                        +"alert('Valid Data Owner')"
                        +"</script>");
                RequestDispatcher rd=request.getRequestDispatcher("/creatorhome.jsp");
                rd.include(request, response);
                }
                else if(rss1.next())
                {
                    session.setAttribute("name", name);
                     out.println("<script>"
                        +"alert('Valid Data User')"
                        +"</script>");
                RequestDispatcher rd=request.getRequestDispatcher("/readerhome.jsp");
                rd.include(request, response);
                }
                else if(name.equals("trust")&&pass.equals("trust"))
                {
                    session.setAttribute("name", name);
                    out.println("<script>"
                        +"alert('Welcome Trusted Authority')"
                        +"</script>");
                RequestDispatcher rd=request.getRequestDispatcher("/publisher.jsp");
                rd.include(request, response);
                }
                else
                {
                    out.println("<script>"
                        +"alert('Invalid User')"
                        +"</script>");
                RequestDispatcher rd=request.getRequestDispatcher("/#");
                rd.include(request, response);
                }
            } catch (Exception ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
                
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
