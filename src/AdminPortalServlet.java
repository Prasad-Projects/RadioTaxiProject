

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.Row;

/**
 * Servlet implementation class AdminPortalServlet
 */
@WebServlet("/AdminPortal")
public class AdminPortalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPortalServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			AdminPortal adminPortal = new AdminPortal();
			List<Row> results = adminPortal.getUnregisteredDrivers();
	
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/unregisteredDrivers.jsp");
			rd.include(request, response);
			for (Row r : results) {
				out.println("<br /><pre>"
						+ " <strong>Username:</strong> " + r.getString("username")
						+ " <strong>Car No:</strong> " + r.getString("car_no")
						+ " <strong>First Name:</strong> " + r.getString("first_name")
						+ " <strong>Last Name:</strong> " + r.getString("last_name")
						+ " <strong>License No:</strong> " + r.getString("license_no") 
						+ " <strong>Mobile No:</strong> " + r.getString("mobile_no")
						+ " 	<a href = \"/RadioTaxiProject-Release/AuthorizeDriver?username=" 
											// change this to avoid using absolute path
						+ r.getString("username") + "\">Approve</a>"
						+ "</pre>");
			}
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
