package br.com.project.enums;

public enum AliquotasIr {

	ALIR(0.075), ALIR2(0.015), ALIR3(0.0225), ALIR4(0.0275);

	private double valor;

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
