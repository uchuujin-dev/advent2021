package advent2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader {

	public ArrayList<Integer> readFile ( String fileName ) throws IOException {
		ArrayList<Integer> input = new ArrayList<>();
		BufferedReader bufferedReader = new BufferedReader( new java.io.FileReader( fileName ) );
		String line;
		while ((line = bufferedReader.readLine()) != null){
			input.add( Integer.valueOf( line ) );
		}
		bufferedReader.close();
		return input;
	}
}
