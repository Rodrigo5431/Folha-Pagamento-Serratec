package br.com.project.sistema;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.project.entity.Dependente;
import br.com.project.entity.Funcionario;
import br.com.project.enums.Parentesco;

public class SystemMenu {

	public static void main(String[] args) {

		try {
			Scanner ler = new Scanner(System.in);
			System.out.println("Digite o caminho do arquivo");
			String caminho = ler.next();
			Scanner scanner = new Scanner(new File(caminho));
			Parentesco parente = Parentesco.NENHUM;
			List<Funcionario> funcionarios = new ArrayList<>();
			List<Dependente> dependentes = new ArrayList<>();
			// src./br/com/project/csv/Funcionario.csv
			// /home/administrador/poo/Folha-Pagamento-Serratec/folhaPagamento/src/br/com/project/csv/Funcionario.csv
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			while (scanner.hasNext()) {
				String linha = scanner.nextLine();
				// System.out.println(linha);

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
							dependentes.add(new Dependente(nomeD, cpfD, dataNascimentoD, parente));
							linha = scanner.nextLine();

						} else if (dadosParentesco[3].equalsIgnoreCase("filho")) {
							parente = Parentesco.FILHO;
							dependentes.add(new Dependente(nomeD, cpfD, dataNascimentoD, parente));
							linha = scanner.nextLine();

						} else if (dadosParentesco[3].equalsIgnoreCase("sobrinho")) {
							parente = Parentesco.SOBRINHO;
							dependentes.add(new Dependente(nomeD, cpfD, dataNascimentoD, parente));
							linha = scanner.nextLine();

						}
						
					}

				}

			}

			for (Funcionario f : funcionarios) {

				System.out.println(
						f.getNome() + ";" + f.getCpf() + ";" + f.getDataNascimento() + ";" + f.getSalarioBruto());
			}
			System.out.println("");

			for (Dependente d : dependentes) {
				System.out.println(
						d.getNome() + ";" + d.getCpf() + ";" + d.getDataNascimento() + ";" + d.getParentesco());
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			System.err.println("Arquivo nao encontrado");
		}
	}
}
