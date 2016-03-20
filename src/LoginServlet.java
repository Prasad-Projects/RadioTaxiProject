

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String user_type = request.getParameter("Rbutton");
		
		Login login = new Login();
		
		if(login.doLogin(username, password, user_type)){
			HttpSession session = request.getSession();
            session.setAttribute("user", username);
            session.setMaxInactiveInterval(-1);
            if(user_type.compareTo("rider") == 0) {
            	request.getRequestDispatcher("riderProfile.jsp").forward(request, response);
            }
            else if(user_type.compareTo("driver") == 0){
            	request.getRequestDispatcher("driverProfile.jsp").forward(request, response);
            }
            else { // admin
            	response.sendRedirect("/RadioTaxiProject-Release/AdminPortal"); // change this to avoid using absolute path
            }
			
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
			out.println("<p align=\"center\"><font color=red>Either user name or password is wrong.</font></p>");
			rd.include(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("GET request received");
	}
}