package com.l2.empacotamento.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackagingResponse {
    private List<PedidoResponse> pedidos;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PedidoResponse {
        private Long pedido_id;
        private List<CaixaResponse> caixas;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CaixaResponse {
        private String caixa_id;
        private List<String> produtos;
        private String observacao;
    }
}
