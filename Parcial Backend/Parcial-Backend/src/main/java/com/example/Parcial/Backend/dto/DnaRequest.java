package com.example.Parcial.Backend.dto;

import com.example.Parcial.Backend.validators.ValidDna;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DnaRequest {
    @ValidDna
    private String[] dna;
}
