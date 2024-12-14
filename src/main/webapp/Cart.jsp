
<%@ page import="library.DBConnect" %>
<%@ page import="library.BookCrontroller" %>
<%@ page import="java.util.List" %>
<%@ page import="library.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%
    
    String auth = (String) session.getAttribute("name");
    if (auth == null) {
        response.sendRedirect("login.jsp");
        return;
    } else {
        request.setAttribute("name", auth);
    }

    ArrayList<Cart> borrowedList = (ArrayList<Cart>) session.getAttribute("borrowedList");
    List<Cart> borrowedBook = null;

    if (borrowedList != null) {
        BookCrontroller bookController = new BookCrontroller(DBConnect.getConnection());
        borrowedBook = bookController.getBorrowed(borrowedList);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Borrowed Books</title>
    <%@ include file="all_component/bootstrap.jsp" %>
    <%@ include file="all_component/Navbar.jsp" %>
</head>
<body>
    <%-- <%@ include file="all_component/Navbar.jsp" %> --%>

    <div class="container my-3">
        <h1 class="my-3" style="color: #795548">
            <i class="fa fa-book" aria-hidden="true"></i> Books Borrowed by <%= auth %>
        </h1>
        
        <div class="card mx-3 shadow-lg" style="border-radius: 10px;  padding: 20px;">
        <table class="table table-striped my-3 ">
		    <thead class="thead-dark">
		        <tr>
		            <th scope="col">Book ID</th>
		            <th scope="col">Book Name</th>
		            <th scope="col">Author</th>
		            <th scope="col">Quantity</th>
		            <th scope="col"></th>
		        </tr>
		    </thead>
		    <tbody>
		        <% 
		            if (borrowedBook != null && !borrowedBook.isEmpty()) {
		                for (Cart item : borrowedBook) {
		        %>
		        <tr>
		            <td><%= item.getBookId() %></td>
		            <td><%= item.getBookName() %></td>
		            <td><%= item.getAuthor() %></td>
		            <td><%= item.getQuantity() %></td>
		            <td>
		                <form action="ReturnBookServlet" method="post" class="d-inline-block">
		                    <input type="hidden" name="bookId" value="<%= item.getBookId() %>">
		                </form>
		            </td>
		            <td>
		            	<a type="submit" class="btn btn-danger btn-sm" 
		            		style="border-radius: 50%; padding: 5px 10px;" 
		            		title="Remove Book" 
		            		href="Remove?bookId=<%= item.getBookId() %>" >
	                        <i class="fa fa-times" aria-hidden="true"></i>
	                    </a>
	                </td>
		        </tr>
		        <% 
		                }
		            } else {
		        %>
		        <tr>
		            <td colspan="5" class="text-center text-muted fw-bolder" style="padding: 10px">No books borrowed.</td>
		        </tr>
		        <% 
		            }
		        %>
		    </tbody>
		</table>
		
		<a type="button" class="btn btn-success" href="BookSummary">Borrowing</a>
        </div>
        
    </div>
</body>
</html>
