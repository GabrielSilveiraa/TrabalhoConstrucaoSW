package SampleApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import framework.DatabaseConnection;
import framework.TableObject;

public class MySqlConnection implements DatabaseConnection {

	private static final String USUARIO = "root";
	private static final String SENHA = "admin";
	private static final String URL = "jdbc:mysql://localhost:3306/teste";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	Connection conn = null;

	@Override
	public Connection createConnection() {

		try {
			conn = DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Retorna a conexao aberta
		return conn;
	}

	@Override
	public boolean createTable() {
		return false;

	}

	@Override
	public boolean createObject(TableObject obj) {

		System.out.println(obj.convertToDict());

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
