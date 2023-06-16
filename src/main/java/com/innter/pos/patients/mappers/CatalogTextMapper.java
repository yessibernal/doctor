package com.innter.pos.patients.mappers;

import com.innter.pos.patients.DTOs.CatalogTextRequest;
import com.innter.pos.patients.DTOs.CatalogTextResponse;
import com.innter.pos.patients.entities.CatalogTextEntity;
import org.springframework.stereotype.Component;

@Component
public class CatalogTextMapper implements ICatalogTextMapper {
    @Override
    public CatalogTextResponse catalogTextToCatalogTextResponse(CatalogTextEntity catalogText) {
        CatalogTextResponse catalogTextResponse = new CatalogTextResponse();
        catalogTextResponse.setId(catalogText.getId());
        catalogTextResponse.setType(catalogText.getType().toLowerCase());
        catalogTextResponse.setValue(catalogText.getValue().toLowerCase());
        return catalogTextResponse;
    }

    @Override
    public CatalogTextEntity catalogTextRequestToCatalogText(CatalogTextRequest catalogTextRequest) {
        CatalogTextEntity catalogText = new CatalogTextEntity();
        catalogText.setType(catalogTextRequest.getType().toLowerCase());
        catalogText.setValue(catalogTextRequest.getValue().toLowerCase());
        return catalogText;
    }
}
