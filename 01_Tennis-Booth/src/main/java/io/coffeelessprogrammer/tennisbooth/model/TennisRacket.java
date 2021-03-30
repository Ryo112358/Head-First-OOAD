package io.coffeelessprogrammer.tennisbooth.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;


@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class TennisRacket {
	
	@Getter private String serialNumber;
	@Getter @Setter private double priceUSD;

	@Getter private TennisRacketSpec spec;
}
