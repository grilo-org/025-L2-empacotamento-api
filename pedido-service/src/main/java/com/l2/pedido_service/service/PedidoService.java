package com.l2.pedido_service.service;


import com.l2.pedido_service.client.BoxServiceClient;
import com.l2.pedido_service.client.VolumeServiceClient;
import com.l2.pedido_service.model.Caixa;
import com.l2.pedido_service.model.request.PackagingRequest;
import com.l2.pedido_service.model.response.PackagingResponse;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PedidoService {

    private final BoxServiceClient boxSelector;
    private final VolumeServiceClient volumeClient;

    public PedidoService(BoxServiceClient boxSelector,
                         VolumeServiceClient volumeClient) {
        this.boxSelector = boxSelector;
        this.volumeClient = volumeClient;
    }

    public PackagingResponse.PedidoResponse processar(PackagingRequest.PedidoRequest pedidoRequest) {
        List<PackagingResponse.CaixaResponse> caixas = new ArrayList<>();
        List<PackagingRequest.ProdutoRequest> produtosRestantes = new ArrayList<>(pedidoRequest.getProdutos());

        produtosRestantes.sort(Comparator.comparingDouble(volumeClient::calcularVolume).reversed());

        List<Caixa> caixasOrdenadas = boxSelector.obterCaixasOrdenadasPorVolume();

        while (!produtosRestantes.isEmpty()) {
            boolean empacotado = false;

            for (Caixa caixa : caixasOrdenadas) {
                List<PackagingRequest.ProdutoRequest> produtosNaCaixa = new ArrayList<>();
                double volumeDisponivel = caixa.getVolume();

                for (PackagingRequest.ProdutoRequest produto : new ArrayList<>(produtosRestantes)) {
                    if (boxSelector.produtoCabeNaCaixa(produto, caixa)) {
                        double volumeProduto = volumeClient.calcularVolume(produto);
                        if (volumeProduto <= volumeDisponivel) {
                            produtosNaCaixa.add(produto);
                            volumeDisponivel -= volumeProduto;
                        }
                    }
                }

                if (!produtosNaCaixa.isEmpty()) {
                    caixas.add(new PackagingResponse.CaixaResponse(
                            caixa.getCaixa_id(),
                            produtosNaCaixa.stream().map(PackagingRequest.ProdutoRequest::getProduto_id).toList(),
                            null
                    ));
                    produtosRestantes.removeAll(produtosNaCaixa);
                    empacotado = true;
                    break;
                }
            }

            if (!empacotado) {
                for (PackagingRequest.ProdutoRequest produto : produtosRestantes) {
                    caixas.add(new PackagingResponse.CaixaResponse(
                            null,
                            List.of(produto.getProduto_id()),
                            "Produto não cabe em nenhuma caixa disponível."
                    ));
                }
                produtosRestantes.clear();
            }
        }

        return new PackagingResponse.PedidoResponse(pedidoRequest.getPedido_id(), caixas);
    }
}