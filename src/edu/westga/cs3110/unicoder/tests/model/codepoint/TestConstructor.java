package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

class TestConstructor {

	@Test
	void testShouldNotAllowNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Codepoint(null);
		});
	}

	@Test
	void testShouldNotAllowEmptyString() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Codepoint("");
		});
	}
}
