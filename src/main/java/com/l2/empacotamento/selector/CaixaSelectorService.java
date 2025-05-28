package com.l2.empacotamento.selector;

import com.l2.empacotamento.dto.request.PackagingRequest;
import com.l2.empacotamento.model.Caixa;

import java.util.List;

public interface CaixaSelectorService {
    List<Caixa> caixasOrdenadasPorVolume();
    boolean produtoCabeNaCaixa(PackagingRequest.ProdutoRequest produto, Caixa caixa);
}
