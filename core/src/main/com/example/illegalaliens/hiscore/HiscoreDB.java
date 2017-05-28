package com.example.illegalaliens.hiscore;

import com.badlogic.gdx.utils.IntArray;

import java.sql.*;

/**
 * Class for adding score to Hiscore table
 * @author Johan Svennungsson
 */
public class HiscoreDB {

	private DatabaseResolver databaseResolver;
	private Connection connection;

	public HiscoreDB(DatabaseResolver databaseResolver) {
		this.databaseResolver = databaseResolver;
	}

	public void create() {
		try {
			connection = databaseResolver.getConnection();
//			ResultSet resultSet = statement.executeQuery("select * from hiscore");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addScore(int score) {
		String query = "INSERT INTO hiscore(score) VALUES (?)";

		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setInt(1, score);

			st.executeUpdate();
			st.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public IntArray getScores() {
		IntArray scores = new IntArray();

		String query = "SELECT score FROM hiscore";

		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				scores.add(rs.getInt("score"));
			}

			st.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return scores;
	}

	public void clearHiscores() {
		String query = "DELETE FROM hiscore";

		try {
			PreparedStatement st = connection.prepareStatement(query);

			st.executeUpdate();
			st.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
