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
			// /home/administrador/poo/Folha-Pagamento-Serratec/folhaPagamento/src/br/com/project/csv/
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			do {
				String linha = scanner.nextLine();
				// System.out.println(linha);

				if (!linha.isEmpty()) {
					String[] dadosLinha = linha.split(";");
					String nome = dadosLinha[0];
					String cpf = dadosLinha[1];
					String dataNascimentoString = dadosLinha[2];
					String salarioString = dadosLinha[3];

					while (!linha.isEmpty()) {
						String[] dadosParentesco = linha.split(";");
						String nomeD = dadosLinha[0];
						String cpfD = dadosLinha[1];
						String dataNascimentoDString = dadosLinha[2];
						LocalDate dataNascimentoD = LocalDate.parse(dataNascimentoDString, formatter);

						if (dadosLinha[3].toLowerCase() == "outro") {
							parente = Parentesco.OUTRO;

							dependentes.add(new Dependente(nomeD, cpfD, dataNascimentoD, parente));

						} else if (dadosLinha[3].toLowerCase() == "filho") {
							parente = Parentesco.FILHO;
							dependentes.add(new Dependente(nomeD, cpfD, dataNascimentoD, parente));

						} else if (dadosLinha[3].toLowerCase() == "sobrinho") {
							parente = Parentesco.SOBRINHO;
							dependentes.add(new Dependente(nomeD, cpfD, dataNascimentoD, parente));

						}
						
					}

					LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
					Double salario = Double.parseDouble(salarioString);

					funcionarios.add(new Funcionario(nome, cpf, dataNascimento, salario));
				}
			} while (scanner.hasNext());

			for (Funcionario f : funcionarios) {

				System.out.println(
						f.getNome() + ";" + f.getCpf() + ";" + f.getDataNascimento() + ";" + f.getSalarioBruto());
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			System.err.println("Arquivo nao encontrado");
		}
	}
}
