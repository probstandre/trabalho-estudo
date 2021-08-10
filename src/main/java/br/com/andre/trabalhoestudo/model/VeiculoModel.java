package br.com.andre.trabalhoestudo.model;

import java.math.BigDecimal;

public class VeiculoModel {

	private static long gerador = 0;

	private Long id;
	private String placa;
	private BigDecimal valor;
	private EnumTipoVeiculoModel tipo;

	public VeiculoModel(String placa, BigDecimal valor, EnumTipoVeiculoModel tipo) {
		this.id = ++gerador;
		this.placa = placa;
		this.valor = valor;
		this.tipo = tipo;
	}

	public void alterar(String placa, BigDecimal valor, EnumTipoVeiculoModel tipo) {
		this.placa = placa;
		this.valor = valor;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getPlaca() {
		return placa;
	}

	public EnumTipoVeiculoModel getTipo() {
		return tipo;
	}
}