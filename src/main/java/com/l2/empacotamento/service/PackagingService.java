package com.l2.empacotamento.service;

import com.l2.empacotamento.dto.request.PackagingRequest;
import com.l2.empacotamento.dto.response.PackagingResponse;
import com.l2.empacotamento.model.Caixa;
import com.l2.empacotamento.processor.PedidoProcessor;
import com.l2.empacotamento.repository.CaixaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class PackagingService {

    private final CaixaRepository caixaRepository;
    private final PedidoProcessor pedidoProcessor;

    public PackagingService(CaixaRepository caixaRepository, PedidoProcessor pedidoProcessor) {
        this.caixaRepository = caixaRepository;
        this.pedidoProcessor = pedidoProcessor;
        inicializarCaixasPadrao();
    }

    @Transactional
    public void inicializarCaixasPadrao() {
        if (caixaRepository.count() == 0) {
            List<Caixa> caixasPadrao = List.of(
                    new Caixa("Caixa 1", 30, 40, 80),
                    new Caixa("Caixa 2", 80, 50, 40),
                    new Caixa("Caixa 3", 50, 80, 60)
            );
            caixaRepository.saveAll(caixasPadrao);
        }
    }

    @Transactional(readOnly = true)
    public PackagingResponse processarPedidos(PackagingRequest request) {
        var pedidos = request.getPedidos().stream()
                .map(pedidoProcessor::processar)
                .toList();

        return new PackagingResponse(pedidos);
    }
}