package com.innter.pos.patients.services.impl;

import com.innter.pos.patients.DAOs.DoctorDAO;
import com.innter.pos.patients.DAOs.ExtensionImageBlackListDAO;
import com.innter.pos.patients.DAOs.ExtensionImageWhiteListDAO;
import com.innter.pos.patients.DTOs.DoctorEditedRequest;
import com.innter.pos.patients.DTOs.DoctorRequest;
import com.innter.pos.patients.DTOs.DoctorResponse;
import com.innter.pos.patients.entities.DoctorEntity;
import com.innter.pos.patients.entities.ExtensionImageBlackListEntity;
import com.innter.pos.patients.entities.ExtensionImageWhiteListEntity;
import com.innter.pos.patients.exceptions.NotFoundInnter;
import com.innter.pos.patients.mappers.DoctorMapper;
import com.innter.pos.patients.services.IDoctorService;
import com.innter.pos.patients.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService implements IDoctorService {

    @Autowired
    private DoctorDAO doctorDAO;
    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private ExtensionImageWhiteListDAO extensionImageWhiteListDAO;

    @Autowired
    private ExtensionImageBlackListDAO extensionImageBlackListDAO;

    @Autowired
    private Constants constants;
    private final Path rootFolder = Paths.get("uploads");

    @Override
    @Transactional
    public DoctorResponse saveDoctor(DoctorRequest newDoctor) {
        try {
            DoctorEntity doctor = doctorMapper.doctorRequestToDoctor(newDoctor);
            doctorDAO.save(doctor);
            DoctorResponse doctorResponse = doctorMapper.doctorToDoctorResponse(doctor);
            return doctorResponse;
        } catch (Exception e) {
            throw new NotFoundInnter("P-404", HttpStatus.NOT_FOUND, "El doctor no se pudo crear correctamente.");
        }
    }


    @Override
    @Transactional
    public DoctorResponse editedDoctor(DoctorEditedRequest newDoctorEdited, Long id) {
        DoctorEntity doctor = findDoctorById(doctorDAO.findById(id));
        doctor.setPhone(newDoctorEdited.getPhone());
        doctor.setEmail(newDoctorEdited.getEmail());
        doctor.setSpecialty(newDoctorEdited.getSpecialty());
        DoctorResponse doctorResponse = doctorMapper.doctorToDoctorResponse(doctor);
        return doctorResponse;
    }


    @Override
    @Transactional
    public DoctorResponse getDoctorCedula(String cedula) { //Se pide un parámetro que es la cedula que se manda en el postman
        try {
            DoctorEntity doctorEntity = doctorDAO.getDoctorByCedula(cedula); //Se llena una entidad de tipo Doctor con la query que se hace en el Dao para buscar el doctor por cedula.
            DoctorResponse doctorResponse = doctorMapper.doctorToDoctorResponse(doctorEntity); //Se retorna el tipo de objeto que se pide en el service y se llena con el mapper y como parámetro,
            return doctorResponse;//se pasa la entidad que se llenó con la query.
        } catch (Exception e) {
            throw new NotFoundInnter("P-404", HttpStatus.NOT_FOUND, "La cedula que ingresaste no es valida.");
        }
    }

    @Override
    @Transactional
    public void save(MultipartFile file, Long idDoctor) {
        String[] parts = file.getOriginalFilename().split("\\.");
        String part2 = parts[1];
        Optional<DoctorEntity> doctor = doctorDAO.findById(idDoctor);

        if (doctor.isPresent()) {
            String nombreImagen = constants.abbreviation + idDoctor + "." + part2;

            List<ExtensionImageBlackListEntity> extensionBlackListImageEntityList = extensionImageBlackListDAO.findByType();
            extensionBlackListImageEntityList.stream().forEach(ext -> {
                if (ext.getType().equalsIgnoreCase(part2)) {
                    throw new NotFoundInnter("P-404", HttpStatus.NOT_FOUND, "El formato de la imagen no es valido.");
                }
            });

            List<ExtensionImageWhiteListEntity> extensionImageEntityList = extensionImageWhiteListDAO.findByType();
            extensionImageEntityList.stream().forEach(ext -> {
                if (ext.getType().equalsIgnoreCase(part2)) {
                    try {
                        Files.copy(file.getInputStream(),
                                this.rootFolder.resolve(nombreImagen));
                    } catch (IOException e) {
                        throw new RuntimeException("Error xd");

                    }
                }
            });

        } else {
            throw new NotFoundInnter("P-404", HttpStatus.NOT_FOUND, "No se encontró el id del Doctor");
        }
    }

    private DoctorEntity findDoctorById(Optional<DoctorEntity> optionalDoctor) {
        return optionalDoctor.orElseThrow(() -> new NotFoundInnter("P-404", HttpStatus.NOT_FOUND, "El doctor no se pudo actualizar."));
    }

}
