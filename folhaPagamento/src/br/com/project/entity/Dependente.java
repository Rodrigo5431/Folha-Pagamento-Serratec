package br.com.project.entity;

import java.time.LocalDate;

import br.com.project.enums.Parentesco;
import br.com.project.exception.DependenteException;

public class Dependente extends Pessoa {

	private Parentesco parentesco;

	public Dependente(String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco)
			throws DependenteException {

		super(nome, cpf, dataNascimento);
		this.parentesco = parentesco;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

}
