package databackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnect {
	Connection conn = null;
	public Connection dbconn(){
	try {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="hr";
			String pwd="hr";
			Properties connectionProps=new Properties();
			connectionProps.put("user",user);
			connectionProps.put("password",pwd);
			conn=DriverManager.getConnection(url,connectionProps);
			System.out.println();
			System.out.println("Database connected");
		}
		catch (Exception dbException) {
		System.out.println(dbException);
		System.out.println("Database Connection Failed");
	}
	return conn;
}
}