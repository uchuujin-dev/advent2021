package advent2021.day01;

import java.io.IOException;
import java.util.ArrayList;

import advent2021.FileReader;

public class Main {
	// Day 01

	// sample array
	// public static final ArrayList<Integer> array = new ArrayList<>( List.of(199, 200 ,208 ,210 ,200 ,207 ,240 ,269 ,260 ,263));
	public static final Day01 day01 = new Day01();
	public static final FileReader reader = new FileReader();
	private static ArrayList<Integer> depths;

	static {
		try {
			depths = reader.readFile( "src/main/resources/input_01"  );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	public static void main( String[] args ) {
		// Day 01
		int result1 = day01.countDepthIncrease( depths );
		System.out.println( result1 ); // 1564

		int result2 = day01.countDepthIncreaseInThrees( depths );
		System.out.println(result2); // 1611
	}
}
