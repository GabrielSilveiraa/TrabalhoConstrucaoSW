package frameworkUI;

import framework.TableObject;
import java.util.List;

public class CrudFramework {

	static List<TableObject> tables;
	
	public CrudFramework(List<TableObject> tables) {
		CrudFramework.tables = tables;
		main(null);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainJFrame(tables);
	}

}
