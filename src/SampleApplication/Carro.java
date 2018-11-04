package SampleApplication;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import framework.TableObject;

public class Carro extends TableObject {

	
	String marca;
	String modelo;
	Double motor;
	String concessionaria;
	
	
	public Carro(String marca,String modelo,Double motor,String concessionaria)
	{
		this.marca = marca;
		this.modelo = modelo;
		this.motor = motor;
		this.concessionaria = concessionaria;
	}
	
	
	@Override
	public void setProperties(Map<String, Object> dict) {
		setId(Integer.parseInt(dict.get("id").toString()));
		marca = dict.get("marca").toString();
		motor =  (Double.parseDouble(dict.get("motor").toString()));
		concessionaria = dict.get("concessionaria").toString();
		modelo = dict.get("modelo").toString();
	}

	@Override
	public Map<String, Object> convertToDict() {
		Map<String, Object> obj = new LinkedHashMap<String, Object>();
		obj.put("id", id);
		obj.put("marca", marca);
		obj.put("modelo", modelo);
		obj.put("motor", motor);
		obj.put("concessionaria", concessionaria);
		return obj;
	}
	
	
	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public Double getMotor() {
		return motor;
	}


	public void setMotor(Double motor) {
		this.motor = motor;
	}


	public String getConcessionaria() {
		return concessionaria;
	}


	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}

	
	

}
