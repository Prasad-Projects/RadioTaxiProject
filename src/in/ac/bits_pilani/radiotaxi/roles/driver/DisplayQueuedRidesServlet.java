package in.ac.bits_pilani.radiotaxi.roles.driver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.Row;

/**
 * Displays rides which have not been allocated a driver
 */
@WebServlet("/displayqueuedrides")
public class DisplayQueuedRidesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisplayQueuedRidesServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if(session != null) {
			PrintWriter out = response.getWriter();
			if(session.getAttribute("type").toString().compareTo("driver") != 0) {
				request.getRequestDispatcher("html/error.html").include(request, response);
				out.println("Access denied");
			} else {
				Driver driver = (Driver) session.getAttribute("user");
				List<Row> results = null;
				try {
					results = driver.getRides();
				} catch(Exception e) {
					request.getRequestDispatcher("html/error.html").include(request, response);
					out.println("Database error");
				}
				request.getRequestDispatcher("html/html-top-common.html").include(request, response);
				request.getRequestDispatcher("html/ridequeue-layout-1.html").include(request, response);
				if(results != null) {
					out.println("<div class=\"row\">");
					out.println("<div class=\"col s2\">Id:</div><div class=\"col s2\">Rider:</div><div class=\"col s2\">Origin:</div><div class=\"col s2\">Destination:</div><div class=\"col s2\">Time:</div>");
					out.println("</div>");
					for (Row r : results) {
						out.println("<div class=\"col s2\">" + r.getInt("booking_id") + "</div><div class=\"col s2\">" +  "</div><div class=\"col s2\">" + r.getString("rider") + "</div><div class=\"col s2\">" + r.getString("origin") + "</div><div class=\"col s2\">"+ r.getString("destination") + "</div><div class=\"col s2\">" + r.getTimestamp("time")+"</div>"
						+ "<form action=\"confirmmatch\" method=\"post\">"
							+ "<button type=\"submit\" value=\"Get Ride\">Get Ride</button>"
							+ "<input type=hidden name=\"booking_id\" value=\"" + r.getInt("booking_id") + "\">"
						+ "</form>");
					}
				} else {
					out.println("No rides to display!");
				}
				request.getRequestDispatcher("html/ridequeue-layout-2.html").include(request, response);
				request.getRequestDispatcher("html/html-bottom-common.html").include(request, response);
			}
		} else {
			response.sendRedirect("index.html");
		}
	}
}
