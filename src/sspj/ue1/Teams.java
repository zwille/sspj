package sspj.ue1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ccwelich
 */
public class Teams implements DBSerializer {

	public static final String SQL_INSERT = "INSERT INTO Team  (team_id, team_name, team_iconUrl, team_stadion)  VALUES (?, ?, ?, ?);";
	public static final String SQL_CREATE =
		"CREATE TABLE Team ("
		+ "team_id INTEGER PRIMARY KEY, "
		+ "team_name TEXT NOT NULL,"
		+ "team_iconUrl TEXT NOT NULL, "
		+ "team_stadion TEXT NOT NULL);";
	
	private Map<Integer, Team> teams;

	public Teams() {
		this.teams = new HashMap<>();
	}

	public Team createTeam(int id, String name, String iconUrl, String stadion) {
		if (teams.containsKey(id)) {
			throw new IllegalArgumentException("dublicate key: " + id);
		}
		Team team = new Team(id, name, iconUrl, stadion);
		teams.put(id, team);
		return team;
	}

	@Override
	public String toString() {
		return "Teams{" + teams + '}';
	}

	public Team get(int id) {
		return teams.get(id);
	}

	@Override
	public void write(Connection connection) throws SQLException {
		String sql = SQL_INSERT;
		PreparedStatement pstmt = connection.prepareStatement(sql);
		for (Team team : teams.values()) {
			System.out.println("insert team = " + team);
			pstmt.setInt(1, team.getId());
			pstmt.setString(2, team.getName());
			pstmt.setString(3, team.getIconUrl());
			pstmt.setString(4, team.getStadion());
			pstmt.addBatch();
		}
		pstmt.executeBatch();
	}
}
