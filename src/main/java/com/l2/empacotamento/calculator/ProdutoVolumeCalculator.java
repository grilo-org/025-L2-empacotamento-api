package com.l2.empacotamento.calculator;

import com.l2.empacotamento.dto.request.PackagingRequest;

public interface ProdutoVolumeCalculator {
    double calcularVolume(PackagingRequest.ProdutoRequest produto);
}
