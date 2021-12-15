package main.test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.persistence.controller.PersistenceController;

class PersistenceControllerTest {

	@Test
	void test() {
		PersistenceController controller = PersistenceController.getInstance();
		assertTrue(controller.getSessionFactory() != null);
		assertTrue(controller.getSession() != null);
	}

}
