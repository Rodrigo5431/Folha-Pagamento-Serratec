package br.com.project.enums;

public enum Ir {
	
	IR(0.), IR2(169.44),IR3(381.44),IR4(662.77),IR5(896.00);
	
	private double valor;
	
	static Ir DEFAULT = IR;
	
	Ir(double valor){
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public static Ir getDEFAULT() {
		return DEFAULT;
	}

	public static void setDEFAULT(Ir dEFAULT) {
		DEFAULT = dEFAULT;
	}

	
}
