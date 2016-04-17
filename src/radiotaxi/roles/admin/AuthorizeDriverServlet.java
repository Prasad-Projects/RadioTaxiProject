package radiotaxi.roles.admin;

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
 * Servlet implementation class AuthorizeDriverServlet
 */
@WebServlet("/authorisedriver")
public class AuthorizeDriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AuthorizeDriverServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if(session != null) {

			PrintWriter out = response.getWriter();
			request.getRequestDispatcher("html/html-top-common.html").include(request, response);
			request.getRequestDispatcher("html/unregistereddrivers-layout-1.html").include(request, response);

			AuthorizeDriver getDrivers = new AuthorizeDriver();
			List<Row> results = null;
			try {
				results = getDrivers.getUnregisteredDrivers();
			} catch(Exception e) {
				request.getRequestDispatcher("html/error.html").include(request, response);
				out.println("Database error");
			}
			if(results != null) {
				for (Row r : results) {
					out.println("<br /><pre>"
						+ " <strong>Username:</strong> " + r.getString("username")
						+ " <strong>Car No:</strong> " + r.getString("car_no")
						+ " <strong>First Name:</strong> " + r.getString("first_name")
						+ " <strong>Last Name:</strong> " + r.getString("last_name")
						+ " <strong>License No:</strong> " + r.getString("license_no")
						+ " <strong>Mobile No:</strong> " + r.getString("mobile_no")
						+ "<form action=\"authorisedriver\" method=\"post\">"
							+ "<button type=\"submit\" value=\"Approve\">Approve</button>"
							+ "<input type=hidden name=\"username\" value=\"" + r.getString("username") + "\">"
							+ "</form>"
						+ "</pre>");
				}
			}
			request.getRequestDispatcher("html/unregistereddrivers-layout-2.html").include(request, response);
			request.getRequestDispatcher("html/html-bottom-common.html").include(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String username = (String) request.getParameter("username");

		if(session != null) {

			PrintWriter out = response.getWriter();
			AuthorizeDriver auth = new AuthorizeDriver();
			boolean authorised = false;

			try {
				authorised = auth.authorise(username);
			} catch(Exception e) {
				request.getRequestDispatcher("html/error.html").include(request, response);
				out.println("Database error");
			}

			if(!authorised) {
				out.println("<p align=\"center\"><font color=red>No unregistered drivers!</font></p>");
				request.getRequestDispatcher("profile").include(request, response);
			}
			else {
				out.println("<p align=\"center\"><font color=green>Driver successfully approved!</font></p>");
				request.getRequestDispatcher("profile").include(request, response);
			}
		}
	}
}
