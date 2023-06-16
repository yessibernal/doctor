package com.innter.pos.patients.mappers;

import com.innter.pos.patients.DTOs.TestInstructionRequest;
import com.innter.pos.patients.DTOs.TestInstructionResponse;
import com.innter.pos.patients.entities.TestInstructionEntity;
import org.springframework.stereotype.Component;

@Component
public class TestInstructionMapper implements ITestInstructionMapper {
    @Override
    public TestInstructionResponse testInstructionToTestInstructionResponse(TestInstructionEntity testInstruction) {
        TestInstructionResponse testInstructionResponse = new TestInstructionResponse();
        testInstructionResponse.setId(testInstruction.getId());
        testInstructionResponse.setPatientInstructions(testInstruction.getPatientInstructions());
        testInstructionResponse.setInstructions(testInstruction.getInstructions());
        testInstructionResponse.setExamDescription(testInstruction.getExamDescription());
        return testInstructionResponse;
    }

    @Override
    public TestInstructionEntity testInstructionRequestToTestInstruction(TestInstructionRequest testInstructionRequest) {
        TestInstructionEntity testInstruction = new TestInstructionEntity();
        testInstruction.setPatientInstructions(testInstructionRequest.getPatientInstructions());
        testInstruction.setInstructions(testInstructionRequest.getInstructions());
        testInstruction.setExamDescription(testInstructionRequest.getExamDescription());
        return testInstruction;
    }
}
