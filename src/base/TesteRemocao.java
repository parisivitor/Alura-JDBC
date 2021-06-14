package base;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import acao.ConnectionFactory;

public class TesteRemocao {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection con = connectionFactory.criaConexao();
		
		PreparedStatement stm = con.prepareStatement("DELETE FROM PRODUDO WHERE ID > ?");   
//		stm.setString(1, "ID");
		stm.setInt(1, 2);
		stm.execute();
		Integer linhasRemovidas = stm.getUpdateCount();
		
		System.out.println("Numero de linhas removidas da table: " + linhasRemovidas);
	}

}
