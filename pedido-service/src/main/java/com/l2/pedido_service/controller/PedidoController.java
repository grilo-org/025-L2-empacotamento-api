package com.l2.pedido_service.controller;


import com.l2.pedido_service.model.request.PackagingRequest;
import com.l2.pedido_service.model.response.PackagingResponse;
import com.l2.pedido_service.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/processar")
    public ResponseEntity<PackagingResponse> processarPedidos(@RequestBody PackagingRequest request) {
        PackagingResponse response = new PackagingResponse(
                request.getPedidos().stream()
                        .map(pedidoService::processar)
                        .toList()
        );
        return ResponseEntity.ok(response);
    }
}
