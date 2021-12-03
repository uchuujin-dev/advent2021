package advent2021;

import java.io.File;
import java.net.URL;

public class FileLocation {

	public File readFileFromClasspath( String day ) {
		URL fileUrl = getClass().getResource( "/" + day );
		return new File( fileUrl.getFile() );
	}

}
