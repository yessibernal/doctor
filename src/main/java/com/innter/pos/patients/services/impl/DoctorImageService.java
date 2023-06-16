package com.innter.pos.patients.services.impl;

import com.innter.pos.patients.exceptions.NoSuchFileExceptionInnter;
import com.innter.pos.patients.services.IDoctorImageService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
@Service
public class DoctorImageService implements IDoctorImageService {

    @Override
    @Transactional
    public byte[] getImageDoctor(String imageName) throws IOException {

        try {
            byte[] bytes = Files.readAllBytes(Paths.get("./uploads/" + imageName.toLowerCase()));
            return bytes;
        } catch (Exception e) {
            throw new NoSuchFileExceptionInnter("P-404", HttpStatus.NOT_FOUND, "El nombre de la imagen que ingresaste, no es valida.");
        }
    }
}
