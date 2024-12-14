
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="all_component/bootstrap.jsp" %>
<title>Home</title>
</head>
<body>
<%@include file="all_component/Navbar.jsp" %>

<div class="container">
<h1 class="my-3" style="color: #795548"> Welcome <%= session.getAttribute("name") != null ? session.getAttribute("name") : "Guest" %>   <i class="fa fa-hand-peace-o mx-2" aria-hidden="true"></i></h1>
<h3 class="my-3 fst-italic"><i class="fa fa-graduation-cap mx-2" aria-hidden="true"></i> MyLibrary</h3>

<%@include file="all_component/books.jsp" %>

</div>

</body>
</html>