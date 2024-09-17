package br.com.project.enums;

public enum AliquotasIr {

	ALIR(0.), ALIR2(0.075), ALIR3(0.15), ALIR4(0.225), ALIR5(0.275);

	private double valor;
	
	static AliquotasIr DEFAULT = ALIR;

	private AliquotasIr(double valor) {
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
