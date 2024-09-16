package br.com.project.entity;

import java.time.LocalDate;

public class Funcionario extends Pessoa {
	private double salarioBruto;
	private double descontoInss;
	private double descontoIR;
	private Dependente dependente;

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;

	}

	@Override
	public String toString() {
		return super.toString() + descontoInss + ";" + descontoIR + ";";
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public Dependente getDependente() {
		return dependente;
	}

	public void setDependente(Dependente dependente) {
		this.dependente = dependente;
	}

}
