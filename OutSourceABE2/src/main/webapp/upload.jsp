<%-- 
    Document   : upload
    Created on : Aug 19, 2016, 4:28:19 PM
    Author     : DLK 1
--%>

<%@page import="java.security.SecureRandom"%>
<%@page import="java.util.Random"%>
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
             <%
                String name=session.getAttribute("name").toString();
                session.setAttribute("name", name);
            %>
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
					<nav id="main-nav" class="8u">
						<ul>
							<li><a  href="creatorhome.jsp">Home</a></li>
							<li><a class="active"  href="upload.jsp">Upload</a></li>
							<li><a href="viewfile.jsp">View File</a></li>	
                                                        <li><a href="integrity.jsp">Check Integrity</a></li>
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
						<h2>Upload File</h2>
					</div>
				</div>						
			</div>		
		<center>
                    <%
                                String tkey="";
       String chars = "abcdefghijklmnopqrstuvwxyz"
                     + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                     + "0123456789";
        final int PW_LENGTH = 15;
        Random rnd = new SecureRandom();
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < PW_LENGTH; i++)
            pass.append(chars.charAt(rnd.nextInt(chars.length())));        
            tkey=pass.toString();
          
                            %>
			<div class="container">			
			 <form action="Url" method="POST" enctype="multipart/form-data" >
          <strong>Creator Name:</strong><input type="text" name="dname" value="<%=name%>" /><br><br>
          &emsp;&emsp;<strong>Category:</strong>
          <input type="text" name="cate" value="" /><br><br>
          &emsp;&emsp;<strong>File Key:</strong>
          <input type="text" name="tkey" value="<%=tkey%>" /><br><br>
         &emsp;&emsp;&emsp;&emsp;&nbsp; <strong>Upload Your File:</strong>
          <input type="file" name="file" value="" /><br><br>          
   <input type="submit" name="Submit" value="Upload" style="background-color: #ffffff; font-weight: bold;"/>
      </form>   
				
				
                        </div></center>
                    
        </div>	
		
		<div class="footer-wrapper">	
			<div class="container">					
			<!-- Footer -->
				<footer>
					<p><img src="images/twitter.png" alt="twitter" />&nbsp;<img src="images/facebook.png" alt="facebook" />&nbsp;<img src="images/rss.png" alt="rss" /></p>
					<p><a href="index.html">Home</a> | <a href="#">Examples</a> | <a href="#">A Page</a> | <a href="#">Another Page</a> | <a href="#p">Contact Us</a></p>
					<p>Copyright &copy; ABC | <a href="#"></a> | <a href="#">Images</a></p>
				</footer>		
			</div>
		</div>
			
	</body>
</html>
