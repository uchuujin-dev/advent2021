package advent2021;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

	// Day01 file reader

	public ArrayList<Integer> readFileAsIntegerArrayList( File fileName ) throws IOException {
		ArrayList<Integer> input = new ArrayList<>();
		BufferedReader bufferedReader = new BufferedReader( new java.io.FileReader( fileName ) );
		String line;
		while ( ( line = bufferedReader.readLine() ) != null ) {
			input.add( Integer.valueOf( line ) );
		}
		bufferedReader.close();
		return input;
	}

	// Day02 file reader
	public Map<String, ArrayList<Integer>> readFileAsMapOfStringArrayList( File fileName )
			throws IOException {
		Map<String, ArrayList<Integer>> input = new HashMap<>();
		ArrayList<Integer> commandForward = new ArrayList<>();
		ArrayList<Integer> commandUp = new ArrayList<>();
		ArrayList<Integer> commandDown = new ArrayList<>();
		input.put( "forward", commandForward );
		input.put( "up", commandUp );
		input.put( "down", commandDown );

		BufferedReader bufferedReader = new BufferedReader( new java.io.FileReader( fileName ) );
		String line;
		while ( ( line = bufferedReader.readLine() ) != null ) {
			if ( line.contains( "forward" ) ) {
				line = line.replaceAll( "\\D+", "" );
				input.get( "forward" ).add( Integer.valueOf( line.replaceAll( "\\D+", "" ) ) );
			} else if ( line.contains( "up" ) ) {
				line = line.replaceAll( "\\D+", "" );
				input.get( "up" ).add( Integer.valueOf( line.replaceAll( "\\D+", "" ) ) );
			} else if ( line.contains( "down" ) ) {
				line = line.replaceAll( "\\D+", "" );
				input.get( "down" ).add( Integer.valueOf( line.replaceAll( "\\D+", "" ) ) );
			} else {
				System.err.println( "ERROR: cannot find appropriate commands." );
			}

		}
		bufferedReader.close();
		return input;
	}

	// Day03 file reader

	public ArrayList<ArrayList<Integer>> readFileInAsVerticalArrays( File fileName )
			throws IOException {
		ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> column1 = new ArrayList<Integer>();
		ArrayList<Integer> column2 = new ArrayList<Integer>();
		ArrayList<Integer> column3 = new ArrayList<Integer>();
		ArrayList<Integer> column4 = new ArrayList<Integer>();
		ArrayList<Integer> column5 = new ArrayList<Integer>();
		ArrayList<Integer> column6 = new ArrayList<Integer>();
		ArrayList<Integer> column7 = new ArrayList<Integer>();
		ArrayList<Integer> column8 = new ArrayList<Integer>();
		ArrayList<Integer> column9 = new ArrayList<Integer>();
		ArrayList<Integer> column10 = new ArrayList<Integer>();
		ArrayList<Integer> column11 = new ArrayList<Integer>();
		ArrayList<Integer> column12 = new ArrayList<Integer>();

		BufferedReader bufferedReader = new BufferedReader( new java.io.FileReader( fileName ) );
		String line;

		while ( ( line = bufferedReader.readLine() ) != null ) {

			column1.add( Character.getNumericValue( line.charAt( 0 ) ) );
			column2.add( Character.getNumericValue( line.charAt( 1 ) ) );
			column3.add( Character.getNumericValue( line.charAt( 2 ) ) );
			column4.add( Character.getNumericValue( line.charAt( 3 ) ) );
			column5.add( Character.getNumericValue( line.charAt( 4 ) ) );
			column6.add( Character.getNumericValue( line.charAt( 5 ) ) );
			column7.add( Character.getNumericValue( line.charAt( 6 ) ) );
			column8.add( Character.getNumericValue( line.charAt( 7 ) ) );
			column9.add( Character.getNumericValue( line.charAt( 8 ) ) );
			column10.add( Character.getNumericValue( line.charAt( 9 ) ) );
			column11.add( Character.getNumericValue( line.charAt( 10 ) ) );
			column12.add( Character.getNumericValue( line.charAt( 11 ) ) );
		}

		input.add( column1 );
		input.add( column2 );
		input.add( column3 );
		input.add( column4 );
		input.add( column5 );
		input.add( column6 );
		input.add( column7 );
		input.add( column8 );
		input.add( column9 );
		input.add( column10 );
		input.add( column11 );
		input.add( column12 );

		return input;
	}

	public ArrayList<ArrayList<Integer>> readFileInAsArrayListOfArrayLists( File fileName )
			throws IOException {
		ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
		BufferedReader bufferedReader = new BufferedReader( new java.io.FileReader( fileName ) );
		String line;
		while ( ( line = bufferedReader.readLine() ) != null ) {
			ArrayList<Integer> row = new ArrayList<>();
			for ( int i = 0; i < line.length(); i++ ) {
				row.add( Character.getNumericValue( line.charAt( i ) ) );
			}
			input.add( row );

		}

		return input;
	}

	public ArrayList<ArrayList<Integer>> readFileSample( File fileName ) throws IOException {
		ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> column1 = new ArrayList<Integer>();
		ArrayList<Integer> column2 = new ArrayList<Integer>();
		ArrayList<Integer> column3 = new ArrayList<Integer>();
		ArrayList<Integer> column4 = new ArrayList<Integer>();
		ArrayList<Integer> column5 = new ArrayList<Integer>();

		BufferedReader bufferedReader = new BufferedReader( new java.io.FileReader( fileName ) );
		String line;

		while ( ( line = bufferedReader.readLine() ) != null ) {

			column1.add( Character.getNumericValue( line.charAt( 0 ) ) );
			column2.add( Character.getNumericValue( line.charAt( 1 ) ) );
			column3.add( Character.getNumericValue( line.charAt( 2 ) ) );
			column4.add( Character.getNumericValue( line.charAt( 3 ) ) );
			column5.add( Character.getNumericValue( line.charAt( 4 ) ) );

		}

		input.add( column1 );
		input.add( column2 );
		input.add( column3 );
		input.add( column4 );
		input.add( column5 );

		return input;
	}

}
