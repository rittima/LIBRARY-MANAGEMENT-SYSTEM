package library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.Cart;
import library.BorrowedBook;

@WebServlet("/BookSummary")
public class BookSummary extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object borrowedListObject = session.getAttribute("borrowedList");

        ArrayList<Cart> borrowedList = null;
        if (borrowedListObject instanceof ArrayList<?>) {
            ArrayList<?> tempList = (ArrayList<?>) borrowedListObject;
            if (tempList.isEmpty() || tempList.get(0) instanceof Cart) {
                borrowedList = (ArrayList<Cart>) tempList;
            }
        }

        String auth = (String) session.getAttribute("name");
        if (auth == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date();

            if (borrowedList != null && auth != null) {
                // Create a detailed list of borrowed books
                ArrayList<BorrowedBook> borrowedBooksDetails = new ArrayList<>();
                for (Cart cart : borrowedList) {
                    BorrowedBook borrowedBook = new BorrowedBook(
                        cart.getBookId(),
                        cart.getBookName(),
                        cart.getAuthor(),
                        format.format(date),  // Borrowed date and time
                        null                 // Return date initially null
                    );
                    borrowedBooksDetails.add(borrowedBook);
                }

                // Store borrowedBooksDetails in the session
                session.setAttribute("borrowedBooksDetails", borrowedBooksDetails);
                response.sendRedirect("borrowedBooks.jsp");
            } else {
                response.sendRedirect("cart.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h1>An error occurred while processing your request</h1>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
