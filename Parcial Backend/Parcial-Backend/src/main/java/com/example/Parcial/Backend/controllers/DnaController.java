package com.example.Parcial.Backend.controllers;

import com.example.Parcial.Backend.services.DnaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Parcial.Backend.dto.DnaRequest;
import com.example.Parcial.Backend.dto.DnaResponse;
import org.springframework.http.HttpStatus;



@RestController
@RequestMapping("/mutant")
public class DnaController {
    private final DnaService dnaService;

    public DnaController(DnaService dnaService){
        this.dnaService = dnaService;
    }

    @PostMapping
    public ResponseEntity<DnaResponse> checkMutant(DnaRequest dnaRequest){
        boolean isMutant = dnaService.analyzeDna(dnaRequest.getDna());
        DnaResponse dnaResponse = new DnaResponse(isMutant);
        if (isMutant){
            return ResponseEntity.ok(dnaResponse);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dnaResponse);
        }
    }


}
