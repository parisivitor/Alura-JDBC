package acao;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	public DataSource dataSource;
/**
 * criandom um pool de conexoes, para melhor o desempenho em um cenario onde diversos clientes 
 * acessam simultaneamente a mesma conexao
 */
	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("123123");
		
		comboPooledDataSource.setMaxPoolSize(15); //tamanho do poll de conexoes
		
		this.dataSource = comboPooledDataSource;
		
	}

	public Connection criaConexao() throws SQLException {
		return this.dataSource.getConnection();

	}
}
