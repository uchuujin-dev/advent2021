package advent2021.day03;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import advent2021.FileLocation;
import advent2021.FileReader;

public class Day03 {

	FileLocation file = new FileLocation();
	File filePath = file.readFileFromClasspath( "day03" );

	ArrayList<Integer> list = new ArrayList<>( List.of( 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0 ) );

	public Integer mostCommonInt( ArrayList<Integer> list ) {
		Map<Integer, Integer> map = new HashMap<>();

		for ( Integer integer : list ) {
			Integer val = map.get( integer );
			map.put( integer, val == null ? 1 : val + 1 );
		}

		Map.Entry<Integer, Integer> max = null;

		for ( Map.Entry<Integer, Integer> e : map.entrySet() ) {
			if ( max == null || e.getValue() > max.getValue() ) {
				max = e;
			}
		}

		return max.getKey();
	}

	public Integer leastCommonInt( ArrayList<Integer> list ) {
		Map<Integer, Integer> map = new HashMap<>();

		for ( Integer integer : list ) {
			Integer val = map.get( integer );
			map.put( integer, val == null ? 1 : val + 1 );
		}

		Map.Entry<Integer, Integer> min = null;

		for ( Map.Entry<Integer, Integer> e : map.entrySet() ) {
			if ( min == null || e.getValue() < min.getValue() ) {
				min = e;
			}
		}

		return min.getKey();
	}

	public int mostOneOrZero( ArrayList<Integer> list ) {
		double sum = 0;

		for ( Integer integer : list ) {
			sum += integer;
		}

		if ( sum >= ( list.size() / 2.0 ) ) {
			return 1;
		} else {
			return 0;
		}
	}

	public int leastOneOrZero( ArrayList<Integer> list ) {
		double sum = 0;

		for ( Integer integer : list ) {
			sum += integer;
		}

		if ( sum >= ( list.size() / 2.0 ) ) {
			return 0;
		} else {
			return 1;
		}
	}

	public int findGamma( ArrayList<ArrayList<Integer>> input ) {
		String gamma = "";

		for ( ArrayList<Integer> integers : input ) {
			gamma = gamma.concat( mostCommonInt( integers ).toString() );
		}

		return Integer.parseInt( gamma, 2 );
	}

	public int findEpsilon( ArrayList<ArrayList<Integer>> input ) {
		String epsilon = "";

		for ( ArrayList<Integer> integers : input ) {
			epsilon = epsilon.concat( leastCommonInt( integers ).toString() );
		}

		return Integer.parseInt( epsilon, 2 );

	}

	public int calcPowerConsumption( int gamma, int epsilon ) {
		return gamma * epsilon;
	}

	public ArrayList<ArrayList<Integer>> updateArrayList( ArrayList<ArrayList<Integer>> newArray ) {
		ArrayList<ArrayList<Integer>> updatedArray = new ArrayList<>();
		for ( ArrayList<Integer> integers : newArray ) {
			updatedArray.add( integers );
		}
		return updatedArray;
	}

	public int findOxygenGeneratorRating( ArrayList<ArrayList<Integer>> input ) {
		ArrayList<ArrayList<Integer>> candidates = input;
		ArrayList<ArrayList<Integer>> candidates2 = new ArrayList<>();
		ArrayList<Integer> tempCol = new ArrayList<>();
		int mostCommon;

		while ( candidates.size() > 1 ) {
			for ( int col = 0; col < candidates.get( 0 ).size(); col++ ) {

				for ( int row = 0; row < candidates.size(); row++ ) {
					tempCol.add( candidates.get( row ).get( col ) );
				}

				mostCommon = mostOneOrZero( tempCol );
				tempCol.clear();

				for ( int row = 0; row < candidates.size(); row++ ) {
					if ( candidates.get( row ).get( col ) == mostCommon ) {
						candidates2.add( candidates.get( row ) );
					}
				}

				if ( candidates.size() != 1 ) {
					candidates.clear();
					for ( ArrayList<Integer> x : candidates2 ) {
						candidates.add( x );
					}
					candidates2.clear();
				}
			}
		}

		String bit = "";
		for ( int i = 0; i < candidates.get( 0 ).size(); i++ ) {
			bit = bit.concat( candidates.get( 0 ).get( i ).toString() );
		}
		return Integer.parseInt( bit, 2 );
	}

	public int findCo2ScrubberRating( ArrayList<ArrayList<Integer>> input ) {
		ArrayList<ArrayList<Integer>> candidates = input;
		ArrayList<ArrayList<Integer>> candidates2 = new ArrayList<>();
		ArrayList<Integer> tempCol = new ArrayList<>();
		int leastCommon;

		while ( candidates.size() > 1 ) {
			for ( int col = 0; col < candidates.get( 0 ).size(); col++ ) {

				for ( int row = 0; row < candidates.size(); row++ ) {
					tempCol.add( candidates.get( row ).get( col ) );
				}

				leastCommon = leastOneOrZero( tempCol );
				tempCol.clear();

				for ( int row = 0; row < candidates.size(); row++ ) {
					if ( candidates.get( row ).get( col ) == leastCommon ) {
						candidates2.add( candidates.get( row ) );
					}
				}

				if ( candidates.size() != 1 ) {
					candidates.clear();
					for ( ArrayList<Integer> x : candidates2 ) {
						candidates.add( x );
					}
					candidates2.clear();
				}

			}
		}
		String bit = "";
		for ( int i = 0; i < candidates.get( 0 ).size(); i++ ) {
			bit = bit.concat( candidates.get( 0 ).get( i ).toString() );
		}
		return Integer.parseInt( bit, 2 );
	}

	public int lifeSupportRating( int x, int y ) {
		return x * y;
	}

	public void day03Solutions() {
		final FileReader reader = new FileReader();
		ArrayList<ArrayList<Integer>> input;

		try {
			input = reader.readFileInAsVerticalArrays( filePath );
			int result1 = calcPowerConsumption( findGamma( input ), findEpsilon( input ) );

			System.out.println( "Day 03 part 1: " + result1 ); // 2003336

			int oxygenGeneratorRating = findOxygenGeneratorRating(
					reader.readFileInAsArrayListOfArrayLists( filePath ) );
			int co2ScrubberRating = findCo2ScrubberRating(
					reader.readFileInAsArrayListOfArrayLists( filePath ) );
			int result2 = lifeSupportRating( oxygenGeneratorRating, co2ScrubberRating );
			System.out.println( "Day 03 part 2: " + result2 ); // 1877139

		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
