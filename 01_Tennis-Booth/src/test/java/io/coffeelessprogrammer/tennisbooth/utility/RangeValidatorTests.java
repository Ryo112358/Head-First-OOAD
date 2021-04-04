package io.coffeelessprogrammer.tennisbooth.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class RangeValidatorTests {
	
	private static final byte[] byteRange = {1, 8};
	private static final float[] floatRange = {34.57f, 82.12f};

	// #region WholeNumberRangeTests
	
	@Test
	final void ByteRange1To8_When_Value0_Then_ReturnFalse() {
		boolean result = RangeValidator.isValueInRange((byte) 0, byteRange);
		assertEquals(result, false);
	}
	
	@Test
	final void ByteRange1To8_When_Value1_Then_ReturnTrue() {
		boolean result = RangeValidator.isValueInRange((byte) 1, byteRange);
		assertEquals(result, true);
	}
	
	@Test
	final void ByteRange1To8_When_Value8_Then_ReturnTrue() {
		boolean result = RangeValidator.isValueInRange((byte) 8, byteRange);
		assertEquals(result, true);
	}
	
	@Test
	final void ByteRange1To8_When_Value9_Then_ReturnFalse() {
		boolean result = RangeValidator.isValueInRange((byte) 9, byteRange);
		assertEquals(result, false);
	}
	
	@Test
	final void When_ByteRangeNull_Then_ReturnTrue() {
		boolean result = RangeValidator.isValueInRange((byte) 4,(byte[]) null);
		assertEquals(result, true);
	}

	// #endRegion

	// #region DecimalRangeTests
	
	@Test
	final void FloatRange_When_ValueBelowLowerBound_Then_ReturnFalse() {
		boolean result = RangeValidator.isValueInRange(34.569f, floatRange);
		assertEquals(result, false);
	}
	
	@Test
	final void FloatRange_When_ValuetLowerBound_Then_ReturnTrue() {
		boolean result = RangeValidator.isValueInRange(34.570f, floatRange);
		assertEquals(result, true);
	}
	
	@Test
	final void FloatRange_When_ValueWithinRange_Then_ReturnTrue() {
		boolean result = RangeValidator.isValueInRange(42.24f, floatRange);
		assertEquals(result, true);
	}
	
	@Test
	final void FloatRange_When_ValueAtUpperBound_Then_ReturnTrue() {
		boolean result = RangeValidator.isValueInRange(82.120f, floatRange);
		assertEquals(result, true);
	}
	
	@Test
	final void FloatRange_When_ValueAboveUpperBound_Then_ReturnFalse() {
		boolean result = RangeValidator.isValueInRange(82.121f, floatRange);
		assertEquals(result, false);
	}
	
	@Test
	final void When_FloatRangeNull_Then_ReturnTrue() {
		boolean result = RangeValidator.isValueInRange(42.24f, (float[]) null);
		assertEquals(result, true);
	}

	// #endRegion

}
