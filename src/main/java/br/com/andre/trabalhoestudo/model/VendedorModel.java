package br.com.andre.trabalhoestudo.model;

public class VendedorModel extends PessoaModel {

	private String pis;

	public VendedorModel(String nome, String documento, String pis) {
		super(nome, documento);
		this.pis = pis;
	}

	public String getPis() {
		return pis;
	}
}