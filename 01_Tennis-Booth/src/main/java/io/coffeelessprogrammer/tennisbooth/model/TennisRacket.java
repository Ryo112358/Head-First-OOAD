package io.coffeelessprogrammer.tennisbooth.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import io.coffeelessprogrammer.tennisbooth.utility.RangeValidator;
import lombok.AccessLevel;


@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class TennisRacket {
	
	@Getter private String serialNumber;
	@Getter @Setter private double priceUSD;

	@Getter private TennisRacketSpec spec;
	
	public boolean matches(TennisRacketFilterCriteria filterCriteria) {
		
		
		// ---| Compare racket price with filter criteria
		
		double[] filterPriceRange = filterCriteria.getPrice();
		if (!RangeValidator.isValueInRange(this.priceUSD, filterPriceRange))
			return false;
		
		
		// ---| Compare racket specification with filter criteria

		Brand filterBrand = filterCriteria.getBrand();
		if (filterBrand != null && filterBrand != this.spec.getBrand())
			return false;

		String filterModel = filterCriteria.getModel();
		if (filterModel != null && !filterModel.isEmpty()
				&& !this.spec.getModel().toLowerCase().contains(filterModel.toLowerCase()))
			return false;
		
		byte filterMains = filterCriteria.getMains();
		if (filterMains != 0 && filterMains != this.spec.getMains())
			return false;
		
		byte filterCrosses = filterCriteria.getCrosses();
		if (filterCrosses != 0 && filterCrosses != this.spec.getCrosses())
			return false;
		
		float[] filterHeadSizeRange = filterCriteria.getHeadSizeRangeCmSq();
		if (!RangeValidator.isValueInRange(this.spec.getHeadSizeCmSq(), filterHeadSizeRange))
			return false;
		
		float[] filterLengthRange = filterCriteria.getLengthRangeCm();
		if (!RangeValidator.isValueInRange(this.spec.getLengthCm(), filterLengthRange))
			return false;
		
		short[] filterStrungWeightRange = filterCriteria.getStrungWeightRangeGrams();
		if (!RangeValidator.isValueInRange(this.spec.getStrungWeightGrams(), filterStrungWeightRange))
			return false;

		Boolean filterRacketBalance = filterCriteria.getIsHeadLight();
		if (filterRacketBalance != null && filterRacketBalance != this.spec.isHeadLight())
			return false;
		
		byte[] filterBalancePtsRange = filterCriteria.getBalancePointsRange();
		if (!RangeValidator.isValueInRange(this.spec.getBalancePoints(), filterBalancePtsRange))
			return false;
		
		return true;
	}
}
