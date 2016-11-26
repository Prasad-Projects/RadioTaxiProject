package in.ac.bits_pilani.radiotaxi.roles.driver;

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

@WebServlet("/completedbookings")
public class DisplayCompletedBookingsServlet extends HttpServlet {

  
    private static final long serialVersionUID = 1L;
    
    
    public DisplayCompletedBookingsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null) {
            PrintWriter out = response.getWriter();
            Driver driver = (Driver) session.getAttribute("user");
            List<HashMap<String, String>> history = null;
            try {
                history = driver.getTravelHistory();
            } catch (Exception e) {
                request.getRequestDispatcher("html/error.html").include(request, response);
                e.printStackTrace(out);
                out.println("Database error");
            }
            if(history != null) {
                request.getRequestDispatcher("html/html-top-common.html").include(request, response);
                request.getRequestDispatcher("html/driverbookings-layout-1.html").include(request, response);
                out.println("<div class = 'row'>");
                out.println("<h5> Your completed rides: </h5> ");
                out.println("</div>");
                out.println("<table class = 'striped'>");
                out.println("<tr>");
                out.println("<th> BookingId </th>");
                out.println("<th> Origin </th>");
                out.println("<th> Destination </th>");
                out.println("<th> Rider </th>");
                out.println("<th> Fare </th>");
                out.println("<th> Time </th>");
                out.println("</tr>");
                for(HashMap<String, String> map : history) {
                    out.println("<tr>");
                    out.println("<td>" + map.get("bookingId") + "</td>");
                    out.println("<td>" + map.get("origin") + "</td>");
                    out.println("<td>" + map.get("destination") + "</td>");
                    out.println("<td>" + map.get("rider") + "</td>");
                    out.println("<td>" + map.get("fare") + "</td>");
                    out.println("<td>" + map.get("time") + "</td>");
                    out.println("</tr>");
                }
            out.println("</table> <br/>");
            request.getRequestDispatcher("html/driverbookings-layout-2.html").include(request, response);
            }
            else {
                out.println("<p> You have no (completed) previous bookings! </p>"); 
            }
            request.getRequestDispatcher("html/html-bottom-common.html").include(request, response);
    
        }
        else {
            response.sendRedirect("index.html");
        }
        

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

}
