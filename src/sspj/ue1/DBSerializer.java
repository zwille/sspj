/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sspj.ue1;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ccwelich
 */
public interface DBSerializer {
	public void write(Connection connection) throws SQLException;
}
