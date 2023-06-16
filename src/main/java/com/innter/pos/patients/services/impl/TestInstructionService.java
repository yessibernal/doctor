package com.innter.pos.patients.services.impl;

import com.innter.pos.patients.DAOs.TestInstructionDAO;
import com.innter.pos.patients.DTOs.TestInstructionRequest;
import com.innter.pos.patients.DTOs.TestInstructionResponse;
import com.innter.pos.patients.entities.TestInstructionEntity;
import com.innter.pos.patients.exceptions.NotFoundInnter;
import com.innter.pos.patients.mappers.TestInstructionMapper;
import com.innter.pos.patients.services.ITestInstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class TestInstructionService implements ITestInstructionService {

    @Autowired
    private TestInstructionDAO testInstructionDAO;

    @Autowired
    private TestInstructionMapper testInstructionMapper;

    @Override
    @Transactional(readOnly = true)
    public TestInstructionResponse getTestInstruction(Long id) {
        TestInstructionEntity optionalTestInstructionEntity = findTestInstruction(testInstructionDAO.findById(id));
        TestInstructionEntity testInstructionEntity = optionalTestInstructionEntity;
        TestInstructionResponse testInstructionResponse = testInstructionMapper.testInstructionToTestInstructionResponse(testInstructionEntity);
        return testInstructionResponse;
    }

    @Override
    public TestInstructionResponse editedTestInstruction(TestInstructionRequest editedTestInstruction, Long id) {
        TestInstructionEntity testInstruction = findTestInstruction(testInstructionDAO.findById(id));
        testInstruction.setPatientInstructions(editedTestInstruction.getPatientInstructions());
        testInstruction.setInstructions(editedTestInstruction.getInstructions());
        testInstruction.setExamDescription(editedTestInstruction.getExamDescription());
        testInstructionDAO.save(testInstruction);
        return testInstructionMapper.testInstructionToTestInstructionResponse(testInstruction);
    }

    private TestInstructionEntity findTestInstruction(Optional<TestInstructionEntity> optionalTestInstruction) {
        return optionalTestInstruction.orElseThrow(() -> new NotFoundInnter("P-400", HttpStatus.NOT_FOUND, "El test no se pudo actualizar."));
    }
}
