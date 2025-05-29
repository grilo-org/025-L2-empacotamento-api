package com.empacotamento.calculator_service.service;

import org.springframework.stereotype.Service;

import com.empacotamento.calculator_service.dto.PackagingRequest;



@Service
public class VolumeService implements ProdutoVolumeCalculator {

	public double calculateVolume(PackagingRequest.ProdutoRequest dimensions) {
		return dimensions.getDimensoes().getAltura() * dimensions.getDimensoes().getLargura()
				* dimensions.getDimensoes().getComprimento();
	}
	
}
