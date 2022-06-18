package resources;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCConnection extends TestBase {
	public static Connection con;
	public static ResultSet rs;

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		prop = TestBase.loadPropertiesFile();
		String host = prop.getProperty("dbHost");
		String port = prop.getProperty("dbPort");
		String dbName = prop.getProperty("dbName");
		String userName = prop.getProperty("dbUserName");
		String pwd = prop.getProperty("dbUserPwd");

		con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName, userName, pwd);
		Statement s = con.createStatement();
		String sql = "select f.title, f.release_year, a.last_name from film f inner join film_actor fa on fa.film_id = f.film_id and f.release_year <> 2006 inner join actor a on a.actor_id = fa.actor_id and a.first_name = \"RIP\"";
		rs = s.executeQuery(sql);

		while (rs.next()) {
			System.out.println(
					rs.getString("title") + "\t" + rs.getString("release_year") + "\t" + rs.getString("last_name"));
		}

		rs.close();
		con.close();
	}
}