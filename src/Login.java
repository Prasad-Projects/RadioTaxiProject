
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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Login() {
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String user_type = request.getParameter("Rbutton");
		
		/*
		System.out.println("connecting to database...");
		Register_db r = new Register_db();
		System.out.println("connected to database!");
		r.use_keyspace();
		System.out.println("database keyspace is use...");
		*/
		
		//if(r.login(username, password, user_type)){
		if (username.compareTo("user1") == 0 && password.compareTo("pass") == 0) { // test
			
			System.out.print("Authentication successful for user " + username);

            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(1*60);
            Cookie userName = new Cookie("user", username);
            userName.setMaxAge(1*60);
            response.addCookie(userName);
            response.sendRedirect("profile.jsp");
			
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("GET request received");
	}
}
