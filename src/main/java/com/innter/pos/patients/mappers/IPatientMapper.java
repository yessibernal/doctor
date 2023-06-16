package com.innter.pos.patients.mappers;

import com.innter.pos.patients.DTOs.PatientRequest;
import com.innter.pos.patients.DTOs.PatientResponse;
import com.innter.pos.patients.entities.PatientEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)

public interface IPatientMapper {

    PatientResponse patientToPatientResponse (PatientEntity patient);

    PatientEntity patientRequestToPatient (PatientRequest patientRequest);
}
