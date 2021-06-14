package base;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import acao.ConnectionFactory;

public class TesteListagem {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection con = connectionFactory.criaConexao();
		
		PreparedStatement stm = con.prepareStatement("SELECT * FROM PRODUDO");   //cria o statement,
		stm.execute();   														// pode ser insert, delete ou select, apenas um select retorn true
		
		ResultSet rst = stm.getResultSet();    									//pega os valores obtidos pelo statement
		
		while(rst.next()) {
			Integer id = rst.getInt(1);										 //joga os valores do statement para um variavel para ser utilizada;
			String nome = rst.getString("nome");
			String descricao = rst.getString("descricao");
			
			System.out.format("| %2d | %15s | %50s |", id, nome, descricao);
			System.out.println();
		}
	}

}
