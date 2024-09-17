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
import br.com.project.enums.Inss;
import br.com.project.enums.Parentesco;
import br.com.project.exception.DependenteException;
import br.com.project.interfaces.Imposto;

public class FuncionarioServices implements Imposto {
	List<Funcionario> funcionarios = new ArrayList<>();
	List<Dependente> dependentes = new ArrayList<>();
	Integer contador = 0;

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
							contador++;
							dependentes.add(dependente);
							linha = scanner.nextLine();

						} else if (dadosParentesco[3].equalsIgnoreCase("filho")) {
							parente = Parentesco.FILHO;
							Dependente dependente = new Dependente(nomeD, cpfD, dataNascimentoD, parente);
							contador++;
							dependentes.add(dependente);

							linha = scanner.nextLine();

						} else if (dadosParentesco[3].equalsIgnoreCase("sobrinho")) {
							parente = Parentesco.SOBRINHO;
							Dependente dependente = new Dependente(nomeD, cpfD, dataNascimentoD, parente);
							contador++;
							dependentes.add(dependente);
							linha = scanner.nextLine();

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
				//Double inss = funcionario.get ();
				//Double ir = funcionario.get ();
				//Double salario = funcionario.get ();
				bw.append(nome + ";" + cpf + ";" + "\n");
				System.out.println("criado");

			}
			bw.close();

		} catch (IOException e) {
			System.err.println("arquivo nao encontrado");
			e.printStackTrace();
		}
	}

	@Override
	public Double descontoInss() {

		double calculoInss = 0.0;

		Inss deducao = Inss.DEDUCAO;
		Inss deducao1 = Inss.DEDUCAO1;
		Inss deducao2 = Inss.DEDUCAO2;
		Inss deducao3 = Inss.DEDUCAO3;

		AliquotaInss ali = AliquotaInss.ALIN;
		AliquotaInss ali2 = AliquotaInss.ALIN2;
		AliquotaInss ali3 = AliquotaInss.ALIN3;
		AliquotaInss ali4 = AliquotaInss.ALIN4;

		return 0.0;

	}

	@Override
	public Double impostoIR() {

		return null;
	}

}
