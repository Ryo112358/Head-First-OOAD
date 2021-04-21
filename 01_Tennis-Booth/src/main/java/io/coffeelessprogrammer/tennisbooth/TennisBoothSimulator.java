package io.coffeelessprogrammer.tennisbooth;

import io.coffeelessprogrammer.tennisbooth.constants.CountryCodeISO;
import io.coffeelessprogrammer.tennisbooth.utility.PhoneNumberGenerator;

public class TennisBoothSimulator {

	public static void main(String[] args) {
		
		PhoneNumberGenerator.genRandPhoneNumber(10);	// E.g. 477-171-3969
		PhoneNumberGenerator.genRandPhoneNumber(11);	// E.g. 314-4640-6032
		PhoneNumberGenerator.genRandPhoneNumber(13);	// E.g. 439-983-806-3968
		PhoneNumberGenerator.genRandPhoneNumber(18);	// E.g 632060126810573808
		
		System.out.println(PhoneNumberGenerator.genRandPhoneNumberForCountry(CountryCodeISO.ESTONIA));			// E.g. +372 5464-9559
		System.out.println(PhoneNumberGenerator.genRandPhoneNumberForCountry(CountryCodeISO.UNITED_STATES));	// E.g. +1 164-545-5591
		System.out.println(PhoneNumberGenerator.genRandPhoneNumberForCountry(CountryCodeISO.CHINA));			// E.g. +86 646-4545-5591
		
		System.out.println("Beijing: " + PhoneNumberGenerator.genChinaPhoneNumber(true, true));					// E.g. +86 050-3711-3527
		System.out.println("Pittsburgh: " + PhoneNumberGenerator.generateRandNumberOfLength(10, "412"));		// E.g. 4126113527
	}
	
}
