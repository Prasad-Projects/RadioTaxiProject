

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterRiderServlet
 */
@WebServlet("/Rider_Register")
public class RegisterRiderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterRiderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			
			RegisterRider register = new RegisterRider();
			register.doRegister(username, firstName, lastName, mobile, password);
			
			response.sendRedirect("index.html");

	    }    
	
}
