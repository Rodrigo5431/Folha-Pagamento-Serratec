package br.com.project.enums;

public enum AliquotaInss {

	ALIN(0.075), ALIN2(0.9), ALIN3(0.12), ALIN4(0.14);

	private double valor;

	private AliquotaInss(double valor) {
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
