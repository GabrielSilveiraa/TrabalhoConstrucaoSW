package SampleApplication;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frameworkUI.CrudFramework;
import framework.TableObject;;

public class Main {

	public static void main(String[] args) throws SQLException {
		MySqlConnection teste = new MySqlConnection();
		
		
		Concessionaria concessionaria = new Concessionaria("Ribeiro Jung", "Avenida Ipiranga", "Concessionaria dos guri", 
				9999, "25/12/1967", "Dos guri", true, false);
		concessionaria.setId(7);
		
		Carro carro = new Carro("'Hyundai'", "'hb50'", 1.0, 7);
		System.out.println(carro.motor.getClass());
		
		carro.setId(4);
		
		
		teste.createConnection();
		
		List<TableObject> tables = new ArrayList<>();
		
		tables.add(carro);
		tables.add(concessionaria);
		
		new CrudFramework(tables, teste);
		
		//teste.createIntermediaryTable(carro, concessionaria);
		//teste.insertRelation(carro, concessionaria);
		
		
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
