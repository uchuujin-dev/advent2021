package advent2021.day09;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import com.google.common.collect.Table;

import advent2021.FileLocation;
import advent2021.FileReader;

public class Day09 {
	FileLocation file = new FileLocation();
	File filePath = file.readFileFromClasspath( "day09" );
	Integer[] largestThreeBasins = { 0, 0, 0, 0 };

	public int findLowPoints( Table<Integer, Integer, Integer> input ) {
		int risk = 0;
		for ( int row = 0; row < input.rowMap().size(); row++ ) {
			for ( int col = 0; col < input.columnMap().size(); col++ ) {
				int num = input.get( row, col );
				int count = 0;

				if ( input.get( row - 1, col ) != null ) { // check above exists
					if ( input.get( row - 1,
							col ) <= num ) { // check if the above is smaller than num, if so then break out of loop
						count++;
					}
				}
				if ( input.get( row, col - 1 ) != null ) { // check left exists
					if ( input.get( row,
							col - 1 ) <= num ) { // check if the left is smaller than num, if so then break out of loop
						count++;
					}
				}
				if ( input.get( row, col + 1 ) != null ) { // check right exists
					if ( input.get( row,
							col + 1 ) <= num ) { // check if the right is smaller than num, if so then break out of loop
						count++;
					}
				}
				if ( input.get( row + 1, col ) != null ) { // check down exists
					if ( input.get( row + 1,
							col ) <= num ) { // check if the down is smaller than num, if so then break out of loop
						count++;
					}
				}
				if ( count == 0 ) {
					risk += num;
					risk++;
				}
			}
		}
		return risk;
	}

	public void findBasins( Table<Integer, Integer, Integer> input ) {
		int mark = 100;

		for ( int row = 0; row < input.rowMap().size(); row++ ) {
			for ( int col = 0; col < input.columnMap().size(); col++ ) {
				int num = input.get( row, col );
				int count = 0;

				if ( input.get( row - 1, col ) != null ) { // check above exists
					if ( input.get( row - 1,
							col ) <= num ) { // check if the above is smaller than num, if so then break out of loop
						count++;
					}
				}
				if ( input.get( row, col - 1 ) != null ) { // check left exists
					if ( input.get( row,
							col - 1 ) <= num ) { // check if the left is smaller than num, if so then break out of loop
						count++;
					}
				}
				if ( input.get( row, col + 1 ) != null ) { // check right exists
					if ( input.get( row,
							col + 1 ) <= num ) { // check if the right is smaller than num, if so then break out of loop
						count++;
					}
				}
				if ( input.get( row + 1, col ) != null ) { // check down exists
					if ( input.get( row + 1,
							col ) <= num ) { // check if the down is smaller than num, if so then break out of loop
						count++;
					}
				}
				if ( count == 0 ) { // num is the lowest point
					dfs( input, num, row, col, input.rowMap().size(), input.columnMap().size(),
							mark );
					mark++;
					Arrays.sort( largestThreeBasins, Collections.reverseOrder() );
					System.out.println( " " );
					System.out.println( mark );
					System.out.println( "num : " + num );
					System.out.println( "row, col : " + row + ", " + col );
					System.out.println( "above : " + input.get( row - 1, col ) );
					System.out.println( "below : " + input.get( row, col - 1 ) );
					System.out.println( "left : " + input.get( row, col + 1 ) );
					System.out.println( "right : " + input.get( row + 1, col ) );
					System.out.println( " " );
					largestThreeBasins[3] = 0;

				}
			}
		}
		System.out.println( mark );
		System.out.println( Arrays.toString( largestThreeBasins ) );
	}

	//	public void restoreTable(Table<Integer, Integer, Integer> input){
	//		for ( int row = 0; row < input.rowMap().size(); row++ ) {
	//			for ( int col = 0; col < input.columnMap().size(); col++ ) {
	//				if(input.get( row, col ) == 10) {
	//					input.put(row, col, )
	//				}
	//			}
	//			}
	//	}

	private void dfs( Table<Integer, Integer, Integer> input, int lowest, int x, int y, int rows,
			int cols, int mark ) {
		if ( x < 0 || x == rows || y < 0 || y == cols || input.get( x, y ) >= 9 ) {
			return;
		}
		input.put( x, y, 9 ); // marking a cell as visited with the basin num
		largestThreeBasins[3] += 1;
		System.out.println( Arrays.toString( largestThreeBasins ) );
		dfs( input, lowest, x - 1, y, rows, cols, mark );
		dfs( input, lowest, x + 1, y, rows, cols, mark );
		dfs( input, lowest, x, y - 1, rows, cols, mark );
		dfs( input, lowest, x, y + 1, rows, cols, mark );
	}

	public void day09Solutions() {
		final FileReader reader = new FileReader();
		try {
			Table<Integer, Integer, Integer> input = reader.readInTableOfIntegers( filePath );
			findBasins( input );

			//					int result1 = uniqueSegments( findLowPoints( input ) );
			//
			//					ArrayList<Integer> outputNums = decipherOutput( signalPatterns );
			//					int result2 = sumOfArrayList( outputNums );
			//
			//					System.out.println( "Day 08 part 1: " + result1 ); // 473
			//					System.out.println( "Day 08 part 2: " + result2 ); // 1097568

		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}

