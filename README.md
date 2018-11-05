# Trabalho de Construcao de Software

## Resumo
  Framework que disponibiliza as operações básicas de um CRUD em cima de um banco de dados relacional (MySQL, Oracle), 
  atraves de uma classe abstrata onde são implementados tais operações e uma UI onde é possivel realizar tais operações.
	
## Configuração do banco de dados
  O usuario já deve estar com o banco relacional escolhido rodando na maquina em questão, então deve ser implementado uma classe
  que extenda da classe "SqlConnection" e implementar o método "CreateConnection", como foi feito na classe "MySqlConnection" na
  aplicação de exemplo.
	
	```
	@Override
	public Connection createConnection() {

		try {
			conn = DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Retorna a conexao aberta
		return conn;
	}
	
	```
	
  Isso visa disponibilizar para a classe "SqlConnection" o atributo "Conn" onde atraves dele sera realizado as outras operações com o 
  banco de dados.
  Obs: É importante verificar a porta em que o seu banco de dados subiu para colocar a mesma no código, por exemplo no MySql a default é   3306, mas isso fica a cargo de quem cria o banco.
		
## Como as tabelas são representadas
  O Framework conta com uma classe chamada "TableObject" que serve para representar um mapeamento entra a classe no banco de dados e o 
  código em si. 
  Toda a classe no banco, em que o usuario queria realizar operações sobre ela, deve ter uma classe extendida de "TableObject" para     
  representar a mesma.
  A classe "TableObject" exige que toda tabela tenha um campo ID, isso garante que o framework trabalhe com um objeto uníco e não tenha 
  repetições.
	
		
  As colunas do banco são representadas por atributos, e chaves primarias e extrangerias da tabela devem ser adicionados aos arrays 
  "primaryKey" e "foreignKey", respectivamente, no construtor. Conforme é realizado na classe "Carro" na aplicação de exemplo:
	
	```
		public Carro(String marca,String modelo,Double motor, int concessionariaId)
	{
		super();
		
		this.marca = marca;
		this.modelo = modelo;
		this.motor = motor;
		this.concessionariaId = concessionariaId;
		
		primaryKey.add("id");
		setforeignKey("concessionariaId","Concessionaria","id");
	}
	
	```
	
  No array "primaryKey" deve ser adicionado o atributo no qual é uma chave primaria no banco de dados, já no array "foreignKey" no qual 
  conta com um método para facilitar a inserção do mesmo ("setforeignKey") deve ser passado o atributo na classe que é uma chave estrangeira, a outra classe na qual ele aponta e por fim o atributo na outra classe. Desse módo é feito a ligação entre a coluna da sua tabela e a coluna da outra tabela.
	
  Além disso, toda classes que representam tabelas devem implementar os seguintes métodos: 
	
	* setProperties(Map<String, Object> dict)
	* Map<String, Object> convertToDict()
	
  Todas a comunicação entre as operações com o banco de dados, UI e os objetos de mapeamento é realizado atraves de dicionarios, para facilitar a manipulação de objetos.
  Assim o "setProperties" é responsavel por transformar atributos de um dicionario para a classe em si e o "convertToDict" realiza o contrario, transforma a classe em si para dentro de um dicionario.
	
  Atenção, a realização das operações com sucesso depende da boa implementação desses dois métodos. Veja o seguinte exemplo:
  
  ```
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
	
```
	
## Relacionamento ?

	
## UI ?
	
	
## Testes ?
	
	
	

