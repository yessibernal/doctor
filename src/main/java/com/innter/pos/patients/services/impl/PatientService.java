package com.innter.pos.patients.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.innter.pos.patients.DAOs.DoctorDAO;
import com.innter.pos.patients.DAOs.PatientDAO;
import com.innter.pos.patients.DAOs.rest.PersonApi;
import com.innter.pos.patients.DTOs.*;
import com.innter.pos.patients.entities.DoctorEntity;
import com.innter.pos.patients.entities.PatientEntity;
import com.innter.pos.patients.exceptions.BadRequestInnter;
import com.innter.pos.patients.exceptions.InternalServerErrorInnter;
import com.innter.pos.patients.exceptions.NotFoundInnter;
import com.innter.pos.patients.mappers.DoctorMapper;
import com.innter.pos.patients.mappers.PatientMapper;
import com.innter.pos.patients.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {
    @Autowired
    private PatientDAO patientDAO;
    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    PersonApi personApi;
    @Autowired
    private DoctorDAO doctorDAO;

    @Override
    @Transactional
    public PatientResponse createPerson(PatientDoctorDto patientDoctorDto) {
        try {
            Long idDoctor = patientDoctorDto.getDoctor().getId(); //Se obtiene id del json.
            PatientEntity patient = new PatientEntity();
            if (idDoctor != null) { //Se valida si el ID del json es diferente de nulo.
                Optional<DoctorEntity> optionalDoctor = doctorDAO.findById(idDoctor); //Se hace un findById y un ifElse, donde si está presente...
                optionalDoctor.ifPresentOrElse(doctorEntity -> { //Con la función de flecha se hace un objeto de tipo DoctorEntity que se llena con lo que se encontró en tabla.
                    List<DoctorEntity> doctorEntities = new ArrayList<>();// Se hace una lista que se inicializa con el tipo de la entidad y
                    doctorEntities.add(doctorEntity);//se le agrega el objeto lleno a la lista del mismo tipo
                    patient.setDoctors(doctorEntities);//y se le setea la lista con el objeto al paciente.
                }, () -> {
                    throw new InternalServerErrorInnter("P-500", HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear el paciente.");
                });
            } else {
                DoctorEntity doctorEntity = new DoctorEntity();//Se crea un objeto nuevo y se llena con lo que viene en el json.
                doctorEntity.setName(patientDoctorDto.getDoctor().getName());
                doctorEntity.setLastName(patientDoctorDto.getDoctor().getLast_name());
                doctorEntity.setSurname(patientDoctorDto.getDoctor().getSurname());
                doctorEntity.setCedula(patientDoctorDto.getDoctor().getCedula());
                doctorEntity.setPhone(patientDoctorDto.getDoctor().getPhone());
                doctorEntity.setEmail(patientDoctorDto.getDoctor().getEmail());
                doctorEntity.setSpecialty(patientDoctorDto.getDoctor().getSpecialty());
                List<DoctorEntity> doctorEntities = new ArrayList<>();// Se hace una lista que se inicializa con el tipo de la entidad y
                doctorEntities.add(doctorEntity);//se le agrega el objeto nuevo a la lista del mismo tipo
                patient.setDoctors(doctorEntities);//y se le setea la lista con el objeto al paciente.
            }
            Gson gson = new Gson();
            PatientResponse patientResponse = new PatientResponse();
            String objectString = gson.toJson(patientDoctorDto.getPerson());//Se tiene un string en formato json
            JsonObject jsonObject = gson.fromJson(personApi.createPerson(objectString), JsonObject.class); //Se convierte el StringJson a un JsonObject que básicamente es un objeto, pero no tiene especificado de que tipo es
            jsonObject.isJsonObject();
            PersonDto personObject = gson.fromJson(jsonObject.get("data"), PersonDto.class);//Se convierte de tipo jsonObject a uno de tipo PersonDto en que ya se puede entrar a sus atributos
            patient.setFileNumber(patientDoctorDto.getFile_number());
            patient.setDiagnostic(patientDoctorDto.getDiagnostic());
            patient.setStatus((short) 1);
            patient.setPerson(personObject.getPerson_id());
            patientDAO.save(patient);
            patientResponse.setId(patient.getId());
            patientResponse.setFileNumber(patientDoctorDto.getFile_number());
            patientResponse.setDiagnostic(patientDoctorDto.getDiagnostic().toLowerCase());
            patientResponse.setPersonDto(patientDoctorDto.getPerson());
            patientResponse.getPersonDto().setPerson_id(personObject.getPerson_id());
            patientResponse.getPersonDto().getAddress().setAddress_id(personObject.getAddress().getAddress_id());
            patientResponse.setDoctorDto(patientDoctorDto.getDoctor());
            return patientResponse;
        } catch (Exception e) {
            throw new BadRequestInnter("P-400", HttpStatus.BAD_REQUEST, "El paciente no se pudo crear con exito.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PatientResponse getPatient(Long id) {
        try {
            PatientEntity patient = patientDAO.findPatientStatusById(id); //Objeto de tipo PatientEntity, este objeto trae los datos de un tipo PatientEntity donde está el ID de la persona que pertenece al paciente
            Gson gson = new Gson();//Se pasa a un dato de tipo int el ID de la persona, proveniente del paciente
            JsonObject jsonObject = gson.fromJson(personApi.getPerson(patient.getPerson()), JsonObject.class); //Se hace un jsonObject, que básicamente es un objeto que está definido solamente como un objeto de ningún tipo.
            //Al pasar ese jsonObject a uno de tipo PersonDto se convierte en uno de ese tipo, pero para pasarlo tienes que ir llegando a él por medio del .get dependiendo como esté en el json
            PersonDto personObject = gson.fromJson(jsonObject.get("data"), PersonDto.class); //Aquí se convierte de tipo jsonObject a uno de tipo PersonDto
            PatientResponse patientDto = patientMapper.patientToPatientResponse(patient); //Se hace un objeto de tipo PatientResponse y se le pasa de parámetro "patient", que viene de la entidad de paciente
            patientDto.setPersonDto(personObject); //Se le setea el objeto personObject al atributo de persona que tiene el paciente.
            return patientDto;
        } catch (Exception e) {
            throw new BadRequestInnter("P-400", HttpStatus.BAD_REQUEST, "El paciente no se pudo encontrar.");
        }
    }

    @Override
    @Transactional
    public PatientResponse updatePatient(PatientEditedDto patient, Long id) {
        //Aquí ya está lleno un objeto de tipo PatientEditedDto (patient)
        PatientResponse patientResponse = new PatientResponse();
        PatientEntity patientEntity = findPatientById(patientDAO.findById(id)); //Se llena una entidad con el método findById
        patientEntity.setDiagnostic(patient.getDiagnostic());
        Gson gson = new Gson();
        String objectString = gson.toJson(patient.getPerson()); //Se separa el objeto PatientEditedDto
        JsonObject jsonObject = gson.fromJson(personApi.updatePerson(objectString, patientEntity.getPerson()), JsonObject.class); //Se llenan los parámetros del PersonApi el string y id de la persona.
        PersonDto personObject = gson.fromJson(jsonObject.get("data"), PersonDto.class);//Se llena el objeto de tipo PersonDto
        patientResponse.setId(patientEntity.getId()); //Se setean los valores.
        patientResponse.setFileNumber(patientEntity.getFileNumber());
        patientResponse.setDiagnostic(patient.getDiagnostic());
        patientResponse.setPersonDto(personObject);
        return patientResponse;
    }

    @Override
    public Boolean deletePatient(Long id) {
        try {
            PatientEntity patient = patientDAO.findPatientStatusById(id);
            personApi.deletePerson(patient.getPerson());
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new BadRequestInnter("P-400", HttpStatus.BAD_REQUEST, "El paciente no se pudo eliminar.");
        }
    }

    private PatientEntity findPatientById(Optional<PatientEntity> optionalPatient) {
        return optionalPatient.orElseThrow(() -> new NotFoundInnter("P-400", HttpStatus.NOT_FOUND, "El paciente no se pudo actualizar."));
    }
}
