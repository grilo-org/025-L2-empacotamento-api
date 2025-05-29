package com.l2.boxservice.service;


import com.l2.boxservice.model.Caixa;
import com.l2.boxservice.repository.CaixaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BoxService {

    private final CaixaRepository caixaRepository;

    public BoxService(CaixaRepository CaixaRepository) {
        this.caixaRepository = CaixaRepository;
    }

    @Transactional
    public void initDefaultBoxes() {
        if (caixaRepository.count() == 0) {
            List<Caixa> defaultBoxes = List.of(
                    new Caixa("Caixa 1", 30, 40, 80),
                    new Caixa("Caixa 2", 80, 50, 40),
                    new Caixa("Caixa 3", 50, 80, 60)
            );
            caixaRepository.saveAll(defaultBoxes);
        }
    }

    @Transactional(readOnly = true)
    public List<Caixa> getAllBoxes() {
        return caixaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Caixa> getBoxesSortedByVolume() {
        return caixaRepository.findAllOrderByVolumeAsc();
    }

    public boolean productFitsInBox(double altura, double largura, double comprimento, Long caixaId) {
        Caixa caixa = caixaRepository.findById(caixaId)
                .orElseThrow(() -> new RuntimeException("Caixa não encontrada"));

        return altura <= caixa.getAltura() &&
                largura <= caixa.getLargura() &&
                comprimento <= caixa.getComprimento();
    }
}