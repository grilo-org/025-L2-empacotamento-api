package com.empacotamento.calculator_service.dto;

import java.util.List;

import lombok.Data;

@Data
public class PackagingRequest {
	private List<PedidoRequest> pedidos;

	@Data
	public static class PedidoRequest {
		private Long pedido_id;
		private List<ProdutoRequest> produtos;
	}

	@Data
	public static class ProdutoRequest {
		private String produto_id;
		private Dimensoes dimensoes;
	}
}
