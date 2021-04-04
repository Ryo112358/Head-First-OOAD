package io.coffeelessprogrammer.tennisbooth.utility;

public class RangeValidator {
	
	public static final boolean isValueInRange(long value, long[] range) {
		if (range != null) {
			if (range[0] != 0 && value < range[0])
				return false;
			if (range[1] != 0 && value > range[1])
				return false;
		}
		
		return true;
	}
	
	public static final boolean isValueInRange(int value, int[] range) {
		if (range != null) {
			if (range[0] != 0 && value < range[0])
				return false;
			if (range[1] != 0 && value > range[1])
				return false;
		}
		
		return true;
	}
	
	public static final boolean isValueInRange(short value, short[] range) {
		if (range != null) {
			if (range[0] != 0 && value < range[0])
				return false;
			if (range[1] != 0 && value > range[1])
				return false;
		}
		
		return true;
	}
	
	public static final boolean isValueInRange(byte value, byte[] range) {
		if (range != null) {
			if (range[0] != 0 && value < range[0])
				return false;
			if (range[1] != 0 && value > range[1])
				return false;
		}
		
		return true;
	}
	
	public static final boolean isValueInRange(double value, double[] range) {
		if (range != null) {
			if (range[0] != 0 && value < range[0])
				return false;
			if (range[1] != 0 && value > range[1])
				return false;
		}
		
		return true;
	}
	
	public static final boolean isValueInRange(float value, float[] range) {
		if (range != null) {
			if (range[0] != 0 && value < range[0])
				return false;
			if (range[1] != 0 && value > range[1])
				return false;
		}
		
		return true;
	}

}
