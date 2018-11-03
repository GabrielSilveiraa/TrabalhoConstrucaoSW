import java.util.Map;

public abstract class GUObject {
	
	private int id = 0;
	private String tableName = "";
	
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