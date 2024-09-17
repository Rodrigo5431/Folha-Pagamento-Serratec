package br.com.project.sistema;



import br.com.project.service.FuncionarioServices;

public class SystemMenu {
	
	

	public static void main(String[] args) {

		FuncionarioServices f = new FuncionarioServices();
		// src./br/com/project/csv/Funcionario.csv
		/// home/administrador/poo/Folha-Pagamento-Serratec/folhaPagamento/src/br/com/project/csv/Funcionario.csv
		 
		f.leitor();
		f.descontoInss();
		f.descontoIR();
		f.salarioLiquido();
		f.gerador();
	}
	

}
