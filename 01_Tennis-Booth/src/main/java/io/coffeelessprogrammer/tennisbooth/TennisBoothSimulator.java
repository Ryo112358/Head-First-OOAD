package io.coffeelessprogrammer.tennisbooth;

import io.coffeelessprogrammer.tennisbooth.utility.PhoneNumberGenerator;

public class TennisBoothSimulator {

	public static void main(String[] args) {
		
		PhoneNumberGenerator.genRandPhoneNumber(10);	// E.g. 477-171-3969
		PhoneNumberGenerator.genRandPhoneNumber(11);	// E.g. 314-4640-6032
		PhoneNumberGenerator.genRandPhoneNumber(13);	// E.g. 439-983-806-3968
		PhoneNumberGenerator.genRandPhoneNumber(18);	// E.g 632060126810573808
	}
	
}
