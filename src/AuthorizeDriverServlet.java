

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
@WebServlet("/AuthorizeDriver")
public class AuthorizeDriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorizeDriverServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		String username = (String) request.getParameter("username");
		
		if(session != null) {
			AuthorizeDriver auth = new AuthorizeDriver();
			//out.println(auth.doAuthorize(username));
			
			if(!auth.doAuthorize(username)) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/unregisteredDrivers.jsp");
				out.println("<p align=\"center\"><font color=red>No unregistered drivers!</font></p>");
				rd.include(request, response);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("POST served at: ").append(request.getContextPath());
	}

}
