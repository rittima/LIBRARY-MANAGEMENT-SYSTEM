package library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final String query = "INSERT INTO User1(Name,Email,Password) VALUES (?,?,?)";
    
    public RegisterServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);	
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }
        
        try (
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracledb", "system", "hpsystem");
            PreparedStatement ps = con.prepareStatement(query);
        ) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            
            int x = ps.executeUpdate();
            if (x > 0) {
            	HttpSession session = request.getSession();
                session.setAttribute("name", name);
                response.sendRedirect("login.jsp");
            } else {
                pw.println("<h1>Registration Failed. Please Try Again.</h1>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
        }
	}
}
