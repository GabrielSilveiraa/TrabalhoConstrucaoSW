package SampleApplication;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		MySqlConnection teste = new MySqlConnection();
		
		Carro carro = new Carro("'Hyundai'", "'hb50'", 1.0, "'sei la'");
		carro.setId(4);
		
		teste.createConnection();
		
		//teste.createObject(carro);

		
		//Como printar, vou botar um toString()
		//System.out.println(teste.readObject(carro, 3).convertToDict());
		
		
		carro.setMarca("'FORD'");
		carro.setMotor(1.5);
		carro.setModelo("'KA'");
		
		teste.updateObject(carro, 5);
		
		
		
		
		//System.out.println(teste.deleteObject(carro));
		
	}

}
