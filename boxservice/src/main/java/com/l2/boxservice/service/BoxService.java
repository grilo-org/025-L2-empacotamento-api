package com.l2.boxservice.service;


import com.l2.boxservice.model.Caixa;
import com.l2.boxservice.repository.CaixaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BoxService {

    private final CaixaRepository CaixaRepository;

    public BoxService(CaixaRepository CaixaRepository) {
        this.CaixaRepository = CaixaRepository;
    }

    @Transactional
    public void initDefaultBoxes() {
        if (CaixaRepository.count() == 0) {
            List<Caixa> defaultBoxes = List.of(
                    new Caixa("Caixa 1", 30, 40, 80),
                    new Caixa("Caixa 2", 80, 50, 40),
                    new Caixa("Caixa 3", 50, 80, 60)
            );
            CaixaRepository.saveAll(defaultBoxes);
        }
    }

    @Transactional(readOnly = true)
    public List<Caixa> getAllBoxes() {
        return CaixaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Caixa> getBoxesSortedByVolume() {
        return CaixaRepository.findAllOrderByVolumeAsc();
    }
}