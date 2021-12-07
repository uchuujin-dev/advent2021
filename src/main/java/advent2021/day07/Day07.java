package advent2021.day07;

import static java.lang.Math.abs;
import static java.util.Collections.max;
import static java.util.Collections.min;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import advent2021.FileReader;

public class Day07 {
	String fileName = "day07";

	public int findOptimalPosition( ArrayList<Integer> positions ) {
		int min = min( positions );
		int max = max( positions );

		Map<Integer, Integer> distances = new HashMap<>();

		for ( int pos = min; pos <= max; pos++ ) {
			int distance = 0;
			for ( Integer position : positions ) {
				distance += abs( position - pos );
			}
			distances.put( pos, distance );
		}
		return min( distances.values() );
	}

	public int findOptimalPositionPart2( ArrayList<Integer> positions ) {
		int min = min( positions );
		int max = max( positions );

		Map<Integer, Integer> distances = new HashMap<>();

		for ( int pos = min; pos <= max; pos++ ) {
			int distance = 0;
			for ( Integer position : positions ) {
				distance += findTriangleNum( abs( position - pos ) );
			}
			distances.put( pos, distance );
		}
		return min( distances.values() );
	}

	public int findTriangleNum( int num ) {
		return ( num * ( num + 1 ) ) / 2;
	}

	public void day07Solutions() {
		final FileReader reader = new FileReader();
		try {
			ArrayList<Integer> positions = reader.readInIntegerList( fileName );
			int result1 = findOptimalPosition( positions );

			int result2 = findOptimalPositionPart2( positions );

			System.out.println( "Day 07 part 1: " + result1 ); // 343605
			System.out.println( "Day 07 part 1: " + result2 ); // 96744904

		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
