package com.l2.empacotamento.restclient;

import com.l2.empacotamento.model.Caixa;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class BoxServiceClient {

    private final RestTemplate restTemplate;

    @Value("${box.service.url}")
    private String serviceUrl;

    public BoxServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Caixa> getBoxesSortedByVolume() {
        String url = serviceUrl + "/api/boxes/sorted-by-volume";

        ResponseEntity<List<Caixa>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Caixa>>() {}
        );

        return response.getBody();
    }

    public void initDefaultBoxes() {
        String url = serviceUrl + "/api/boxes/init-default";
        restTemplate.postForObject(url, null, Void.class);
    }
}