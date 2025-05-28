package com.l2.empacotamento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Caixa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caixa_id;
    private double altura;
    private double largura;
    private double comprimento;

    public double getVolume() {
        return altura * largura * comprimento;
    }

    public Caixa() {}

    public Caixa(String caixa_id, double altura, double largura, double comprimento) {
        this.caixa_id = caixa_id;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
    }

}
