package com.innter.pos.patients.services;

import com.innter.pos.patients.DTOs.PatientDoctorDto;
import com.innter.pos.patients.DTOs.PatientDto;
import com.innter.pos.patients.DTOs.PatientEditedDto;
import com.innter.pos.patients.DTOs.PatientResponse;
import com.innter.pos.patients.entities.DoctorEntity;

public interface IPatientService {


    public PatientResponse createPerson(PatientDoctorDto patientDoctorDto);

    public PatientResponse getPatient(Long id);

    public PatientResponse updatePatient(PatientEditedDto patient, Long id);

    public Boolean deletePatient(Long id);

}
