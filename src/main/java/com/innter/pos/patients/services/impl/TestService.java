package com.innter.pos.patients.services.impl;

import com.innter.pos.patients.DAOs.TestDAO;
import com.innter.pos.patients.DAOs.TestInstructionDAO;
import com.innter.pos.patients.DTOs.TestRequest;
import com.innter.pos.patients.DTOs.TestResponse;
import com.innter.pos.patients.entities.TestEntity;
import com.innter.pos.patients.entities.TestInstructionEntity;
import com.innter.pos.patients.exceptions.BadRequestInnter;
import com.innter.pos.patients.exceptions.NotFoundInnter;
import com.innter.pos.patients.mappers.TestInstructionMapper;
import com.innter.pos.patients.mappers.TestMapper;
import com.innter.pos.patients.services.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService implements ITestService {

    @Autowired
    private TestDAO testDAO;

    @Autowired
    private TestInstructionDAO testInstructionDAO;

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private TestInstructionMapper testInstructionMapper;

    @Override
    @Transactional
    public List<TestResponse> getTestName(String testName, Pageable pageable) {
        try {
            String name = testName.toLowerCase();
            List<TestEntity> testEntity = testDAO.getTestByName(name, pageable);
            List<TestResponse> testResponse = new ArrayList<>();
            testEntity.stream().forEach(test -> {
                testResponse.add(testMapper.testToTestResponse(test));
            });
            return testResponse;
        } catch (Exception e) {
            throw new NotFoundInnter("P-404", HttpStatus.NOT_FOUND, "El test no se puede encontrar.");
        }
    }

    @Override
    public TestResponse saveTestWithInstruction(TestRequest newTest) {
        try {
            TestEntity test = testMapper.testRequestToTest(newTest);
            TestInstructionEntity testInstruction = new TestInstructionEntity();
            testInstruction.setPatientInstructions(newTest.getTestInstruction().getPatientInstructions());
            testInstruction.setInstructions(newTest.getTestInstruction().getInstructions());
            testInstruction.setExamDescription(newTest.getTestInstruction().getExamDescription());
            testDAO.save(test);
            TestResponse testResponse = testMapper.testToTestResponse(test);
            testInstruction.setId(testResponse.getId());
            testInstructionDAO.save(testInstruction);
            return testResponse;
        } catch (Exception e) {
            throw new NotFoundInnter("P-404", HttpStatus.NOT_FOUND, "El test no se puede crear.");
        }
    }

    @Override
    public Boolean deleteTestWithInstruction(Long id) {
        try {
            testDAO.deleteById(id);
            testInstructionDAO.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new BadRequestInnter("P-400", HttpStatus.BAD_REQUEST, "El test no se pudo eliminar.");
        }
    }

}
