package advent2021;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

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

}
