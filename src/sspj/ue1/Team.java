package sspj.ue1;

import java.util.HashSet;

/**
 *
 * @author ccwelich
 */
public class Team extends Entity<Team> {

	private final String name;
	private final String iconUrl;
	private final String stadion;
	private final HashSet<String> players;

	public Team(int id, String name, String iconUrl, String stadion) {
		super(id);
		this.name = name;
		this.iconUrl = iconUrl;
		this.stadion = stadion;
		this.players = new HashSet<>();
	}

	void addPlayer(String player) {
		if (players.contains(player)) {
			throw new IllegalArgumentException("dublicate Player: " + player);
		}
		players.add(player);
	}

	@Override
	public String toString() {
		return "Team{" + "id=" + id + ", name=" + name + ", iconUrl=" + iconUrl + ", stadion=" + stadion + ", players=" + players + '}';
	}

	public String getName() {
		return name;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public String getStadion() {
		return stadion;
	}

	public Iterable<String> getPlayers() {
		return players;
	}

	
}
