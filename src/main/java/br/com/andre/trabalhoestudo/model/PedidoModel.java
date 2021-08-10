package br.com.andre.trabalhoestudo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoModel {

	private static long gerador = 0;

	private Long id;
	private EnumStatusPedidoModel status;
	private LocalDateTime dataPagamento;
	private LocalDateTime dataPedido;
	private List<ItemPedidoModel> itens;

	public PedidoModel() {
		this.id = ++gerador;
		this.itens = new ArrayList<>();
		this.dataPedido = LocalDateTime.now();
		this.status = EnumStatusPedidoModel.AGUARDANDO_PAGAMENTO;
	}
	public BigDecimal getValorTotal() {
		BigDecimal soma = BigDecimal.ZERO;
		for (ItemPedidoModel i : itens) {
			soma = soma.add(i.getValorTotal());
		}
		return soma;
	}

	public void addVeiculo(VeiculoModel v, Integer qtd) {
		this.itens.add(new ItemPedidoModel(v, qtd));
	}

	public void pagar() {
		this.status = EnumStatusPedidoModel.PAGO;
		this.dataPagamento = LocalDateTime.now();
	}

	public ItemPedidoModel retirarItemPedido(ItemPedidoModel itemPedido) {
		this.itens.remove(itemPedido);
		return itemPedido;
	}

	public Long getId() {
		return id;
	}
	public EnumStatusPedidoModel getStatus() {
		return status;
	}
	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}
	public LocalDateTime getDataPedido() {
		return dataPedido;
	}
	public List<ItemPedidoModel> getItens() {
		return itens;
	}
}