

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
@WebServlet("/ConfirmMatch")
public class ConfirmMatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmMatchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		int bookingId = Integer.parseInt(request.getParameter("booking_id"));
		HttpSession session = request.getSession(false);
		String driver = (String) session.getAttribute("user");
		
		ConfirmMatch match = new ConfirmMatch();
		if(!match.confirmMatch(bookingId, driver)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/rideQueue.jsp");
			out.println("<p align=\"center\"><font color=red>No unmatched rides!</font></p>");
			rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("POST served at: ").append(request.getContextPath());
	}

}
