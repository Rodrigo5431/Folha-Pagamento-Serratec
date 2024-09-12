package br.com.project.sistema;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import br.com.project.entity.Funcionario;
import br.com.project.entity.Pessoa;

public class SystemMenu {

	public static void main(String[] args) {

		try {
			Funcionario funcionario = new Funcionario();
			Scanner scanner = new Scanner(new File("src./br/com/project/csv/Funcionario.csv"));
			scanner.useDelimiter(";");
			
			
			
			
			while (scanner.hasNext()) {
				//funcionario.setNome(scanner);
				
				
				System.out.println(scanner.next());
			}
			
			scanner.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado");
		}
	}
	
}

