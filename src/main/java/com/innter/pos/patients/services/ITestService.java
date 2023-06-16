package com.innter.pos.patients.services;

import com.innter.pos.patients.DTOs.TestRequest;
import com.innter.pos.patients.DTOs.TestResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITestService {
    List<TestResponse> getTestName(String testName, Pageable pageable);

    TestResponse saveTestWithInstruction(TestRequest newTest);

    Boolean deleteTestWithInstruction(Long id);
}
