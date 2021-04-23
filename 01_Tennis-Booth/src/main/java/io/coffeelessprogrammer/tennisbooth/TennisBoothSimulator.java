package io.coffeelessprogrammer.tennisbooth;


public class TennisBoothSimulator {

	public static void main(String[] args) {
		
		System.out.println("5 Digit Max: " + genMaxValueOfNDigits(5));
		
	}
	
	private static long genMaxValueOfNDigits(int numDigits) {
		if (numDigits < 1 || numDigits > 18) return 0;
		
		long max = 9;
		
		for (int i=1; i < numDigits; ++i) {
			max = max * 10 + 9;
		}
		
		return max;
	}
	
}
