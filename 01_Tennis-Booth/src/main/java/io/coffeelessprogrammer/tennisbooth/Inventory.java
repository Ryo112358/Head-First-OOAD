package io.coffeelessprogrammer.tennisbooth;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import io.coffeelessprogrammer.tennisbooth.model.Brand;
import io.coffeelessprogrammer.tennisbooth.model.TennisRacket;
import io.coffeelessprogrammer.tennisbooth.model.TennisRacketSpec;

public class Inventory {

	private List<TennisRacket> tennisRackets;

	public Inventory() {
		this.tennisRackets = new ArrayList<>();
	}

	public void addTennisRacket(String serialNum, double price, String model, Brand brand, float headSize, float length,
			short strungWeight, Boolean isHeadLight, byte balancePts, byte mains, byte crosses,
			String frameComposition) {

		TennisRacket racket = new TennisRacket(serialNum, price, new TennisRacketSpec(model, brand, headSize, length,
				strungWeight, isHeadLight, balancePts, mains, crosses, frameComposition));

		this.tennisRackets.add(racket);
	}

	public TennisRacket getTennisRacket(String serialNumber) {
		for (Iterator<TennisRacket> i = tennisRackets.iterator(); i.hasNext();) {

			TennisRacket racket = i.next();

			if (racket.getSerialNumber().equals(serialNumber))
				return racket;
		}
		return null;
	}

	public List<TennisRacket> searchFor(TennisRacketSpec racketSearchCriteria) {
		List<TennisRacket> matchingRackets = new ArrayList<>();

		for (Iterator<TennisRacket> i = tennisRackets.iterator(); i.hasNext();) {

			TennisRacket stockRacket = i.next();
			TennisRacketSpec stockRacketSpec = stockRacket.getSpec();

			// ---| Compare inventory rackets with search criteria

			if (stockRacketSpec.matches(racketSearchCriteria))
				matchingRackets.add(stockRacket);
		}

		return matchingRackets;
	}
}
