package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

class TestToUtf32 {

	@Test
	void testInvalidData() {
		Codepoint point = new Codepoint("110000");
		assertThrows(IllegalArgumentException.class, () -> {
			point.toUTF32();
		});
	}
	
	@Test
	void testAtLowerBound() {
		Codepoint character = new Codepoint("0");
		String result = character.toUTF32();
		String expected = "00000000";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	@Test
	void testInRange() {
		Codepoint character = new Codepoint("183A5");
		String result = character.toUTF32();
		String expected = "000183a5";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	@Test
	void testAtUpperBound() {
		Codepoint character = new Codepoint("10FFFF");
		String result = character.toUTF32();
		String expected = "0010FFFF";

		assertTrue(expected.equalsIgnoreCase(result));

	}

}
