package io.coffeelessprogrammer.tennisbooth;

import io.coffeelessprogrammer.tennisbooth.model.Brand;
import io.coffeelessprogrammer.tennisbooth.model.TennisRacket;

public class TennisBoothSimulator {

	public static void main(String[] args) {
		
		System.out.println("Compare Enum Result: " + compareEnums());

		
		System.out.println("Match Racket Model: " + matchRacketModel("pro staff"));
		System.out.println("Match Racket Model: " + matchRacketModel("Rf97"));
		System.out.println("Match Racket Model: " + matchRacketModel("aramid"));
		System.out.println("Match Racket Model: " + matchRacketModel("Pro Staff RF97 v13"));

	}
	
	public static boolean compareEnums() {
		return Brand.BABOLAT == Brand.WILSON;
	}
	
	public static boolean matchRacketModel(String searchModel) {
		TennisRacket sampleRacket = new TennisRacket("Pro Staff RF97 v13", Brand.WILSON, "W01", 249.00, 625.81f, 68.58f, (short) 357, true,
				(byte) 9, "Graphite/Aramid");
		
		return sampleRacket.getModel().toLowerCase().contains(searchModel.toLowerCase());
	}

}
