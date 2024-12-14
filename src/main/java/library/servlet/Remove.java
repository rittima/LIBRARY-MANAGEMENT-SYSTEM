package library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.Cart;

@WebServlet("/Remove")
public class Remove extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()){
			String id = request.getParameter("bookId");
			if (id!=null) {
				ArrayList<Cart> borrowedList = (ArrayList<Cart>) request.getSession().getAttribute("borrowedList");
				if (borrowedList != null) {
					for (Cart cart : borrowedList) {
						if (cart.getBookId() == Integer.parseInt(id)) {
							borrowedList.remove(borrowedList.indexOf(cart));
							break;
						}
					}
					response.sendRedirect("Cart.jsp");
				}
			} else {
				response.sendRedirect("Cart.jsp");
			}
		} 
	}

}
