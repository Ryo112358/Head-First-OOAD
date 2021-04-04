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
	
	@Getter private float headSizeCmSq;
	@Getter private float lengthCm;
	@Getter private short strungWeightGrams;
	@Getter private Boolean isHeadLight;
	@Getter private byte balancePoints;
	
	@Getter private byte mains;
	@Getter private byte crosses;
	
	@Getter private String frameComposition;
	
	public boolean matches(TennisRacketSpec racketSpec) {

		Brand searchBrand = racketSpec.getBrand();
		if (searchBrand != null && searchBrand != this.brand)
			return false;

		String searchModel = racketSpec.getModel();
		if (searchModel != null && !searchModel.isEmpty()
				&& !this.model.toLowerCase().contains(searchModel.toLowerCase()))
			return false;

		Boolean searchBalance = racketSpec.getIsHeadLight();
		if (searchBalance != null && searchBalance != this.isHeadLight)
			return false;
		
		byte searchMains = racketSpec.getMains();
		if (searchMains != 0 && searchMains != this.mains)
			return false;
		
		byte searchCrosses = racketSpec.getCrosses();
		if (searchCrosses != 0 && searchCrosses != this.crosses)
			return false;
		
		return true;
	}

}
