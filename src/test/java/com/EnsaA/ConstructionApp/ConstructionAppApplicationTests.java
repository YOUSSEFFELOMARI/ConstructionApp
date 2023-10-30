package com.EnsaA.ConstructionApp;

import com.EnsaA.ConstructionApp.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConstructionAppApplicationTests {

	@Autowired
	EmployeeService employeeService;

	@Test
	void contextLoads() {
		assert employeeService!= null;
	}

}
