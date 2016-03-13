

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterDriverServlet
 */
@WebServlet("/Driver_Register")
public class RegisterDriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterDriverServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
	  
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();
		
		String username=request.getParameter("username"); 
		String firstName=request.getParameter("firstname");  
		String lastName=request.getParameter("lastname");
		String mobile=request.getParameter("mobile");
		String password=request.getParameter("password");  
		String licence=request.getParameter("licence");
		String carNo=request.getParameter("car_no");
		
		RegisterDriver register = new RegisterDriver();
		register.doRegister(username, firstName, lastName, mobile, password, licence, carNo);
		
		response.sendRedirect("index.html");
		
    }
}

