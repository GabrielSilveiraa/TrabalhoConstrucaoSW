package framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class TableObject {

	protected int id;

	protected ArrayList<Object> relations = new ArrayList<Object>();
	protected ArrayList<String> primaryKey = new ArrayList<>();
	protected HashMap<String, HashMap<String, String>> foreignKey = new HashMap<String, HashMap<String, String>>();
	
	public abstract void setProperties(Map<String, Object> dict);
	public abstract Map<String, Object> convertToDict();
	
	public TableObject()
	{
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<String> getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(ArrayList<String> primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	public HashMap<String, HashMap<String, String>> getForeignKey() {
		return foreignKey;
	}
	
	public void setforeignKey(String atributo, String outraClasse, String outroAtributo) {
		
		HashMap<String, String> aux = new HashMap<String, String>();
		
		aux.put(outraClasse, outroAtributo);
		foreignKey.put(atributo, aux);		
	}	
	
	public ArrayList<Object> getRelations() {
		return relations;
	}
	
	public void setRelations(ArrayList<Object> relations) {
		this.relations = relations;
	}
	
	
}
