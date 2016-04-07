

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			if(session.getAttribute("type").toString().compareTo("rider") == 0) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/riderProfile.jsp");
				rd.include(request, response);				
			}
			else if(session.getAttribute("type").toString().compareTo("driver") == 0) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/driverProfile.jsp");
				rd.include(request, response);
			}
			else if(session.getAttribute("type").toString().compareTo("admin") == 0) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminportal");
				rd.forward(request, response);
			}
		}
		else {
			response.sendRedirect("/index.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
