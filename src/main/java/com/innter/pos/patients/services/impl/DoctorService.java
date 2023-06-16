package com.innter.pos.patients.services.impl;

import com.innter.pos.patients.DAOs.DoctorDAO;
import com.innter.pos.patients.DTOs.DoctorEditedRequest;
import com.innter.pos.patients.DTOs.DoctorRequest;
import com.innter.pos.patients.DTOs.DoctorResponse;
import com.innter.pos.patients.entities.DoctorEntity;
import com.innter.pos.patients.mappers.DoctorMapper;
import com.innter.pos.patients.services.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DoctorService implements IDoctorService {

    @Autowired
    private DoctorDAO doctorDAO;

    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    @Transactional
    public DoctorResponse saveDoctor(DoctorRequest newDoctor) {
        DoctorEntity doctor = doctorMapper.doctorRequestToDoctor(newDoctor);
        doctorDAO.save(doctor);
        DoctorResponse doctorResponse = doctorMapper.doctorToDoctorResponse(doctor);
        return doctorResponse;
    }

    @Override
    @Transactional
    public DoctorResponse editedDoctor(DoctorEditedRequest newDoctorEdited, Long id) {
        DoctorEntity doctor = doctorDAO.findById(id).get();
        doctor.setPhone(newDoctorEdited.getPhone());
        doctor.setEmail(newDoctorEdited.getEmail());
        doctor.setSpecialty(newDoctorEdited.getSpecialty());
        System.out.println("doctor1.-"+ doctor);
        DoctorResponse doctorResponse = doctorMapper.doctorToDoctorResponse(doctor);
        System.out.println(doctorResponse);
        return doctorResponse;
    }


}
