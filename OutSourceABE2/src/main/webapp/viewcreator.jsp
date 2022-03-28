<%-- 
    Document   : viewcreator
    Created on : Aug 19, 2016, 6:59:44 PM
    Author     : DLK 1
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300italic,600|Source+Code+Pro" rel="stylesheet" />
		<!--[if lte IE 8]><script src="js/html5shiv.js" type="text/javascript"></script><![endif]-->
		<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
		<script src="js/jquery.dropotron.min.js"></script>		
		<script src="js/skel.min.js">	
		{
			prefix: 'css/style',
			preloadStyleSheets: true,
			resetCSS: true,
			boxModel: 'border',
			grid: { gutters: 30 },
			breakpoints: {
				wide: { range: '1200-', containers: 1140, grid: { gutters: 50 } },
				narrow: { range: '481-1199', containers: 960 },
				mobile: { range: '-480', containers: 'fluid', lockViewport: true, grid: { collapse: true } }
			}
		}
		</script>
		<script>
			$(function() {

				// Note: make sure you call dropotron on the top level <ul>
				$('#main-nav > ul').dropotron({ 
					offsetY: -10 // Nudge up submenus by 10px to account for padding
				});

			});
		</script>
		<script>
			// DOM ready
			$(function() {
    
			// Create the dropdown base
			$("<select />").appendTo("nav");
   
			// Create default option "Go to..."
			$("<option />", {
				"selected": "selected",
				"value"   : "",
				"text"    : "Menu"
			}).appendTo("nav select");
   
			// Populate dropdown with menu items
			$("nav a").each(function() {
			var el = $(this);
			$("<option />", {
				"value"   : el.attr("href"),
				"text"    : el.text()
			}).appendTo("nav select");
			});
   
			// To make dropdown actually work
			// To make more unobtrusive: http://css-tricks.com/4064-unobtrusive-page-changer/
			$("nav select").change(function() {
				window.location = $(this).find("option:selected").val();
			});
  
			});
		</script>	
	</head>
	<body>
		<div id="header_container">		
		    <div class="container">
			<!-- Header -->
				<div id="header" class="row">
					 <div>
						 <div>
                        <h3 style="margin-left: 180px;margin-bottom: -32px;margin-top: -25px; font-size:39px; font-family:times new roman;color:white;">
                            Outsourced Attribute-Based Encryption with Keyword Search Function for Cloud Storage</h3>
                            </div>
					</div>
					
					<nav id="main-nav" class="9u">
						<ul>
							<li><a  href="publisher.jsp">Home</a></li>
							<li><a class="active" href="viewcreator.jsp">View Data Owner</a></li>
							<li><a  href="viewreader.jsp">View Data User</a></li>							
							<li><a href="sendfile.jsp">Send File</a></li>
                                                        <li><a href="logout.jsp">Logout</a></li>
						</ul>
					</nav>
				</div>
			</div>	
			
        </div>		

		<div id="site_content">
		
		    	
		
		    <div class="container">						
				<div class="12u">
					<div id="strapline">						
						<h2>View Data Owner</h2>
					</div>
				</div>						
			</div>		
		
			<center>
			<div class="container">			
			
                    <%
        try{
        //Dis=no
        

//if(Fin.equals("yes")){
   // System.out.println(Fin);
    %>
  <center>
     <table id="pro_display_table" border="0" cellspacing="1px"  style="border: 4px solid #FB9217; width:auto ;margin-left: -90px;height: auto;">
                                 <tr>
    <th scope="row">&nbsp;</th>
    <td>&nbsp;</td>
  </tr>
         <tr>  
                                    <td align="center" style="font-weight: bold;font-size: 17px;color:#1800FE;"><b><strong>&nbsp;&nbsp;&nbsp;&nbsp;Id</strong></b></td>                            
                                    <td align="center" style="font-weight: bold;font-size: 17px;color:#1800FE;"><b><strong>&nbsp;&nbsp; Name</strong></b></td>
                                    <td align="center" style="font-weight: bold;font-size: 17px;color:#1800FE;"><b><strong>&nbsp;&nbsp;&nbsp;&nbsp;Mobile</strong></b></td> 
                                    <td align="center" style="font-weight: bold;font-size: 17px;color:#1800FE;"><b><strong>&nbsp;&nbsp;&emsp;&emsp;Email</strong></b></td>
                                </tr>
         <tr>
    <th scope="row">&nbsp;</th>
    <td>&nbsp;</td>
  </tr>
<% 
            String ac="Active";
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/utility", "root", "root");
                        String Query22="select * from dreg ";
                        PreparedStatement ps22=con.prepareStatement(Query22);
                        ResultSet rs22=ps22.executeQuery();
                                      while(rs22.next())
                                      {
                                         
                                          %>
                                <tr>                                  
                                    <td align="center" style="font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=rs22.getString("id")%></td>
                                    <td align="center" style="font-weiolor:#080606;ght: bold;">&emsp;&emsp;&emsp;<%=rs22.getString("name")%></td>
                                    <td align="center" style="font-weight: bold;">&nbsp;&nbsp;<%=rs22.getString("mobile")%></td>
                                    <td align="center" style="font-weight: bold;"><%=rs22.getString("email")%></td>
                                    
 <tr>
    <th scope="row">&nbsp;</th>
    <td>&nbsp;</td>
  </tr>
                                    <%}%>
              </table>
</center>
     <%//}
                                     
   }catch(Exception e){
   System.out.println(e);
   }
   %>    
         
          <br><br>		
				
			</div></center>
        </div>	
		
		<div class="footer-wrapper">	
			<div class="container">					
			<!-- Footer -->
				<footer>
					<p><img src="images/twitter.png" alt="twitter" />&nbsp;<img src="images/facebook.png" alt="facebook" />&nbsp;<img src="images/rss.png" alt="rss" /></p>
					<p><a href="index.html">Home</a> | <a href="#">Examples</a> | <a href="#">A Page</a> | <a href="#">Another Page</a> | <a href="#p">Contact Us</a></p>
					
				</footer>		
			</div>
		</div>
			
	</body>
</html>
