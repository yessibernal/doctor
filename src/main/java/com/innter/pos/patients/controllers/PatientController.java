package com.innter.pos.patients.controllers;

import com.innter.commons.response.Success;
import com.innter.pos.patients.DTOs.PatientDoctorDto;
import com.innter.pos.patients.DTOs.PatientDto;
import com.innter.pos.patients.DTOs.PatientEditedDto;
import com.innter.pos.patients.entities.DoctorEntity;
import com.innter.pos.patients.services.IPatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/patient")
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPerson(@Valid @RequestBody PatientDoctorDto patientDoctorDto) {
        System.out.println("CONTROLLER:PatientDoctorDto--"+patientDoctorDto);
        try {
            Success responseSuccess = new Success();
            responseSuccess.setCode(HttpStatus.CREATED.value());
            responseSuccess.setData(patientService.createPerson(patientDoctorDto));
            responseSuccess.setMessage("El paciente se creo exitosamente.");
            responseSuccess.setUUID("hucsdoicjsokj3827equwhdil23");
            return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByIdPatientPerson(@Valid @PathVariable long id) {
        try {
            Success responseSuccess = new Success();
            responseSuccess.setCode(HttpStatus.FOUND.value());
            responseSuccess.setData(patientService.getPatient(id));
            responseSuccess.setMessage("El paciente con el id:" + id + " se encontró exitosamente.");
            responseSuccess.setUUID("hucsdoicjsokj3827equwhdil23");
            return new ResponseEntity<>(responseSuccess, HttpStatus.FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePatient(@Valid @RequestBody PatientEditedDto patient, @PathVariable long id) {
        Success responseSuccess = new Success();
        responseSuccess.setCode(HttpStatus.OK.value());
        responseSuccess.setData(patientService.updatePatient(patient, id));
        responseSuccess.setMessage("El paciente con el id:" + id + " se actualizo exitosamente.");
        responseSuccess.setUUID("hucsdoicjsokj3827equwhdil23");
        try {
            return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePatient(@PathVariable("id") long id) {
        Success responseSuccess = new Success();
        responseSuccess.setCode(HttpStatus.OK.value());
        responseSuccess.setData(patientService.deletePatient(id));
        responseSuccess.setMessage("El paciente con el id:" + id + " se elimino correctamente.");
        responseSuccess.setUUID("hucsdoicjsokj3827equwhdil23");
        try {
            return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
