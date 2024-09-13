package br.com.project.entity;

import java.time.LocalDate;

import br.com.project.enums.Parentesco;

public class Dependente extends Pessoa {

	private Parentesco parentesco;

	public Dependente(String nome, String cpf, LocalDate dataNascimento) {
		super(nome, cpf, dataNascimento);
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

}
