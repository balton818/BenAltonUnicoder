package edu.westga.cs3110.unicoder.model;

public class Codepoint {
	
	private static final int UTF16_TWO_BYTE_UPPER_BOUND = 0b1111111111111111;
	private static final int UTF16_TWO_BYTE_MID_LOWER_BOUND = 0b1110000000000000;
	private static final int UTF16_TWO_BYTE_MID_UPPER_BOUND = 0b1101011111111111;
	
	
	private static final int UTF8_THREE_BYTE_UPPER_BOUND = 0b1111111111111111;
	private static final int UTF8_THREE_BYTE_LOWER_BOUND = 0b100000000000;
	private static final int UTF8_TWO_BYTE_UPPER_BOUND = 0b11111111111;
	private static final int UTF8_TWO_BYTE_LOWER_BOUND = 0b10000000;
	private static final int UTF8_SINGLE_BYTE_UPPER_BOUND = 0b01111111;
	
	private static final int UTF_FOUR_BYTE_UPPER_BOUND = 0b100001111111111111111;
	private static final int UTF_FOUR_BYTE_LOWER_BOUND = 0b10000000000000000;
	private static final int UTF_LOWER_BOUND = 0b0;
	
	private int hex;

	public Codepoint(String hexadecimal) {
		if (hexadecimal == null) {
			throw new IllegalArgumentException("Hexadecimal string cannot be null");
		}
		if (hexadecimal.isEmpty()) {
			throw new IllegalArgumentException("Hexadecimal string cannot be empty");
		}
		this.hex = Integer.parseUnsignedInt(hexadecimal, 16);
	}

	public String toUTF32() {
		return null;
	}

	public String toUTF16() {
		if (this.hex > UTF16_TWO_BYTE_MID_UPPER_BOUND && this.hex < UTF16_TWO_BYTE_MID_LOWER_BOUND) {
			throw new IllegalArgumentException("Hex value inside invalid range for UTF-16 encoding");
		}
		if (this.hex >= UTF_LOWER_BOUND && this.hex <= UTF16_TWO_BYTE_MID_LOWER_BOUND) {
			return this.encodeAsTwoByteUTF16(this.hex);
		} else if (this.hex >= UTF16_TWO_BYTE_MID_UPPER_BOUND && this.hex <= UTF16_TWO_BYTE_UPPER_BOUND) {
			return this.encodeAsTwoByteUTF16(this.hex);
		} else if (this.hex >= UTF_FOUR_BYTE_LOWER_BOUND && this.hex <= UTF_FOUR_BYTE_UPPER_BOUND) {
			return this.encodeAsFourByteUTF16(this.hex);
		} else {
			throw new IllegalArgumentException("Hex value out of range for UTF-16 encoding");
		}
	}

	private String encodeAsFourByteUTF16(int hexAsInt) {
		// TODO Auto-generated method stub
		return null;
	}

	private String encodeAsTwoByteUTF16(int hexAsInt) {
		String result = Integer.toHexString(hexAsInt);
		result = String.format("%4s", result);
		
		return result;
	}

	public String toUTF8() {
				
		if (this.hex >= UTF_LOWER_BOUND && this.hex <= UTF8_SINGLE_BYTE_UPPER_BOUND) {
			return this.encodeAsSingleByteUTF8(this.hex);
		} else if (this.hex >= UTF8_TWO_BYTE_LOWER_BOUND && this.hex <= UTF8_TWO_BYTE_UPPER_BOUND) {
			return this.encodeAsTwoByteUTF8(this.hex);
		} else if (this.hex >= UTF8_THREE_BYTE_LOWER_BOUND && this.hex <= UTF8_THREE_BYTE_UPPER_BOUND) {
			return this.encodeAsThreeByteUTF8(this.hex);
		} else if (this.hex >= UTF_FOUR_BYTE_LOWER_BOUND && this.hex <= UTF_FOUR_BYTE_UPPER_BOUND) {
			return this.encodeAsFourByteUTF8(this.hex);
		} else {
			throw new IllegalArgumentException("Hex value out of range for UTF-8 encoding");
		}
	}

	private String encodeAsFourByteUTF8(int hexAsInt) {
		String hiByte = "11110";
		String midByte1 = "10";
		String midByte2 = "10";
		String loByte = "10";
		String bits = String.format("%21s", Integer.toBinaryString(hexAsInt)).replace(" ", "0");
		String result = "";
		
		String firstSet = bits.substring(0, 3);
		hiByte += firstSet;
		hiByte = Integer.toHexString(Integer.parseInt(hiByte, 2));
		
		String secondSet = bits.substring(3, 9);
		midByte1 += secondSet;
		midByte1 = Integer.toHexString(Integer.parseInt(midByte1, 2));
		
		String thirdSet = bits.substring(9, 15);
		midByte2 += thirdSet;
		midByte2 = Integer.toHexString(Integer.parseInt(midByte2, 2));
		
		String fourthSet = bits.substring(15, 21);
		loByte += fourthSet;
		loByte = Integer.toHexString(Integer.parseInt(loByte, 2));
		
		result = hiByte + midByte1 + midByte2 + loByte;
		
		return result;
	}

	private String encodeAsThreeByteUTF8(int hexAsInt) {
		String hiByte = "1110";
		String midByte = "10";
		String loByte = "10";
		String bits = String.format("%16s", Integer.toBinaryString(hexAsInt)).replace(" ", "0");
		String result = "";
		
		String firstSet = bits.substring(0, 4);
		hiByte += firstSet;
		
		String secondSet = bits.substring(4, 10);
		midByte += secondSet;
		
		String thirdSet = bits.substring(10, 16);
		loByte += thirdSet;
		
		result = hiByte + midByte + loByte;
		result = Integer.toHexString(Integer.parseInt(result, 2));
		
		return result;
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
