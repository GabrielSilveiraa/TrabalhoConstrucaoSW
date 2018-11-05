# Trabalho de Construcao de Software

##Resumo

	Framework que disponibiliza as opera��es b�sicas de um CRUD em cima de um banco de dados relacional (MySQL, Oracle), atraves de uma classe abstrata onde s�o implementados tais opera��es e 
	uma UI onde � possivel realizar tais opera��es.
	
##Configura��o do banco de dados

	O usuario j� deve estar com o banco relacional escolhido rodando na maquina em quest�o, ent�o deve ser implementado uma classe
	que extenda da classe "SqlConnection" e implementar o m�todo "CreateConnection", como foi feito na classe "MySqlConnection" na aplica��o de exemplo.
	
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
	
	Isso visa disponibilizar para a classe "SqlConnection" o atributo "Conn" onde atraves dele sera realizado as outras opera��es com o banco de dados.
	Obs: � importante verificar a porta em que o seu banco de dados subiu para colocar a mesma no c�digo, por exemplo no MySql a default � 3306, mas isso fica a cargo de quem cria o banco.
		
## Como as tabelas s�o representadas

	O Framework conta com uma classe chamada "TableObject" que serve para representar um mapeamento entra a classe no banco de dados e o c�digo em si. 
	Toda a classe no banco, em que o usuario queria realizar opera��es sobre ela, deve ter uma classe extendida de "TableObject" para representar a mesma.
	A classe "TableObject" exige que toda tabela tenha um campo ID, isso garante que o framework trabalhe com um objeto un�co e n�o tenha repeti��es.
	
		
	As colunas do banco s�o representadas por atributos, e chaves primarias e extrangerias da tabela devem ser adicionados aos arrays "primaryKey" e "foreignKey", respectivamente, no construtor. Conforme � realizado na classe "Carro" na aplica��o de exemplo:
	
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
	No array "primaryKey" deve ser adicionado o atributo no qual � uma chave primaria no banco de dados, j� no array "foreignKey" no qual conta com um m�todo para facilitar a inser��o do mesmo ("setforeignKey") deve ser passado o atributo na classe que � uma chave estrangeira,
	a outra classe na qual ele aponta e por fim o atributo na outra classe. Desse m�do � feito a liga��o entre a coluna da sua tabela e a coluna da outra tabela.
	
	Al�m disso, toda classes que representam tabelas devem contar com os m�todos: 
	
	* setProperties(Map<String, Object> dict)
	* Map<String, Object> convertToDict()
	
	Todas a comunica��o entre as opera��es com o banco de dados, UI e os objetos de mapeamento � realizado atraves de dicionarios, para facilitar a manipula��o de objetos.
	Assim o "setProperties" � responsavel por transformar atributos de um dicionario para a classe em si e o "convertToDict" realiza o contrario, transforma a classe em si para dentro de um dicionario.
	
	Aten��o, a realiza��o das opera��es com sucesso depende da boa implementa��o desses dois m�todos, verifique atentamente eles e use a aplica��o de exemplo como base.
	
## Relacionamento ?

	
## UI ?
	
	
## Testes ?
	
	
	

