package com.l2.boxservice.dto.request;

import lombok.Data;

@Data
public class ProductFitRequest {
    private double altura;
    private double largura;
    private double comprimento;
    private Long caixaId;
}
