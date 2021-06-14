package Teste;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import acao.ConnectionFactory;
import dao.ProdutoDAO;
import modelo.Produto;

public class TesteInsereListaRemoveProduto {

	public static void main(String[] args) throws SQLException, InterruptedException {
		Produto p1 = new Produto("Video Game", "Xbox 360");
		
		try(Connection con = new ConnectionFactory().criaConexao()){
			
			ProdutoDAO produtoDAO = new ProdutoDAO(con);
//			produtoDAO.salva(p1);
			
			List<Produto> lista = produtoDAO.listar();
			for (Produto produto : lista) {
				System.out.format("| %2d | %15s | %50s |", produto.getId(), produto.getNome(), produto.getDescricao());
				System.out.println();
			}
			
//			Thread.sleep(1500);
//			produtoDAO.removeIdMaior(31);
//			produtoDAO.listar();
		}
	}

}
