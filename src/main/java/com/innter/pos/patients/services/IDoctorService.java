package com.innter.pos.patients.services;

import com.innter.pos.patients.DTOs.DoctorEditedRequest;
import com.innter.pos.patients.DTOs.DoctorRequest;
import com.innter.pos.patients.DTOs.DoctorResponse;

public interface IDoctorService {

    public DoctorResponse saveDoctor (DoctorRequest newDoctor);

    public DoctorResponse editedDoctor (DoctorEditedRequest newDoctorEdited, Long id);

}
