package com.l2.empacotamento.selector;

import com.l2.empacotamento.dto.request.PackagingRequest;
import com.l2.empacotamento.model.Caixa;
import com.l2.empacotamento.repository.CaixaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaixaSelectorServiceImpl implements CaixaSelectorService {

    private final CaixaRepository caixaRepository;

    public CaixaSelectorServiceImpl(CaixaRepository caixaRepository) {
        this.caixaRepository = caixaRepository;
    }

    @Override
    public List<Caixa> caixasOrdenadasPorVolume() {
        return caixaRepository.findAllOrderByVolumeAsc();
    }

    @Override
    public boolean produtoCabeNaCaixa(PackagingRequest.ProdutoRequest produto, Caixa caixa) {
        return caixaRepository.findCaixasQueCabemProduto(
                produto.getDimensoes().getAltura(),
                produto.getDimensoes().getLargura(),
                produto.getDimensoes().getComprimento()
        ).stream().anyMatch(c -> c.getId().equals(caixa.getId()));
    }
}
