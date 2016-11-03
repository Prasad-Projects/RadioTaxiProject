package in.ac.bits_pilani.radiotaxi.roles.admin;

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
 * Performs driver authentication
 */
@WebServlet("/approvedriver")
public class ApproveDriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ApproveDriverServlet() {
		super();
	}

	/**
	 * Serves a list of all unverified drivers to the admin
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if(session != null) {

			PrintWriter out = response.getWriter();
			request.getRequestDispatcher("html/html-top-common.html").include(request, response);
			request.getRequestDispatcher("html/unregistereddrivers-layout-1.html").include(request, response);

			Admin admin = (Admin) session.getAttribute("user");
			List<HashMap<String, String>> unregDrivers = null;
			try {
				unregDrivers = admin.getUnregisteredDrivers();
			} catch(Exception e) {
				request.getRequestDispatcher("html/error.html").include(request, response);
				out.println("Database error");
			}
			if(unregDrivers != null) {
				    out.println("<table class = 'striped'>");
                    out.println("<tr>");
                    out.println("<th> Username </th>");
                    out.println("<th> Car No </th>");
                    out.println("<th> First name </th>");
                    out.println("<th> Last name </th>");
                    out.println("<th> License No </th>");
                    out.println("<th> Mobile No </th>");
                    out.println("<th />");
                    out.println("</tr>");
                    for(HashMap<String, String> map : unregDrivers) {
                        out.println("<tr>");
                        out.println("<td>" + map.get("username") + "</td>");
                        out.println("<td>" + map.get("carNo") + "</td>");
                        out.println("<td>" + map.get("firstName") + "</td>");
                        out.println("<td>" + map.get("lastName") + "</td>");
                        out.println("<td>" + map.get("licenseNo") + "</td>");
                        out.println("<td>" + map.get("mobileNo") + "</td>");
                        out.println("<td>" + "<form action=\"approvedriver\" "
                                + "method=\"post\">"
                                + "<button type=\"submit\" value=\"Approve\">"
                                + "Approve</button>"
                                + "<input type=hidden name=\"username\" value=\"" + 
                                map.get("username") + "\">"
                                + "</form>" + "</td>");
				}
			}
			request.getRequestDispatcher("html/unregistereddrivers-layout-2.html").include(request, response);
			request.getRequestDispatcher("html/html-bottom-common.html").include(request, response);

		}

	}

	/**
	 * Performs approval of driver by the admin. Driver's username is extracted from
	 * the request
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String username = (String) request.getParameter("username");

		if(session != null) {

			PrintWriter out = response.getWriter();
			Admin admin = (Admin) session.getAttribute("user");
			boolean approved = false;

			try {
				approved = admin.approveDriver(username);
			} catch(Exception e) {
				request.getRequestDispatcher("html/error.html").include(request, response);
				out.println("Database error");
			}

			if(!approved) {
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
