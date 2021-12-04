package advent2021.day04;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import advent2021.FileLocation;
import advent2021.FileReader;

public class Day04 {
	FileLocation file = new FileLocation();
	File filePath = file.readFileFromClasspath( "day04" );

	public int[] findWinner( ArrayList<Integer> bingoNums, HashMap<Integer, int[][]> bingoBoards ) {

		for ( int bingoNum : bingoNums ) {

			for ( int board = 0; board < bingoBoards.size(); board++ ) {

				for ( int row = 0; row < 5; row++ ) {
					for ( int num = 0; num < 5; num++ ) {
						if ( bingoBoards.get( board )[row][num] == bingoNum ) {
							bingoBoards.get( board )[row][num] = 1000;

							if ( bingo( bingoBoards.get( board ) ) ) {
								int[] info = { board, bingoNum };
								return info;
							}
						}

					}

				}

			}

		}
		int[] empty = {};
		return empty;

	}

	public int[] findLoser( ArrayList<Integer> bingoNums, HashMap<Integer, int[][]> bingoBoards ) {

		HashMap<Integer, int[][]> winners = new HashMap<>();

		for ( int bingoNum : bingoNums ) {

			for ( int board = 0; board < bingoBoards.size(); board++ ) {

				for ( int row = 0; row < 5; row++ ) {
					for ( int num = 0; num < 5; num++ ) {
						if ( bingoBoards.get( board )[row][num] == bingoNum ) {
							bingoBoards.get( board )[row][num] = 1000;
							if ( bingo( bingoBoards.get( board ) ) ) {
								winners.put( board, bingoBoards.get( board ) );
								if ( winners.size() == bingoBoards.size() ) {
									int[] info = { board, bingoNum };
									return info;
								}
							}
						}
					}
				}
			}
		}
		int[] empty = {};
		return empty;
	}

	public boolean bingo( int[][] board ) {
		// checks 1000s
		int[] bingo = { 1000, 1000, 1000, 1000, 1000 };

		for ( int row = 0; row < 5; row++ ) {
			if ( Arrays.toString( board[row] ).equals( Arrays.toString( bingo ) ) ) {
				return true;
			}

			for ( int col = 0; col < 5; col++ ) {
				if ( board[0][col] == 1000 && board[1][col] == 1000 && board[2][col] == 1000 && board[3][col] == 1000 && board[4][col] == 1000 ) {
					return true;
				}
			}
		}
		return false;
	}

	public int calcScore( HashMap<Integer, int[][]> bingoBoards, int[] winningBoardNum ) {
		int score;
		int sum = 0;
		int winningBoard = winningBoardNum[0];
		int winningNum = winningBoardNum[1];

		int[][] board = bingoBoards.get( winningBoard );
		for ( int row = 0; row < 5; row++ ) {
			for ( int num = 0; num < 5; num++ ) {
				if ( board[row][num] != 1000 ) {
					sum += board[row][num];
				}

			}

		}
		score = sum * winningNum;
		return score;
	}

	public void day04Solutions() {
		final FileReader reader = new FileReader();
		ArrayList<Integer> bingoNums;
		HashMap<Integer, int[][]> bingoBoards = new HashMap<>();
		try {
			bingoNums = reader.readBingoNumbers( filePath );
			bingoBoards = reader.readMatrix( filePath );

			int result1 = calcScore( bingoBoards, findWinner( bingoNums, bingoBoards ) );
			System.out.println( "Day 04 part 1: " + result1 ); // 41668

			int result2 = calcScore( bingoBoards, findLoser( bingoNums, bingoBoards ) );
			System.out.println( "Day 04 part 2: " + result2 ); // 10478

		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
