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

public class Url extends HttpServlet 
{   
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
                    savedFile = new File("C:\\files\\" + finalimage);
                    try {
						item.write(savedFile);
                                                 BufferedReader br= null;
                    CipherHelper c= new CipherHelper();
                    String sCurrentLine;
                    StringBuilder b= new StringBuilder();
                    br = new BufferedReader(new FileReader("C:\\files\\" + finalimage));
                    while ((sCurrentLine = br.readLine()) != null) 
                    {
                        hashh=Integer.toString(sCurrentLine.hashCode());
                       // System.out.println(sCurrentLine);
                        b.append(c.cipher("12345678",sCurrentLine));
                        System.out.println("hiii"+sCurrentLine);
                         String myAppContext = sCurrentLine;
                    }
                    PrintWriter writer = new PrintWriter("C:\\files\\enc\\"+finalimage, "UTF-8");
                    writer.println(b.toString());
                    writer.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        }
        String strQuery = "";
        PrintWriter out=response.getWriter();
      
      SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        PreparedStatement ps,ps1; 
	try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/utility","root","root");
            strQuery = "insert into file (dname,fname,fkey,category,hhash) "
                + "values('" +m.get(0)+ "','" + ms.get(0)+ "','" + m.get(2) + "','"+m.get(1)+"','"+hashh+"')";
            ps=con.prepareStatement(strQuery);
            ps.executeUpdate(strQuery);
            
        
        System.out.println("itemName::::: "+strQuery);       
        
        out.println("<script>"
				+"alert('File uploaded successfully')"
				+"</script>");
		RequestDispatcher rd=request.getRequestDispatcher("/upload.jsp");
		rd.include(request, response); 

	}
	catch(Exception e){System.out.println(e);}
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
