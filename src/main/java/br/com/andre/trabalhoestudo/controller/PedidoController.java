package br.com.andre.trabalhoestudo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.andre.trabalhoestudo.armazenamento.SalvaDados;
import br.com.andre.trabalhoestudo.model.EnumStatusPedidoModel;
import br.com.andre.trabalhoestudo.model.ItemPedidoModel;
import br.com.andre.trabalhoestudo.model.PedidoModel;
import br.com.andre.trabalhoestudo.model.VeiculoModel;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@GetMapping("/cadastrar")
	public PedidoModel CadastrarPedido() {
		PedidoModel pedido = new PedidoModel();
		SalvaDados.pedidos.add(pedido);
		return pedido;
	}

	@GetMapping("/adicionar")
	public PedidoModel AdicionarProduto(@RequestParam Long idPedido, 
			@RequestParam Long idVeiculo,
			@RequestParam Integer qtd) {
		for (PedidoModel pedido : SalvaDados.pedidos) {
			if (idPedido.equals(pedido.getId())) {
				for (VeiculoModel veiculo: SalvaDados.veiculos) {
					if (idVeiculo.equals(veiculo.getId())) {
						pedido.addVeiculo(veiculo, qtd);
						return pedido;						
					}	
				}
			}
		}
		return null;
	}

	@GetMapping("/retirar")
	public PedidoModel RemoverItemProduto(@RequestParam(value = "id") Long id_item) {
		for (PedidoModel pedido : SalvaDados.pedidos) {

			for (ItemPedidoModel item : pedido.getItens()) {
				if (id_item.equals(item.getId())) {
					pedido.retirarItemPedido(item);
					return pedido;
				}				
			}
		}

		return null;
	}

	@GetMapping("/pagar")
	public PedidoModel ConfirmarPagamento(@RequestParam Long id) {
		for (PedidoModel pedido : SalvaDados.pedidos) {
			if (id.equals(pedido.getId())) {
				pedido.pagar();
				return pedido;
			}
		}
		return null;
	}


	@GetMapping("/excluir")
	public PedidoModel ExcluirPedido(@RequestParam Long id) {
		for (PedidoModel pedido : SalvaDados.pedidos) {
			if (id.equals(pedido.getId())) {
				if (EnumStatusPedidoModel.AGUARDANDO_PAGAMENTO.equals(pedido.getStatus())) {
					SalvaDados.pedidos.remove(pedido);
					return pedido;
				}
			}
		}
		return null;
	}

	@GetMapping("/listar")
	public List<PedidoModel> ListarPedidos(){
		return SalvaDados.pedidos;
	}
}