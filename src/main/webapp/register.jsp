<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="all_component/bootstrap.jsp" %>
<title>Register Page</title>
</head>
<body class="bg-body">
<%@include file="all_component/Navbar.jsp" %>

<!-- Login Body -->
<div class="container my-3" style="padding-top: 30px">
	<div class="row">
		<div class="col-md-4 offset-md-4">
			<div class="card" style="border: none;">
				<div class="card-header text-center text-white c-head bg-custom">
					<i class="fa fa-user-plus fa-2x"></i>
					<h3>Register</h3>
				</div>
				<div class="card-body">
					<form method="post" action="register" class="RegisterServlet" >
					  <div class="mb-3">
					    <label for="name" class="form-label ">Full Name</label>
					    <input type="text" class="form-control input-style" name="name" >
					  </div>
					  <div class="mb-3">
					    <label for="email" class="form-label">Email address</label>
					    <input type="email" class="form-control input-style" name="email" aria-describedby="emailHelp">
					  </div>
					  <div class="mb-3">
					    <label for="password" class="form-label">Password</label>
					    <input type="password" class="form-control input-style" name="password">
					  </div>
					  <div class="mb-3 form-check">
					    <input type="checkbox" class="form-check-input" id="check">
					    <label class="form-check-label" for="check">Remember me</label>
					  </div>
					  <div class="d-grid gap-2">
					  	<button type="submit" class="btn btn-dark bg-custom">Sign up</button>
					  </div>
					</form>
				</div>
			</div>	
		</div>
	</div>
</div>


</body>


</html>