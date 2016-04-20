package in.ac.bits_pilani.radiotaxi.roles;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ac.bits_pilani.radiotaxi.db.AccessDB;

@WebServlet("/updateprofile")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {

			PrintWriter out = response.getWriter();
			request.getRequestDispatcher("html/html-top-common.html").include(request, response);

			if(session.getAttribute("type").toString().compareTo("rider") == 0) {
				String username = (String) session.getAttribute("user");
				String new_password = request.getParameter("new_password");
				try {
					AccessDB.updateRiderDetails(username, new_password);
				} catch (Exception e) {
						request.getRequestDispatcher("html/error.html").include(request, response);
						out.println("Database error");
				}
			}
			else if(session.getAttribute("type").toString().compareTo("driver") == 0) {
				String username = (String) session.getAttribute("user");
				String new_password=request.getParameter("new_password");
				try {
					AccessDB.updateDriverDetails(username, new_password);
				} catch (Exception e) {
						request.getRequestDispatcher("html/error.html").include(request, response);
						out.println("Database error");
				}
			}
			else if(session.getAttribute("type").toString().compareTo("admin") == 0) {
				// TODO
			}

			request.getRequestDispatcher("html/html-bottom-common.html").include(request, response);
		}
		else {
			response.sendRedirect("index.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
