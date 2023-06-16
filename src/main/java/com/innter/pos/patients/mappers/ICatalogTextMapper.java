package com.innter.pos.patients.mappers;

import com.innter.pos.patients.DTOs.CatalogTextRequest;
import com.innter.pos.patients.DTOs.CatalogTextResponse;
import com.innter.pos.patients.entities.CatalogTextEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICatalogTextMapper {

    CatalogTextResponse catalogTextToCatalogTextResponse(CatalogTextEntity catalogText);

    CatalogTextEntity catalogTextRequestToCatalogText(CatalogTextRequest catalogTextRequest);
}
