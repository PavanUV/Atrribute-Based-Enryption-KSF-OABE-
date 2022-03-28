/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Sai Krishna
 */
public class integrity extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String id;
    int hhh;
    String h1,h2;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        {
            try {
                HttpSession session=request.getSession();
                 String name=session.getAttribute("name").toString();
                session.setAttribute("name", name);
                id=request.getParameter("id");
                Class.forName("com.mysql.jdbc.Driver");
                Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/utility","root","root");
                String sql="select * from file where id='"+id+"'";
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    String filename=rs.getString("fname");
                    h1=rs.getString("hhash");
                    String filePath = "C:\\files\\"+filename;              
		    BufferedReader br= null;                  
                    String sCurrentLine;
                    StringBuilder b= new StringBuilder();                   
                    br = new BufferedReader(new FileReader("C:\\files\\" + filename));
                    while ((sCurrentLine = br.readLine()) != null) 
                    {
                         System.out.println("hiii"+sCurrentLine);
                         
                         hhh=sCurrentLine.hashCode();
                        System.out.println("hash value="+hhh);
                    }
                    h2=Integer.toString(hhh);
                    System.out.println(h1+" "+h2);
                    if(h1.equals(h2))
                    {
                        out.println("<script>"
				+"alert('Both Hash Values are same. Your File is Secure')"
				+"</script>");
		RequestDispatcher rd=request.getRequestDispatcher("/creatorhome.jsp");
		rd.include(request, response);
                    }
                    else
                    {
                        out.println("<script>"
				+"alert('Your File is Modified')"
				+"</script>");
		RequestDispatcher rd=request.getRequestDispatcher("/creatorhome.jsp");
		rd.include(request, response);
                    }
                    }
                else
                {
                   out.println("<script>"
				+"alert('File Not Found')"
				+"</script>");
		RequestDispatcher rd=request.getRequestDispatcher("/creatorhome.jsp");
		rd.include(request, response); 
                }        
    } catch (Exception ex) 
    {
                Logger.getLogger(integrity.class.getName()).log(Level.SEVERE, null, ex);
                out.println("<script>"
				+"alert('Check ur DB')"
				+"</script>");
		RequestDispatcher rd=request.getRequestDispatcher("/creatorhome.jsp");
		rd.include(request, response);
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
