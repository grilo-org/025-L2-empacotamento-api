package com.l2.boxservice.controller;


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

    @GetMapping("/fits")
    public ResponseEntity<Boolean> productFitsInBox(
            @RequestParam double altura,
            @RequestParam double largura,
            @RequestParam double comprimento,
            @RequestParam Long caixaId) {

        boolean fits = boxService.productFitsInBox(altura, largura, comprimento, caixaId);
        return ResponseEntity.ok(fits);
    }
}
