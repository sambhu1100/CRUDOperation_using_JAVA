package DBOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entities.User;

public class GetAllUsers {

	private Connection con;

    public GetAllUsers(Connection con) {
        this.con = con;
    }
	public List<User> getUser(){
		
        List<User> listofuser=new ArrayList<User>();
        try {
            //user -->database

            String query = "select * from  tbluser";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()) {
            User user=new User();
            
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            listofuser.add(user);
            	
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listofuser;

    }
	
}
