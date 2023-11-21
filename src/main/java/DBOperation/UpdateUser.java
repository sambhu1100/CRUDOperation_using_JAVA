package DBOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUser {
	private Connection con;
	 public UpdateUser(Connection con){
		this.con=con;
	}
	 
	public int edituser(int id,String name,String username,String email,String phone) {
		int x=0;
		try {
			PreparedStatement psmt=con.prepareStatement("UPDATE tbluser SET name='"+name+"',username='"+username+"',email='"+email+"',phone='"+ phone+"' where id=" + id+" ");
			x=psmt.executeUpdate();
			x=1;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return x;
	}

}
