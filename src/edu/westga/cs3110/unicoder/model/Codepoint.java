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
		long hexAsInt = Integer.parseUnsignedInt(this.hex, 16);
		if (hexAsInt >= 0b0 && hexAsInt < 0b10000000) {
			this.encodeAsSingleByteUTF8();
		} else if (hexAsInt >= 0b10000000 && hexAsInt < 0b100000000000) {
			this.encodeAsTwoByteUTF8();
		} else if (hexAsInt >= 0b100000000000 && hexAsInt < 0b1111111111111111) {
			this.encodeAsThreeByteUTF8();
		}
		
		return null;
	}

	private void encodeAsThreeByteUTF8() {
		// TODO Auto-generated method stub
		
	}

	private void encodeAsTwoByteUTF8() {
		// TODO Auto-generated method stub
		
	}

	private void encodeAsSingleByteUTF8() {
		// TODO Auto-generated method stub
		
	}

}
