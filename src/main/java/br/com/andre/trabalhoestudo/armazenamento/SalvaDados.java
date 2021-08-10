package br.com.andre.trabalhoestudo.armazenamento;

import java.util.ArrayList;
import java.util.List;

import br.com.andre.trabalhoestudo.model.PedidoModel;
import br.com.andre.trabalhoestudo.model.PessoaModel;
import br.com.andre.trabalhoestudo.model.VeiculoModel;

public class SalvaDados {

	private SalvaDados() { }

	public static List<VeiculoModel> veiculos = new ArrayList<>();
	public static List<PedidoModel> pedidos = new ArrayList<>();
	public static List<PessoaModel> pessoas = new ArrayList<>();
}