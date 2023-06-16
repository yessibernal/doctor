package com.innter.pos.patients.services;

import com.innter.pos.patients.DTOs.TestInstructionRequest;
import com.innter.pos.patients.DTOs.TestInstructionResponse;

public interface ITestInstructionService {

    TestInstructionResponse getTestInstruction(Long id);

    TestInstructionResponse editedTestInstruction(TestInstructionRequest editedTestInstruction, Long id);
}
