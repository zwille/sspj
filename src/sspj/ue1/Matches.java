
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
public class Matches implements DBSerializer{
	public static final String SQL_INSERT = "INSERT INTO Match  ("
		+ "match_id, "
		+ "match_timeZoneId, "
		+ "match_dateTime, "
		+ "match_dateTimeUTC, "
		+ "match_lastUpdate, "
		+ "match_isFinished, "
		+ "match_teamId1, "
		+ "match_teamId2)  VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String SQL_CREATE = 
	"CREATE TABLE Match ("
		+ "match_id INTEGER PRIMARY KEY, "
		+ "match_timeZoneId TEXT NOT NULL,"
		+ "match_dateTime VARCHAR(24) NOT NULL,"
		+ "match_dateTimeUTC VARCHAR(24) NOT NULL,"
		+ "match_lastUpdate VARCHAR(24) NOT NULL,"
		+ "match_isFinished BOOLEAN NOT NULL,"
		+ "match_teamId1 INTEGER REFERENCES Team (team_id),"
		+ "match_teamId2 INTEGER REFERENCES Team (team_id));"
		;

	private final Teams teams;
	
	private final Map<Integer, Match> matches;
	
	public Matches(Teams teams) {
		this.teams = teams;
		this.matches = new HashMap<>();
	}
	Match createMatch(
		int id, 
		String dateTime,
		String timeZoneId, 
		String dateTimeUTC,
		int team1Id,
		int team2Id, 
		String lastUpdate,
		boolean isFinished) {
		Team team1 = teams.get(team1Id);
		Team team2 = teams.get(team2Id);
		if(matches.containsKey(id)) {
			throw new IllegalArgumentException("dublicate match id: "+id);
		}
		Match rc = new Match(id, dateTime, dateTimeUTC, lastUpdate, timeZoneId, isFinished, team1, team2);
		matches.put(id, rc);
		return rc;
	}

	@Override
	public String toString() {
		return "Matches{" + matches + '}';
	}

	@Override
	public void write(Connection connection) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT);
		
		for (Match match : matches.values()) {
			System.out.println("insert match = " + match);
			pstmt.setInt(1, match.getId());
			pstmt.setString(2, match.getTimeZoneId());
			pstmt.setString(3, match.getDateTime());
			pstmt.setString(4, match.getDateTimeUTC());
			pstmt.setString(5, match.getLastUpdate());
			pstmt.setBoolean(6, match.isFinished());
			pstmt.setInt(7, match.getTeam1().getId());
			pstmt.setInt(8, match.getTeam2().getId());
			pstmt.addBatch();
		}
		pstmt.executeBatch();
	}
	
}
