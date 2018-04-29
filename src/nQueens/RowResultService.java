package nQueens;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedDeque;

public class RowResultService {
	ConcurrentLinkedDeque<int[]> resultSet = new ConcurrentLinkedDeque<>();
	Date start;
	Date last;
	private int size;

	public void add(int[] previousQueens, int lastQueen) {
		int[] result = new int[previousQueens.length];
		System.arraycopy(previousQueens, 0, result, 0, previousQueens.length);
		result[result.length - 1] = lastQueen;
		resultSet.add(result);

	}

	public RowResultService(int size) {
		start = Date.from(Instant.now());
		this.size = size;
	}

	public void print() {
		last = Date.from(Instant.now());
		// printTables();

		System.out.println("\tTable size: " + size + "\tNb of results: " + resultSet.size() + "\tCalculated in: "
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