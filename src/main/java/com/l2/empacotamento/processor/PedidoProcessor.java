package com.l2.empacotamento.processor;

import com.l2.empacotamento.dto.request.PackagingRequest;
import com.l2.empacotamento.dto.response.PackagingResponse;
import com.l2.empacotamento.model.Caixa;
import com.l2.empacotamento.restclient.VolumeCalculatorClient;
import com.l2.empacotamento.selector.CaixaSelectorService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PedidoProcessor {

    private final CaixaSelectorService caixaSelector;
    private final VolumeCalculatorClient volumeClient;

    public PedidoProcessor(CaixaSelectorService caixaSelector, 
                         VolumeCalculatorClient volumeClient) {
        this.caixaSelector = caixaSelector;
        this.volumeClient = volumeClient;
    }

    public PackagingResponse.PedidoResponse processar(PackagingRequest.PedidoRequest pedidoRequest) {
        List<PackagingResponse.CaixaResponse> caixas = new ArrayList<>();
        List<PackagingRequest.ProdutoRequest> produtosRestantes = new ArrayList<>(pedidoRequest.getProdutos());

        produtosRestantes.sort(Comparator.comparingDouble(volumeClient::calculateProductVolume).reversed());
        
        List<Caixa> caixasOrdenadas = caixaSelector.caixasOrdenadasPorVolume();

        while (!produtosRestantes.isEmpty()) {
            boolean empacotado = false;

            for (Caixa caixa : caixasOrdenadas) {
                List<PackagingRequest.ProdutoRequest> produtosNaCaixa = new ArrayList<>();
                double volumeDisponivel = caixa.getVolume();

                for (PackagingRequest.ProdutoRequest produto : new ArrayList<>(produtosRestantes)) {
                    if (caixaSelector.produtoCabeNaCaixa(produto, caixa)) {
                        double volumeProduto = volumeClient.calculateProductVolume(produto);
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
