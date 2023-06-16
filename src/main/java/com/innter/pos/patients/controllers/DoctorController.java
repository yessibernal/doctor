package com.innter.pos.patients.controllers;

import com.innter.commons.response.Success;
import com.innter.pos.patients.DTOs.DoctorEditedRequest;
import com.innter.pos.patients.DTOs.DoctorRequest;
import com.innter.pos.patients.DTOs.PatientDto;
import com.innter.pos.patients.services.IDoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/doctor")
public class DoctorController {

    @Autowired
    private IDoctorService doctorService;


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDoctor(@Valid @RequestBody DoctorRequest doctorRequest) {
        try {
            Success responseSuccess = new Success();
            responseSuccess.setCode(HttpStatus.CREATED.value());
            responseSuccess.setData(doctorService.saveDoctor(doctorRequest));
            responseSuccess.setMessage("El doctor se creo exitosamente.");
            responseSuccess.setUUID("hucsdoicjsokj3827equwhdil23");
            return new ResponseEntity<>(responseSuccess, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDoctor(@Valid @RequestBody DoctorEditedRequest newDoctorEdited, @PathVariable long id) {
        try {
        Success responseSuccess = new Success();
        responseSuccess.setCode(HttpStatus.OK.value());
        responseSuccess.setData(doctorService.editedDoctor(newDoctorEdited, id));
        responseSuccess.setMessage("El doctor con el id:"+ id +" se actualizo exitosamente.");
        responseSuccess.setUUID("hucsdoicjsokj3827equwhdil23");
            return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
