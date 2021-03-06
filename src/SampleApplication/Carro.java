package SampleApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import framework.TableObject;

public class Carro extends TableObject {

	String marca;
	String modelo;
	Double motor;
	int concessionariaId;
	
	public Carro(String marca,String modelo,Double motor, int concessionariaId)
	{
		super();
		
		this.marca = marca;
		this.modelo = modelo;
		this.motor = motor;
		this.concessionariaId = concessionariaId;
		
		primaryKey.add("id");
		setforeignKey("concessionariaId","Concessionaria","id");
//		relations.add(Concessionaria.class);		
	}
	
	
	@Override
	public void setProperties(Map<String, Object> dict) {
		setId(Integer.parseInt(dict.get("id").toString()));
		marca = dict.get("marca").toString();
		motor =  (Double.parseDouble(dict.get("motor").toString()));
		concessionariaId = Integer.parseInt(dict.get("concessionariaId").toString());
		modelo = dict.get("modelo").toString();
	}

	@Override
	public Map<String, Object> convertToDict() {
		Map<String, Object> obj = new LinkedHashMap<String, Object>();
		obj.put("id", id);
		obj.put("marca", marca);
		obj.put("modelo", modelo);
		obj.put("motor", motor);
		obj.put("concessionariaId", concessionariaId);
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


	public int getIdConcessionaria() {
		return concessionariaId;
	}

	public void setConcessionaria(int concessionariaId) {
		this.concessionariaId = concessionariaId;
	}

	
	

}
