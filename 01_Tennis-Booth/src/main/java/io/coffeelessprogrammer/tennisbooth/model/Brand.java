package io.coffeelessprogrammer.tennisbooth.model;

public enum Brand {
	TECHNIFIBRE("Technifibre"), BABOLAT("Babolat"), PACIFIC("Pacific"), WILSON("Wilson"),
	HEAD("Head"), PRINCE("Prince"), VOLKL("Volkl"), DIADEM("Diadem");

	private String brand;

	private Brand(String brand) {
		this.brand = brand;
	}

	public String toString() {
		return this.brand;
	}
}
