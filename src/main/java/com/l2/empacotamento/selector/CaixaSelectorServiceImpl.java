package com.l2.empacotamento.selector;

import com.l2.empacotamento.dto.request.PackagingRequest;
import com.l2.empacotamento.model.Caixa;
import com.l2.empacotamento.restclient.BoxServiceClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaixaSelectorServiceImpl implements CaixaSelectorService {

    private final BoxServiceClient boxServiceClient;

    public CaixaSelectorServiceImpl(BoxServiceClient boxServiceClient) {
        this.boxServiceClient = boxServiceClient;
    }

    @Override
    public List<Caixa> caixasOrdenadasPorVolume() {
        return boxServiceClient.getBoxesSortedByVolume();
    }

    @Override
    public boolean produtoCabeNaCaixa(PackagingRequest.ProdutoRequest produto, Caixa caixa) {
        return produto.getDimensoes().getAltura() <= caixa.getAltura() &&
                produto.getDimensoes().getLargura() <= caixa.getLargura() &&
                produto.getDimensoes().getComprimento() <= caixa.getComprimento();
    }
}
