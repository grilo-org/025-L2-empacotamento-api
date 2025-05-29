package com.l2.pedido_service.client;

import com.l2.pedido_service.model.Dimensoes;
import com.l2.pedido_service.model.request.PackagingRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class VolumeServiceClient {

    private final RestTemplate restTemplate;

    @Value("${volume.service.url}")
    private String serviceUrl;

    public VolumeServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double calcularVolume(PackagingRequest.ProdutoRequest produto) {
        String url = serviceUrl + "/api/volume/calculate";
        return restTemplate.postForObject(url, produto, Double.class);
    }

}
