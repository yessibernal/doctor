package com.innter.pos.patients.controllers;

import com.innter.commons.response.SuccessResponse;
import com.innter.pos.patients.DTOs.PatientDoctorDto;
import com.innter.pos.patients.DTOs.PatientEditedDto;
import com.innter.pos.patients.services.IPatientService;
import com.innter.pos.patients.utils.ResponseUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/patient")
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @Autowired
    private ResponseUtils responseUtils;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPerson(@Valid @RequestBody PatientDoctorDto patientDoctorDto) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(patientService.createPerson(patientDoctorDto),
                "El paciente se creo exitosamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdPatientPerson(@Valid @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(patientService.getPatient(id),
                "El paciente con el id:" + id + " se encontró exitosamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.FOUND);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePatient(@Valid @RequestBody PatientEditedDto patient, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(patientService.updatePatient(patient, id),
                "El paciente con el id:" + id + " se actualizo exitosamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePatient(@PathVariable("id") long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(patientService.deletePatient(id),
                "El paciente con el id:" + id + " se elimino correctamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }
}
