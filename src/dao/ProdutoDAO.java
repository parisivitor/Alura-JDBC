package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.Produto;

public class ProdutoDAO {
	Connection con;

	public ProdutoDAO(Connection con) {
		this.con = con;
	}

	public void salva(Produto prod) throws SQLException {
		String insert = "INSERT INTO PRODUDO (nome, descricao) VALUES (?,?)";
		try (PreparedStatement pstm = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
			pstm.setString(1, prod.getNome());
			pstm.setString(2, prod.getDescricao());
			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					prod.setId(rst.getInt(1));
					System.out.println(prod);
				}
			}
		}
	}

	public List<Produto> listar() throws SQLException {
		List<Produto> lista = new ArrayList<>();

		try (PreparedStatement stm = con.prepareStatement("SELECT * FROM PRODUDO")) {
			stm.execute(); // pode ser insert, delete ou select, apenas um select retorn true

			try (ResultSet rst = stm.getResultSet()) {// pega os valores obtidos pelo statement
				while (rst.next()) {
					lista.add(new Produto(rst.getInt(1), rst.getString(2), rst.getString(3)));
				}
			}
		}

		return lista;

	}
	
	public List<Produto> buscar(Categoria ct) throws SQLException {
		List<Produto> lista = new ArrayList<>();
		System.out.println("-----CONNECTION PRODUTO-----");
		try (PreparedStatement pstm = con.prepareStatement("SELECT * FROM PRODUDO WHERE CATEGORIA_ID = ?")) {
			pstm.setInt(1, ct.getId());
			pstm.execute(); // pode ser insert, delete ou select, apenas um select retorn true

			try (ResultSet rst = pstm.getResultSet()) {// pega os valores obtidos pelo statement
				while (rst.next()) {
					lista.add(new Produto(rst.getInt(1), rst.getString(2), rst.getString(3)));
				}
			}
		}

		return lista;

	}
	

	public void removeIdMaior(Integer n) throws SQLException {
		PreparedStatement stm = con.prepareStatement("DELETE FROM PRODUDO WHERE ID > ?");
		stm.setInt(1, n);
		stm.execute();
		Integer linhasRemovidas = stm.getUpdateCount();

		System.out.println("Numero de linhas removidas da table: " + linhasRemovidas);
	}

}
