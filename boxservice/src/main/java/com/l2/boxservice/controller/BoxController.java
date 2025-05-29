package com.l2.boxservice.controller;


import com.l2.boxservice.dto.request.ProductFitRequest;
import com.l2.boxservice.model.Caixa;
import com.l2.boxservice.service.BoxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boxes")
public class BoxController {

    private final BoxService boxService;

    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @GetMapping
    public List<Caixa> getAllBoxes() {
        return boxService.getAllBoxes();
    }

    @PostMapping("/init-default")
    public void initDefaultBoxes() {
        boxService.initDefaultBoxes();
    }

    @GetMapping("/sorted-by-volume")
    public List<Caixa> getBoxesSortedByVolume() {
        return boxService.getBoxesSortedByVolume();
    }

    @PostMapping("/fits")
    public ResponseEntity<Boolean> productFitsInBox(@RequestBody ProductFitRequest request) {
        boolean fits = boxService.productFitsInBox(
                request.getAltura(),
                request.getLargura(),
                request.getComprimento(),
                request.getCaixaId()
        );
        return ResponseEntity.ok(fits);
    }
}
