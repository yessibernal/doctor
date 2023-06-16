package com.innter.pos.patients.controllers;

import com.innter.commons.response.SuccessResponse;
import com.innter.pos.patients.DTOs.TestInstructionRequest;
import com.innter.pos.patients.services.ITestInstructionService;
import com.innter.pos.patients.utils.ResponseUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/api/testInstruction")
public class TestInstructionController {
    @Autowired
    private ITestInstructionService testInstructionService;

    @Autowired
    private ResponseUtils responseUtils;


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTestInstructionById(@Valid @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(testInstructionService.getTestInstruction(id),
                "Las instrucciones del paciente con el id:" + id + " se encontraron exitosamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.FOUND);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTestInstruction(@Valid @RequestBody TestInstructionRequest newTestInstructionRequest, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(testInstructionService.editedTestInstruction(newTestInstructionRequest, id),
                "Las instrucciones con el id:" + id + " se actualizaron exitosamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);

    }
}
