package com.l2.empacotamento.restclient;

import com.l2.empacotamento.dto.request.PackagingRequest;
import com.l2.empacotamento.dto.response.PackagingResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PedidoServiceClient {

    private final RestTemplate restTemplate;

    @Value("${pedido.service.url}")
    private String serviceUrl;

    public PedidoServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PackagingResponse processarPedidos(PackagingRequest request) {
        String url = serviceUrl + "/api/pedidos/processar";
        return restTemplate.postForObject(url, request, PackagingResponse.class);
    }
}
