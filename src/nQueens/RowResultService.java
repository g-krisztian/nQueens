package nQueens;

import java.util.concurrent.ConcurrentLinkedDeque;

public class RowResultService {

	ConcurrentLinkedDeque<int[]> resultSet = new ConcurrentLinkedDeque<>();

	public void add(int[] previousQueens, int lastQueen) {
		int[] result = new int[previousQueens.length];
		System.arraycopy(previousQueens, 0, result, 0, previousQueens.length);
		result[result.length - 1] = lastQueen;
		resultSet.add(result);
		System.out.println("Nb of results: " + resultSet.size());
	}

	public void print() {
		for (int[] is : resultSet) {
			for (int i : is) {
				System.out.print(i + ", ");
			}
			System.out.println();
		}

		System.out.println("Nb of results: " + resultSet.size());
	}

}
