package io.coffeelessprogrammer.tennisbooth;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import io.coffeelessprogrammer.tennisbooth.model.Brand;
import io.coffeelessprogrammer.tennisbooth.model.TennisRacket;
import io.coffeelessprogrammer.tennisbooth.model.TennisRacketFilterCriteria;
import io.coffeelessprogrammer.tennisbooth.model.TennisRacketSpec;

public class Inventory {

	private List<TennisRacket> tennisRackets;

	public Inventory() {
		this.tennisRackets = new ArrayList<>();
	}

	public void addTennisRacket(String serialNum, double price, String model, Brand brand, byte mains, byte crosses,
			float headSize, float length, short strungWeight, Boolean isHeadLight, byte balancePts,
			String frameComposition) {

		TennisRacket racket = new TennisRacket(serialNum, price, new TennisRacketSpec(model, brand, mains, crosses,
				headSize, length, strungWeight, isHeadLight, balancePts, frameComposition));

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

	public List<TennisRacket> searchFor(TennisRacketFilterCriteria searchCriteria) {
		List<TennisRacket> matchingRackets = new ArrayList<>();

		for (Iterator<TennisRacket> i = tennisRackets.iterator(); i.hasNext();) {

			TennisRacket stockRacket = i.next();

			// ---| Compare inventory rackets with search criteria

			if (stockRacket.matches(searchCriteria))
				matchingRackets.add(stockRacket);
		}

		return matchingRackets;
	}
}
