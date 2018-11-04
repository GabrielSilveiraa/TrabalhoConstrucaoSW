package SampleApplication;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frameworkUI.CrudFramework;
import framework.TableObject;;

public class Main {

	public static void main(String[] args) throws SQLException {
		MySqlConnection teste = new MySqlConnection();
		
		Carro carro = new Carro("'Hyundai'", "'hb50'", 1.0, "'sei la'");
		
		System.out.println(carro.motor.getClass());
		
		
		carro.setId(4);
		
		teste.createConnection();
		
		List<TableObject> tables = new ArrayList<>();
		
		tables.add(carro);
		
		new CrudFramework(tables, teste);
		
		//teste.createObject(carro);

		
		//Como printar, vou botar um toString()
		//System.out.println(teste.readObject(carro, 3).convertToDict());
		
		
//		carro.setMarca("'FORD'");
//		carro.setMotor(1.5);
//		carro.setModelo("'KA'");
//		
//		teste.updateObject(carro, 5);
		
		
		
		
		//System.out.println(teste.deleteObject(carro));
		
	}

}
