package advent2021.day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import advent2021.FileLocation;
import advent2021.FileReader;

public class Day02 {

	FileLocation file = new FileLocation();
	File filePath = file.readFileFromClasspath( "day02" );

	public Integer calculatePosition( Map<String, ArrayList<Integer>> input ) {
		int forward = 0;
		int up = 0;
		int down = 0;

		for ( Map.Entry<String, ArrayList<Integer>> entry : input.entrySet() ) {
			switch ( entry.getKey() ) {
			case "forward":
				for ( Integer command : entry.getValue() ) {
					forward = command + forward;
				}
				break;
			case "up":
				for ( Integer command : entry.getValue() ) {
					up = command + up;
				}
				break;
			case "down":
				for ( Integer command : entry.getValue() ) {
					down = command + down;

				}
				break;
			}

		}
		return forward * ( down - up );
	}

	public int calculateAim( File fileName ) throws IOException {
		int pos;
		int aim = 0;
		int depth = 0;
		int horizontalPos = 0;

		BufferedReader bufferedReader = new BufferedReader( new java.io.FileReader( fileName ) );
		String line;
		while ( ( line = bufferedReader.readLine() ) != null ) {
			if ( line.contains( "forward" ) ) {
				line = line.replaceAll( "\\D+", "" );
				horizontalPos = horizontalPos + Integer.parseInt( line );
				depth = aim * Integer.parseInt( line ) + depth;
			} else if ( line.contains( "up" ) ) {
				line = line.replaceAll( "\\D+", "" );
				aim = aim - Integer.parseInt( line );
			} else if ( line.contains( "down" ) ) {
				line = line.replaceAll( "\\D+", "" );
				aim = aim + Integer.parseInt( line );
			} else {
				System.err.println( "ERROR: cannot compute command: " + line );
			}

		}
		pos = horizontalPos * depth;
		bufferedReader.close();
		return pos;
	}

	public void day02Solutions() {
		final FileReader reader = new FileReader();
		Map<String, ArrayList<Integer>> commands;

		try {
			commands = reader.readFileAsMapOfStringArrayList( filePath );

			int result1 = calculatePosition( commands );
			System.out.println( "Day 02 part 1: " + result1 ); // 1882980

			int result2 = calculateAim( filePath );
			System.out.println( "Day 02 part 2: " + result2 ); // 1971232560

		} catch ( IOException e ) {
			e.printStackTrace();
		}

	}

}
