package br.com.project.enums;

public enum Parentesco {

	FILHO("filho"), SOBRINHO("sobrinho"), OUTRO(""), NENHUM("nenhum");
	
	private String nome;

	static Parentesco DEFAULT = NENHUM;

	public static Parentesco getDefault() {
		return DEFAULT;
		
	}
	

	private Parentesco(String nome) {
		this.nome = nome;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public static Parentesco getDEFAULT() {
		return DEFAULT;
	}


	public static void setDEFAULT(Parentesco dEFAULT) {
		DEFAULT = dEFAULT;
	}
	
	

}
