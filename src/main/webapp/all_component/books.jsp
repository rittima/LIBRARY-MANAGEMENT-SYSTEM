
<%@page import="java.sql.*"%>

<%
    // Database connection variables
    String url = "jdbc:oracle:thin:@localhost:1521:oracledb";
    String user = "system";
    String password = "hpsystem";

    
    Connection connect = null;
    Statement st = null;
    ResultSet book = null;

    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
		connect = DriverManager.getConnection(url, user, password);
		st = connect.createStatement();       
		book = st.executeQuery("SELECT * FROM BOOK");
%>

<table class="table  table-hover">
    
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Book Name</th>
            <th scope="col">Author</th>
            <!-- <th scope="col">Quantity</th> -->
            <th scope="col"></th>
        </tr>
    </thead>
    <tbody>
        <% while (book.next()) { %>
        <tr>
            <th scope="row"><%= book.getInt("bookId") %></th>
            <td><%= book.getString("bookName") %></td>
            <td><%= book.getString("author") %></td>
            <%-- <td ><%= book.getInt("quantity") %></td> --%>
            <td>
            	<a  style="background: #795548" type="button" class="btn btn-dark" href="addCart?bookId=<%= book.getInt("bookId") %>"><i class="fa fa-plus mx-1" aria-hidden="true"></i>Borrow</a>
            	 
            </td>
        </tr>
        <% } %>
    </tbody>
</table>

<%
    } catch (Exception e) {
        
        out.println("<p>Error: " + e.getMessage() + "</p>");
    } finally {
        
        if (book != null) try { book.close(); } catch (SQLException ignore) {}
        if (st != null) try { st.close(); } catch (SQLException ignore) {}
        if (connect != null) try { connect.close(); } catch (SQLException ignore) {}
    }
%>
