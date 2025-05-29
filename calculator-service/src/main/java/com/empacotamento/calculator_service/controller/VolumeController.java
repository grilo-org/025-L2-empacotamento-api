package com.empacotamento.calculator_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empacotamento.calculator_service.dto.PackagingRequest;
import com.empacotamento.calculator_service.service.VolumeService;

@RestController
@RequestMapping("/api/volume")
public class VolumeController {

    private final VolumeService volumeService;

    public VolumeController(VolumeService volumeService) {
        this.volumeService = volumeService;
    }

    @PostMapping("/calculate")
    public double calculateVolume(@RequestBody PackagingRequest.ProdutoRequest dimensions) {
        return volumeService.calculateVolume(dimensions);
    }
}
