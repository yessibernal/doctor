package com.innter.pos.patients.mappers;

import com.innter.pos.patients.DTOs.DoctorRequest;
import com.innter.pos.patients.DTOs.DoctorResponse;
import com.innter.pos.patients.entities.DoctorEntity;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper implements IDoctorMapper {
    @Override
    public DoctorResponse doctorToDoctorResponse(DoctorEntity doctor) {
        DoctorResponse doctorResponse = new DoctorResponse();
        doctorResponse.setId(doctor.getId());
        doctorResponse.setName(doctor.getName().toLowerCase());
        doctorResponse.setLastName(doctor.getLastName().toLowerCase());
        doctorResponse.setSurname(doctor.getSurname().toLowerCase());
        doctorResponse.setCedula(doctor.getCedula().toLowerCase());
        doctorResponse.setPhone(doctor.getPhone().toLowerCase());
        doctorResponse.setEmail(doctor.getEmail().toLowerCase());
        doctorResponse.setSpecialty(doctor.getSpecialty().toLowerCase());
        return doctorResponse;
    }

    @Override
    public DoctorEntity doctorRequestToDoctor(DoctorRequest doctorRequest) {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setName(doctorRequest.getName().toLowerCase());
        doctor.setLastName(doctorRequest.getLastName().toLowerCase());
        doctor.setSurname(doctorRequest.getSurname().toLowerCase());
        doctor.setCedula(doctorRequest.getCedula().toLowerCase());
        doctor.setPhone(doctorRequest.getPhone().toLowerCase());
        doctor.setEmail(doctorRequest.getEmail().toLowerCase());
        doctor.setSpecialty(doctorRequest.getSpecialty().toLowerCase());
        return doctor;
    }
}
