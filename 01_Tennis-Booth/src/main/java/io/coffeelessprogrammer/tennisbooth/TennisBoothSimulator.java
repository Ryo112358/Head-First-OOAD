package io.coffeelessprogrammer.tennisbooth;

import java.util.Random;
import java.util.stream.LongStream;

public class TennisBoothSimulator {

	public static void main(String[] args) {
		
		genRandomPhoneNumOfLength(6);
		genRandomPhoneNumOfLength(8);
		genRandomPhoneNumOfLength(9);
		genRandomPhoneNumOfLength(10);
		genRandomPhoneNumOfLength(11);
		genRandomPhoneNumOfLength(12);
		genRandomPhoneNumOfLength(13);
		genRandomPhoneNumOfLength(14);
		genRandomPhoneNumOfLength(18);
	}
	
	public static String genRandomPhoneNumOfLength(int length) {
		if (length < 1 || length > 18) return null;
		
		String phoneNumber;
		
		Random rand = new Random(System.currentTimeMillis());
		
		long lowerBound = (long) Math.pow(10L, length-1);
		long upperBound = genMaxValueOfNDigits(length);
		
		try(LongStream longStream = rand.longs(lowerBound, upperBound+1).limit(1)) {
			
			long phoneNum = longStream.findFirst().getAsLong();
			
			if (length == 9) {
				phoneNumber = ((Long) phoneNum).toString().replaceAll("^(\\d{3})(\\d{3})(\\d{3})$", "$1-$2-$3");
			}
			else if (length == 10) {
				phoneNumber = ((Long) phoneNum).toString().replaceAll("^(\\d{3})(\\d{3})(\\d{4})$", "$1-$2-$3");
			}
			else if (length == 11) {
				phoneNumber = ((Long) phoneNum).toString().replaceAll("^(\\d{3})(\\d{4})(\\d{4})$", "$1-$2-$3");
			}
			else if (length == 12) {
				phoneNumber = ((Long) phoneNum).toString().replaceAll("^(\\d{4})(\\d{4})(\\d{4})$", "$1-$2-$3");
			}
			else if (length == 13) {
				phoneNumber = ((Long) phoneNum).toString().replaceAll("^(\\d{3})(\\d{3})(\\d{3})(\\d{4})$", "$1-$2-$3-$4");
			}
			else {
				phoneNumber = ((Long) phoneNum).toString();
			}
		
			System.out.println(phoneNum + " — " + phoneNumber);
		}
		
		return phoneNumber;
	}
	
	public static long genMaxValueOfNDigits(int numDigits) {
		if (numDigits < 1 || numDigits > 18) return 0;
		
		long max = 9L;
		
		for (int i=1; i < numDigits; ++i) {
			max = max * 10 + 9;
		}
		
		return max;
	}
}
