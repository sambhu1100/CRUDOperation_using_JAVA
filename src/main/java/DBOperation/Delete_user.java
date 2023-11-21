package DBOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Delete_user {
	private Connection con;
	public Delete_user(Connection con){
		this.con=con;
	}
	
	public boolean delete_user(int user_id) {
		boolean v=false;
		try {
			PreparedStatement psmt=this.con.prepareStatement("delete from tbluser where id="+user_id);
			int result=psmt.executeUpdate();
			if(result !=0) {
				v=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}
}
