package nQueen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;


public class app {

	public static void main(String[] args) {
		final int SIZE = 3;
		
		byte[] firstField = new byte[SIZE];

		int[] previousQueens = new int[SIZE];

		List<int[]> rs = Collections.synchronizedList(new ArrayList<int[]>());
		Row firstRow = new Row(firstField, previousQueens, 0, es, rs);

		printRs(rs);
	}
	

	private static void printRs(List<int[]> rs) {
		System.out.println(rs);
		for (int[] item : rs) {
			
			for (int i : item) {
				System.out.print(i);
			}
			System.out.println();
		}

	}

}
