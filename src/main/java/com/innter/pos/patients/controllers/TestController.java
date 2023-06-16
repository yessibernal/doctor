package com.innter.pos.patients.controllers;

import com.innter.commons.response.SuccessResponse;
import com.innter.pos.patients.DTOs.TestRequest;
import com.innter.pos.patients.services.ITestService;
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
@RequestMapping(value = "/api/test")
public class TestController {
    @Autowired
    private ITestService testService;

    @Autowired
    private ResponseUtils responseUtils;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTestByName(@Valid @RequestParam String testName,
                                           @RequestParam(required = false) Integer pageIndex,
                                           @RequestParam(required = false) Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        SuccessResponse responseSuccess = responseUtils.successResponseOK(testService.getTestName(testName, pageable),
                "El examen con el nombre:" + testName + "se encontro exitosamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.FOUND);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTestWhitInstruction(@Valid @RequestBody TestRequest newTest) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(testService.saveTestWithInstruction(newTest),
                "El examen del paciente se creo exitosamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCatalogText(@Valid @PathVariable("id") long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(testService.deleteTestWithInstruction(id),
                "El examen con el id: " + id + " se elimino exitosamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.FOUND);
    }

}
