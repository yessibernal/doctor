package com.innter.pos.patients.mappers;

import com.innter.pos.patients.DTOs.TestInstructionRequest;
import com.innter.pos.patients.DTOs.TestInstructionResponse;
import com.innter.pos.patients.entities.TestInstructionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITestInstructionMapper {
    TestInstructionResponse testInstructionToTestInstructionResponse(TestInstructionEntity testInstruction);

    TestInstructionEntity testInstructionRequestToTestInstruction(TestInstructionRequest testInstructionRequest);
}
