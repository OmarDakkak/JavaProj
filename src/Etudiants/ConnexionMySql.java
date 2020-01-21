package Etudiants;

import javax.swing.*;
import java.sql.*;

public class ConnexionMySql {
	Connection conn = null;
	
	public static Connection ConnectionDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/GESTIONETUD", "omar", "omar");
			return conn;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
