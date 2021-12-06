package advent2021.day05;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import advent2021.FileLocation;
import advent2021.FileReader;

public class Day05 {
	FileLocation file = new FileLocation();
	File filePath = file.readFileFromClasspath( "day05" );

	// returns an arraylist of coords to flip in grid
	// go through list and do grid[x][y]++

	public ArrayList<int[]> dangerCoords( ArrayList<String[][]> coords ) {
		ArrayList<int[]> dangerCoords = new ArrayList<>();
		int coord1 = 0; // index
		int coord2 = 1; // index
		int x = 0; // index
		int y = 1; //index

		// for each row in arraylist check if coords.get(0)[x] is same as .get(1)[x] OR [y] = [y]
		for ( int row = 0; row < coords.size(); row++ ) {
			int x1 = Integer.parseInt( coords.get( row )[coord1][x] );
			int y1 = Integer.parseInt( coords.get( row )[coord1][y] );
			int x2 = Integer.parseInt( coords.get( row )[coord2][x] );
			int y2 = Integer.parseInt( coords.get( row )[coord2][y] );
			int[] startCoord = { x1, y1 };
			int[] endCoord = { x2, y2 };

			if ( x1 == x2 ) { // x is the same, so do y - y
				int smallerY = min( y1, y2 );
				int biggerY = max( y1, y2 );
				if ( biggerY - smallerY > 1 ) {

					for ( int yCoord = smallerY + 1; yCoord < biggerY; yCoord++ ) {
						int[] tempCoord = { x1, yCoord };
						dangerCoords.add( tempCoord );
					}
				}

				dangerCoords.add( startCoord );
				dangerCoords.add( endCoord );

			} else if ( y1 == y2 ) { // y is the same, so do x - x
				int smallerX = min( x1, x2 );
				int biggerX = max( x1, x2 );
				if ( biggerX - smallerX > 1 ) {

					for ( int xCoord = smallerX + 1; xCoord < biggerX; xCoord++ ) {
						int[] tempCoord = { xCoord, y1 };
						dangerCoords.add( tempCoord );
					}
				}

				dangerCoords.add( startCoord );
				dangerCoords.add( endCoord );

			} else {
				continue;
			}

		}

		// if so then do the calc x-x or y-y and make it positive ie. [7,0] [7,4] then it should return [7,0] [7,1] [7,2] [7,3] [7,4]

		return dangerCoords;
	}

	public ArrayList<int[]> dangerCoordsDiagnal( ArrayList<String[][]> coords ) {
		ArrayList<int[]> dangerCoords = new ArrayList<>();
		int coord1 = 0; // index
		int coord2 = 1; // index
		int x = 0; // index
		int y = 1; //index

		// for each row in arraylist check if coords.get(0)[x] is same as .get(1)[x] OR [y] = [y]
		for ( int row = 0; row < coords.size(); row++ ) {
			int x1 = Integer.parseInt( coords.get( row )[coord1][x] );
			int y1 = Integer.parseInt( coords.get( row )[coord1][y] );
			int x2 = Integer.parseInt( coords.get( row )[coord2][x] );
			int y2 = Integer.parseInt( coords.get( row )[coord2][y] );
			int[] startCoord = { x1, y1 };
			int[] endCoord = { x2, y2 };

			if ( x1 == x2 ) { // x is the same, so do y - y
				int smallerY = min( y1, y2 );
				int biggerY = max( y1, y2 );
				if ( biggerY - smallerY > 1 ) {

					for ( int yCoord = smallerY + 1; yCoord < biggerY; yCoord++ ) {
						int[] tempCoord = { x1, yCoord };
						dangerCoords.add( tempCoord );

					}
				}

				dangerCoords.add( startCoord );
				dangerCoords.add( endCoord );

			} else if ( y1 == y2 ) { // y is the same, so do x - x
				int smallerX = min( x1, x2 );
				int biggerX = max( x1, x2 );
				if ( biggerX - smallerX > 1 ) {

					for ( int xCoord = smallerX + 1; xCoord < biggerX; xCoord++ ) {
						int[] tempCoord = { xCoord, y1 };
						dangerCoords.add( tempCoord );

					}
				}

				dangerCoords.add( startCoord );
				dangerCoords.add( endCoord );

			} else { // diagnal
				int smallerX = min( x1, x2 );
				int biggerX = max( x1, x2 );
				int diffX = biggerX - smallerX;
				int smallerY = min( y1, y2 );
				int biggerY = max( y1, y2 );
				int diffY = biggerY - smallerY;

				// if diagnal downwards \ (x2-x1 and y2-y1 are both positive)
				// tempcoord x++ y++ or x-- y--
				if ( x2 - x1 > 0 && y2 - y1 > 0 ) {
					for ( int increase = 1; increase < diffX; increase++ ) {
						int[] tempCoord = { x1 + increase, y1 + increase };
						dangerCoords.add( tempCoord );

					}
					dangerCoords.add( startCoord );
					dangerCoords.add( endCoord );
				} else if ( x1 - x2 > 0 && y1 - y2 > 0 ) {
					for ( int increase = 1; increase < diffX; increase++ ) {
						int[] tempCoord = { x1 - increase, y1 - increase };
						dangerCoords.add( tempCoord );

					}
					dangerCoords.add( startCoord );
					dangerCoords.add( endCoord );
				}

				// if diagnal upwards/ (x2 - x1 OR y2 - y1 one of them is negative)
				// if x increases then x++ y--, else x-- y++ ([0,5] [5,0]) ([5,0] [0,5])

				else if ( x2 - x1 > 0 && y2 - y1 < 0 ) {
					for ( int increase = 1; increase < diffX; increase++ ) {
						int[] tempCoord = { x1 + increase, y1 - increase };
						dangerCoords.add( tempCoord );

					}
					dangerCoords.add( startCoord );
					dangerCoords.add( endCoord );
				} else if ( x2 - x1 < 0 && y2 - y1 > 0 ) {
					for ( int increase = 1; increase < diffX; increase++ ) {
						int[] tempCoord = { x1 - increase, y1 + increase };
						dangerCoords.add( tempCoord );

					}
					dangerCoords.add( startCoord );
					dangerCoords.add( endCoord );
				} else {
					System.err.println( "coord not calculated: " + Arrays.toString(
							startCoord ) + " " + Arrays.toString( endCoord ) );
				}

			}

		}

		// if so then do the calc x-x or y-y and make it positive ie. [7,0] [7,4] then it should return [7,0] [7,1] [7,2] [7,3] [7,4]

		return dangerCoords;
	}

	public int[][] ventField( ArrayList<int[]> coords ) {

		int fieldSize = 1000;
		int[][] grid = new int[fieldSize][fieldSize];

		for ( int row = 0; row < coords.size(); row++ ) {
			int x = coords.get( row )[0];
			int y = coords.get( row )[1];
			grid[x][y]++;
		}

		return grid;
	}

	public int calcDangerPoints( int[][] grid ) {
		// check how many places are 2 or larger
		int dangerPoints = 0;

		for ( int row = 0; row < 1000; row++ ) {
			for ( int col = 0; col < 1000; col++ ) {
				if ( grid[row][col] >= 2 ) {
					dangerPoints++;
				}
			}

		}
		return dangerPoints;
	}

	public void day05Solutions() {
		final FileReader reader = new FileReader();

		try {

			ArrayList<String[][]> coords = reader.readInCoords( filePath );
			ArrayList<int[]> dangerCoords = dangerCoords( coords );
			int[][] ventField = ventField( dangerCoords );
			int result1 = calcDangerPoints( ventField );

			ArrayList<int[]> dangerCoords2 = dangerCoordsDiagnal( coords );
			int[][] ventField2 = ventField( dangerCoords2 );
			int result2 = calcDangerPoints( ventField2 );

			System.out.println( "Day 05 part 1: " + result1 ); // 4826

			System.out.println( "Day 05 part 2: " + result2 ); // 16793

		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
