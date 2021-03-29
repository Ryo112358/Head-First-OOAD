package io.coffeelessprogrammer.tennisbooth;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import io.coffeelessprogrammer.tennisbooth.model.Brand;
import io.coffeelessprogrammer.tennisbooth.model.TennisRacket;

public class Inventory {

	private List<TennisRacket> tennisRackets;

	public Inventory() {
		this.tennisRackets = new ArrayList<>();
	}

	public void addTennisRacket(String model, Brand brand, String serialNum, double price, float headSize, float length,
			short strungWeight, Boolean isHeadLight, byte balancePts, String frameComposition) {

		TennisRacket racket = new TennisRacket(model, brand, serialNum, price, headSize, length, strungWeight,
				isHeadLight, balancePts, frameComposition);

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

	public List<TennisRacket> searchFor(TennisRacket racketSearchCriteria) {
		List<TennisRacket> matchingRackets = new ArrayList<>();
		
		for (Iterator<TennisRacket> i = tennisRackets.iterator(); i.hasNext();) {
			TennisRacket stockRacket = i.next();

			// ---| Compare inventory rackets with search criteria
			// ------| Ignore serial number

			Brand searchBrand = racketSearchCriteria.getBrand();
			if (searchBrand != null && searchBrand != stockRacket.getBrand())
				continue;

			String searchModel = racketSearchCriteria.getModel();
			if (searchModel != null && !searchModel.isEmpty()
					&& !stockRacket.getModel().toLowerCase().contains(searchModel.toLowerCase()))
				continue;

			Boolean searchBalance = racketSearchCriteria.getIsHeadLight();
			if (searchBalance != null && searchBalance != stockRacket.getIsHeadLight())
				continue;

			matchingRackets.add(stockRacket);
		}
		
		return matchingRackets;
	}
}
