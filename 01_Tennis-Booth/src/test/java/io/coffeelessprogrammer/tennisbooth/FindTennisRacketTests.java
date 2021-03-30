package io.coffeelessprogrammer.tennisbooth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.coffeelessprogrammer.tennisbooth.model.Brand;
import io.coffeelessprogrammer.tennisbooth.model.TennisRacket;
import io.coffeelessprogrammer.tennisbooth.model.TennisRacketSpec;


public final class FindTennisRacketTests {

	private static final Inventory inventory = new Inventory();

	@BeforeAll
	private static void setup() {

		// ---| Initialize Inventory

		// ---| Technifibre
		inventory.addTennisRacket("T01", 229.00, "TFight 315 RS", Brand.TECHNIFIBRE, 632.26f, 68.58f, (short) 332, true,
				(byte) 7, "Graphite/Dynacore HD");

		// ---| Babolat
		inventory.addTennisRacket("B01", 219.00, "Pure Strike 18x20 3rd Gen", Brand.BABOLAT, 632.26f, 68.58f, (short) 323,
				true, (byte) 4, "Graphite");
		inventory.addTennisRacket("B02", 229.00, "Pure Drive Plus 2021", Brand.BABOLAT, 645.16f, 69.85f, (short) 318, true,
				(byte) 6, "Graphite");

		// ---| Wilson
		inventory.addTennisRacket("W01", 249.00, "Pro Staff RF97 v13", Brand.WILSON, 625.81f, 68.58f, (short) 357, true,
				(byte) 9, "Graphite/Aramid");
		inventory.addTennisRacket("W02", 169.00, "Triad Five", Brand.WILSON, 664.51f, 69.22f, (short) 283, false, (byte) 6,
				"Graphite");
		inventory.addTennisRacket("W03", 99.00, "Hyper Hammer 5.3 Stretch OS", Brand.WILSON, 709.68f, 69.85f, (short) 255,
				false, (byte) 8, "Graphite/Hyper Carbon");

		// ---| Head
		inventory.addTennisRacket("H01", 189.00, "Graphene 360+ Instinct PWR", Brand.HEAD, 741.93f, 70.36f, (short) 244,
				false, (byte) 9, "Graphite/Graphene 360+");
	}
	
	// #region UnitTests

	@DisplayName("Find Pro Staff RF97")
	@Test
	public final void SearchForProStaff_When_MatchingModelCapitalization_Then_RacketFound() {
		TennisRacketSpec racketCriteria = new TennisRacketSpec("Pro Staff RF97 v13", Brand.WILSON, 0f, 0f, (short) 0, true,
				(byte) 0, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 1);
		assertEquals(searchResult.get(0).getSpec().getModel(), "Pro Staff RF97 v13");
	}

	@DisplayName("Find Pro Staff RF97")
	@Test
	public final void SearchForProStaff_When_DifferingModelCapitalization_Then_RacketFound() {
		TennisRacketSpec racketCriteria = new TennisRacketSpec("PRO StaFF Rf97 V13", Brand.WILSON, 0f, 0f, (short) 0, true,
				(byte) 0, null);
		
		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 1);
		assertEquals(searchResult.get(0).getSpec().getModel(), "Pro Staff RF97 v13");
	}

	@DisplayName("Conflicting Model & Brand")
	@Test
	public final void SearchForProStaff_When_WrongBrand_Then_RacketNotFound() {
		TennisRacketSpec racketCriteria = new TennisRacketSpec("PRO STAFF RF97 V13", Brand.BABOLAT, 0f, 0f, (short) 0, null,
				(byte) 0, null);
		
		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 0);
	}

	@DisplayName("Find All Wilson Rackets")
	@Test
	public final void SearchForBrand_When_BrandWilson_Then_ReturnAllWilsonRackets() {
		TennisRacketSpec racketCriteria = new TennisRacketSpec(null, Brand.WILSON, 0f, 0f, (short) 0, null,
				(byte) 0, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 3);
		
		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext(); ) {
			TennisRacket racket = i.next();
			assertEquals(racket.getSpec().getBrand(), Brand.WILSON);
		}
	}

	@DisplayName("Find All Rackets Containing 'Pure'")
	@Test
	public final void SearchForModel_When_ModelContainsPure_Then_ReturnPureBabolatRackets() {
		TennisRacketSpec racketCriteria = new TennisRacketSpec("pure", null, 0f, 0f, (short) 0, null,
				(byte) 0, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 2);
		
		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext(); ) {
			TennisRacket racket = i.next();
			assertEquals(racket.getSpec().getBrand(), Brand.BABOLAT);
		}
	}

	@DisplayName("Find All Head Light Rackets")
	@Test
	public final void SearchForBalance_When_CriteriaHeadLight_Then_ReturnAllHeadLightRackets() {
		TennisRacketSpec racketCriteria = new TennisRacketSpec(null, null, 0f, 0f, (short) 0, true,
				(byte) 0, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 4);
	}
	
	// #endRegion
}
