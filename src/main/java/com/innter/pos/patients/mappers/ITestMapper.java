package com.innter.pos.patients.mappers;

import com.innter.pos.patients.DTOs.TestRequest;
import com.innter.pos.patients.DTOs.TestResponse;
import com.innter.pos.patients.entities.TestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITestMapper {
    TestResponse testToTestResponse(TestEntity test);

    TestEntity testRequestToTest(TestRequest testRequest);
}
