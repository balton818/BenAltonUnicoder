package edu.westga.cs3110.unicoder.model;

public class Codepoint {
	
	private static final int UTF8_THREE_BYTE_UPPER_BOUND = 0b1111111111111111;
	private static final int UTF8_THREE_BYTE_LOWER_BOUND = 0b100000000000;
	private static final int UTF8_TWO_BYTE_UPPER_BOUND = 0b11111111111;
	private static final int UTF8_TWO_BYTE_LOWER_BOUND = 0b10000000;
	private static final int UTF8_SINGLE_BYTE_LOWER_BOUND = 0b0;
	private static final int UTF8_SINGLE_BYTE_UPPER_BOUND = 0b01111111;

	private String hex;
	

	public Codepoint(String hexadecimal) {
		if (hexadecimal == null) {
			throw new IllegalArgumentException("Hexadecimal string cannot be null");
		}
		if (hexadecimal.isEmpty()) {
			throw new IllegalArgumentException("Hexadecimal string cannot be empty");
		}
		this.hex = hexadecimal;
	}

	public String toUTF32() {
		return null;
	}

	public String toUTF16() {
		return null;
	}

	public String toUTF8() {
		int hexAsInt = Integer.parseUnsignedInt(this.hex, 16);
				
		if (hexAsInt >= UTF8_SINGLE_BYTE_LOWER_BOUND && hexAsInt <= UTF8_SINGLE_BYTE_UPPER_BOUND) {
			return this.encodeAsSingleByteUTF8(hexAsInt);
		} else if (hexAsInt >= UTF8_TWO_BYTE_LOWER_BOUND && hexAsInt <= UTF8_TWO_BYTE_UPPER_BOUND) {
			return this.encodeAsTwoByteUTF8(hexAsInt);
		} else if (hexAsInt >= UTF8_THREE_BYTE_LOWER_BOUND && hexAsInt <= UTF8_THREE_BYTE_UPPER_BOUND) {
			return this.encodeAsThreeByteUTF8(hexAsInt);
		} else {
			throw new IllegalArgumentException("Hex value is out of range for UTF8 encoding");
		}
	}

	private String encodeAsThreeByteUTF8(int hexAsInt) {
		
		return null;
	}

	private String encodeAsTwoByteUTF8(int hexAsInt) {
		String upperBits = "110";
		String lowerBits = "10";
		String result = "";
		String bits = String.format("%12s", Integer.toBinaryString(hexAsInt)).replace(" ", "0");
		
		String firstSet = String.format("%5s", Integer.parseInt(bits.substring(0, 6))).replace(" ", "0");
		String secondSet = String.format("%6s", Integer.parseInt(bits.substring(6, 12))).replace(" ", "0");
		upperBits += firstSet;
		lowerBits += secondSet;
		
		result = upperBits + lowerBits;
		result = Integer.toHexString(Integer.parseInt(result, 2));
		
		return result;
	}

	private String encodeAsSingleByteUTF8(int hexAsInt) {
		int firstHexDigit = 0;
		int secondHexDigit = 0;
		String result = "";
		String bits = String.format("%8s", hexAsInt).replace(" ", "0");
		
		firstHexDigit = Integer.parseInt(bits.substring(0, 4));
		secondHexDigit = Integer.parseInt(bits.substring(5, 8));
		result += Integer.toHexString(firstHexDigit);
		result += Integer.toHexString(secondHexDigit);
		result = String.format("%4s", result).replace(" ", "0");
		
		return result;
	}

}
