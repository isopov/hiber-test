package test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.service.TestService;

public class Main {

	public static void main(String[] args) {
		try (ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml")) {
			TestService testService = applicationContext.getBean(TestService.class);
			testService.doTest();
		}
	}

}
