package br.com.project.sistema;

import java.io.BufferedWriter;
import java.io.FileWriter;

import br.com.project.entity.Funcionario;
import br.com.project.service.FuncionarioServices;

public class SystemMenu {
	
	

	public static void main(String[] args) {

		FuncionarioServices f = new FuncionarioServices();
		//src./br/com/project/csv/Funcionario.csv
		
		f.leitor();
		f.descontoInss();
		f.gerador();
	}
	

}
