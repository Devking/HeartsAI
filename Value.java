// note that enums naturally implement Comparable! They are comparable in the order given below
public enum Value { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }

/*
How to compare enums:

Value one = Value.ONE;
Value two = Value.TWO;

// compareTo will be positive if the caller is larger than the parameter
// otherwise it will be negative
if (one.compareTo(two) < 0) {
	System.out.println("this will be printed because one is less than two");
}

*/