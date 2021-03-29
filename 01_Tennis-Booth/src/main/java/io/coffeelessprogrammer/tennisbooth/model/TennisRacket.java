package io.coffeelessprogrammer.tennisbooth.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;


@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class TennisRacket {

	@Getter private String model;
	@Getter private Brand brand;
	
	@Getter private String serialNumber;
	@Getter @Setter private double priceUSD;
	
	@Getter private float headSizeCmSq;
	@Getter private float lengthCm;
	@Getter private short strungWeightGrams;
	@Getter private Boolean isHeadLight;
	@Getter private byte balancePoints;
	
	@Getter private String frameComposition;
}
