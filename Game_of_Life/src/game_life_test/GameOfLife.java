package game_life_test;

import java.util.Random;

public class GameOfLife {
	
	static void nextGen(int field[][], int x) throws InterruptedException {
		
		int cnt = 10;
		
		while (cnt != 0) {
			int[][] next = new int[x][x];
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < x; j++) {
					int aliveNear = 0;
					for (int m = -1; m <= 1; m++) {
						for (int n = -1; n <= 1; n++) {
							if ((i + m < 0) ||					// upper border
								(i + m > x - 1) ||				// lower
								(j + n < 0) ||					// left
								(j + n > x - 1))				// right
									continue;
							aliveNear += field[i + m][j + n];
						}
					}
					aliveNear -= field[i][j];
					
					if ((field[i][j] == 1) && (aliveNear < 2))
						next[i][j] = 0;
					
					else if ((field[i][j] == 1) && (aliveNear > 3))
						next[i][j] = 0;
					
					else if ((field[i][j] == 0) && (aliveNear == 3))
						next[i][j] = 1;
					
					else
						next[i][j] = field[i][j];
				}
			}
			System.out.println("Next Generation");
			printField(next);
			System.out.println();
			field = next;
			Thread.sleep(500);
			cnt--;
		}
	}
	
	static void printField(int field[][]) {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length; j++) {
				System.out.print((field[i][j] == 0) ? "⬡ " : "⬢ ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
//		Random filling:
//		
//		Random ran = new Random();
//		int x = ran.nextInt(6) + 5;
//		
//		int [][] field = new int[x][x];
//		for (int i = 0; i < x; i++) {
//		    for (int j = 0; j < x; j++) {
//		        field[i][j] = ran.nextInt(2);
//		    }
//		}
		
//		manual filling:
		int[][] field = { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	            { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
	            { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
	            { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
	            { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
	            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
	            { 0, 0, 0, 1, 0, 0, 0, 1, 1, 0 },
	            { 0, 0, 0, 1, 0, 0, 1, 0, 0, 1 },
	            { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0 } };
		
		System.out.println("Start Generation");
		printField(field);
		System.out.println();
		nextGen(field, field.length);
	}
}
