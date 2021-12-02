package advent2021.day01;

import java.io.IOException;
import java.util.ArrayList;

import advent2021.FileLocation;
import advent2021.FileReader;

public class Day01 {

	FileLocation file = new FileLocation();
	String filePath = file.getFileLocation( "day01" );

	public int countDepthIncrease( ArrayList<Integer> depthsArray ) {
		int count = 0;
		for ( int i = 1; i < depthsArray.size(); i++ ) {
			if ( depthsArray.get( i ) > depthsArray.get( i - 1 ) ) {
				count++;
			}
		}
		return count;
	}

	public int countDepthIncreaseInThrees(ArrayList<Integer> depthsArray) {
		int count = 0;
		for ( int i = 3; i < depthsArray.size(); i++ ) {
			if ( ( depthsArray.get( i ) + depthsArray.get( i - 1 ) + depthsArray.get(
					i - 2 ) ) > ( depthsArray.get( i - 1 ) + depthsArray.get(
					i - 2 ) + depthsArray.get( i - 3 ) ) ) {
				count++;
			}

		}
		return count;
	}

	public void day01Solutions() {
		final FileReader reader = new FileReader();
		file.getFileLocation( "day01" );
		ArrayList<Integer> depths;
		try {
			depths = reader.readFileAsIntegerArrayList( filePath );

			int result1 = countDepthIncrease( depths );
			System.out.println( "Day 01 part 1: " + result1 ); // 1564

			int result2 = countDepthIncreaseInThrees( depths );
			System.out.println( "Day 01 part 2: " + result2 ); // 1611
		} catch ( IOException e ) {
			e.printStackTrace();
		}

	}

}


