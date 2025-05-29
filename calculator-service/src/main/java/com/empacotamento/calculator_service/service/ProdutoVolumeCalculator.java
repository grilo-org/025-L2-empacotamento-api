package com.empacotamento.calculator_service.service;

import com.empacotamento.calculator_service.dto.PackagingRequest;

public interface ProdutoVolumeCalculator {
	double calculateVolume(PackagingRequest.ProdutoRequest dimensions);
}
