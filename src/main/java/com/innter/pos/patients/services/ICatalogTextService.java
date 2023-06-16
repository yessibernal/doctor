package com.innter.pos.patients.services;

import com.innter.pos.patients.DTOs.CatalogTextRequest;
import com.innter.pos.patients.DTOs.CatalogTextResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatalogTextService {
    CatalogTextResponse saveCatalogText(CatalogTextRequest newCatalogText);

    List<CatalogTextResponse> getCatalogText(String type, Pageable pageable);

    Boolean deleteCatalogText(Long id);
}
