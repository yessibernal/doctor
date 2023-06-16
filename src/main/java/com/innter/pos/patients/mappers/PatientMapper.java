package com.innter.pos.patients.mappers;

import com.innter.pos.patients.DTOs.PatientRequest;
import com.innter.pos.patients.DTOs.PatientResponse;
import com.innter.pos.patients.entities.PatientEntity;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper  implements IPatientMapper{

    @Override
    public PatientResponse patientToPatientResponse(PatientEntity patient) {
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setId(patient.getId());
        patientResponse.setFileNumber(patient.getFileNumber());
        patientResponse.setDiagnostic(patient.getDiagnostic());
        return patientResponse;
    }

    @Override
    public PatientEntity patientRequestToPatient(PatientRequest patientRequest) {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFileNumber(patientRequest.getFileNumber());
        patientEntity.setDiagnostic(patientRequest.getDiagnostic());
        return patientEntity;
    }
}
