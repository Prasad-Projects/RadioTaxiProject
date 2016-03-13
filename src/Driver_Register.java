
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Driver_Register")
public class Driver_Register  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	  protected void doPost(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException {  
			response.setContentType("text/html");  
			PrintWriter out=response.getWriter();  	
			String firstname=request.getParameter("firstname");  
			String lastname=request.getParameter("lastname");
			String username=request.getParameter("username");  
			String password=request.getParameter("password");  
			String confirm_password=request.getParameter("confirm_password");  
			String mobile=request.getParameter("mobile");
			String licence=request.getParameter("licence");
			String car_no=request.getParameter("car_no");
			Register_db r = new Register_db();
			r.use_keyspace();
			r.register_driver(username, firstname, lastname, mobile, confirm_password, car_no, licence);
	    }    
	
}

