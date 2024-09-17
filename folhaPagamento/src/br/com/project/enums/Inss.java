package br.com.project.enums;

public enum Inss {

	DEDUCAO(0.), DEDUCAO1(21.18), DEDUCAO2(101.18), DEDUCAO3(181.18);

	private double valorInss;

	private Inss(double valorInss) {
		this.valorInss = valorInss;
	}

	public double getValorInss() {
		return valorInss;
	}

	public void setValorInss(double valorInss) {
		this.valorInss = valorInss;
	}

}
