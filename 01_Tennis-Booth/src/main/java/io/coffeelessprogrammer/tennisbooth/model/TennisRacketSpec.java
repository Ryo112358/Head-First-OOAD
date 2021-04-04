package io.coffeelessprogrammer.tennisbooth.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class TennisRacketSpec {

	@Getter private String model;
	@Getter private Brand brand;
	
	@Getter private byte mains;
	@Getter private byte crosses;
	
	@Getter private float headSizeCmSq;
	@Getter private float lengthCm;
	@Getter private short strungWeightGrams;
	@Getter private boolean isHeadLight;
	@Getter private byte balancePoints;
	
	@Getter private String frameComposition;

}
