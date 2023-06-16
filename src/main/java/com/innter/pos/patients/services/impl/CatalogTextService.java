package com.innter.pos.patients.services.impl;

import com.innter.pos.patients.DAOs.CatalogTextDAO;
import com.innter.pos.patients.DAOs.CatalogTypeDAO;
import com.innter.pos.patients.DTOs.CatalogTextRequest;
import com.innter.pos.patients.DTOs.CatalogTextResponse;
import com.innter.pos.patients.entities.CatalogTextEntity;
import com.innter.pos.patients.exceptions.BadRequestInnter;
import com.innter.pos.patients.exceptions.InternalServerErrorInnter;
import com.innter.pos.patients.mappers.CatalogTextMapper;
import com.innter.pos.patients.services.ICatalogTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogTextService implements ICatalogTextService {


    @Autowired
    private CatalogTextDAO catalogTextDAO;

    @Autowired
    private CatalogTextMapper catalogTextMapper;

    @Autowired
    private CatalogTypeDAO catalogTypeDAO;

    @Override
    @Transactional
    public CatalogTextResponse saveCatalogText(CatalogTextRequest newCatalogText) {
        String catalogTextRequest = newCatalogText.getType().toLowerCase();
        try {
            if (catalogTypeDAO.findByType(catalogTextRequest) == null) {
                throw new InternalServerErrorInnter("P-500", HttpStatus.BAD_REQUEST, "Catalogo no creado.");
            } else {
                CatalogTextEntity catalogText = catalogTextMapper.catalogTextRequestToCatalogText(newCatalogText);
                catalogTextDAO.save(catalogText);
                CatalogTextResponse catalogTextResponse = catalogTextMapper.catalogTextToCatalogTextResponse(catalogText);
                return catalogTextResponse;
            }
        } catch (Exception e) {
            throw new BadRequestInnter("P-400", HttpStatus.BAD_REQUEST, "El catalogo no se pudo crear con exito.");
        }
    }

    @Override
    @Transactional
    public List<CatalogTextResponse> getCatalogText(String type, Pageable pageable) {
        try {
            List<CatalogTextEntity> catalogText = catalogTextDAO.getCatalogTextByType(type, pageable);
            List<CatalogTextResponse> catalogTextResponse = new ArrayList<>();
            catalogText.stream().forEach(catalogText1 -> {
                catalogTextResponse.add(catalogTextMapper.catalogTextToCatalogTextResponse(catalogText1));
            });
            return catalogTextResponse;

        } catch (Exception e) {
            throw new BadRequestInnter("P-400", HttpStatus.BAD_REQUEST, "El catalogo no se pudo encontrar.");
        }
    }

    @Override
    public Boolean deleteCatalogText(Long id) {
        if (catalogTextDAO.existsById(id)) {
            catalogTextDAO.deleteById(id);
            return Boolean.TRUE;
        } else {
            throw new BadRequestInnter("P-400", HttpStatus.BAD_REQUEST, "El catalogo no se pudo eliminar.");
        }
    }
}
