package hiscore;

import java.sql.Connection;

/**
 * Credit to https://code.google.com/archive/p/libgdx-users/wikis/SQLite.wiki
 * @author Johan Svennungsson
 */
public interface DatabaseResolver {
	Connection getConnection();
}
