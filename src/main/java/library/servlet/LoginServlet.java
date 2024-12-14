package library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);	
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
		RequestDispatcher rd = null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracledb","system","hpsystem");
			PreparedStatement ps=connect.prepareStatement("SELECT * FROM USER1 WHERE EMAIL=? AND PASSWORD=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
				{
				String userName = rs.getString("Name"); // Assuming the column for the name is "Name" in the table
	            session.setAttribute("name", userName);
				session.setAttribute("email",email);
				session.setAttribute("userLoggedIn", true);
				rd=request.getRequestDispatcher("Home.jsp");
				}
				else
				{
//					rd=request.getRequestDispatcher("login.jsp");
					
				    out.println("<script type=\"text/javascript\">");
				    out.println("alert('Invalid login credentials. Please try again.');");
				    out.println("location='login.jsp';");
				    out.println("</script>");
				    return; 
				}
				rd.forward(request, response);
			}
			
		catch(Exception e) {
			e.printStackTrace();
			}
		}
}
