package sspj.ue1;

import java.sql.PreparedStatement;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author ccwelich
 */
public class Match extends Entity {
	private final String dateTime;
	private final String dateTimeUTC;
	private final String lastUpdate;
	private final String timeZoneId;
	private final boolean finished;
	private final Team team1;
	private final Team team2;
	private final Set<Result> results;

	Match(int id, String dateTime, String dateTimeUTC, String lastUpdate,
		String timeZoneId, boolean finished, Team team1, Team team2) {
		super(id);
		this.dateTime = dateTime;
		this.dateTimeUTC = dateTimeUTC;
		this.lastUpdate = lastUpdate;
		this.timeZoneId = timeZoneId;
		this.finished = finished;
		this.team1 = team1;
		this.team2 = team2;
		this.results = new TreeSet<>();
	}

	void addResult(Result result) {
		if(results.contains(result)) {
			throw new IllegalArgumentException("dublicate result: "+result);
		}
		results.add(result);
	}

	@Override
	public String toString() {
		return "Match{" + "id=" + id + ", dateTime=" + dateTime + ", dateTimeUTC=" + dateTimeUTC + ", lastUpdate=" + lastUpdate + ", timeZoneId=" + timeZoneId + ", finished=" + finished + ", team1=" + team1 + ", team2=" + team2 + ", results=" + results + '}';
	}

	
	public void write(PreparedStatement stmt) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public String getDateTime() {
		return dateTime;
	}

	public String getDateTimeUTC() {
		return dateTimeUTC;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public String getTimeZoneId() {
		return timeZoneId;
	}

	public boolean isFinished() {
		return finished;
	}

	public Team getTeam1() {
		return team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public Iterable<Result> getResults() {
		return results;
	}

	
}
