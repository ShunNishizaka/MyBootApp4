package jp.te4a.spring.boot.myapp5;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyBootApp5ApplicationTests {

	@Test
	void contextLoads() {
		HelloController helloController = new HelloController();
		String expect  ="index";
		String actual = helloController.index();
		assertEquals(expect, actual);

	}

}
