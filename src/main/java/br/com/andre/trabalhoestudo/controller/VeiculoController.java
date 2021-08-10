package br.com.andre.trabalhoestudo.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.andre.trabalhoestudo.armazenamento.SalvaDados;
import br.com.andre.trabalhoestudo.model.EnumTipoVeiculoModel;
import br.com.andre.trabalhoestudo.model.VeiculoModel;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

	public void checarListaVeiculos() {
		if(Objects.isNull(SalvaDados.veiculos) || SalvaDados.veiculos.size() < 1) {
			throw new RuntimeException("Não há nenhum veiculo cadastrado!");
		}
	}

	public VeiculoModel procurarVeiculo(String placa) {
		checarListaVeiculos();
		for(VeiculoModel v: SalvaDados.veiculos) {
			if (placa.equals(v.getPlaca())) {
				return v;
			}
		}

		throw new RuntimeException("Veiculo inexistente!");
	}

	@GetMapping("/validar-placa")
	public String validarCPF(@RequestParam(value = "placa") String placa) {
		String placaValida = "A placa é válida";
		VeiculoModel v = procurarVeiculo(placa);

		if (placa.length() > 0) {
			if (placa.length() < 7) {
				placaValida = "Placa Invalida";
			} else {
				if (!placa.matches("[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2}|[A-Z]{3}[0-9]{4}")) {
					placaValida = "Placa Invalida";
				}
			}
		}

		return placaValida;
	}
	@GetMapping("/cadastrar")
	public VeiculoModel CadastrarVeiculo(@RequestParam String placa,
			@RequestParam BigDecimal valor,
			@RequestParam EnumTipoVeiculoModel tipo) {

		VeiculoModel veiculo = new VeiculoModel(placa, valor, tipo);
		SalvaDados.veiculos.add(veiculo);
		return veiculo;
	}

	@GetMapping("/alterar")
	public VeiculoModel AlterarVeiculo(@RequestParam Long id,
			@RequestParam String placa,
			@RequestParam BigDecimal valor,
			@RequestParam EnumTipoVeiculoModel tipo) {

		for (VeiculoModel veiculo : SalvaDados.veiculos) {
			if (id.equals(veiculo.getId())) {
				veiculo.alterar(placa, valor, tipo);
				return veiculo;
			}
		}
		return null;
	}

	@GetMapping("/excluir")
	public VeiculoModel ExcluirVeiculo(@RequestParam Long id) {
		VeiculoModel veiculo = null;
		for (VeiculoModel v : SalvaDados.veiculos) {
			if (id.equals(v.getId())) {
				veiculo = v;
				break;
			}
		}
		if (Objects.nonNull(veiculo)) {
			SalvaDados.veiculos.remove(veiculo);
			return veiculo;
		}
		return null;
	}



	@GetMapping("/listar")
	public List<VeiculoModel> ListarVeiculo() {
		return SalvaDados.veiculos;
	}
}