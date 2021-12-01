package advent2021.day01;

import java.util.ArrayList;

public class Day01 {

	public int countDepthIncrease(ArrayList<Integer> depthsArray) {
		int count = 0;
		for ( int i = 1; i < depthsArray.size(); i++ ) {
			if(depthsArray.get(i) > depthsArray.get(i-1)) {
				count++;
			};
		}
		return count;
	}

	public int countDepthIncreaseInThrees(ArrayList<Integer> depthsArray) {
		int count = 0;
		for ( int i = 3; i < depthsArray.size(); i++ ) {
			if ((depthsArray.get( i ) + depthsArray.get( i-1 ) + depthsArray.get( i-2 )) > (depthsArray.get( i-1 ) + depthsArray.get( i-2 ) + depthsArray.get( i-3 ))) {
				count++;
			}

		}
		return count;
	}


}


