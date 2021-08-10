package br.com.andre.trabalhoestudo.model;

import java.math.BigDecimal;

public class ItemPedidoModel {
	private static long gerador = 0;

	private Long id;
	private VeiculoModel veiculo;
	private Integer qtd;
	private BigDecimal valorTotal;

	public ItemPedidoModel(VeiculoModel veiculo, Integer qtd) {
		this.id = ++gerador;
		this.veiculo = veiculo;
		this.qtd = qtd;
		this.valorTotal = veiculo.getValor().multiply(BigDecimal.valueOf(qtd));
	}

	public Long getId() {
		return id;
	}

	public VeiculoModel getVeiculo() {
		return veiculo;
	}

	public Integer getQtd() {
		return qtd;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}
}