
package sspj.ue1;

import java.util.List;
import org.jdom2.Element;


public class XmlReaderTeams extends XmlReader {
	public static final String TEAM = "Team";
	public static final String TEAM_ID = "teamID";
	public static final String TEAM_NAME = "teamName";
	public static final String TEAM_ICON_URL = "teamIconURL";
	public static final String STADION = "stadion";
	public static final String PLAYER = "player";
	private final Teams teams;

	public XmlReaderTeams(Teams teams) {
		this.teams = teams;
	}

	
	@Override
	protected void readElements(Element root) {
		for(Element child: root.getChildren()) {
			assert child.getName().equals(TEAM);
			List<Element> cc = child.getChildren();
			Element id = cc.get(0);
			assert id.getName().equals(TEAM_ID);
			Element name = cc.get(1);
			assert name.getName().equals(TEAM_NAME);
			Element iconUrl = cc.get(2);
			assert iconUrl.getName().equals(TEAM_ICON_URL);
			Element stadion = cc.get(3);
			assert stadion.getName().equals(STADION);
			Team team = teams.createTeam(
				Integer.parseInt(id.getTextNormalize()), 
				name.getTextNormalize(), 
				iconUrl.getTextNormalize(), 
				stadion.getTextNormalize());
			for (int i = 4; i < cc.size(); i++) {
				Element player = cc.get(i);
				assert player.getName().equals(PLAYER);
				team.addPlayer(player.getTextNormalize());
			}
		}
	}

}
