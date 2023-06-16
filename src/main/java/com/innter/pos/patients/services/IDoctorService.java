package com.innter.pos.patients.services;

import com.innter.pos.patients.DTOs.DoctorEditedRequest;
import com.innter.pos.patients.DTOs.DoctorRequest;
import com.innter.pos.patients.DTOs.DoctorResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IDoctorService {

    DoctorResponse saveDoctor(DoctorRequest newDoctor) ;

    DoctorResponse editedDoctor(DoctorEditedRequest newDoctorEdited, Long id);

    DoctorResponse getDoctorCedula(String cedula);

    void save (MultipartFile file, Long idDoctor);

}
