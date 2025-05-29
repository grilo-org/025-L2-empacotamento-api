package com.l2.pedido_service.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Dimensoes {
    private double altura;
    private double largura;
    private double comprimento;
}
