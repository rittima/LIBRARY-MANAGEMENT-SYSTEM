package library.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import library.Cart;
import library.DBConnect;

@WebServlet("/addCart")
public class AddCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        try {
            String bookIdParam = request.getParameter("bookId");
            if (bookIdParam == null || bookIdParam.isEmpty()) {
                response.getWriter().println("<h1>Invalid book ID</h1>");
                return;
            }

            int borrowedBook = Integer.parseInt(bookIdParam);

            // Retrieve current cart list from session
            ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("borrowedList");
            if (cartList == null) {
                cartList = new ArrayList<>();
                session.setAttribute("borrowedList", cartList);
            }

            // Check if the book is already in the cart
            for (Cart item : cartList) {
                if (item.getBookId() == borrowedBook) {
                    response.getWriter().println("<h1>This book is already in the cart</h1>");
//                    response.sendRedirect("Cart.jsp");
                    return;
                }
            }

            // Fetch book details from the database
            try (Connection conn = DBConnect.getConnection()) {
                String query = "SELECT BOOKID, BOOKNAME, AUTHOR FROM BOOK WHERE BOOKID = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, borrowedBook);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            Cart newItem = new Cart();
                            newItem.setBookId(rs.getInt("BOOKID"));
                            newItem.setBookName(rs.getString("BOOKNAME"));
                            newItem.setAuthor(rs.getString("AUTHOR"));
                            newItem.setQuantity(1); // Fixed quantity of 1
                            cartList.add(newItem);
                        } else {
                            response.getWriter().println("<h1>Book not found</h1>");
                            return;
                        }
                    }
                }
            }

            response.sendRedirect("Cart.jsp");
        } catch (NumberFormatException e) {
            response.getWriter().println("<h1>Invalid book ID format</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h1>An error occurred while processing your request</h1>");
        }
    }
}
