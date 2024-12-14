<%@ page import="java.util.ArrayList" %>
<%@ page import="library.BorrowedBook" %>
<%
    ArrayList<BorrowedBook> borrowedBooksDetails = (ArrayList<BorrowedBook>) session.getAttribute("borrowedBooksDetails");
    String auth = (String) session.getAttribute("name");
    if (auth == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Borrowed Books</title>
    <%@ include file="all_component/bootstrap.jsp" %>
</head>
<body>
    <%@ include file="all_component/Navbar.jsp" %>
    <div class="container my-3">
        <h1 class="my-3" style="color: #795548">
            <i class="fa fa-book" aria-hidden="true"></i> Borrowed Books by <%= auth %>
        </h1>
        <div class="card mx-3 shadow-lg" style="border-radius: 10px; padding: 20px;">
            <table class="table table-striped my-3">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Book ID</th>
                        <th scope="col">Book Name</th>
                        <th scope="col">Author</th>
                        <th scope="col">Borrowed Date</th>
                        <th scope="col">Return Date</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (borrowedBooksDetails != null && !borrowedBooksDetails.isEmpty()) {
                            for (BorrowedBook book : borrowedBooksDetails) {
                    %>
                    <tr>
                        <td><%= book.getBookId() %></td>
                        <td><%= book.getBookName() %></td>
                        <td><%= book.getAuthor() %></td>
                        <td><%= book.getBorrowedDate() %></td>
                        <td><%= book.getReturnDate() != null ? book.getReturnDate() : "<span style='color: red;'>Not Returned</span>" %></td>
                        <td>
                            <form action="ReturnBookServlet" method="post">
                                <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
                                <button type="submit" class="btn btn-warning btn-sm" 
                                    <%= book.getReturnDate() != null ? "disabled" : "" %>>
                                    Return
                                </button>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="6" class="text-center text-muted fw-bolder">No books borrowed.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
