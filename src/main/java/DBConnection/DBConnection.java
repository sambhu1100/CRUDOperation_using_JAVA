package DBConnection;

import java.sql.*;

public class DBConnection {
	
	private static Connection con;//connection=null

    public static Connection getCon() {
		try {

            if (con == null) {
                //driver class load
                Class.forName("com.mysql.jdbc.Driver");

                //create a connection..
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crudoperation", "root", "root");
               System.out.println("Connection Successfully done");            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		return con;
	}
    
    
}
