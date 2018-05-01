package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection createConnection() throws SQLException, ClassNotFoundException {
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://sql.rafalkaczmar.nazwa.pl:3306/rafalkaczmar_Lekarz";
		Class.forName(myDriver);
		return DriverManager.getConnection(myUrl, "rafalkaczmar_Lekarz", "Mango1996");
	}

}
