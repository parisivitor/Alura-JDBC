package base;
import java.sql.SQLException;

import acao.ConnectionFactory;

import java.sql.Connection;


public class TesteConexao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection con = connectionFactory.criaConexao();
		
		System.out.println("Abertura de conexao!");
		con.close();
		System.out.println("Fechando conexao!");
	}

}
