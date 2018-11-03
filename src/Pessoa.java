
import java.util.HashMap;
import java.util.Map;

public class Pessoa extends GUObject {
	
	String cpf;
	String nome;
	int idade;
	
	@Override
	public void setProperties(Map<String, Object> dict) {
		setId((int) dict.get("id"));
		cpf = (String) dict.get("cpf");
		nome = (String) dict.get("nome");
		idade = (int) dict.get("idade");
	}

	@Override
	public Map<String, Object> convertToDict() {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("id", getId());
		obj.put("cpf", cpf);
		obj.put("nome", nome);
		obj.put("idade", idade);
		return obj;
	}

}
