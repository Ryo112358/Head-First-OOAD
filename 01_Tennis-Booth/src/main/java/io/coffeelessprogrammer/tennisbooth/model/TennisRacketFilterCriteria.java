package io.coffeelessprogrammer.tennisbooth.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class TennisRacketFilterCriteria {

	@Getter private String model;
	@Getter private Brand brand;
	
	/* All arrays represent a range, i.e. {min, max} 
	 *  - A value of 0 represents no min/max respectively
	 */
	@Getter private double[] price;
	
	@Getter private byte mains;
	@Getter private byte crosses;
	
	@Getter private float[] headSizeRangeCmSq;			// Head size range, e.g. {645, 675}
	@Getter private float[] lengthRangeCm;				// Racket length range, e.g. {60, 70}
	@Getter private short[] strungWeightRangeGrams;		// Strung weight range, e.g. {300, 330}
	
	@Getter private Boolean isHeadLight;
	@Getter private byte[] balancePointsRange;			// Balance point range, e.g. {5, 8}

}
