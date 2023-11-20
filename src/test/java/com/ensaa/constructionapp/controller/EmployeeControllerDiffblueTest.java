package com.ensaa.constructionapp.controller;

import com.ensaa.constructionapp.dto.ConstructionSiteDto;
import com.ensaa.constructionapp.dto.EmployeeDto;
import com.ensaa.constructionapp.service.ConstructionSiteService;
import com.ensaa.constructionapp.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmployeeController.class, EmployeeDto.class})
@ExtendWith(SpringExtension.class)
class EmployeeControllerDiffblueTest {
    @MockBean
    private ConstructionSiteService constructionSiteService;

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeDto employeeDto;

    @MockBean
    private EmployeeService employeeService;

    /**
     * Method under test: {@link EmployeeController#saveEmployee(EmployeeDto)}
     */
    @Test
    void testSaveEmployee() throws Exception {
        EmployeeDto employeeDto2 = new EmployeeDto();
        ConstructionSiteDto constructionSiteDto = ConstructionSiteDto.builder()
                .Address("42 Main St")
                .constructionSiteId(1)
                .endDate("2020-03-01")
                .name("Name")
                .startDate("2020-03-01")
                .build();
        employeeDto2.setConstructionSiteDto(constructionSiteDto);
        employeeDto2.setEmployerId(1);
        employeeDto2.setHomeAddress("42 Main St");
        employeeDto2.setLastName("Doe");
        employeeDto2.setName("Name");
        employeeDto2.setPhone("6625550144");
        employeeDto2.setSalary("Salary");
        String content = (new ObjectMapper()).writeValueAsString(employeeDto2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/Employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employeeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }
}
