package io.coffeelessprogrammer.tennisbooth.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


final class RangeValidatorTests {
	
	private static final byte[] byteRange = {1, 8};
	private static final float[] floatRange = {34.57f, 82.12f};
	
	@BeforeAll
	private static void setup() {
		System.out.println("--- Execution Order ---\n");
	}

	// #region WholeNumberRangeTests
	
	@Test
	@Order(1)
	final void ByteRange1To8_When_Value0_Then_ReturnFalse() {
		boolean result = RangeValidator.isValueInRange((byte) 0, byteRange);
		assertEquals(result, false);
		
		System.out.println("Natural Range - Test 1");
	}
	
	@Test
	@Order(1)
	final void ByteRange1To8_When_Value1_Then_ReturnTrue() {
		boolean result = RangeValidator.isValueInRange((byte) 1, byteRange);
		assertEquals(result, true);
		
		System.out.println("Natural Range - Test 2");
	}
	
	@Test
	@Order(1)
	final void ByteRange1To8_When_Value8_Then_ReturnTrue() {
		boolean result = RangeValidator.isValueInRange((byte) 8, byteRange);
		assertEquals(result, true);
		
		System.out.println("Natural Range - Test 3");
	}
	
	@Test
	@Order(1)
	final void ByteRange1To8_When_Value9_Then_ReturnFalse() {
		boolean result = RangeValidator.isValueInRange((byte) 9, byteRange);
		assertEquals(result, false);
		
		System.out.println("Natural Range - Test 4");
	}
	
	@Test
	@Order(1)
	final void When_ByteRangeNull_Then_ReturnTrue() {
		boolean result = RangeValidator.isValueInRange((byte) 4,(byte[]) null);
		assertEquals(result, true);
		
		System.out.println("Natural Range - Test 5");
	}

	// #endRegion

	// #region DecimalRangeTests
	
	@Test
	@Order(2)
	final void FloatRange_When_ValueBelowLowerBound_Then_ReturnFalse() {
		boolean result = RangeValidator.isValueInRange(34.569f, floatRange);
		assertEquals(result, false);
		
		System.out.println("Decimal Range - Test 1");
	}
	
	@Test
	@Order(2)
	final void FloatRange_When_ValuetLowerBound_Then_ReturnTrue() {
		boolean result = RangeValidator.isValueInRange(34.570f, floatRange);
		assertEquals(result, true);
		
		System.out.println("Decimal Range - Test 2");
	}
	
	@Test
	@Order(2)
	final void FloatRange_When_ValueWithinRange_Then_ReturnTrue() {
		boolean result = RangeValidator.isValueInRange(42.24f, floatRange);
		assertEquals(result, true);
		
		System.out.println("Decimal Range - Test 3");
	}
	
	@Test
	@Order(2)
	final void FloatRange_When_ValueAtUpperBound_Then_ReturnTrue() {
		boolean result = RangeValidator.isValueInRange(82.120f, floatRange);
		assertEquals(result, true);
		
		System.out.println("Decimal Range - Test 4");
	}
	
	@Test
	@Order(2)
	final void FloatRange_When_ValueAboveUpperBound_Then_ReturnFalse() {
		boolean result = RangeValidator.isValueInRange(82.121f, floatRange);
		assertEquals(result, false);
		
		System.out.println("Decimal Range - Test 5");
	}
	
	@Test
	@Order(2)
	final void When_FloatRangeNull_Then_ReturnTrue() {
		boolean result = RangeValidator.isValueInRange(42.24f, (float[]) null);
		assertEquals(result, true);
		
		System.out.println("Decimal Range - Test 6");
	}

	// #endRegion

}
