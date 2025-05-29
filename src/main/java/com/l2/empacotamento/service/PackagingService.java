package com.l2.empacotamento.service;

import com.l2.empacotamento.dto.request.PackagingRequest;
import com.l2.empacotamento.dto.response.PackagingResponse;
import com.l2.empacotamento.processor.PedidoProcessor;
import com.l2.empacotamento.restclient.BoxServiceClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PackagingService {

    private final BoxServiceClient boxServiceClient;
    private final PedidoProcessor pedidoProcessor;

    public PackagingService(BoxServiceClient boxServiceClient,
                            PedidoProcessor pedidoProcessor) {
        this.boxServiceClient = boxServiceClient;
        this.pedidoProcessor = pedidoProcessor;
    }

    public void inicializarCaixasPadrao() {
        boxServiceClient.initDefaultBoxes();
    }

    @Transactional(readOnly = true)
    public PackagingResponse processarPedidos(PackagingRequest request) {
        return new PackagingResponse(
                request.getPedidos().stream()
                        .map(pedidoProcessor::processar)
                        .toList()
        );
    }
}