package base;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import acao.ConnectionFactory;

public class TesteInsercao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection con = connectionFactory.criaConexao()) { //utilizar o try with-resouces para garantir os .close necessarios (ResultSet, Connection, PreparedStatement)
			con.setAutoCommit(false); // FALSE = faz com que o commit, a atualizacao no banco seja feita apenas quando
										// eu mandar, tem que utliza o commit();

//		Statement stm = con.createStatement();   //cria o statement,
//		stm.execute("INSERT INTO PRODUDO (nome, descricao) VALUES ('mouse','mouse sem fio')", Statement.RETURN_GENERATED_KEYS);	// pode ser insert, delete ou select, apenas um select retorn true

			try (PreparedStatement stm = con.prepareStatement("INSERT INTO PRODUDO (nome, descricao) VALUES (?,?)",
					Statement.RETURN_GENERATED_KEYS)) {
				adicionaValores("SmartTv", "45 polegadas led", stm);
				adicionaValores("Radio", "Recarregavel e com lanterna", stm);
				con.commit(); // faz o commit, atualiza o banco, quando tiver setado autoCommit com false.
				stm.close();
				con.rollback();
			} catch (Exception e) {
				e.printStackTrace();
				con.rollback();
				System.out.println("ROLLBACK EXECUTADO");
			}
		}
	}

	private static void adicionaValores(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);
		stm.execute();

		if (nome.equals("Radio"))throw new RuntimeException("Erro ao adicionar radio"); // if para testar try catch, commit e rollback

		try (ResultSet rst = stm.getGeneratedKeys()) {
			while (rst.next()) {
				System.out.println("Item adicionado com id de: " + rst.getInt(1));
			}
		}

	}

}
