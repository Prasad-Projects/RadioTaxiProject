

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthorizeDriverServlet
 */
@WebServlet("/authorisedriver")
public class AuthorizeDriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AuthorizeDriverServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		String username = (String) request.getParameter("username");
		
		if(session != null) {
			AuthorizeDriver auth = new AuthorizeDriver();
			
			if(!auth.authorise(username)) {
				out.println("<p align=\"center\"><font color=red>No unregistered drivers!</font></p>");
				request.getRequestDispatcher("WEB-INF/unregisteredDrivers.jsp").include(request, response);
			}
			
		}
	}
}
