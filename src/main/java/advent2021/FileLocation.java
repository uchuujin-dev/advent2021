package advent2021;

public class FileLocation {

	public String getFileLocation( String day ) {
		return String.format( "src/main/resources/%s", day );
	}

}
