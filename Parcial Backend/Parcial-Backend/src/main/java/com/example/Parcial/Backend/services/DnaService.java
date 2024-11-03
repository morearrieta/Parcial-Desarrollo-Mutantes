package com.example.Parcial.Backend.services;

import com.example.Parcial.Backend.repositories.DnaRepository;
import com.example.Parcial.Backend.entities.Dna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DnaService {
    private final DnaRepository dnaRepository;
    private static final int SEQUENCE_LENGTH = 4;

    @Autowired
    public DnaService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public static boolean isMutant(String[] dna) {
        int n = dna.length;
        int sequenceCount = 0;

        sequenceCount += checkRows(dna, n);
        if (sequenceCount > 1) return true;

        sequenceCount += checkColumns(dna, n);
        if (sequenceCount > 1) return true;

        sequenceCount += checkDiagonals(dna, n);
        return sequenceCount > 1;
    }

    public static int checkRows(String[] dna, int n) {
        int sequenceCount = 0;

        for (int i = 0; i < n; i++) {
            int count = 1;
            for (int j = 1; j < n; j++) {
                if (dna[i].charAt(j) == dna[i].charAt(j - 1)) {
                    count++;
                    if (count == SEQUENCE_LENGTH) {
                        sequenceCount++;
                        if (sequenceCount > 1) return sequenceCount;
                    }
                } else {
                    count = 1;
                }
            }
        }
        return sequenceCount;
    }

    private static int checkColumns(String[] dna, int n) {
        int sequenceCount = 0;

        for (int j = 0; j < n; j++) {
            int count = 1;
            for (int i = 1; i < n; i++) {
                if (dna[i].charAt(j) == dna[i - 1].charAt(j)) {
                    count++;
                    if (count == SEQUENCE_LENGTH) {
                        sequenceCount++;
                        if (sequenceCount > 1) return sequenceCount;
                    }
                } else {
                    count = 1;
                }
            }
        }
        return sequenceCount;
    }

    private static int checkDiagonals(String[] dna, int n) {
        int sequenceCount = 0;
        for (int i = 0; i <= n - SEQUENCE_LENGTH; i++) {
            for (int j = 0; j <= n - SEQUENCE_LENGTH; j++) {
                if (checkDiagonal(dna, i, j, 1, 1, n)) {
                    sequenceCount++;
                    if (sequenceCount > 1) return sequenceCount;
                }
            }
        }
        return sequenceCount;
    }

    private static boolean checkDiagonal(String[] dna, int x, int y, int dx, int dy, int n) {
        char first = dna[x].charAt(y);
        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (x + i * dx >= n || y + i * dy >= n || y + i * dy < 0 || dna[x + i * dx].charAt(y + i * dy) != first) {
                return false;
            }
        }
        return true;
    }

    public boolean analyzeDna(String[] dna) {
        String dnaSequence = String.join(",", dna);

        Optional<Dna> existingDna = dnaRepository.findByDna(dnaSequence);
        if (existingDna.isPresent()) {
            return existingDna.get().getIsMutant();
        }

        boolean isMutant = isMutant(dna);
        Dna dnaEntity = Dna.builder()
                .dna(dnaSequence)
                .isMutant(isMutant)
                .build();
        dnaRepository.save(dnaEntity);

        return isMutant;
    }
}
