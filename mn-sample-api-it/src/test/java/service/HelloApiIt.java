package mn.sample.api.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
class HelloApiIt {
	
	IntegrationTestConfig conf = new IntegrationTestConfig();

	@Rule
	void test() {
		fail("Not yet implemented");
	}

}
