package br.com.project.sistema;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.project.entity.Funcionario;

public class SystemMenu {

	public static void main(String[] args) {

		try {
			Scanner ler = new Scanner(System.in);
			System.out.println("Digite o caminho do arquivo");
			String caminho = ler.next();
			Scanner scanner = new Scanner(new File(caminho));
			List<Funcionario> funcionarios = new ArrayList<>();
			// "src./br/com/project/csv/Funcionario.csv"
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			
			do {
				String linha = scanner.nextLine();
				//System.out.println(linha);
				
				if (!linha.isEmpty()) {
					String[] dadosLinha = linha.split(";");
					String nome = dadosLinha[0];
					String cpf = dadosLinha[1];
					String dataNascimentoString = dadosLinha[2];
					String salarioString = dadosLinha[3];
					while(!linha.isEmpty()) {
						String[] dadosParentesco = linha.split(";");
						String nomeD = dadosLinha[0];
						String cpfD = dadosLinha[1];
						String dataNascimentoDString = dadosLinha[2];
						
						String parentesco = dadosLinha[3];
						
					}

					LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
					Double salario = Double.parseDouble(salarioString);
					
					funcionarios.add(new Funcionario(nome, cpf, dataNascimento, salario));
				}
			}while (scanner.hasNext());
			
			
			for (Funcionario f : funcionarios) {
			
				System.out.println(f.getNome()+";"+ f.getCpf() + ";" + f.getDataNascimento() + ";" + f.getSalarioBruto());
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			System.err.println("Arquivo nao encontrado");
		}
	}
}
