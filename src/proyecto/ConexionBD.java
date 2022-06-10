package proyecto;

import java.io.*;
import java.sql.*;

public class ConexionBD {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

	String db = "clinica";
	String login = "root";
	String password = "";
	String url = "jdbc:mysql://127.0.0.1/" + db;
	String driver = "com.mysql.cj.jdbc.Driver";

	Class.forName(driver);

	Connection conexion = DriverManager.getConnection(url, login, password);

	System.out.println("Conexion a la base de datos '" + db + "' establecida");

	conexion.close();

    }

}
