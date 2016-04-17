package in.ac.bits_pilani.radiotaxi.roles.driver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ConfirmMatchServlet
 */
@WebServlet("/confirmmatch")
public class ConfirmMatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConfirmMatchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if(session.getAttribute("type") != null) {
			PrintWriter out = response.getWriter();

			if(session.getAttribute("type").toString().compareTo("driver") != 0) {
				request.getRequestDispatcher("html/error.html").include(request, response);
				out.println("Access denied");
			} else {

				int bookingId = Integer.parseInt(request.getParameter("booking_id"));
				String driver = (String) session.getAttribute("user");

				ConfirmMatch match = new ConfirmMatch();
				boolean confirmed = false;
				try {
					confirmed = match.confirmMatch(bookingId, driver);
				} catch(Exception e) {
					request.getRequestDispatcher("html/error.html").include(request, response);
					out.println("Database error");
				}

				request.getRequestDispatcher("html/html-top-common.html").include(request, response);
				if(!confirmed) {
					out.println("<p align=\"center\"><font color=red>No unmatched rides!</font></p>");
				}
				else {
					out.println("<p align=\"center\"><font color=green>Successfully allotted ride!</font></p>");
				}
				request.getRequestDispatcher("displayqueuedrides").include(request, response);
				request.getRequestDispatcher("html/html-bottom-common.html").include(request, response);
			}
		} else {
			response.sendRedirect("index.html");
		}
	}
}
