package io.coffeelessprogrammer.tennisbooth;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {

	private List<TennisRacket> tennisRackets;

	public Inventory() {
		this.tennisRackets = new ArrayList<>();
	}

	public void addTennisRacket(String model, String brand, String serialNum, double price, float headSize,
			float length, short strungWeight, boolean isHeadLight, byte balancePts, String frameComposition) {

		TennisRacket racket = new TennisRacket(model, brand, serialNum, price, headSize, length, strungWeight,
				isHeadLight, balancePts, frameComposition);

		this.tennisRackets.add(racket);
	}

	public TennisRacket getTennisRacket(String serialNumber) {
		for (Iterator<TennisRacket> i = tennisRackets.iterator(); i.hasNext();) {

			TennisRacket racket = i.next();

			if (racket.getSerialNumber().equals(serialNumber)) {
				return racket;
			}
		}
		return null;
	}

	public TennisRacket searchFor(TennisRacket racketSearchCriteria) {
		for (Iterator<TennisRacket> i = tennisRackets.iterator(); i.hasNext();) {
			TennisRacket stockRacket = i.next();

			// ---| Compare inventory rackets with search criteria
			// ------| Ignore serial number and price

			String searchBrand = racketSearchCriteria.getBrand();
			if ((searchBrand != null) && (!searchBrand.isEmpty()) && (!searchBrand.equals(stockRacket.getBrand())))
				continue;
//			System.out.println("---| Passed brand check:" + searchBrand + " = " + stockRacket.getBrand());

			String searchModel = racketSearchCriteria.getModel();
			if ((searchModel != null) && (!searchModel.isEmpty()) && (!searchModel.equals(stockRacket.getModel())))
				continue;
//			System.out.println("---| Passed model check:" + searchModel + " = " + stockRacket.getModel());

			boolean searchBalance = racketSearchCriteria.isHeadLight();
			if (searchBalance != stockRacket.isHeadLight())
				continue;
//			System.out.println("---| Passed balance check");

			return stockRacket;
		}
		return null;
	}
}
