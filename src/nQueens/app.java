package nQueens;

import java.util.concurrent.ConcurrentLinkedDeque;

public class app {

	public static void main(String[] args) {
		final int SIZE = 3;
		byte[] firstField = new byte[SIZE];
		int[] previousQueens = new int[SIZE];
		ConcurrentLinkedDeque<int[]> resultSet = new ConcurrentLinkedDeque<>();
		
		Row firstRow = new Row(firstField, previousQueens, 0);
		RowRunnerService threadService = new RowRunnerServiceJava();
		threadService.add(firstRow);
		threadService.start();
		
		
	}

}
