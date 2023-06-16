package com.innter.pos.patients.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.innter.pos.patients.DAOs.DoctorDAO;
import com.innter.pos.patients.DAOs.PatientDAO;
import com.innter.pos.patients.DAOs.rest.PersonApi;
import com.innter.pos.patients.DTOs.*;
import com.innter.pos.patients.entities.DoctorEntity;
import com.innter.pos.patients.entities.PatientEntity;
import com.innter.pos.patients.mappers.DoctorMapper;
import com.innter.pos.patients.mappers.PatientMapper;
import com.innter.pos.patients.services.IPatientService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println("SERVICE:PatientDoctorDto--"+patientDoctorDto);
        Long idDoctor = patientDoctorDto.getDoctor().getId();

        System.out.println("SERVICE:idDoctor--"+idDoctor);

        Gson gson = new Gson();
        PatientResponse patientResponse = new PatientResponse();
        String objectString = gson.toJson(patientDoctorDto.getPerson());//Se tiene un string en formato json
        JsonObject jsonObject = gson.fromJson(personApi.createPerson(objectString), JsonObject.class); //Se convierte el StringJson a un JsonObject que basicamente es un objeto, pero no tiene especificado de que tipo es
        jsonObject.isJsonObject();
        PersonDto personObject = gson.fromJson(jsonObject.get("data"), PersonDto.class);//Se convierte de tipo jsonObject a uno de tipo PersonDto en que ya se puede entrar a sus atributos
        PatientEntity patient = new PatientEntity();
        patient.setFileNumber(patientDoctorDto.getFile_number());
        patient.setDiagnostic(patientDoctorDto.getDiagnostic());
        patient.setStatus((short) 1);
        patient.setPerson(personObject.getPerson_id());
        patientResponse.setId(patient.getId());
        System.out.println("SERVICE:patientResponse--"+patientResponse);
        System.out.println("SERVICE:patient--"+patient);


        if (idDoctor != null){
            Optional<DoctorEntity> optionalDoctor = doctorDAO.findById(idDoctor);
            Boolean isBrandPresent = optionalDoctor.isPresent();
            System.out.println("SERVICE:isBrandPresent--"+isBrandPresent);

            if (isBrandPresent){
                DoctorEntity doctor = doctorDAO.getDoctor(idDoctor);
                System.out.println("ENTRA AL 2DO IF"+doctor);
            }
            return null;
        }

        DoctorEntity doctorEntity=new DoctorEntity();
        doctorEntity.setName(patientDoctorDto.getDoctor().getName());
        doctorEntity.setLastName(patientDoctorDto.getDoctor().getLast_name());
        doctorEntity.setSurname(patientDoctorDto.getDoctor().getSurname());
        doctorEntity.setCedula(patientDoctorDto.getDoctor().getCedula());
        doctorEntity.setPhone(patientDoctorDto.getDoctor().getPhone());
        doctorEntity.setEmail(patientDoctorDto.getDoctor().getEmail());
        doctorEntity.setSpecialty(patientDoctorDto.getDoctor().getSpecialty());


        List<DoctorEntity> doctorEntities= new ArrayList<>();
        doctorEntities.add(doctorEntity);
        patient.setDoctors(doctorEntities);


        patientDAO.save(patient);//Actualiza el dato si no lo tiene
        patientResponse.setId(patient.getId());
        patientResponse.setFileNumber(patientDoctorDto.getFile_number());
        patientResponse.setDiagnostic(patientDoctorDto.getDiagnostic().toLowerCase());
        patientResponse.setPersonDto(patientDoctorDto.getPerson());
        patientResponse.getPersonDto().setPerson_id(personObject.getPerson_id());
        patientResponse.getPersonDto().getAddress().setAddress_id(personObject.getAddress().getAddress_id());
        patientResponse.setDoctorDto(patientDoctorDto.getDoctor());
        patientResponse.getDoctorDto().setId(patient.getDoctors().get(0).getId());
        return patientResponse;
    }
    @Override
    @Transactional(readOnly = true)
    public PatientResponse getPatient(Long id) {
        PatientEntity patient = patientDAO.findPatientStatusById(id); //Objeto de tipo PatientEntity, este objeto trae los datos de un tipo PatientEntity donde está el id de la persona que pertenece al paciente
        Gson gson = new Gson();//Se pasa a un dato de tipo int el id de la persona, proveniente del paciente
        JsonObject jsonObject = gson.fromJson(personApi.getPerson(patient.getPerson()), JsonObject.class); //Se hace un jsonObject, que basicamente es un objeto que está definido solamente como un objeto de ningún tipo.
        //Al pasar ese jsonObject a uno de tipo PersonDto se convierte en uno de ese tipo, pero para pasarlo tienes que ir llegando a él por medio del .get dependiendo como esté en el json
        PersonDto personObject = gson.fromJson(jsonObject.get("data"), PersonDto.class); //Aquí se convierte de tipo jsonObject a uno de tipo PersonDto
        PatientResponse patientDto = patientMapper.patientToPatientResponse(patient); //Se hace un objeto de tipo PatientResponse y se le pasa de parametro "patient", que viene de la entidad de paciente
        patientDto.setPersonDto(personObject); //Se le setea el objeto personObject al atributo de persona que tiene el paciente.
        return patientDto;
    }
    @Override
    @Transactional
    public PatientResponse updatePatient(PatientEditedDto patient, Long id) {
        //Aquí ya está lleno un objeto de tipo PatientEditedDto (patient)
        PatientResponse patientResponse = new PatientResponse();
        PatientEntity patientEntity = patientDAO.findById(id).get(); //Se llena una entidad con el metodo findById
        patientEntity.setDiagnostic(patient.getDiagnostic());
        Gson gson = new Gson();
        String objectString = gson.toJson(patient.getPerson()); //Se separa el objeto PatientEditedDto
        JsonObject jsonObject = gson.fromJson(personApi.updatePerson(objectString,patientEntity.getPerson()), JsonObject.class); //Se llenan los parámetros del PersonApi el string y el id de la persona.
        PersonDto personObject = gson.fromJson(jsonObject.get("data"), PersonDto.class);//Se llena el objeto de tipo PersonDto
        patientResponse.setId(patientEntity.getId()); //Se setean los valores.
        patientResponse.setFileNumber(patientEntity.getFileNumber());
        patientResponse.setDiagnostic(patient.getDiagnostic());
        patientResponse.setPersonDto(personObject);
        return patientResponse;
    }

    @Override
    public Boolean deletePatient(Long id) {
        PatientEntity patient = patientDAO.findPatientStatusById(id);
        personApi.deletePerson(patient.getPerson());
        return Boolean.TRUE;
    }
}
