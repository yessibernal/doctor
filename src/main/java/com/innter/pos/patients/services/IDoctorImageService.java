package com.innter.pos.patients.services;

import java.io.IOException;

public interface IDoctorImageService {
    byte[] getImageDoctor(String imageName) throws IOException;
}
