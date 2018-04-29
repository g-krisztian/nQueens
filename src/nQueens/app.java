package nQueens;

import java.util.ArrayList;
import java.util.List;

public class app {

	public static void main(String[] args) throws InterruptedException {

		int size;
		int cores = Runtime.getRuntime().availableProcessors();

		size = Integer.parseInt(args[0]);

		if (args.length > 1) {
			cores = Integer.parseInt(args[1]);
		}

		byte[] firstField = new byte[size];
		int[] noQueens = new int[size];
		RowResultService resultService = new RowResultService(size);
		SimpleConcurentFifo queue = new SimpleConcurentFifo();

		Row firstRow = new Row();
		firstRow.setColumn(0);
		firstRow.setLength(size);
		firstRow.setCurrentRow(firstField);
		firstRow.setCurrentQueens(noQueens);

		queue.push(firstRow);

		List<RowThread> threadPool = new ArrayList<>();

		for (int i = 0; i <= cores; i++) {

			RowThread rowThread = new RowThread(queue, resultService);
			threadPool.add(rowThread);
			rowThread.start();
		}

		for (RowThread rowThread : threadPool) {
			rowThread.join();
		}

		System.out.print("Number of threads: " + cores);
		resultService.print();
	}

}
