package br.com.project.entity;

import java.time.LocalDate;

import br.com.project.enums.Parentesco;
import br.com.project.exception.DependenteException;

public class Dependente extends Pessoa {

	private Parentesco parentesco;
	private LocalDate data;

	public Dependente(String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco)
			throws DependenteException {

		
		
		super(nome, cpf);
		this.parentesco = parentesco;
		this.data = dataNascimento;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

}
