package in.ac.bits_pilani.radiotaxi.roles.driver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
				List<HashMap<String, String>> history = null;
				try {
                    history = driver.getRides();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
				request.getRequestDispatcher("html/html-top-common.html").include(request, response);
				request.getRequestDispatcher("html/ridequeue-layout-1.html").include(request, response);
				if(history!= null) {
	                out.println("<table class = 'striped'>");
	                out.println("<tr>");
	                out.println("<th> BookingId </th>");
	                out.println("<th> Origin </th>");
	                out.println("<th> Destination </th>");
	                out.println("<th> Rider </th>");
	                out.println("<th> Time </th>");
	                out.println("<th />");
	                out.println("</tr>");
	                for(HashMap<String, String> map : history) {
	                    out.println("<tr>");
	                    out.println("<td>" + map.get("bookingId") + "</td>");
	                    out.println("<td>" + map.get("origin") + "</td>");
	                    out.println("<td>" + map.get("destination") + "</td>");
	                    out.println("<td>" + map.get("rider") + "</td>");
	                    out.println("<td>" + map.get("time") + "</td>");
	                    out.println("<td>" + "<form action=\"confirmmatch\" method=\"post\">"
	                            + "<button type=\"submit\" value=\"Get Ride\">Get Ride</button>"
	                            + "<input type=hidden name=\"booking_id\" value=\"" + 
	                            Integer.parseInt(map.get("bookingId")) + "\">"
	                        + "</form>" + "</td>");
	                    out.println("</tr>");
	                }
	            out.println("</table>");
					
					
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	    doGet(request, response);
	}
}
