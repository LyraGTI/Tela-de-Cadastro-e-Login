package ClassesConexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {


	public static Connection faz_conexao() throws SQLException {
		
		// Conexão com o banco de dados aqui mané.
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			return DriverManager.getConnection("jdbc:mysql://localhost/db_senhas","root","1234");
			
			
			// Aqui vai ser o tratamento de erro e a mensagem.
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			throw new SQLException(e.getException());
			
		}
		
		
	}
	
	
	
}
