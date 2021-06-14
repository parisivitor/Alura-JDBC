package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.Produto;

public class CategoriaDAO {
	Connection con;

	public CategoriaDAO(Connection con) {
		this.con = con;
	}

	public List<Categoria> listar() throws SQLException {
		List<Categoria> lista = new ArrayList<>();

		System.out.println("-----CONNECTION CATEGODIA-----");

		try (PreparedStatement pstm = con.prepareStatement("SELECT * FROM CATEGORIA")) {
			pstm.execute(); // pode ser insert, delete ou select, apenas um select retorn true

			try (ResultSet rst = pstm.getResultSet()) {// pega os valores obtidos pelo statement
				while (rst.next()) {
					lista.add(new Categoria(rst.getInt(1), rst.getString(2)));
				}
			}
		}
		return lista;
	}
/**
 * fazendo o INNER JOIN eu conecto ao banco apenas uma vez, para fazer a busca, melhorando o fluxo de dados e desempenho 
 * @return
 * @throws SQLException
 */
	public List<Categoria> listarComProduto() throws SQLException {
		List<Categoria> lista = new ArrayList<>();
		System.out.println("-----CONNECTION CATEGODIA-----");
		Categoria ultima = null;
		try (PreparedStatement pstm = con
				.prepareStatement("SELECT * FROM CATEGORIA C INNER JOIN PRODUDO P ON C.ID = P.CATEGORIA_ID")) {
			pstm.execute(); // pode ser insert, delete ou select, apenas um select retorn true

			try (ResultSet rst = pstm.getResultSet()) {// pega os valores obtidos pelo statement
				while (rst.next()) {
					if (ultima == null || !ultima.getNome().equals(rst.getString(2))) {
						Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
						ultima = categoria;
						lista.add(categoria);
					}
					Produto produto = new Produto(rst.getInt(3), rst.getString(4),rst.getString(5));
					ultima.adiciona(produto);
				}
			}
		}
		return lista;
	}
}
