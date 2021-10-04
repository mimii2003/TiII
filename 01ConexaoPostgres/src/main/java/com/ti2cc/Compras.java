package com.ti2cc;

public class Compras {
	private int chocolate_preto;
	private int chocolate_branco;
	private int	arroz;
	private String nome_comprador;
	private String senha;
	private char sexo;
	
	public Compras() {
		this.chocolate_preto = -1;
		this.chocolate_branco = -1;
		this.arroz = -1;
		this.nome_comprador = "";
		this.senha = "";
		this.sexo = '*';
	}
	
	public Compras(int chocolate_preto, int chocolate_branco, int arroz, String nome_comprador, String senha, char sexo) {
		this.chocolate_preto = chocolate_preto;
		this.chocolate_branco = chocolate_branco;
		this.arroz = arroz;
		this.nome_comprador = nome_comprador;
		this.senha = senha;
		this.sexo = sexo;
	}

	public int getChocolate_preto() {
		return chocolate_preto;
	}

	public void setChocolate_preto(int chocolate_preto) {
		this.chocolate_preto = chocolate_preto;
	}

	public int getChocolate_branco() {
		return chocolate_branco;
	}

	public void setChocolate_branco(int chocolate_branco) {
		this.chocolate_branco = chocolate_branco;
	}

	public int getArroz() {
		return arroz;
	}

	public void setArroz(int arroz) {
		this.arroz = arroz;
	}

	public String getNome_comprador() {
		return nome_comprador;
	}

	public void setNome_comprador(String nome_comprador) {
		this.nome_comprador = nome_comprador;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	@Override
	public String toString() {
		return "Compras [chocolate_preto=" + chocolate_preto + ", chocolate_branco =" + chocolate_branco + ", arroz=" + arroz + ",  nome_comprador=" +  nome_comprador +", senha =" + senha +", sexo =" + sexo + "]";
	}	
}
