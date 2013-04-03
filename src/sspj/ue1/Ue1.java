package sspj.ue1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.JDOMException;

/**
 *
 * @author ccwelich
 */
public class Ue1 {
	
	public static void main(String[] args) throws JDOMException, IOException,
		ClassNotFoundException {
		String dbDriver = "org.postgresql.Driver";
		String dbUrl = "jdbc:postgresql:sspj_ue1";
		Teams teams = new Teams();
		ResultTypes resultTypes = new ResultTypes();
		Results results = new Results(resultTypes);
		Matches matches = new Matches(teams);
		XmlReader teamsReader = new XmlReaderTeams(teams);
		XmlReader matchesReader = new XmlReaderMatches(matches, results);
		teamsReader.read(
			"/home/zwille/Dropbox/Studium/ServerProgJEE/daten/teams.xml");
		//System.out.println("teams = " + teams);
		matchesReader.read(
			"/home/zwille/Dropbox/Studium/ServerProgJEE/daten/matches.xml");
		//System.out.println("matches = " + matches);
		Class.forName(dbDriver);
		try (Connection connection = DriverManager.getConnection(dbUrl)) {
			connection.setAutoCommit(false);
			// create tables
			Statement stmt = connection.createStatement();
			stmt.addBatch("DROP TABLE IF EXISTS Result");
			stmt.addBatch("DROP TABLE IF EXISTS Match");
			stmt.addBatch("DROP TABLE IF EXISTS Team");
			stmt.addBatch("DROP TABLE IF EXISTS ResultType");
			stmt.addBatch(Teams.SQL_CREATE);
			stmt.addBatch(Matches.SQL_CREATE);
			stmt.addBatch(ResultTypes.SQL_CREATE);
			stmt.addBatch(Results.SQL_CREATE);
			stmt.executeBatch();
			connection.commit();
			
			// insert values
			teams.write(connection);
			resultTypes.write(connection);
			matches.write(connection);
			results.write(connection);
			connection.commit();
		} catch (SQLException ex) {
			Logger.getLogger(Ue1.class.getName()).log(Level.SEVERE, null, ex);
			Logger.getLogger(Ue1.class.getName()).log(Level.SEVERE, null, ex.
				getNextException());
		}		
		
		
	}
}
