package br.com.project.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.project.entity.Dependente;
import br.com.project.entity.Funcionario;
import br.com.project.enums.AliquotaInss;
import br.com.project.enums.AliquotasIr;
import br.com.project.enums.Inss;
import br.com.project.enums.Ir;
import br.com.project.enums.Parentesco;
import br.com.project.exception.DependenteException;
import br.com.project.interfaces.Imposto;

public class FuncionarioServices implements Imposto {
	List<Funcionario> funcionarios = new ArrayList<>();
	List<Dependente> dependentes = new ArrayList<>();
	Integer contador = 0;
	double calculoInss = 0.;
	double calculoIr = 0.;

	public double getCalculoInss() {
		return calculoInss;
	}

	public void setCalculoInss(double calculoInss) {
		this.calculoInss = calculoInss;
	}

	public void leitor() {
		try {
			Scanner ler = new Scanner(System.in);
			System.out.println("Digite o caminho do arquivo");
			String caminho = ler.next();
			Scanner scanner = new Scanner(new File(caminho));
			Parentesco parente = Parentesco.NENHUM;
			// src./br/com/project/csv/Funcionario.csv
			// /home/administrador/poo/Folha-Pagamento-Serratec/folhaPagamento/src/br/com/project/csv/Funcionario.csv
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			while (scanner.hasNext()) {
				String linha = scanner.nextLine();

				if (!linha.isEmpty()) {
					String[] dadosLinha = linha.split(";");
					String nome = dadosLinha[0];
					String cpf = dadosLinha[1];
					String dataNascimentoString = dadosLinha[2];
					String salarioString = dadosLinha[3];

					LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
					Double salario = Double.parseDouble(salarioString);
					Funcionario funcionario = new Funcionario(nome, cpf, dataNascimento, salario);

					funcionarios.add(funcionario);
					linha = scanner.nextLine();

					while (!linha.isEmpty()) {
						if (linha.isEmpty()) {
							break;
						}

						String[] dadosParentesco = linha.split(";");
						String nomeD = dadosParentesco[0];
						String cpfD = dadosParentesco[1];
						String dataNascimentoDString = dadosParentesco[2];
						LocalDate dataNascimentoD = LocalDate.parse(dataNascimentoDString, formatter);

						if (dadosParentesco[3].equalsIgnoreCase("outro")) {
							parente = Parentesco.OUTRO;
							Dependente dependente = new Dependente(nomeD, cpfD, dataNascimentoD, parente);
							dependentes.add(dependente);
							linha = scanner.nextLine();
							// funcionario.setDependente(dependente);

						} else if (dadosParentesco[3].equalsIgnoreCase("filho")) {
							parente = Parentesco.FILHO;
							Dependente dependente = new Dependente(nomeD, cpfD, dataNascimentoD, parente);
							contador++;
							dependentes.add(dependente);
							// funcionario.setDependente(dependente);

							linha = scanner.nextLine();

						} else if (dadosParentesco[3].equalsIgnoreCase("sobrinho")) {
							parente = Parentesco.SOBRINHO;
							Dependente dependente = new Dependente(nomeD, cpfD, dataNascimentoD, parente);
							dependentes.add(dependente);
							linha = scanner.nextLine();
							// dependentesSet.add(dependentes);
							System.out.println(funcionario.getDependentes() + "teste2");

						}

					}

				}

			}
			/*
			 * for (Funcionario f : funcionarios) {
			 * 
			 * System.out.println( f.getNome() + ";" + f.getCpf() + ";" +
			 * f.getDataNascimento() + ";" + f.getSalarioBruto() + ";"); }
			 * 
			 * System.out.println("");
			 * 
			 * for (Dependente d : dependentes) { System.out.println(d.getNome() + ";" +
			 * d.getCpf() + ";" + d.getData() + ";" + d.getParentesco()); }
			 */

			scanner.close();

		} catch (FileNotFoundException e) {
			System.err.println("Arquivo nao encontrado");
		} catch (DependenteException e) {

			e.printStackTrace();
		}

	}

	public void gerador() {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("src./br/com/project/csv/Resultado.csv"));
			for (Funcionario funcionario : funcionarios) {
				String nome = funcionario.getNome();
				String cpf = funcionario.getCpf();
				Double inss = (double) Math.round(funcionario.getDescontoInss());
				// Double ir = funcionario.get ();
				// Double salario = funcionario.get ();
				// descontoInss();
				bw.append(nome + ";" + cpf + ";" + inss + ";" + "\n");

			}
			bw.close();

		} catch (IOException e) {
			System.err.println("arquivo nao encontrado");
			e.printStackTrace();
		}

	}

	@Override
	public Double descontoInss() {

		Double salario = 0.;
		AliquotaInss aliquota = AliquotaInss.ALINDEF;
		Inss inss = Inss.DEDUCAO;

		for (Funcionario f : funcionarios) {
			calculoInss = 0.;
			salario = f.getSalarioBruto();

			if (salario > 0.00 && salario <= 1412.00) {
				aliquota = AliquotaInss.ALIN;
				inss = Inss.DEDUCAO;

			} else if (salario > 1412.01 && salario <= 2666.68) {
				aliquota = AliquotaInss.ALIN2;
				inss = Inss.DEDUCAO1;

			} else if (salario > 2666.69 && salario <= 4000.03) {
				aliquota = AliquotaInss.ALIN3;
				inss = Inss.DEDUCAO2;

			} else if (salario > 4000.04) {
				aliquota = AliquotaInss.ALIN4;
				inss = Inss.DEDUCAO3;
			}

			double taxaAliquota = aliquota.getValor();
			double taxaInss = inss.getValorInss();

			calculoInss = (salario * taxaAliquota) - taxaInss;
			f.setDescontoInss(calculoInss);

		}
		return null;

	}

	@Override
	public Double impostoIR() {
		calculoIr = 0.0;
		Double salario = 0.0;
		double valorDependentes = 189.59;

		for (Funcionario f : funcionarios) {
			salario = f.getSalarioBruto();
			double inss = calculoInss;
			System.out.println(calculoInss);

			int numDependentes = f.getDependentes().size();
			System.out.println(numDependentes + "teste1");
			double deducaoDependentes = numDependentes * valorDependentes;

			AliquotasIr aliquota = AliquotasIr.ALIR;
			Ir deducao = Ir.IR;

			if (salario > 0 && salario <= 2259.00) {
				aliquota = AliquotasIr.ALIR;
				deducao = Ir.IR;
			}

			else if (salario > 2259.00 && salario <= 2826.65) {
				aliquota = AliquotasIr.ALIR2;
				deducao = Ir.IR2;

			} else if (salario > 2826.65 && salario <= 3751.05) {
				aliquota = AliquotasIr.ALIR3;
				deducao = Ir.IR3;
			} else if (salario > 3751.05 && salario <= 4664.68) {
				aliquota = AliquotasIr.ALIR4;
				deducao = Ir.IR4;
			} else if (salario > 4664.69) {
				aliquota = AliquotasIr.ALIR5;
				deducao = Ir.IR5;
			}

			if (calculoIr < 0) {
				calculoIr = 0.0;
			}

			f.setDescontoIR(calculoIr);

		}

		return null;

	}

	public void salarioLiquido() {

		Double salarioL = 0.;

		for (Funcionario f : funcionarios) {

			salarioL = f.getSalarioBruto();

			salarioL -= f.getDescontoInss();
			salarioL -= f.getDescontoIR();

			f.setSalarioLiquido(salarioL);

		}

	}
}
