package hiscore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Credit to https://code.google.com/archive/p/libgdx-users/wikis/SQLite.wiki
 * @author Johan Svennungsson
 */
public class DesktopDatabaseResolver implements DatabaseResolver {

	@Override
	public Connection getConnection() {
		String url = "jdbc:sqlite:data/IllegalAliens.sqlite";
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
