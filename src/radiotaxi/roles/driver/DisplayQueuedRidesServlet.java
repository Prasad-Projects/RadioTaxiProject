package radiotaxi.roles.driver;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.Row;

/**
 * Servlet implementation class displayQueuedRides
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
			if(session.getAttribute("type").toString().compareTo("driver") != 0) {
				response.sendRedirect("WEB-INF/error.jsp");
			} else {
				DisplayQueuedRides rides = new DisplayQueuedRides();

				try {
					List<Row> results = rides.getRides();
					request.setAttribute("results", results);
					request.getRequestDispatcher("WEB-INF/rideQueue.jsp").forward(request, response);
				} catch(Exception e) {
					//request.setAttribute("errMsg", e.getMessage());
					request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
				}
			}
		} else {
			response.sendRedirect("index.html");
		}
	}
}
