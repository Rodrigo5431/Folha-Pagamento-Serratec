package br.com.project.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.project.entity.Funcionario;

public class FuncionarioDAO {

	private Connection connection;

	public FuncionarioDAO() {
		connection = new ConnectionFactory().getConnection();

	}

	public void inserir(Funcionario f) {
		String sql = "Insert into funcionario (nome, cpf, datanascimento, salariobruto) values (?,?,?,?)";
				
		try {
			PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		stmt.setString(1, f.getNome());
		stmt.setString(2, f.getCpf());
		stmt.setDate(3,java.sql.Date.valueOf(f.getDataNascimento()));
		stmt.setDouble(4, f.getSalarioBruto());
	
		stmt.execute();
		
		//connection.close();
			
	} catch (SQLException e) {
		System.err.println("Querry errada");
	}
	
	}

}
