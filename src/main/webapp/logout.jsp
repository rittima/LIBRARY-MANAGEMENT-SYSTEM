<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="all_component/bootstrap.jsp" %>
<title>Logout Page</title>
</head>
<body class="bg-body">
<%@include file="all_component/Navbar.jsp" %>


<%
		session.removeAttribute("name");
		session.removeAttribute("userLoggedIn");
		session.invalidate(); 
		
		response.sendRedirect("login.jsp");
	
%>
 
 </body>
</html>