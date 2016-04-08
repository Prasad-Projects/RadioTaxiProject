

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
			if(session.getAttribute("type").toString().compareTo("driver") != 0) {
				response.sendRedirect("error.jsp");
			} else {

				PrintWriter out = response.getWriter();

				int bookingId = Integer.parseInt(request.getParameter("booking_id"));
				String driver = (String) session.getAttribute("user");

				ConfirmMatch match = new ConfirmMatch();
				if(!match.confirmMatch(bookingId, driver)) {
					out.println("<p align=\"center\"><font color=red>No unmatched rides!</font></p>");
					request.getRequestDispatcher("WEB-INF/rideQueue.jsp").include(request, response);
				}
				else {
					out.println("<p align=\"center\"><font color=green>Successfully allotted ride!</font></p>");
					request.getRequestDispatcher("WEB-INF/rideQueue.jsp").include(request, response);
				}
			}
		} else {
			response.sendRedirect("index.html");
		}
	}
}
