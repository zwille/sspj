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
public class ResultTypes implements DBSerializer {

	public static final String SQL_INSERT = "INSERT INTO ResultType  (resultType_id, resultType_name)  VALUES (?, ?);";
	public static final String SQL_CREATE =
		"CREATE TABLE ResultType ("
		+ "resultType_id INTEGER PRIMARY KEY, "
		+ "resultType_name TEXT NOT NULL);";
	private final Map<Integer, ResultType> types;

	public ResultTypes() {
		this.types = new HashMap<>();
	}

	public boolean containsKey(int typeId) {
		return types.containsKey(typeId);
	}

	public ResultType get(int typeId) {
		return types.get(typeId);
	}

	public ResultType createType(int typeId, String typeName) {
		ResultType type = new ResultType(typeId, typeName);
		ResultType old = types.put(typeId, type);
		if (old != null) {
			throw new IllegalArgumentException("dublicate type: " + type);
		}
		return type;
	}

	@Override
	public void write(Connection connection) throws SQLException {
		String sql = SQL_INSERT;
		PreparedStatement pstmt = connection.prepareStatement(sql);
		for (ResultType type : types.values()) {
			System.out.println("insert resultType = " + type);
			pstmt.setInt(1, type.getId());
			pstmt.setString(2, type.getName());
			pstmt.addBatch();
		}
		pstmt.executeBatch();
	}
}
