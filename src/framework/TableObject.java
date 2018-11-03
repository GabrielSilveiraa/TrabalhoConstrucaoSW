package framework;

import java.util.Map;

public abstract class TableObject {

	private int id;
	
	private String tableName;
	
	public abstract void setProperties(Map<String, Object> dict);
	public abstract Map<String, Object> convertToDict();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
	
	
}
