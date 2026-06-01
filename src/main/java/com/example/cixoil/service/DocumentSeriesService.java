package com.example.cixoil.service;

import com.example.cixoil.exception.InvalidArgumentException;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.model.DocumentSeries;
import com.example.cixoil.repository.DocumentSeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DocumentSeriesService {

    private final DocumentSeriesRepository documentSeriesRepository;

    @Transactional
    public String  generateNextCorrelative(String series) {
        if (!isValidSeries(series)) throw new InvalidArgumentException("La serie es inválida");
        DocumentSeries documentSeries = requireBySeries(series);
        Long next = documentSeries.getCurrentNumber() + 1;
        documentSeries.setCurrentNumber(next);
        return String.format("%08d", next);
    }

    // Require

    private DocumentSeries requireBySeries(String series) {
        return documentSeriesRepository.findBySeries(series)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la serie: " + series));
    }

    // Validations

    private boolean isValidSeries(String series) {
        return !(series == null || series.isBlank());
    }
}
