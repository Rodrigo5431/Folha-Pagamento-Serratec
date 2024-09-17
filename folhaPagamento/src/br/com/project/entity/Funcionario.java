package br.com.project.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.project.exception.DependenteException;

public class Funcionario extends Pessoa {
	private double salarioBruto;
	private double descontoInss;
	private double descontoIR;
	private Set <Dependente> dependentesSet = new HashSet();

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto)
			throws DependenteException {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;

	}

	@Override
	public String toString() {
		return super.toString() + descontoInss + ";" + descontoIR + ";";
	}
	

	public double getDescontoInss() {
		return descontoInss;
	}

	public void setDescontoInss(double descontoInss) {
		this.descontoInss = descontoInss;
	}

	public double getDescontoIR() {
		return descontoIR;
	}

	public void setDescontoIR(double descontoIR) {
		this.descontoIR = descontoIR;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public Set<Dependente> getDependentes() {
		return dependentesSet;
	}

	public void setDependentes(Set<Dependente> dependentes) {
		this.dependentesSet = dependentes;
	}

	
	

	

}
