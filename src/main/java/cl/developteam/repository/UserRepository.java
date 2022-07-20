package cl.developteam.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class UserRepository {
	private Connection dbConnection;

	public UserRepository() throws SQLException {
		String url = "jdbc:postgresql://localhost/open-port";
		Properties props = new Properties();
		props.setProperty("user", "postgres");
		props.setProperty("password", "root");
		// props.setProperty("ssl", "true");
		dbConnection = DriverManager.getConnection(url, props);
	}

	public String getUserPasswordByEmail(String email) throws SQLException {
		String sql_command = "SELECT \"password\" FROM public.usuario WHERE email = '" + email + "'";
		Statement statement = dbConnection.createStatement();
		ResultSet result = statement.executeQuery(sql_command);
		if (result.next()) {
			return result.getString("password");
		} else {
			return null;
		}
	}

	public void saveNewUser(String email, String password) throws SQLException {
		String sql_command = "INSERT INTO public.usuario (\"password\",email,created_at,update_at,last_login) VALUES ('" + password + "','" + email + "','2017-03-31 09:30:20.000','2017-03-31 09:30:20.000','2017-03-31 09:30:20.000');";

		System.out.println("Create JDBC statement.");
		Statement statement = dbConnection.createStatement();

		System.out.println("Simple SELECT query: " + sql_command);
		int result = statement.executeUpdate(sql_command);
	}

	public void updateUser(String email, String newPassword) throws SQLException {
		String sql_command = "UPDATE public.usuario SET \"password\"='" + newPassword + "' WHERE email='" + email + "';";

		System.out.println("Create JDBC statement.");
		Statement statement = dbConnection.createStatement();

		System.out.println("Simple SELECT query: " + sql_command);
		int result = statement.executeUpdate(sql_command);
	}

}
