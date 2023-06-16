package com.innter.pos.patients.mappers;

import com.innter.pos.patients.DTOs.DoctorRequest;
import com.innter.pos.patients.DTOs.DoctorResponse;
import com.innter.pos.patients.entities.DoctorEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDoctorMapper {

    DoctorResponse doctorToDoctorResponse (DoctorEntity doctor);

    DoctorEntity doctorRequestToDoctor (DoctorRequest doctorRequest);
}
