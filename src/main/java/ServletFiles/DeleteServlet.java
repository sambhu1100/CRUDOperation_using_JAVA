package ServletFiles;

import java.io.IOException;
import java.io.PrintWriter;

import DBOperation.Delete_user;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String id=request.getParameter("id");
		int uid=Integer.parseInt(id);
		Delete_user du= new Delete_user(DBConnection.DBConnection.getCon());
		boolean get=du.delete_user(uid);
		HttpSession session=request.getSession();
		if(get) {
			session.setAttribute("msg", "delete successfully");
		}else {
			session.setAttribute("msg", "Delete fail");
		}
		response.sendRedirect("index.jsp");
	}

	
}
