package ServletFiles;

import java.io.IOException;
import java.io.PrintWriter;

import DBOperation.UpdateUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class EditServlet
 */
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		out.println("find successfully");
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		out.println("done");
		
		String uid=request.getParameter("uid");
		int  id=Integer.parseInt(uid);
		
		String name=request.getParameter("name");
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		
		
		
		UpdateUser updateuser=new UpdateUser(DBConnection.DBConnection.getCon());
	    int x=	updateuser.edituser(id, name, username, email, phone);
	   HttpSession session=request.getSession();
		if(x != 0) {
			session.setAttribute("msg", "Update Successfully");
		}else {
			session.setAttribute("msg", "Update  fail");
		}
		response.sendRedirect("index.jsp");
	}

}
