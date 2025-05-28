package com.l2.empacotamento.controller;

import com.l2.empacotamento.dto.request.PackagingRequest;
import com.l2.empacotamento.dto.response.PackagingResponse;
import com.l2.empacotamento.service.PackagingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/packaging")
public class PackagingController {

    private final PackagingService packagingService;

    public PackagingController(PackagingService packagingService) {
        this.packagingService = packagingService;
    }

    @PostMapping
    public ResponseEntity<PackagingResponse> processarPedidos(@RequestBody PackagingRequest request) {
        PackagingResponse response = packagingService.processarPedidos(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/init-default-boxes")
    public ResponseEntity<Void> inicializarCaixasPadrao() {
        packagingService.inicializarCaixasPadrao();
        return ResponseEntity.ok().build();
    }
}
