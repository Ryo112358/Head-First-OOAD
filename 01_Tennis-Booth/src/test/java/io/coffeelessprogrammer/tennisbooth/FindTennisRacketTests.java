package io.coffeelessprogrammer.tennisbooth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.coffeelessprogrammer.tennisbooth.model.Brand;
import io.coffeelessprogrammer.tennisbooth.model.TennisRacket;
import io.coffeelessprogrammer.tennisbooth.model.TennisRacketFilterCriteria;
import io.coffeelessprogrammer.tennisbooth.utility.RangeValidator;


public final class FindTennisRacketTests {

	private static final Inventory inventory = new Inventory();

	@BeforeAll
	private static void setup() {

		// ---| Initialize Inventory

		// ---| Technifibre
		inventory.addTennisRacket("T01", 229.00, "TFight 315 RS", Brand.TECHNIFIBRE, (byte) 16, (byte) 19, 632.26f,
				68.58f, (short) 332, true, (byte) 7, "Graphite/Dynacore HD");

		// ---| Babolat
		inventory.addTennisRacket("B01", 219.00, "Pure Strike 18x20 3rd Gen", Brand.BABOLAT, (byte) 18, (byte) 20,
				632.26f, 68.58f, (short) 323, true, (byte) 4, "Graphite");
		inventory.addTennisRacket("B02", 229.00, "Pure Drive Plus 2021", Brand.BABOLAT, (byte) 16, (byte) 19, 645.16f,
				69.85f, (short) 318, true, (byte) 6, "Graphite");

		// ---| Wilson
		inventory.addTennisRacket("W01", 249.00, "Pro Staff RF97 v13", Brand.WILSON, (byte) 16, (byte) 19, 625.81f,
				68.58f, (short) 357, true, (byte) 9, "Graphite/Aramid");
		inventory.addTennisRacket("W02", 169.00, "Triad Five", Brand.WILSON, (byte) 16, (byte) 20, 664.51f, 69.22f,
				(short) 283, false, (byte) 6, "Graphite");
		inventory.addTennisRacket("W03", 99.00, "Hyper Hammer 5.3 Stretch OS", Brand.WILSON, (byte) 16, (byte) 20,
				709.68f, 69.85f, (short) 255, false, (byte) 8, "Graphite/Hyper Carbon");

		// ---| Head
		inventory.addTennisRacket("H01", 189.00, "Graphene 360+ Instinct PWR", Brand.HEAD, (byte) 16, (byte) 19,
				741.93f, 70.36f, (short) 244, false, (byte) 9, "Graphite/Graphene 360+");
	}

	// #region TennisRacketInventoryFilterTests

	@DisplayName("Find Pro Staff RF97")
	@Test
	final void SearchByModel_When_MatchingModelCapitalization_Then_RacketFound() {
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria("Pro Staff RF97 v13", Brand.WILSON,
				null, (byte) 0, (byte) 0, null, null, null, true, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 1);
		assertEquals(searchResult.get(0).getSpec().getModel(), "Pro Staff RF97 v13");
	}

	@DisplayName("Find Pro Staff RF97")
	@Test
	final void SearchByModel_When_DifferingModelCapitalization_Then_RacketFound() {
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria("PRO StaFF Rf97 V13", null, null,
				(byte) 0, (byte) 0, null, null, null, null, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 1);
		assertEquals(searchResult.get(0).getSpec().getModel(), "Pro Staff RF97 v13");
	}

	@DisplayName("Conflicting Model & Brand")
	@Test
	final void SearchByModel_When_WrongBrand_Then_RacketNotFound() {
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria("PRO STAFF RF97 V13", Brand.BABOLAT,
				null, (byte) 0, (byte) 0, null, null, null, null, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 0);
	}

	@DisplayName("Find All Rackets Containing 'Pure'")
	@Test
	final void SearchByModel_When_ModelContainsPure_Then_ReturnPureBabolatRackets() {
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria("pure", null, null, (byte) 0,
				(byte) 0, null, null, null, null, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 2);

		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext();) {
			TennisRacket racket = i.next();
			assertEquals(racket.getSpec().getBrand(), Brand.BABOLAT);
		}
	}

	@DisplayName("Find All Wilson Rackets")
	@Test
	final void SearchByBrand_When_BrandWilson_Then_ReturnWilsonRackets() {
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria(null, Brand.WILSON, null, (byte) 0,
				(byte) 0, null, null, null, null, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 3);

		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext();) {
			TennisRacket racket = i.next();
			assertEquals(racket.getSpec().getBrand(), Brand.WILSON);
		}
	}

	@DisplayName("Find All Rackets w/ 18 Mains")
	@Test
	final void SearchByStringPattern_When_Criteria18Mains_Then_Return18MainRackets() {
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria(null, null, null, (byte) 18,
				(byte) 0, null, null, null, null, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 1);

		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext();) {
			TennisRacket racket = i.next();
			assertEquals(racket.getSpec().getMains(), (byte) 18);
		}
	}

	@DisplayName("Find All Rackets w/ 20 Crosses")
	@Test
	final void SearchByStringPattern_When_Criteria20Crosses_Then_Return20CrossRackets() {
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria(null, null, null, (byte) 0,
				(byte) 20, null, null, null, null, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 3);

		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext();) {
			TennisRacket racket = i.next();
			assertEquals(racket.getSpec().getCrosses(), (byte) 20);
		}
	}

	@DisplayName("Find All Rackets w/ 16x20 String Pattern")
	@Test
	final void SearchByStringPattern_When_CriteriaMains16Crosses20_Then_Return16x20Rackets() {
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria(null, null, null, (byte) 16,
				(byte) 20, null, null, null, null, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 2);

		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext();) {
			TennisRacket racket = i.next();
			assertEquals(racket.getSpec().getMains(), (byte) 16);
			assertEquals(racket.getSpec().getCrosses(), (byte) 20);
		}
	}

	@DisplayName("Find All Rackets Under $225 in Price")
	@Test
	final void SearchByPrice_When_PriceRangeUnder225_Then_ReturnRacketsInRange() {
		double[] priceRange = {0, 225};
		
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria(null, null, priceRange, (byte) 0,
				(byte) 0, null, null, null, null, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 4);

		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext();) {
			TennisRacket racket = i.next();
			assertEquals(RangeValidator.isValueInRange(racket.getPriceUSD(), priceRange), true);
		}
	}

	@DisplayName("Find All Rackets Over $225 in Price")
	@Test
	final void SearchByPrice_When_PriceRangeOver225_Then_ReturnRacketsInRange() {
		double[] priceRange = {225, 0};
		
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria(null, null, priceRange, (byte) 0,
				(byte) 0, null, null, null, null, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 3);

		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext();) {
			TennisRacket racket = i.next();
			assertEquals(RangeValidator.isValueInRange(racket.getPriceUSD(), priceRange), true);
		}
	}

	@DisplayName("Find All Rackets in Price Range $80-$200")
	@Test
	final void SearchByPrice_When_PriceRange80To200_Then_ReturnRacketsInRange() {
		double[] priceRange = {80, 200};
		
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria(null, null, priceRange, (byte) 0,
				(byte) 0, null, null, null, null, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 3);

		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext();) {
			TennisRacket racket = i.next();
			assertEquals(RangeValidator.isValueInRange(racket.getPriceUSD(), priceRange), true);
		}
	}

	@DisplayName("Find All Rackets in Price Range $200-$250")
	@Test
	final void SearchByPrice_When_PriceRange200To250_Then_ReturnRacketsInRange() {
		double[] priceRange = {200, 250};
		
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria(null, null, priceRange, (byte) 0,
				(byte) 0, null, null, null, null, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 4);

		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext();) {
			TennisRacket racket = i.next();
			assertEquals(RangeValidator.isValueInRange(racket.getPriceUSD(), priceRange), true);
		}
	}

	@DisplayName("Find All Rackets w/ Head Size b/w 625 - 650 Cm Sq")
	@Test
	final void SearchByHeadSize_When_Range625To650_Then_ReturnRacketsInRange() {
		float[] headSizeRange = {625f, 650f};
		
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria(null, null, null, (byte) 0,
				(byte) 0, headSizeRange, null, null, null, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 4);

		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext();) {
			TennisRacket racket = i.next();
			assertEquals(RangeValidator.isValueInRange(racket.getSpec().getHeadSizeCmSq(), headSizeRange), true);
		}
	}

	@DisplayName("Find All Rackets w/ Length b/w 68.58 - 69.85 Cm")
	@Test
	final void SearchByRacketLength_When_Range68To70_Then_ReturnRacketsInRange() {
		float[] racketLengthRange = {68.58f, 69.85f};
		
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria(null, null, null, (byte) 0,
				(byte) 0, null, racketLengthRange, null, null, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 6);

		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext();) {
			TennisRacket racket = i.next();
			assertEquals(RangeValidator.isValueInRange(racket.getSpec().getLengthCm(), racketLengthRange), true);
		}
	}

	@DisplayName("Find All Rackets w/ Strung Weight b/w 280 - 360 Grams")
	@Test
	final void SearchByStrungWeight_When_Range280To360_Then_ReturnRacketsInRange() {
		short[] strungWeightRange = {280, 360};
		
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria(null, null, null, (byte) 0,
				(byte) 0, null, null, strungWeightRange, null, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 5);

		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext();) {
			TennisRacket racket = i.next();
			assertEquals(RangeValidator.isValueInRange(racket.getSpec().getStrungWeightGrams(), strungWeightRange), true);
		}
	}

	@DisplayName("Find All Head Light Rackets")
	@Test
	final void SearchByBalance_When_CriteriaHeadLight_Then_ReturnHeadLightRackets() {
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria(null, null, null, (byte) 0, (byte) 0,
				null, null, null, true, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 4);

		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext();) {
			TennisRacket racket = i.next();
			assertEquals(racket.getSpec().isHeadLight(), true);
		}
	}

	@DisplayName("Find All Rackets b/w 7-9 Points Head Heavy")
	@Test
	final void SearchByBalance_When_CriteriaHeadHeavy7To9Pts_Then_ReturnRacketsInRange() {
		byte[] balancePtsRange = {7, 9};
		
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria(null, null, null, (byte) 0, (byte) 0,
				null, null, null, true, balancePtsRange);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 2);

		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext();) {
			TennisRacket racket = i.next();
			assertEquals(RangeValidator.isValueInRange(racket.getSpec().getBalancePoints(), balancePtsRange), true);
		}
	}

	@DisplayName("Find All Rackets Over 5 Points Head Light")
	@Test
	final void SearchByBalance_When_CriteriaOver5PtsHeadLight_Then_ReturnRacketsInRange() {
		byte[] balancePtsRange = {5, 0};
		
		TennisRacketFilterCriteria racketCriteria = new TennisRacketFilterCriteria(null, null, null, (byte) 0, (byte) 0,
				null, null, null, true, balancePtsRange);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 3);

		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext();) {
			TennisRacket racket = i.next();
			assertEquals(RangeValidator.isValueInRange(racket.getSpec().getBalancePoints(), balancePtsRange), true);
		}
	}

	// #endRegion
}
