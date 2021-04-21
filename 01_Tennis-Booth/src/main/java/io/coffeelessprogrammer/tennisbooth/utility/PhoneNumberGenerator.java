package io.coffeelessprogrammer.tennisbooth.utility;

import java.util.Random;
import java.util.stream.LongStream;

import io.coffeelessprogrammer.tennisbooth.constants.CountryCodeISO;

public final class PhoneNumberGenerator {
	
	public static String genRandPhoneNumberForCountry(CountryCodeISO country) {
		String phoneNumber;
		
		switch(country) {
			case CHINA:
				phoneNumber = genChinaPhoneNumber(true, false);
				break;
			case ESTONIA:
				phoneNumber = genEstoniaPhoneNumber(true);
				break;
			default:
				phoneNumber = genUnitedStatesPhoneNumber(true);
				break;
		}
		
		return phoneNumber;
	}
	
	public static String genRandPhoneNumber(int length) {
		return formatAsPhoneNumber(generateRandNumberOfLength(length));
	}
	
	public static long generateRandNumberOfLength(int length) {
		if (length < 5 || length > 18) return 0L;
		
		long randNumber;
		
		Random rand = new Random(System.currentTimeMillis());
		
		long lowerBound = (long) Math.pow(10L, length-1);
		long upperBound = genMaxValueOfNDigits(length) + 1;
		
		try(LongStream longStream = rand.longs(1, lowerBound, upperBound)) {
			
			randNumber = longStream.findFirst().getAsLong();
		}
		
		return randNumber;
	}
	
	public static long generateRandNumberOfLength(int length, int firstDigit) {
		if (length < 5 || length > 18) return 0L;
		if (firstDigit < 0 || firstDigit > 9) return 0L;
		
		long randNumber;
		
		Random rand = new Random(System.currentTimeMillis());
		
		long lowerBound, upperBound;
		
		if(firstDigit == 0) {
			lowerBound = (long) Math.pow(10L, length-2);
			upperBound = (long) Math.pow(10L, length-1);
		}
		else if (firstDigit == 9) {
			lowerBound = (long) Math.pow(10L, length-1) * firstDigit;
			upperBound = (long) Math.pow(10L, length);
		}
		else {
			lowerBound = (long) Math.pow(10L, length-1) * firstDigit;
			upperBound = (long) Math.pow(10L, length-1) * (firstDigit + 1);
		}
		
		try(LongStream longStream = rand.longs(1, lowerBound, upperBound)) {
			randNumber = longStream.findFirst().getAsLong();
		}
		
		return randNumber;
	}
	
	// #region Helpers
	
	private static long genMaxValueOfNDigits(int numDigits) {
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
		
		if (digitCount == 7) {
			phoneNumber = ((Long) number).toString().replaceAll("^(\\d{3})(\\d{4})$", "$1-$2");
		}
		else if (digitCount == 8) {
			phoneNumber = ((Long) number).toString().replaceAll("(\\d{4})(\\d{4})$", "$1-$2");
		}
		else if (digitCount == 9) {
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
	
	// #region GeneratePhoneNumberByCountry
	
	public static String genChinaPhoneNumber(boolean includeCountryCallingCode, boolean isBeijingNumber) {
		String phoneNumber;
		long randNum;
		
		if (isBeijingNumber) {
			randNum = generateRandNumberOfLength(11, 0);
			phoneNumber = Long.toString(randNum).replaceAll("^(\\d{2})(\\d{4})(\\d{4})$", "0$1-$2-$3");
		} else {
			randNum = generateRandNumberOfLength(11);
			phoneNumber = ((Long) randNum).toString().replaceAll("^(\\d{3})(\\d{4})(\\d{4})$", "$1-$2-$3");
		}
		
		if (includeCountryCallingCode) {
			return "+86 " + phoneNumber;
		}
		
		return phoneNumber;
	}
	
	public static String genEstoniaPhoneNumber(boolean includeCountryCallingCode) {
		long randNum = generateRandNumberOfLength(8, 5);
		String phoneNumber = ((Long) randNum).toString().replaceAll("^(\\d{4})(\\d{4})$", "$1-$2");
		
		if (includeCountryCallingCode) {
			return "+372 " + phoneNumber;
		}
		
		return phoneNumber;
	}
	
	public static String genUnitedStatesPhoneNumber(boolean includeCountryCallingCode) {
		long randNum = generateRandNumberOfLength(10);
		String phoneNumber = ((Long) randNum).toString().replaceAll("^(\\d{3})(\\d{3})(\\d{4})$", "$1-$2-$3");
		
		if (includeCountryCallingCode) {
			return "+1 " + phoneNumber;
		}
		
		return phoneNumber;
	}
	
	// #endRegion
}
