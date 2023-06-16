package com.innter.pos.patients.controllers;

import com.innter.commons.response.SuccessResponse;
import com.innter.pos.patients.DTOs.DoctorEditedRequest;
import com.innter.pos.patients.DTOs.DoctorRequest;
import com.innter.pos.patients.services.IDoctorImageService;
import com.innter.pos.patients.services.IDoctorService;
import com.innter.pos.patients.utils.ResponseUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/doctor")
@CrossOrigin("*")
public class DoctorController {

    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private IDoctorImageService doctorImageService;

    @Autowired
    private ResponseUtils responseUtils;


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDoctor(@Valid @RequestBody DoctorRequest doctorRequest) {
        SuccessResponse responseSuccess = responseUtils.successResponseCreate(doctorService.saveDoctor(doctorRequest),
                "El doctor se creo exitosamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);

    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDoctor(@Valid @RequestBody DoctorEditedRequest newDoctorEdited, @PathVariable long id) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(doctorService.editedDoctor(newDoctorEdited, id),
                "El doctor con el id:" + id + " se actualizo exitosamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
    }


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDoctorByCedula(@Valid @RequestParam String cedula) {
        SuccessResponse responseSuccess = responseUtils.successResponseOK(doctorService.getDoctorCedula(cedula),
                "El doctor con la cédula:" + cedula + "se encontro exitosamente.");
        return new ResponseEntity<>(responseSuccess, HttpStatus.FOUND);

    }

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("files") MultipartFile files,
                                        @RequestParam Long idDoctor) {
        doctorService.save(files, idDoctor);
        SuccessResponse responseSuccess = responseUtils.successResponseOKVoid("La imagen del doctor se subio correctamente.");
        return ResponseEntity.status(HttpStatus.OK).body(responseSuccess);
    }

    @GetMapping(value = "/classpath", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> classpathFile(@RequestParam String imageName) throws IOException {
        doctorImageService.getImageDoctor(imageName);
        byte[] bytes = doctorImageService.getImageDoctor(imageName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(bytes);
    }

}
