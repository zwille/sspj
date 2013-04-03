
package sspj.ue1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author ccwelich
 */
public class Results implements DBSerializer {
	public static final String SQL_INSERT = "INSERT INTO Result  (match_id,  result_orderId, result_points1, result_points2, result_name)  VALUES (?, ?, ?, ?, ?);";
	public static final String SQL_CREATE =
		"CREATE TABLE Result ("
		+ "match_id INTEGER REFERENCES Match (match_id), "
		+ "result_orderId INTEGER,"
		+ "result_points1 INTEGER NOT NULL, "
		+ "result_points2 INTEGER NOT NULL, "
		+ "result_name TEXT NOT NULL, "
		+ "CONSTRAINT result_pk PRIMARY KEY (match_id, result_orderId));";
	
	private final ResultTypes types;
	
	private final Set<Result> results;
	
	public Results(ResultTypes types) {
		this.types = types;
		this.results = new TreeSet<>();
	}
	
	public Result createResult(
		Match parent,
		String name, 
		int points1,
		int points2,
		int orderId, 
		String typeName, 
		int typeId) {
		
		ResultType type;	
		if(types.containsKey(typeId)) {
			type = types.get(typeId);
		} else {
			type = types.createType(typeId, typeName);
		}
		Result rc = new Result(parent, type, orderId, points1, points1, name);
		if(results.contains(rc)) {
			throw new IllegalArgumentException("dublicate Result for match "+parent+" and orderId: "+orderId);
		}
		results.add(rc);
		return rc;
	}

	@Override
	public void write(Connection connection) throws SQLException {
		String sql = SQL_INSERT;
		PreparedStatement pstmt = connection.prepareStatement(sql);
		for (Result result : results) {
			System.out.println("insert result = " + result);
			pstmt.setInt(1, result.getParent().getId());
			pstmt.setInt(2, result.getOrderId());
			pstmt.setInt(3, result.getPoints1());
			pstmt.setInt(4, result.getPoints2());
			pstmt.setString(5, result.getName());
			pstmt.addBatch();
		}
		pstmt.executeBatch();
	}
}
