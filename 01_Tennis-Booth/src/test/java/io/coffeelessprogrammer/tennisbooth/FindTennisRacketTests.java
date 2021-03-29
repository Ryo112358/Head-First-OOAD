package io.coffeelessprogrammer.tennisbooth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.coffeelessprogrammer.tennisbooth.model.Brand;
import io.coffeelessprogrammer.tennisbooth.model.TennisRacket;


public final class FindTennisRacketTests {

	private static final Inventory inventory = new Inventory();

	@BeforeAll
	private static void setup() {

		// ---| Initialize Inventory

		// ---| Technifibre
		inventory.addTennisRacket("TFight 315 RS", Brand.TECHNIFIBRE, "T01", 229.00, 632.26f, 68.58f, (short) 332, true,
				(byte) 7, "Graphite/Dynacore HD");

		// ---| Babolat
		inventory.addTennisRacket("Pure Strike 18x20 3rd Gen", Brand.BABOLAT, "B01", 219.00, 632.26f, 68.58f, (short) 323,
				true, (byte) 4, "Graphite");
		inventory.addTennisRacket("Pure Drive Plus 2021", Brand.BABOLAT, "B02", 229.00, 645.16f, 69.85f, (short) 318, true,
				(byte) 6, "Graphite");

		// ---| Wilson
		inventory.addTennisRacket("Pro Staff RF97 v13", Brand.WILSON, "W01", 249.00, 625.81f, 68.58f, (short) 357, true,
				(byte) 9, "Graphite/Aramid");
		inventory.addTennisRacket("Triad Five", Brand.WILSON, "W02", 169.00, 664.51f, 69.22f, (short) 283, false, (byte) 6,
				"Graphite");
		inventory.addTennisRacket("Hyper Hammer 5.3 Stretch OS", Brand.WILSON, "W03", 99.00, 709.68f, 69.85f, (short) 255,
				false, (byte) 8, "Graphite/Hyper Carbon");

		// ---| Head
		inventory.addTennisRacket("Graphene 360+ Instinct PWR", Brand.HEAD, "H01", 189.00, 741.93f, 70.36f, (short) 244,
				false, (byte) 9, "Graphite/Graphene 360+");
	}
	
	// #region UnitTests

	@DisplayName("Find Pro Staff RF97 – Found")
	@Test
	public final void SearchForProStaff_When_MatchingModelCapitalization_Then_RacketFound() {
		TennisRacket racketCriteria = new TennisRacket("Pro Staff RF97 v13", Brand.WILSON, null, 0, 0f, 0f, (short) 0, true,
				(byte) 0, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 1);
		assertEquals(searchResult.get(0).getModel(), "Pro Staff RF97 v13");
	}

	@DisplayName("Find Pro Staff RF97 – Found")
	@Test
	public final void SearchForProStaff_When_DifferingModelCapitalization_Then_RacketFound() {
		TennisRacket racketCriteria = new TennisRacket("PRO StaFF Rf97 V13", Brand.WILSON, null, 0, 0f, 0f, (short) 0, true,
				(byte) 0, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 1);
		assertEquals(searchResult.get(0).getModel(), "Pro Staff RF97 v13");
	}

	@DisplayName("Find Pro Staff RF97 – Not Found")
	@Test
	public final void SearchForProStaff_When_WrongBrand_Then_RacketNotFound() {
		TennisRacket racketCriteria = new TennisRacket("PRO STAFF RF97 V13", Brand.BABOLAT, null, 0, 0f, 0f, (short) 0, true,
				(byte) 0, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 0);
	}

	@DisplayName("Find All Wilson Rackets")
	@Test
	public final void SearchForBrand_When_BrandWilson_Then_ReturnAllWilsonRackets() {
		TennisRacket racketCriteria = new TennisRacket(null, Brand.WILSON, null, 0, 0f, 0f, (short) 0, null,
				(byte) 0, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 3);
		
		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext(); ) {
			TennisRacket racket = i.next();
			assertEquals(racket.getBrand(), Brand.WILSON);
		}
	}

	@DisplayName("Find All Rackets Containing 'Pure'")
	@Test
	public final void SearchForModel_When_ModelContainsPure_Then_ReturnPureBabolatRackets() {
		TennisRacket racketCriteria = new TennisRacket("pure", null, null, 0, 0f, 0f, (short) 0, null,
				(byte) 0, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 2);
		
		for (Iterator<TennisRacket> i = searchResult.iterator(); i.hasNext(); ) {
			TennisRacket racket = i.next();
			assertEquals(racket.getBrand(), Brand.BABOLAT);
		}
	}

	@DisplayName("Find All Head Light Rackets")
	@Test
	public final void SearchForBalance_When_CriteriaHeadLight_Then_ReturnAllHeadLightRackets() {
		TennisRacket racketCriteria = new TennisRacket(null, null, null, 0, 0f, 0f, (short) 0, true,
				(byte) 0, null);

		List<TennisRacket> searchResult = inventory.searchFor(racketCriteria);

		assertEquals(searchResult.size(), 4);
	}
	
	// #endRegion
}
