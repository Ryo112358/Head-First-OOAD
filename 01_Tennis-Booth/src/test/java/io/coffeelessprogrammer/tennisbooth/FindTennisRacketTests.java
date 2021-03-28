package io.coffeelessprogrammer.tennisbooth;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class FindTennisRacketTests {

	private static final Inventory inventory = new Inventory();

	@BeforeAll
	private static void setup() {

		// ---| Initialize Inventory

		// ---| Technifibre
		inventory.addTennisRacket("TFight 315 RS", "Tecnifibre", "T01", 229.00, 632.26f, 68.58f, (short) 332, true,
				(byte) 7, "Graphite/Dynacore HD");

		// ---| Babolat
		inventory.addTennisRacket("Pure Strike 18x20 3rd Gen", "Babolat", "B01", 219.00, 632.26f, 68.58f, (short) 323,
				true, (byte) 4, "Graphite");
		inventory.addTennisRacket("Pure Drive Plus 2021", "Babolat", "B02", 229.00, 645.16f, 69.85f, (short) 318, true,
				(byte) 6, "Graphite");

		// ---| Wilson
		inventory.addTennisRacket("Pro Staff RF97 v13", "Wilson", "W01", 249.00, 625.81f, 68.58f, (short) 357, true,
				(byte) 9, "Graphite/Aramid");
		inventory.addTennisRacket("Triad Five", "Wilson", "W02", 169.00, 664.51f, 69.22f, (short) 283, false, (byte) 6,
				"Graphite");
		inventory.addTennisRacket("Hyper Hammer 5.3 Stretch OS", "Wilson", "W03", 99.00, 709.68f, 69.85f, (short) 255,
				false, (byte) 8, "Graphite/Hyper Carbon");

		// ---| Head
		inventory.addTennisRacket("Graphene 360+ Instinct PWR", "Head", "H01", 189.00, 741.93f, 70.36f, (short) 244,
				false, (byte) 9, "Graphite/Graphene 360+");
	}
	
	// #region UnitTests

	@DisplayName("Find Pro Staff RF97 – Found")
	@Test
	public final void SearchForProStaff_When_MatchingCapitalization_Then_RacketFound() {
		TennisRacket racketCriteria = new TennisRacket("Pro Staff RF97 v13", "Wilson", null, 0, 0f, 0f, (short) 0, true,
				(byte) 0, null);

		TennisRacket searchResult = inventory.searchFor(racketCriteria);

		assertNotNull(searchResult, "Pro Staff RF97 not in stock (but it should be).");
	}

	@DisplayName("Find Pro Staff RF97 – Not Found")
	@Test
	public final void SearchForProStaff_When_DifferingModelCapitalization_Then_RacketNotFound() {
		TennisRacket racketCriteria = new TennisRacket("PRO STAFF RF97 V13", "Wilson", null, 0, 0f, 0f, (short) 0, true,
				(byte) 0, null);

		TennisRacket searchResult = inventory.searchFor(racketCriteria);

		assertNull(searchResult, "Pro Staff RF97 was magically found?!");
	}

	@DisplayName("Find Pro Staff RF97 – Not Found")
	@Test
	public final void SearchForProStaff_When_DifferingBrandCapitalization_Then_RacketNotFound() {
		TennisRacket racketCriteria = new TennisRacket("Pro Staff RF97 v13", "wilson", null, 0, 0f, 0f, (short) 0, true,
				(byte) 0, null);

		TennisRacket searchResult = inventory.searchFor(racketCriteria);

		assertNull(searchResult, "Pro Staff RF97 was magically found?!");
	}
	
	// #endRegion
}
