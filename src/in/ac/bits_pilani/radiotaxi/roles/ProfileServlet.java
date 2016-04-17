package in.ac.bits_pilani.radiotaxi.roles;

import java.io.IOException;
import java.io.PrintWriter;

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
       
    public ProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {

			PrintWriter out = response.getWriter();
			request.getRequestDispatcher("html/html-top-common.html").include(request, response);

			if(session.getAttribute("type").toString().compareTo("rider") == 0) {
				request.getRequestDispatcher("html/riderprofile-layout-1.html").include(request, response);
				String user = (String) session.getAttribute("user");
				out.println("<h1 class=\"teal lighten-2 white-text\" style=\"text-align:center;font-size:200%;font-family:Calibri;\">Hi! "+user+"</h1>");
				request.getRequestDispatcher("html/riderprofile-layout-2.html").include(request, response);
			}
			else if(session.getAttribute("type").toString().compareTo("driver") == 0) {
				request.getRequestDispatcher("html/driverprofile-layout-1.html").include(request, response);
				String user = (String) session.getAttribute("user");
				out.println("<h1 class=\"teal lighten-2 white-text\" style=\"text-align:center;font-size:200%;font-family:Calibri;\">Hi! "+user+"</h1>");
				request.getRequestDispatcher("html/driverprofile-layout-2.html").include(request, response);
			}
			else if(session.getAttribute("type").toString().compareTo("admin") == 0) {
				request.getRequestDispatcher("html/adminprofile-layout-1.html").include(request, response);
				String user = (String) session.getAttribute("user");
				out.println("<h1 class=\"teal lighten-2 white-text\" style=\"text-align:center;font-size:200%;font-family:Calibri;\">Hi! "+user+"</h1>");
				request.getRequestDispatcher("html/adminprofile-layout-2.html").include(request, response);
			}

			request.getRequestDispatcher("html/html-bottom-common.html").include(request, response);
		}
		else {
			response.sendRedirect("index.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
