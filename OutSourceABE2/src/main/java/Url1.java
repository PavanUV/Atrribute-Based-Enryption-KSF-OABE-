/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sai Krishna
 */
import java.io.*;
import java.security.SecureRandom;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Url1 extends HttpServlet 
{   int hhh;
    String h1,h2;
    Date dNow = new Date( );
      SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
    String hashh="";
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
{      
    
     List<String> ms = new ArrayList<String>();
        String finalimage = "";
        boolean isMultipart = ServletFileUpload.isMultipartContent(
                request);
        List<String> m = new ArrayList<String>();
        File savedFile;
        
        if (!isMultipart) {
           
        } else {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;

            try {
                items = upload.parseRequest(request);
                //System.out.println("items: "+items);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                List<String> al = new ArrayList<String>();

                String sss = "";
                FileItem item = (FileItem) itr.next();
                String value = "";
                String a[];
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    //System.out.println("name: "+name+'\n');
                    value = item.getString();
                    // System.out.println("value: "+value);
                    al.add(value);
                    for (int i = 0; i < al.size(); i++) {
                        sss += al.get(i);
                        System.out.println("is thios image" + sss);

     //System.out.println("the value sixcwe"+m.size());
                    }

                    a = sss.split("-");
                    for (int i = 0; i < a.length; i++) 
                    {

                        String am = a[i];
                        System.out.println("aaaaaaaaaaaaaaaa" + a[i]);
                        m.add(a[i]);
                    }
                } else {

                    String itemName = item.getName();

                    String reg = "[.*]";
                    String replacingtext = "";
                    Pattern pattern = Pattern.compile(reg);
                    Matcher matcher = pattern.matcher(itemName);
                    StringBuffer buffer = new StringBuffer();

                    while (matcher.find()) {
                        matcher.appendReplacement(buffer, replacingtext);
                    }
                    int IndexOf = itemName.indexOf(".");
                    int Indexf = itemName.indexOf(".");
                    String domainName = itemName.substring(IndexOf);
                    finalimage = buffer.toString() + domainName;
                    System.out.println("Final Image===" + finalimage);
                    ms.add(finalimage);
                    savedFile = new File("C:\\files\\inte\\" + finalimage);
                    try {
						item.write(savedFile);
                                                 BufferedReader br= null;
                    CipherHelper c= new CipherHelper();
                    String sCurrentLine;
                    StringBuilder b= new StringBuilder();
                    br = new BufferedReader(new FileReader("C:\\files\\inte\\" + finalimage));
                    while ((sCurrentLine = br.readLine()) != null) 
                    {
                        hashh=Integer.toString(sCurrentLine.hashCode());
                       // System.out.println(sCurrentLine);
                       
                    }
                    
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        }
        String strQuery = "";
        PrintWriter out=response.getWriter();
        String id=m.get(0);
        String name=m.get(1);
        HttpSession session=request.getSession();
        session.setAttribute("name", name);
        try
        {
	Class.forName("com.mysql.jdbc.Driver");
                Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/utility","root","root");
                String sql="select * from req where id='"+id+"'";
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                String fid="";
                if(rs.next())
                {
                   fid=rs.getString("fid");
                }
                String sql1="select * from file where id='"+fid+"'";
                PreparedStatement ps1=con.prepareStatement(sql1);
                ResultSet rs1 = ps1.executeQuery();
                if(rs1.next())
                {
                    String filename=rs1.getString("fname");
                    h1=rs1.getString("hhash");
                    String filePath = "C:\\files\\inte\\"+filename;              
		    BufferedReader br= null;                  
                    String sCurrentLine;
                    StringBuilder b= new StringBuilder();                   
                    br = new BufferedReader(new FileReader("C:\\files\\inte\\" + filename));
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
		RequestDispatcher rd=request.getRequestDispatcher("/readerhome.jsp");
		rd.include(request, response);
                    }
                    else
                    {
                        out.println("<script>"
				+"alert('Your File is Modified')"
				+"</script>");
		RequestDispatcher rd=request.getRequestDispatcher("/readerhome.jsp");
		rd.include(request, response);
                    }
                    }
                else
                {
                   out.println("<script>"
				+"alert('File Not Found')"
				+"</script>");
		RequestDispatcher rd=request.getRequestDispatcher("/readerhome.jsp");
		rd.include(request, response); 
                }        
    } catch (Exception ex) 
    {
                Logger.getLogger(viewintegrity.class.getName()).log(Level.SEVERE, null, ex);
                out.println("<script>"
				+"alert('Check ur DB')"
				+"</script>");
		RequestDispatcher rd=request.getRequestDispatcher("/readerhome.jsp");
		rd.include(request, response);
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
