

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
 * Servlet implementation class Book
 */
@WebServlet("/Book")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);
		if(session != null) {
			String rider = (String) session.getAttribute("user");
			String origin = request.getParameter("origin");
			String dest = request.getParameter("dest");
			
			Booking b = new Booking();
			String query = b.bookTrip(rider, origin, dest); // add driver later when confirmed
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/riderBookings.jsp");
			out.println("<p align=\"center\"><font color=green>Booking successful!</font></p><br />");
			rd.include(request, response);
		}
		else {
			response.sendRedirect("index.html");
		}
	}
}
