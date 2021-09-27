package jp.te4a.spring.boot.myapp4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyBootApp4ApplicationTests {

	@Test
	void contextLoads() {
		HelloController helloController = new HelloController();
		String expect  ="this is Spring Boot sample.";
		String actual = helloController.index();
		assertEquals(expect, actual);

	}

}
