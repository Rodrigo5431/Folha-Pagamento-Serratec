package br.com.project.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.project.exception.DependenteException;

public abstract class Pessoa {

	private String nome;
	private String cpf;
	private LocalDate dataNascimento;

	private static Set<String> cpfsExistentes = new HashSet<>();

	public Pessoa(String nome, String cpf, LocalDate dataNascimento) throws DependenteException {

		if (cpfsExistentes.contains(cpf)) {
			throw new DependenteException("Esse CPF já foi cadastrado.");

		}

		if (dataNascimento.isAfter(LocalDate.now().minusYears(18))) {
			throw new DependenteException("O Dependente deve ser menor de 18 anos.");
		}

		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public Pessoa(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;

	}

	@Override
	public String toString() {
		return nome + ";" + cpf + ";" + dataNascimento + ";";
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

}
