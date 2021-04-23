package io.coffeelessprogrammer.tennisbooth.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.coffeelessprogrammer.tennisbooth.constants.CountryCodeISO;

final class PhoneNumberGeneratorTests {
	
	@Test
	final void GeneratePhoneNumber_UnitedStates() {
		final String phoneNumber = RandomPhoneNumberGenerator.genUnitedStatesPhoneNumber(false);
		
		System.out.println("US: " + phoneNumber);
		
		assertEquals(12, phoneNumber.length());
		assertEquals(10, phoneNumber.replaceAll("\\D", "").length());
	}
	
	@Test
	final void GeneratePhoneNumber_UnitedStates_WithCountryCode() {
		final String phoneNumber = RandomPhoneNumberGenerator.generate(CountryCodeISO.UNITED_STATES);
		
		System.out.println("US w/ Country Code: " + phoneNumber);
		
		assertTrue(phoneNumber.startsWith("+1"));
		assertEquals(15, phoneNumber.length());
		assertEquals(11, phoneNumber.replaceAll("\\D", "").length());
	}
	
	@Test
	final void GeneratePhoneNumber_China() {
		final String phoneNumber = RandomPhoneNumberGenerator.genChinaPhoneNumber(false, false);
		
		System.out.println("China: " + phoneNumber);
		
		assertEquals(13, phoneNumber.length());
		assertEquals(11, phoneNumber.replaceAll("\\D", "").length());
	}
	
	@Test
	final void GeneratePhoneNumber_Beijing_China() {
		final String phoneNumber = RandomPhoneNumberGenerator.genChinaPhoneNumber(false, true);
		
		System.out.println("Beijing, China: " + phoneNumber);
		
		assertEquals('0', phoneNumber.charAt(0));
		assertEquals(13, phoneNumber.length());
		assertEquals(11, phoneNumber.replaceAll("\\D", "").length());
	}
	
	@Test
	final void GeneratePhoneNumber_China_WithCountryCode() {
		final String phoneNumber = RandomPhoneNumberGenerator.generate(CountryCodeISO.CHINA);
		
		System.out.println("China w/ Country Code: " + phoneNumber);
		
		assertTrue(phoneNumber.startsWith("+86"));
		assertEquals(17, phoneNumber.length());
		assertEquals(13, phoneNumber.replaceAll("\\D", "").length());
	}
	
	@Test
	final void GeneratePhoneNumber_Estonia() {
		final String phoneNumber = RandomPhoneNumberGenerator.genEstoniaPhoneNumber(false);
		
		System.out.println("Estonia: " + phoneNumber);
		
		assertEquals(9, phoneNumber.length());
		assertEquals(8, phoneNumber.replaceAll("\\D", "").length());
	}
	
	@Test
	final void GeneratePhoneNumber_Estonia_WithCountryCode() {
		final String phoneNumber = RandomPhoneNumberGenerator.generate(CountryCodeISO.ESTONIA);
		
		System.out.println("Estonia w/ Country Code: " + phoneNumber);
		
		assertTrue(phoneNumber.startsWith("+372"));
		assertEquals(14, phoneNumber.length());
		assertEquals(11, phoneNumber.replaceAll("\\D", "").length());
	}
	
	@Test
	final void GeneratePhoneNumber_Pittsburgh_WithPrefix412() {
		final String phoneNumber = RandomPhoneNumberGenerator.generateRandNumberOfLength(10, "412");
		
		System.out.println("Pittsburgh, PA: " + phoneNumber);
		
		assertTrue(phoneNumber.startsWith("412"));
		assertEquals(10, phoneNumber.length());
		assertEquals(10, phoneNumber.replaceAll("\\D", "").length());
	}

}
