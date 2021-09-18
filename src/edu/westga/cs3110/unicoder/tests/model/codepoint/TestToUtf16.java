package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import edu.westga.cs3110.unicoder.model.Codepoint;

class TestToUtf16 {

	@ParameterizedTest
	@CsvSource({"D800", "D9A5", "DFFF", "110000"})
	void testOutOfBounds(String input) {
		Codepoint point = new Codepoint(input);
		assertThrows(IllegalArgumentException.class, () -> {
			point.toUTF16();
		});
	}
	
	@Test
	void testTwoByteAtLowerBound() {
		Codepoint character = new Codepoint("0");
		String result = character.toUTF8();
		String expected = "0000";

		assertTrue(expected.equalsIgnoreCase(result));

	}
	
	@Test
	void testTwoByteInRange() {
		Codepoint character = new Codepoint("AbCd");
		String result = character.toUTF16();
		String expected = "abcd";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	@Test
	void testTwoByteAtMiddleUpperBound() {
		Codepoint character = new Codepoint("d7ff");
		String result = character.toUTF16();
		String expected = "d7ff";

		assertTrue(expected.equalsIgnoreCase(result));

	}
	
	@Test
	void testTwoByteAtMiddleLowerBound() {
		Codepoint character = new Codepoint("e000");
		String result = character.toUTF16();
		String expected = "E000";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	@Test
	void testTwoByteAtUpperBound() {
		Codepoint character = new Codepoint("Ffff");
		String result = character.toUTF16();
		String expected = "FFFF";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	@Test
	void testFourByteAtLowerBound() {
		Codepoint character = new Codepoint("10000");
		String result = character.toUTF16();
		String expected = "d800dc00";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	@Test
	void testFourByteInRange() {
		Codepoint character = new Codepoint("2B74D");
		String result = character.toUTF16();
		String expected = "D86DdF4D";

		assertTrue(expected.equalsIgnoreCase(result));
	}
	
	@Test
	void testFourByteAtUpperBound() {
		Codepoint character = new Codepoint("10FFFF");
		String result = character.toUTF16();
		String expected = "dbFFdFfF";

		assertTrue(expected.equalsIgnoreCase(result));
	}

}
