package io.coffeelessprogrammer.tennisbooth.utility;

import java.util.Random;
import java.util.stream.LongStream;

import io.coffeelessprogrammer.tennisbooth.constants.CountryCodeISO;

public final class RandomPhoneNumberGenerator {
	
	public static String generate(int length) {
		return formatAsPhoneNumber(generateRandNumberOfLength(length));
	}
	
	public static String generate(CountryCodeISO country) {
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
	
	public static String generateRandNumberOfLength(int length) {
		if (length < 5 || length > 18) return null;
		
		long randNumber;
		
		Random rand = new Random(System.currentTimeMillis());
		
		long lowerBound = (long) Math.pow(10L, length-1);
		long upperBound =  (long) Math.pow(10L, length);
		
		try(LongStream longStream = rand.longs(1, lowerBound, upperBound)) {
			
			randNumber = longStream.findFirst().getAsLong();
		}
		
		return ((Long) randNumber).toString();
	}
	
	public static String generateRandNumberOfLength(int length, int firstDigit) {
		if (length < 5 || length > 18) return null;
		if (firstDigit < 0 || firstDigit > 9) return null;
		
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
		
		if (firstDigit == 0)
			return "0" + ((Long) randNumber).toString();
		
		return ((Long) randNumber).toString();
	}
	
	public static String generateRandNumberOfLength(int totalLength, String numericalPrefix) {
		if (totalLength < 5 || totalLength > 18) return null;
		try {
			Integer.decode(numericalPrefix);
		} catch (NumberFormatException nfe) { return null; }
		
		long randNumber;
		
		Random rand = new Random(System.currentTimeMillis());
		
		long lowerBound, upperBound;
		
		lowerBound = (long) Math.pow(10L, totalLength-numericalPrefix.length()-1);
		upperBound = (long) Math.pow(10L, totalLength-numericalPrefix.length());
		
		try(LongStream longStream = rand.longs(1, lowerBound, upperBound)) {
			randNumber = longStream.findFirst().getAsLong();
		}
		
		return numericalPrefix + randNumber;
	}
	
	// #region Helpers
	
	private static String formatAsPhoneNumber(String number) {
		
		int digitCount = number.length();
		
		String phoneNumber;
		
		if (digitCount == 7) {
			phoneNumber = number.replaceAll("^(\\d{3})(\\d{4})$", "$1-$2");
		}
		else if (digitCount == 8) {
			phoneNumber = number.replaceAll("(\\d{4})(\\d{4})$", "$1-$2");
		}
		else if (digitCount == 9) {
			phoneNumber = number.replaceAll("^(\\d{3})(\\d{3})(\\d{3})$", "$1-$2-$3");
		}
		else if (digitCount == 10) {
			phoneNumber = number.replaceAll("^(\\d{3})(\\d{3})(\\d{4})$", "$1-$2-$3");
		}
		else if (digitCount == 11) {
			phoneNumber = number.replaceAll("^(\\d{3})(\\d{4})(\\d{4})$", "$1-$2-$3");
		}
		else if (digitCount == 12) {
			phoneNumber = number.replaceAll("^(\\d{4})(\\d{4})(\\d{4})$", "$1-$2-$3");
		}
		else if (digitCount == 13) {
			phoneNumber = number.replaceAll("^(\\d{3})(\\d{3})(\\d{3})(\\d{4})$", "$1-$2-$3-$4");
		}
		else {
			phoneNumber = number;
		}
	
		// System.out.println(number + " — " + phoneNumber);
		
		return phoneNumber;
	}
	
	// #endRegion
	
	// #region GeneratePhoneNumberByCountry
	
	public static String genChinaPhoneNumber(boolean includeCountryCallingCode, boolean isBeijingNumber) {
		String phoneNumber;
		String randNum;
		
		if (isBeijingNumber) {
			randNum = generateRandNumberOfLength(11, 0);
		} else {
			randNum = generateRandNumberOfLength(11);
		}
		
		phoneNumber = randNum.toString().replaceAll("^(\\d{3})(\\d{4})(\\d{4})$", "$1-$2-$3");
		
		if (includeCountryCallingCode)
			return "+86 " + phoneNumber;
		
		return phoneNumber;
	}
	
	public static String genEstoniaPhoneNumber(boolean includeCountryCallingCode) {
		String randNum = generateRandNumberOfLength(8, 5);
		String phoneNumber = randNum.replaceAll("^(\\d{4})(\\d{4})$", "$1-$2");
		
		if (includeCountryCallingCode)
			return "+372 " + phoneNumber;
		
		return phoneNumber;
	}
	
	public static String genUnitedStatesPhoneNumber(boolean includeCountryCallingCode) {
		String randNum = generateRandNumberOfLength(10);
		String phoneNumber = randNum.replaceAll("^(\\d{3})(\\d{3})(\\d{4})$", "$1-$2-$3");
		
		if (includeCountryCallingCode)
			return "+1 " + phoneNumber;
		
		return phoneNumber;
	}
	
	// #endRegion
}
