package br.com.project.entity;

import java.time.LocalDate;

public class Funcionario extends Pessoa {
	private double salarioBruto;
	private double descontoInss;
	private double descontoIR;

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;

	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

}
