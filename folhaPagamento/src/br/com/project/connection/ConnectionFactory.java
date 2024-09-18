package br.com.project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	String url = "jdbc:postgresql://localhost:5432/folhapagamento";
	String usuario = "postgres";
	String senha = "40028922@";

	private Connection connection;

	public Connection getConnection() {

		System.out.println("Conectando");
		try {
			connection = DriverManager.getConnection(url,usuario,senha);
			if(connection != null) {
				System.out.println("Conectado com sucesso");
			}
			else {
				System.err.println("Erro nos dados");
			}
			
		} catch (SQLException e) {
			System.err.println("nao foi possivel conectar");
		}

		return connection;
	}

}
