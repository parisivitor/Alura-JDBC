package Teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import acao.ConnectionFactory;
import dao.CategoriaDAO;
import modelo.Categoria;
import modelo.Produto;

public class TesteListagemCategoria {
	public static void main(String[] args) throws SQLException {
		
		try(Connection con = new ConnectionFactory().criaConexao()){
			CategoriaDAO categoriaDAO = new CategoriaDAO(con);
			List<Categoria> lista = categoriaDAO.listarComProduto();
			lista.forEach(ct -> {
				System.out.println(ct.getNome());
					for(Produto produto : ct.getProdutos()) {
						System.out.println(ct.getNome() + " - " + produto.getNome());
					}
			});
		}
	}

}
