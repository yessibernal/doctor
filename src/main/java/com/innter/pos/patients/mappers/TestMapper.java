package com.innter.pos.patients.mappers;

import com.innter.pos.patients.DTOs.TestRequest;
import com.innter.pos.patients.DTOs.TestResponse;
import com.innter.pos.patients.entities.TestEntity;
import org.springframework.stereotype.Component;

@Component
public class TestMapper implements ITestMapper {

    @Override
    public TestResponse testToTestResponse(TestEntity test) {
        TestResponse testResponse = new TestResponse();
        testResponse.setId(test.getId());
        testResponse.setDisplayId(test.getDisplayId().toLowerCase());
        testResponse.setTestName(test.getTestName().toLowerCase());
        testResponse.setReportName(test.getReportName().toLowerCase());
        testResponse.setDeliveryTime(test.getDeliveryTime().toLowerCase());
        testResponse.setDepartment(test.getDepartment());
        testResponse.setSampleType(test.getSampleType());
        testResponse.setContainerType(test.getContainerType());
        testResponse.setResult(test.getResult());
        testResponse.setUnits(test.getUnits());
        testResponse.setDecimals(test.getDecimals());
        testResponse.setDefaultValue(test.getDefaultValue());
        testResponse.setTechniqueUsed(test.getTechniqueUsed());
        testResponse.setMaker(test.getMaker());
        testResponse.setLowerLimit(test.getLowerLimit());
        testResponse.setUpperLimit(test.getUpperLimit());
        testResponse.setSatKey(test.getSatKey().toLowerCase());
        testResponse.setSatCatalog(test.getSatCatalog().toLowerCase());
        return testResponse;
    }

    @Override
    public TestEntity testRequestToTest(TestRequest testRequest) {
        TestEntity test = new TestEntity();
        test.setDisplayId(testRequest.getDisplayId().toLowerCase());
        test.setTestName(testRequest.getTestName().toLowerCase());
        test.setReportName(testRequest.getReportName().toLowerCase());
        test.setDeliveryTime(testRequest.getDeliveryTime().toLowerCase());
        test.setDepartment(testRequest.getDepartment());
        test.setSampleType(testRequest.getSampleType());
        test.setContainerType(testRequest.getContainerType());
        test.setResult(testRequest.getResult());
        test.setUnits(testRequest.getUnits());
        test.setDecimals(testRequest.getDecimals());
        test.setDefaultValue(testRequest.getDefaultValue());
        test.setTechniqueUsed(testRequest.getTechniqueUsed());
        test.setMaker(testRequest.getMaker());
        test.setLowerLimit(testRequest.getLowerLimit());
        test.setUpperLimit(testRequest.getUpperLimit());
        test.setSatKey(testRequest.getSatKey().toLowerCase());
        test.setSatCatalog(testRequest.getSatCatalog().toLowerCase());
        return test;
    }
}
