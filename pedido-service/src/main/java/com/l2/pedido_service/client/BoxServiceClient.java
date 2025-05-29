package com.l2.pedido_service.client;

import com.l2.pedido_service.model.Caixa;
import com.l2.pedido_service.model.Dimensoes;
import com.l2.pedido_service.model.request.PackagingRequest;
import com.l2.pedido_service.model.request.ProductFitRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class BoxServiceClient {

    private final RestTemplate restTemplate;

    @Value("${box.service.url}")
    private String serviceUrl;

    public BoxServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Caixa> obterCaixasOrdenadasPorVolume() {
        String url = serviceUrl + "/api/boxes/sorted-by-volume";

        ResponseEntity<List<Caixa>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Caixa>>() {}
        );

        return response.getBody();
    }

    public boolean produtoCabeNaCaixa(PackagingRequest.ProdutoRequest produto, Caixa caixa) {
        String url = serviceUrl + "/api/boxes/fits";

        ProductFitRequest request = new ProductFitRequest();
        request.setAltura(produto.getDimensoes().getAltura());
        request.setLargura(produto.getDimensoes().getLargura());
        request.setComprimento(produto.getDimensoes().getComprimento());
        request.setCaixaId(caixa.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProductFitRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Boolean> response = restTemplate.postForEntity(url, entity, Boolean.class);
        return Boolean.TRUE.equals(response.getBody());
    }


}
