
 <%@page import="library.Cart"%>
<%@page import="java.util.ArrayList"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-custom navbar-custom">
  <div class="container-fluid">
    <div class="navbar-brand" ><b>My Library</b></div>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      
       <%
       Boolean userLoggedIn = (Boolean) session.getAttribute("userLoggedIn"); 
       if (userLoggedIn != null && userLoggedIn) { 
       %>
      
        <li class="nav-item">
         	<a class="nav-link" aria-current="page" href="Home.jsp">
	         	<i class="fa fa-home mx-1" aria-hidden="true"></i>
	         	Home
         	</a>
        </li>
        <li class="nav-item">
          	<a class="nav-link" href="Cart.jsp">
          		<i class="fa fa-shopping-cart mx-1" aria-hidden="true"></i>
          		Your Cart 
          		<span class="badge rounded-pill bg-danger">
          			${borrowedList != null ? borrowedList.size() : 0}
          			<%-- ${borrowedList.size()} --%>
          		</span>
          	</a>
        </li>
        
        <li class="nav-item">
         	<a class="nav-link" aria-current="page" href="borrowedBooks.jsp">
	         	<i class="fa fa-list mx-1" aria-hidden="true"></i>
	         	Book Summary
         	</a>
        </li> 
      <% } %>
      </ul>
      </div>
      
  	 <ul class="navbar-nav">
        <% if (userLoggedIn != null && userLoggedIn) { %>
        <li class="nav-item ms-auto">
          <a class="nav-link" href="logout.jsp"><i class="fa fa-sign-out mx-1" aria-hidden="true"></i> Logout</a>
        </li>
        <% } else { %>
        <li class="nav-item">
          <a class="nav-link" href="login.jsp">
            <i class="fa fa-sign-in mx-1" aria-hidden="true"></i> Sign in
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="register.jsp">
            <i class="fa fa-user-plus mx-1" aria-hidden="true"></i> Sign up
          </a>
        </li>
        <% } %>
      </ul>
  
  </div>
</nav>
 
 
 
 