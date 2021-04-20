package io.coffeelessprogrammer.tennisbooth.utility;

import java.util.Random;
import java.util.stream.LongStream;

public final class PhoneNumberGenerator {
	
	public static String genRandPhoneNumber(int length) {
		return formatAsPhoneNumber(generateRandNumberOfLength(length));
	}
	
	public static long generateRandNumberOfLength(int length) {
		if (length < 5 || length > 18) return 0L;
		
		long randNumber;
		
		Random rand = new Random(System.currentTimeMillis());
		
		long lowerBound = (long) Math.pow(10L, length-1);
		long upperBound = genMaxValueOfNDigits(length);
		
		try(LongStream longStream = rand.longs(1, lowerBound, upperBound+1)) {
			
			randNumber = longStream.findFirst().getAsLong();
		}
		
		return randNumber;
	}
	
	// #region Helpers
	
	public static long genMaxValueOfNDigits(int numDigits) {
		if (numDigits < 1 || numDigits > 18) return 0;
		
		long max = 9L;
		
		for (int i=1; i < numDigits; ++i) {
			max = max * 10 + 9;
		}
		
		return max;
	}
	
	private static String formatAsPhoneNumber(long number) {
		
		int digitCount = Long.toString(number).length();
		
		String phoneNumber;
		
		if (digitCount == 9) {
			phoneNumber = ((Long) number).toString().replaceAll("^(\\d{3})(\\d{3})(\\d{3})$", "$1-$2-$3");
		}
		else if (digitCount == 10) {
			phoneNumber = ((Long) number).toString().replaceAll("^(\\d{3})(\\d{3})(\\d{4})$", "$1-$2-$3");
		}
		else if (digitCount == 11) {
			phoneNumber = ((Long) number).toString().replaceAll("^(\\d{3})(\\d{4})(\\d{4})$", "$1-$2-$3");
		}
		else if (digitCount == 12) {
			phoneNumber = ((Long) number).toString().replaceAll("^(\\d{4})(\\d{4})(\\d{4})$", "$1-$2-$3");
		}
		else if (digitCount == 13) {
			phoneNumber = ((Long) number).toString().replaceAll("^(\\d{3})(\\d{3})(\\d{3})(\\d{4})$", "$1-$2-$3-$4");
		}
		else {
			phoneNumber = ((Long) number).toString();
		}
	
		// System.out.println(number + " — " + phoneNumber);
		
		return phoneNumber;
	}
	
	// #endRegion
}
