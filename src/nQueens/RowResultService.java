package nQueens;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedDeque;

public class RowResultService {
	ConcurrentLinkedDeque<int[]> resultSet = new ConcurrentLinkedDeque<>();
	Date start;
	Date last;
	private int size;
	private int results;

	public void add(int[] previousQueens, int lastQueen) {
//		int[] result = new int[previousQueens.length];
//		System.arraycopy(previousQueens, 0, result, 0, previousQueens.length);
//		result[result.length - 1] = lastQueen;
//		resultSet.add(result);
		results++;
	}

	public RowResultService(int size) {
		start = Date.from(Instant.now());
		this.size = size;
		results = 0;
	}

	public void print() {
		last = Date.from(Instant.now());
		// printTables();

		System.out.println("\tTable size: " + size + "\tNb of results: " + results + "\tCalculated in: "
				+ (last.getTime() - start.getTime()) + "ms");
	}

	private void printTables() {
		for (int[] is : resultSet) {
			for (int i : is) {
				System.out.print(i + ", ");
			}
			System.out.println();
		}
	}

	public int getSize() {
		return size;
	}

}