package library.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.BorrowedBook;

@WebServlet("/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<BorrowedBook> borrowedBooksDetails = (ArrayList<BorrowedBook>) session.getAttribute("borrowedBooksDetails");
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        if (borrowedBooksDetails != null) {
            for (BorrowedBook book : borrowedBooksDetails) {
                if (book.getBookId() == bookId && book.getReturnDate() == null) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    book.setReturnDate(format.format(new Date()));
                    break;
                }
            }
            session.setAttribute("borrowedBooksDetails", borrowedBooksDetails);
        }
        response.sendRedirect("borrowedBooks.jsp");
    }
}

