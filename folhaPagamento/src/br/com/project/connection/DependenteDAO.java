package br.com.project.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.project.entity.Dependente;

public class DependenteDAO {

	private Connection connection;

	public DependenteDAO() {
		connection = new ConnectionFactory().getConnection();

	}

	public void inserirDependente(Dependente d) {
		String sql = "Insert into dependente (nome, cpf, datanascimento) values (?,?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, d.getNome());
			stmt.setString(2, d.getCpf());
			stmt.setDate(3, java.sql.Date.valueOf(d.getData()));
			// stmt.setString(4, d.getParentesco());

			stmt.execute();

			// connection.close();

		} catch (SQLException e) {
			System.err.println("Querry errada");
		}

	}
}