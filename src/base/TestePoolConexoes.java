package base;
import java.sql.SQLException;

import acao.ConnectionFactory;

public class TestePoolConexoes {

	public static void main(String[] args) throws SQLException, InterruptedException {

		ConnectionFactory cf = new ConnectionFactory();
		
		for(int i=0; i<15; i++) {
			cf.criaConexao();
			System.out.println("COnexao de numero: "+i);
		}
		Thread.sleep(30000); //para msotrar as conexoes abertas no terminal do mysql
	}

}
