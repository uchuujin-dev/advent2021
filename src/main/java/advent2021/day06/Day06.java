package advent2021.day06;

import static java.util.Collections.frequency;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import advent2021.FileReader;

public class Day06 {
	String fileName = "day06";

	public long fishCount( ArrayList<Integer> fishTimers, int numOfDays ) {

		int numOfFishDay0 = frequency( fishTimers, 0 );
		int numOfFishDay1 = frequency( fishTimers, 1 );
		int numOfFishDay2 = frequency( fishTimers, 2 );
		int numOfFishDay3 = frequency( fishTimers, 3 );
		int numOfFishDay4 = frequency( fishTimers, 4 );
		int numOfFishDay5 = frequency( fishTimers, 5 );
		int numOfFishDay6 = frequency( fishTimers, 6 );
		int numOfFishDay7 = frequency( fishTimers, 7 );
		int numOfFishDay8 = frequency( fishTimers, 8 );

		long[] states = { numOfFishDay0,
						  numOfFishDay1,
						  numOfFishDay2,
						  numOfFishDay3,
						  numOfFishDay4,
						  numOfFishDay5,
						  numOfFishDay6,
						  numOfFishDay7,
						  numOfFishDay8 };

		for ( int days = 1; days <= numOfDays; days++ ) {

			long state1 = states[1];
			states[1] = states[2];
			states[2] = states[3];
			states[3] = states[4];
			states[4] = states[5];
			states[5] = states[6];
			states[6] = states[7] + states[0];
			states[7] = states[8];
			states[8] = states[0];
			states[0] = state1;
		}
		System.out.println( Arrays.stream( states ).sum() );
		return Arrays.stream( states ).sum();
	}

	public void day06Solutions() {
		final FileReader reader = new FileReader();
		try {
			ArrayList<Integer> timers = reader.readInIntegerList( fileName );

			long result1 = fishCount( timers, 80 );

			long result2 = fishCount( timers, 256 );

			System.out.println( "Day 06 part 1: " + result1 ); // 374994
			System.out.println( "Day 06 part 2: " + result2 ); // 1686252324092

		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
