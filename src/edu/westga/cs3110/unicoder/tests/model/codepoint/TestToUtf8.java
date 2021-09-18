package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

class TestToUtf8 {

	@Test
	void testOutOfBoundValue() {
		Codepoint character1 = new Codepoint("1FFFF");
		Codepoint character2 = new Codepoint("FFFFF");
		assertAll(

				() -> assertThrows(IllegalArgumentException.class, () -> {
					character1.toUTF8();
				}), () -> assertThrows(IllegalArgumentException.class, () -> {
					character2.toUTF8();
				}));
	}

	@Test
	void testSingleByteAtLowerBound() {
		Codepoint character = new Codepoint("00");
		String result = character.toUTF8();
		String expected = "0000";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	@Test
	void testSingleByteInRange() {
		Codepoint character = new Codepoint("3D");
		String result = character.toUTF8();
		String expected = "003D";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	@Test
	void testSingleByteAtUpperBound() {
		Codepoint character = new Codepoint("7F");
		String result = character.toUTF8();
		String expected = "007F";

		assertTrue(expected.equalsIgnoreCase(result));
	}

}
