package com.l2.empacotamento.restclient;

import com.l2.empacotamento.dto.request.PackagingRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VolumeCalculatorClient {

    private final RestTemplate restTemplate;
    
    @Value("${volume.calculator.service.url}")
    private String serviceUrl;

    public VolumeCalculatorClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double calculateProductVolume(PackagingRequest.ProdutoRequest produto) {
        return restTemplate.postForObject(
            serviceUrl + "/api/volume/calculate", 
            produto, 
            Double.class
        );
    }
}
