package edu.westga.cs3110.unicoder.model;

public class Codepoint {
	
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
		
		
		if (hexAsInt >= 0b0 && hexAsInt < 0b10000000) {
			return this.encodeAsSingleByteUTF8(hexAsInt);
		} else if (hexAsInt >= 0b10000000 && hexAsInt < 0b100000000000) {
			return this.encodeAsTwoByteUTF8(hexAsInt);
		} else if (hexAsInt >= 0b100000000000 && hexAsInt < 0b1111111111111111) {
			return this.encodeAsThreeByteUTF8(hexAsInt);
		} else {
			throw new IllegalArgumentException("Hex value is out of range for UTF8 encoding");
		}
	}

	private String encodeAsThreeByteUTF8(int hexAsInt) {
		
		return null;
	}

	private String encodeAsTwoByteUTF8(int hexAsInt) {
		return null;
	}

	private String encodeAsSingleByteUTF8(int hexAsInt) {
		String firstHexDigit = "";
		String secondHexDigit = "";
		String result = "00";
		String bits = String.format("%8s", hexAsInt).replace(" ", "0");
		System.out.println(bits);
		
		firstHexDigit = bits.substring(0, 4);
		secondHexDigit = bits.substring(5, 8);
		result += firstHexDigit;
		result += secondHexDigit;
		System.out.println(result);
		
		return result;
	}

}
