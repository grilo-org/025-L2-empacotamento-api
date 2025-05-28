package com.l2.empacotamento.calculator;

import com.l2.empacotamento.dto.request.PackagingRequest;
import org.springframework.stereotype.Component;

@Component
public class ProdutoVolumeCalculatorImpl implements ProdutoVolumeCalculator {

    @Override
    public double calcularVolume(PackagingRequest.ProdutoRequest produto) {
        var d = produto.getDimensoes();
        return d.getAltura() * d.getLargura() * d.getComprimento();
    }

}
