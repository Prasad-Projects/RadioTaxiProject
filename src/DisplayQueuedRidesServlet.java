
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Row;

/**
 * Servlet implementation class displayQueuedRides
 */
@WebServlet("/displayQueuedRides")
public class DisplayQueuedRidesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayQueuedRidesServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		DisplayQueuedRides rides = new DisplayQueuedRides();
		List<Row> results = rides.getRides();

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/rideQueue.jsp");
		rd.include(request, response);
		for (Row r : results) {
			out.println("<br /><pre><a href = \"/RadioTaxiProject-Release/ConfirmMatch?booking_id=" // change this to avoid using absolute path
					+ r.getInt("booking_id") + "\">" + // TODO
					r.getInt("booking_id") + "</a>" + " <strong>Rider:</strong> " + r.getString("rider") + " <strong>Origin:</strong> " + r.getString("origin") + " <strong>Destination:</strong> "
					+ r.getString("destination") + " <strong>Time:</strong> " + r.getTimestamp("time") + "</pre>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("POST served at: ").append(request.getContextPath());
	}
}
