

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
@WebServlet("/adminportal")
public class AdminPortalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPortalServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			AdminPortal adminPortal = new AdminPortal();
			List<Row> results = adminPortal.getUnregisteredDrivers();
	
			request.setAttribute("results", results);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/unregisteredDrivers.jsp");
			rd.forward(request, response);
		}		
	}
}
