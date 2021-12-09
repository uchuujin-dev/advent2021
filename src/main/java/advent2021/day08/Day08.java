package advent2021.day08;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import advent2021.FileLocation;
import advent2021.FileReader;

public class Day08 {
	FileLocation file = new FileLocation();
	File filePath = file.readFileFromClasspath( "day08" );

	public ArrayList<String> parseOutPutValuesOnly( ArrayList<String> signalPatterns ) {
		ArrayList<String> outputValues = new ArrayList<>();
		for ( String signalPattern : signalPatterns ) {
			outputValues.add( signalPattern.substring( signalPattern.indexOf( "|" ) + 2 ) );
		}

		return outputValues;

	}

	public int uniqueSegments( ArrayList<String> outputValues ) {
		// unique values are 1, 4, 7, 8, each with 2, 4, 3, and 7 signal lights
		int uniqueSegmentsCount = 0;
		for ( String outputValue : outputValues ) {
			String[] value = outputValue.split( " " );
			for ( String s : value ) {
				if ( s.length() == 2 || s.length() == 4 || s.length() == 3 || s.length() == 7 ) {
					uniqueSegmentsCount++;
				}
			}
		}
		return uniqueSegmentsCount;
	}

	public ArrayList<Integer> decipherOutput( ArrayList<String> signalPatterns ) {
		Map<Integer, String> decipheredPatterns = new HashMap<>();
		ArrayList<Integer> outputNums = new ArrayList<>();
		for ( String signalPattern : signalPatterns ) {
			String patterns = signalPattern.substring( 0, signalPattern.indexOf( "|" ) - 1 );
			String[] value = patterns.split( " " );

			String outputSignal = signalPattern.substring( signalPattern.indexOf( "|" ) + 2 );
			String[] outputSignals = outputSignal.split( " " );

			// find unique values first
			for ( String s : value ) {
				if ( s.length() == 2 ) {
					decipheredPatterns.put( 1, s );
				} else if ( s.length() == 4 ) {
					decipheredPatterns.put( 4, s );
				} else if ( s.length() == 3 ) {
					decipheredPatterns.put( 7, s );
				} else if ( s.length() == 7 ) {
					decipheredPatterns.put( 8, s );
				}
			}

			// find 9
			for ( String s : value ) {
				if ( s.length() == 6 && containsAllOfCharInAnyOrder( s,
						decipheredPatterns.get( 4 ) ) ) {
					decipheredPatterns.put( 9, s );
				}
			}

			// find the char in 8 but not 9

			// find 0 and 2
			// if the string contains the character from 8 and not 9, and length is 6 = 0
			// otherwise length is 5 then its 2
			for ( String s : value ) {
				String inEightButNotNine = uniqueCharacter( decipheredPatterns.get( 8 ),
						decipheredPatterns.get( 9 ) );
				if ( s.length() == 6 && containsAllOfCharInAnyOrder( s,
						decipheredPatterns.get( 1 ) ) && s.contains( inEightButNotNine ) ) {
					decipheredPatterns.put( 0, s );
				} else if ( s.length() == 5 && s.contains( inEightButNotNine ) ) {
					decipheredPatterns.put( 2, s );
				}
			}

			//

			// find 6
			// if the string contains the character from 8 but not 0, and length is 6
			for ( String s : value ) {
				String inEightButNotZero = uniqueCharacter( decipheredPatterns.get( 8 ),
						decipheredPatterns.get( 0 ) );
				if ( s.length() == 6 && s.contains(
						inEightButNotZero ) && !containsAllOfCharInAnyOrder( s,
						decipheredPatterns.get( 1 ) ) ) {
					decipheredPatterns.put( 6, s );
				}
			}

			for ( String s : value ) {
				if ( s.length() == 5 && overlappingCharCount( decipheredPatterns.get( 2 ),
						s ) == 4 ) {
					decipheredPatterns.put( 3, s );
				} else if ( s.length() == 5 && overlappingCharCount( decipheredPatterns.get( 2 ),
						s ) == 3 ) {
					decipheredPatterns.put( 5, s );
				}
			}

			outputNums.add( findOutputNum( decipheredPatterns, outputSignals ) );
			decipheredPatterns.clear();
		}
		return outputNums;
	}

	public int sumOfArrayList( ArrayList<Integer> arrayList ) {
		int sum = 0;
		for ( Integer integer : arrayList ) {
			sum += integer;
		}
		return sum;
	}

	public int findOutputNum( Map<Integer, String> decipheredCode, String[] outputSignals ) {
		String num = "";

		for ( String outputSignal : outputSignals ) {
			char[] code = outputSignal.toCharArray();
			Arrays.sort( code );
			for ( int key : decipheredCode.keySet() ) {
				char[] charInValue = decipheredCode.get( key ).toCharArray();
				Arrays.sort( charInValue );
				if ( Arrays.equals( code, charInValue ) ) {
					num = num + key;
					break;
				}
			}
		}
		return Integer.parseInt( num );
	}

	public boolean containsAllOfCharInAnyOrder( String original, String match ) {
		for ( int i = 0; i < match.length(); i++ ) {
			if ( !original.contains( String.valueOf( match.charAt( i ) ) ) ) {
				return false;
			}
		}
		return true;
	}

	public int overlappingCharCount( String one, String two ) {
		int count = 0;
		for ( int i = 0; i < two.length(); i++ ) {
			if ( one.contains( String.valueOf( two.charAt( i ) ) ) ) {
				count++;
			}
		}
		return count;
	}

	public String uniqueCharacter( String biggerString, String smallerString ) {
		List<Character> chars;
		chars = biggerString.chars()
				.distinct()
				.mapToObj( c -> (char) c )
				.collect( Collectors.toList() );
		String unique = "";

		for ( Character character : chars ) {
			if ( !smallerString.contains( String.valueOf( character ) ) ) {
				unique = String.valueOf( character );
				break;
			}
		}
		return unique;
	}

	public void day08Solutions() {
		final FileReader reader = new FileReader();
		try {
			ArrayList<String> signalPatterns = reader.readInSignalPatterns( filePath );
			ArrayList<String> outputValues = parseOutPutValuesOnly( signalPatterns );

			int result1 = uniqueSegments( outputValues );

			ArrayList<Integer> outputNums = decipherOutput( signalPatterns );
			int result2 = sumOfArrayList( outputNums );

			System.out.println( "Day 08 part 1: " + result1 ); // 473
			System.out.println( "Day 08 part 2: " + result2 ); // 1097568

		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
