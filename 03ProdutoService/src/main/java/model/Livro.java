package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String	NOME_PADRAO = "Novo Livro";
	public static final String	GENERO_PADRAO = "ROMANCE";
	public static final int MAX_ESTOQUE = 1000;
	private int id;
	private String nome;
	private float preco;
	private int quantidade;
	private LocalDateTime dataPublicacao;	
	private String genero;

	
	public Livro() {
		id = -1;
		nome = NOME_PADRAO;
		preco = 0.01F;
		quantidade = 0;
		dataPublicacao = LocalDateTime.now();
		genero = GENERO_PADRAO;
	
	}

	public Livro(int id, String nome, float preco, int quantidade, LocalDateTime publicacao, LocalDate v, String genero) {
		setId(id);
		setNome(nome);
		setPreco(preco);
		setQuant(quantidade);
		setDataPublicacao(publicacao);
		setGenero(genero);
		
	}		
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.length() >= 3)
			this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		if (preco > 0)
			this.preco = preco;
	}

	public int getQuant() {
		return quantidade;
	}
	
	public void setQuant(int quantidade) {
		if (quantidade >= 0 && quantidade <= MAX_ESTOQUE)
			this.quantidade = quantidade;
	}
	
	

	public LocalDateTime getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDateTime dataPublicacao) {
		// Pega a Data Atual
		LocalDateTime agora = LocalDateTime.now();
		// Garante que a data de fabricação não pode ser futura
		if (agora.compareTo(dataPublicacao) >= 0)
			this.dataPublicacao = dataPublicacao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		if (genero.length() >= 3)
			this.genero = genero;
	}
	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Livro: " + nome + "   Preço: R$" + preco + "   Quant.: " + quantidade + "   Fabricação: "
				+ dataPublicacao + "   Genero: " + genero;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Livro) obj).getId());
	}	
}