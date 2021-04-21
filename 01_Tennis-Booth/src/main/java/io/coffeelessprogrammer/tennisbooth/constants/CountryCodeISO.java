package io.coffeelessprogrammer.tennisbooth.constants;

import lombok.Getter;

public enum CountryCodeISO {
	CHINA("CHN"),
	ESTONIA("EST"),
	UNITED_STATES("USA");
	
	@Getter
	private final String countryCode;
	
	CountryCodeISO(String countryCode) { this.countryCode = countryCode; }
	
	public final String toString() { return this.countryCode; }
}
