package radiotaxi.roles.rider;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			String rider = (String) session.getAttribute("user");
			String origin = request.getParameter("origin");
			String dest = request.getParameter("dest");
			String distance= request.getParameter("distance");
			String duration = request.getParameter("time");
			System.out.println("\n\n\n\n"+duration+"\n\n\n\n");
			System.out.println("\n\n\n\n"+distance+"\n\n\n\n");
			
			int d=10,t=8;
			
			String s = duration;
		    Pattern p = Pattern.compile("([0-9]+)");
		    Matcher m = p.matcher(s);
		    if (m.find()) {
		         d=Integer.parseInt(m.group());
		    }
			
		    s = distance;
		    p = Pattern.compile("([0-9]+)");
		    m = p.matcher(s);
		    if (m.find()) {
		    	t=Integer.parseInt(m.group());
		    }
		    
			Booking b = new Booking();
			int fare = d*7+t;
			System.out.println(fare);
			try {
				b.bookTrip(rider, origin, dest,fare); // add driver later when confirmed
				request.setAttribute("success", true);
				request.getRequestDispatcher("WEB-INF/riderBookings.jsp").forward(request, response);
			} catch(Exception e) {
				//request.setAttribute("errMsg", e.getMessage());
				request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			}
		}
		else {
			response.sendRedirect("index.html");
		}
	}
}
