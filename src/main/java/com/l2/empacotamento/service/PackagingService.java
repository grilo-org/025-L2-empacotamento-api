package com.l2.empacotamento.service;

import com.l2.empacotamento.dto.request.PackagingRequest;
import com.l2.empacotamento.dto.response.PackagingResponse;
import com.l2.empacotamento.restclient.BoxServiceClient;
import com.l2.empacotamento.restclient.PedidoServiceClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PackagingService {

    private final BoxServiceClient boxServiceClient;
    private final PedidoServiceClient pedidoProcessor;

    public PackagingService(BoxServiceClient boxServiceClient,
                            PedidoServiceClient pedidoProcessor) {
        this.boxServiceClient = boxServiceClient;
        this.pedidoProcessor = pedidoProcessor;
    }

    public void inicializarCaixasPadrao() {
        boxServiceClient.initDefaultBoxes();
    }

    @Transactional(readOnly = true)
    public PackagingResponse processarPedidos(PackagingRequest request) {
        return pedidoProcessor.processarPedidos(request);
    }
}