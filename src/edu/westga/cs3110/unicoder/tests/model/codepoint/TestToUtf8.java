package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

class TestToUtf8 {

	@Test
	void testOutOfBoundValue() {
		Codepoint character1 = new Codepoint("11FFFF");
		Codepoint character2 = new Codepoint("FFFFFF");
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
	
	@Test
	void testTwoByteAtLowerBound() {
		Codepoint character = new Codepoint("80");
		String result = character.toUTF8();
		String expected = "c280";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	@Test
	void testTwoByteInRange() {
		Codepoint character = new Codepoint("010d");
		String result = character.toUTF8();
		String expected = "c48d";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	@Test
	void testTwoByteAtUpperBound() {
		Codepoint character = new Codepoint("07ff");
		String result = character.toUTF8();
		String expected = "dfbf";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	@Test
	void testThreeByteAtLowerBound() {
		Codepoint character = new Codepoint("0800");
		String result = character.toUTF8();
		String expected = "e0A080";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	
	@Test
	void testThreeByteInRange() {
		Codepoint character = new Codepoint("4ce3");
		String result = character.toUTF8();
		String expected = "e4b3a3";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	@Test
	void testThreeByteAtUpperBound() {
		Codepoint character = new Codepoint("fFff");
		String result = character.toUTF8();
		String expected = "eFBfBF";

		assertTrue(expected.equalsIgnoreCase(result));
	}

	@Test
	void testFourByteAtLowerBound() {
		Codepoint character = new Codepoint("10000");
		String result = character.toUTF8();
		String expected = "f0908080";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	@Test
	void testFourByteInRange() {
		Codepoint character = new Codepoint("10C1C");
		String result = character.toUTF8();
		String expected = "f090B09C";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	@Test
	void testFourByteAtUpperBound() {
		Codepoint character = new Codepoint("10FFFF");
		String result = character.toUTF8();
		String expected = "f48FbFBf";

		assertTrue(expected.equalsIgnoreCase(result));
	}
}
