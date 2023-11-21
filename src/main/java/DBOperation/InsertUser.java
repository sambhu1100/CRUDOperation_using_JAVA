package DBOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Entities.User;

public class InsertUser {
	private Connection con;

    public InsertUser(Connection con) {
        this.con = con;
    }
	public boolean saveUser(User user) {
		
        boolean f = false;
        try {
            //user -->database

            String query = "insert into tbluser(name,username,email,phone) values (?,?,?,?)";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhone());
           
            pstmt.executeUpdate();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
	}
}
