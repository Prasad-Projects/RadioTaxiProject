package in.ac.bits_pilani.radiotaxi.roles.rider;

import in.ac.bits_pilani.radiotaxi.CabType;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Book
 */
@WebServlet("/book")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/book.html").forward(request, response);

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if(session != null) {
			PrintWriter out = response.getWriter();

			Rider rider = (Rider) session.getAttribute("user");
			String origin = request.getParameter("origin");
			String dest = request.getParameter("dest");
			String distance = request.getParameter("distance");
			String duration = request.getParameter("time");

			float[] originCoord = {Float.parseFloat(request.getParameter("orig_lat")),
					Float.parseFloat(request.getParameter("orig_lng"))};
			float[] destCoord = {Float.parseFloat(request.getParameter("dest_lat")),
					Float.parseFloat(request.getParameter("dest_lng"))};
			
			CabType cab;
			
			String cabtype = request.getParameter("cabtype");
			switch(cabtype) {
			case "regular": cab = CabType.Regular; break;
			case "extended": cab = CabType.Extended; break;
			case "double": cab = CabType.Double; break;
			default: cab = CabType.Regular;
			}
			
			Booking b = new Booking();
			//request.getRequestDispatcher("html/html-top-common.html").include(request, response);
			//request.getRequestDispatcher("html/riderbookings-layout-1.html").include(request, response);
			try {
				b.bookTrip(rider.getUsername(), origin, dest, cab, distance, duration, originCoord, destCoord); 
				// add driver later when confirmed
			} catch(Exception e) {
				request.getRequestDispatcher("html/error.html").include(request, response);
				out.println("Database error");
			}
			//request.getRequestDispatcher("html/html-bottom-common.html").include(request, response);
			request.getRequestDispatcher("/bookings").forward(request, response);
		}
		else {
			response.sendRedirect("index.html");
		}
	}
}
