

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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getParameter("username") == null) {
			response.sendRedirect("index.html");
		}
		else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String userType = request.getParameter("Rbutton");
			
			Login login = new Login();
			
			if(login.doLogin(username, password, userType)){
			//if (username.compareTo("user1") == 0 && password.compareTo("pass") == 0) { // test

				//System.out.print("Authentication successful for user " + username);

	            HttpSession session = request.getSession();
	            session.setAttribute("user", username);
	            session.setAttribute("type", userType);
	            //setting session to expiry in 30 mins
	            session.setMaxInactiveInterval(1800);
	            //Cookie userName = new Cookie("user", username);
	            //userName.setMaxAge(5*60);
	            //response.addCookie(userName);

	            request.getRequestDispatcher("/profile").forward(request, response);

			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
				out.println("<p align=\"center\"><font color=red>Either user name or password is wrong.</font></p>");
				rd.include(request, response);
			}
		}
	}
}