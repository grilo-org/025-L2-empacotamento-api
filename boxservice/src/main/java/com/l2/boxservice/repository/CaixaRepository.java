package com.l2.boxservice.repository;

import com.l2.boxservice.model.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long> {

    @Query("SELECT c FROM Caixa c ORDER BY (c.altura * c.largura * c.comprimento) ASC")
    List<Caixa> findAllOrderByVolumeAsc();

    @Query("SELECT c FROM Caixa c WHERE c.altura >= :altura AND c.largura >= :largura AND c.comprimento >= :comprimento " +
            "ORDER BY (c.altura * c.largura * c.comprimento) ASC")
    List<Caixa> findCaixasQueCabemProduto(double altura, double largura, double comprimento);
}
