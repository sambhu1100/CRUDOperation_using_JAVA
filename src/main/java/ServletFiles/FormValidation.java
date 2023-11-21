package ServletFiles;

import java.io.IOException;
import java.io.PrintWriter;

import DBOperation.InsertUser;
import Entities.Message;
import Entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class FormValidation
 */
public class FormValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormValidation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 PrintWriter out=response.getWriter();
	 String name = request.getParameter("name");
     String username = request.getParameter("username");
     String email = request.getParameter("email");
     String phone = request.getParameter("phone");
     
      User user=new User(name,username,email,phone);
      
      InsertUser i=new InsertUser(DBConnection.DBConnection.getCon());   
       HttpSession session=request.getSession();
      //Message mssg=new Message();
       if(i.saveUser(user)) {
    	   session.setAttribute("Registered Successfully","msg");
    	   
    	}else {
    		session.setAttribute("Registertion Failed","msg");
       }
       response.sendRedirect("index.jsp");
    }

}
