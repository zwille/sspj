package sspj.ue1;

import org.jdom2.Element;

public class XmlReaderMatches extends XmlReader {
	private final Matches matches;
	private final Results results;

	public XmlReaderMatches(Matches matches, Results results) {
		this.matches = matches;
		this.results = results;
	}
	

	@Override
	protected void readElements(Element root) {
		for (Element child : root.getChildren()) {
			assert child.getName().equals("Matchdata");
			Element id = child.getChild("matchID");
			Element dateTime = child.getChild("matchDateTime");
			Element timeZoneId = child.getChild("TimeZoneID");
			Element dateTimeUTC = child.getChild("matchDateTimeUTC");
			//ignore group
			//ignore league
			Element team1 = child.getChild("idTeam1");
			Element team2 = child.getChild("idTeam2");
			Element lastUpdate = child.getChild("lastUpdate");
			Element matchIsFinished = child.getChild("matchIsFinished");
			
			Match match = matches.createMatch(
				Integer.parseInt(id.getTextNormalize()),
				dateTime.getTextNormalize(),
				timeZoneId.getTextNormalize(),
				dateTimeUTC.getTextNormalize(),
				Integer.parseInt(team1.getTextNormalize()),
				Integer.parseInt(team2.getTextNormalize()),
				lastUpdate.getTextNormalize(),
				matchIsFinished.getTextNormalize().equals("true")
				);
			Element matchResults = child.getChild("matchResults");
			for (Element matchResult : matchResults.getChildren()) {
				assert matchResult.getName().equals("matchResult");
				Element name = matchResult.getChild("resultName");
				Element points1 = matchResult.getChild("pointsTeam1");
				Element points2 = matchResult.getChild("pointsTeam2");
				Element orderId = matchResult.getChild("resultOrderID");
				Element typeName = matchResult.getChild("resultTypeName");
				Element typeId = matchResult.getChild("resultTypeId");
				match.addResult(results.createResult(
					match,
					name.getTextNormalize(),
					Integer.parseInt(points1.getTextNormalize()),
					Integer.parseInt(points2.getTextNormalize()),
					Integer.parseInt(orderId.getTextNormalize()),
					typeName.getTextNormalize(),
					Integer.parseInt(typeId.getTextNormalize())
					));
				
			}
			/* ignore goals: insufficent data
			Element goals = child.getChild("goals");
			
			for (Element goal : goals.getChildren()) {
				Element goalId = goals.getChild("goalID");
				Element matchId = goals.getChild("goalMachID");
				assert matchId.getTextNormalize().equals(id.getTextNormalize());
				Element score1 = goals.getChild("goalScoreTeam1");
				Element score2 = goals.getChild("goalScoreTeam2");
				Element minute = goals.getChild("goalMatchMinute");
				Element getterId = goals.getChild("goalGetterID");
				Element getterName = goals.getChild("goalGetterName");
				Element penalty = goals.getChild("goalPenalty");
				Element ownGoal = goals.getChild("goalOwnGoal");
				Element overtime = goals.getChild("goalOvertime");
				match.createGoal(
					goalId
					);

			}*/
		}
	}
}
