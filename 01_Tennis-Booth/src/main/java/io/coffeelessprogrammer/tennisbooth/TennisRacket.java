package io.coffeelessprogrammer.tennisbooth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;


@AllArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
public class TennisRacket {

	@Getter private String model;
	@Getter private String brand;
	
	@Getter private String serialNumber;
	@Getter @Setter private double priceUSD;
	
	@Getter private float headSizeCmSq;
	@Getter private float lengthCm;
	@Getter private short strungWeightGrams;
	@Getter private boolean isHeadLight;
	@Getter private byte balancePoints;
	
	@Getter private String frameComposition;
}
