<%-- 
    Document   : sendreq
    Created on : Aug 19, 2016, 7:55:36 PM
    Author     : DLK 1
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%
    String name=session.getAttribute("name").toString();
                session.setAttribute("name", name);
    String id=request.getParameter("id");
    Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/utility", "root", "root");
      String Qu="select * from file where id='"+id+"'";
      PreparedStatement pss=con.prepareStatement(Qu);
      ResultSet rss=pss.executeQuery();
      String filename = "",fileid = "",fkey="";  
      
      if(rss.next())
      {
         filename=rss.getString("fname");
         fkey=rss.getString("fkey");         
      }
      String q="insert into req (fname,fid,fkey,rname) values ('"+filename+"','"+id+"','"+fkey+"','"+name+"') ";
       PreparedStatement pss1=con.prepareStatement(q);
       pss1.executeUpdate();
       out.println("<script>"
				+"alert('Request Send Successfully')"
				+"</script>");
       RequestDispatcher rd=request.getRequestDispatcher("readerhome.jsp");
		rd.include(request, response);
%>