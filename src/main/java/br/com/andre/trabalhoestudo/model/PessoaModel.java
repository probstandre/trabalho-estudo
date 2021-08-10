package br.com.andre.trabalhoestudo.model;

public abstract class PessoaModel {

	private static long gerador = 0;

	private Long id;
	private String nome;
	private String documento;


	public PessoaModel(String nome, String documento) {
		this.id = ++gerador;
		this.nome = nome;
		this.documento = documento;
	}

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}
}