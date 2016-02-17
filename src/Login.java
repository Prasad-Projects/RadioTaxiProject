
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		PrintWriter out=response.getWriter();  
//		request.getRequestDispatcher("index.html").include(request, response);  
	//	System.out.println("ggg");  
		String username=request.getParameter("username");  
		String password=request.getParameter("password");  
		String btn=request.getParameter("Rbutton");
		System.out.println("rajat is");

		Register_db r = new Register_db();
	//	System.out.println("rajat");
		r.use_keyspace();
	//	System.out.println("rajat is gone");
		if(r.login(username, password, btn)){
		System.out.print("Welcome, "+username);  
		HttpSession session=request.getSession();  
		session.setAttribute("name",username);  
		request.getRequestDispatcher("profile.html").include(request, response);  
		}  
		else{
		    System.out.print("Sorry, username or password error!");
		    System.out.println(btn);
		    request.getRequestDispatcher("index.html").include(request, response);  
		}  
		out.close();  
    }     	

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	PrintWriter out=response.getWriter();
    	out.println("get");
    }
}
