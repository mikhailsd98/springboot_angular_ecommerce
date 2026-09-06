package com.springboot.mynewapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.mySpringBootCRUDapp.MynewappApplication;

@SpringBootTest(classes = MynewappApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MynewappApplicationTests {

	@Test
	void contextLoads() {
	}

}
