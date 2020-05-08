package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import bookstoreJPAdatabase2.web.StudentController;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookstoreJpAdatbase2ApplicationTests {

	@Autowired
	private StudentController controller;
	
	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
