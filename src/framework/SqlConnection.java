package framework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


import framework.DatabaseConnection;
import framework.TableObject;

public abstract class SqlConnection implements DatabaseConnection {

	Connection conn = null;
	
	@Override
	public boolean createTable() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean createObject(TableObject obj) {


		String sql = "INSERT INTO " + obj.getClass().getSimpleName() + " ("
				+ obj.convertToDict().keySet().toString().replace("[", "").replace("]", "") + ")" + "values ("
				+ obj.convertToDict().values().toString().replace("[", "").replace("]", "") + ");";

		Statement stmt;

		try {

			stmt = conn.createStatement();
			stmt.execute(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public TableObject readObject(TableObject table, int id) throws SQLException {

		String sql = "SELECT * from " + table.getClass().getSimpleName() + " where id = " + id + ";";

		Statement stmt;
		ResultSetMetaData rsmd;

		ResultSet result;

		stmt = conn.createStatement();
		result = stmt.executeQuery(sql);

		rsmd = result.getMetaData();

		table.setId(id);
		Map<String, Object> dict = new HashMap<String, Object>();

		while (result.next()) {
			
			for (int i = 1; i <= rsmd.getColumnCount(); ++i) {

				dict.put(rsmd.getColumnName(i), result.getObject(i));

			}
		}

		table.setProperties(dict);
		
		return table;

	}

	@Override
	public boolean updateObject(TableObject obj, int id) {

		String sql = "UPDATE CARRO SET " + obj.convertToDict().toString().replace("{", "").replace("}", "")
				+ " WHERE ID = " + id + ";";
				
		Statement stmt;

		System.out.println(sql);
		
		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}	
		
		
		
		return true;
		
		
	}

	@Override
	public boolean deleteObject(TableObject obj) {

		String sql = "DELETE FROM " + obj.getClass().getSimpleName() + " WHERE ID = " + obj.getId() + ";";
		
		System.out.println(sql);
		
		Statement stmt;

		ResultSet result;

		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
				
		return true;

		
		
	}

}
