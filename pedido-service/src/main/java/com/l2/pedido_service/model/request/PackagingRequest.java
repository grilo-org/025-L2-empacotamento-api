package com.l2.pedido_service.model.request;

import com.l2.pedido_service.model.Dimensoes;
import lombok.Data;

import java.util.List;

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
