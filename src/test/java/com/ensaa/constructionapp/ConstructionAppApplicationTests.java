package com.ensaa.constructionapp;

import com.ensaa.constructionapp.service.MonthService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConstructionAppApplicationTests {



	@Test
	void contextLoads() {
		MonthService monthService = null;
		assert( monthService == null);
	}

}
