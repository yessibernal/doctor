package com.innter.pos.patients.controllers;

import com.innter.commons.response.SuccessResponse;
import com.innter.pos.patients.DTOs.CatalogTextRequest;
import com.innter.pos.patients.services.ICatalogTextService;
import com.innter.pos.patients.utils.ResponseUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/api/catalogText")

public class CatalogTextController {


    @Autowired
    private ICatalogTextService catalogTextService;

    @Autowired
    private ResponseUtils responseUtils;


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDoctor(@Valid @RequestBody CatalogTextRequest catalogTextRequest) {

        SuccessResponse responseSuccess = responseUtils.successResponseCreate(catalogTextService.saveCatalogText(catalogTextRequest),
                "El catalogo se creo exitosamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCatalogTextByType(@Valid @RequestParam String type,
                                                  @RequestParam(required = false) Integer pageIndex,
                                                  @RequestParam(required = false) Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(catalogTextService.getCatalogText(type, pageable),
                "El catalogo de tipo:" + type + " se encontro exitosamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.FOUND);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCatalogText(@Valid @PathVariable("id") long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(catalogTextService.deleteCatalogText(id),
                "El tipo de catalogo con el id:" + id + " se elimino exitosamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.FOUND);
    }
}
