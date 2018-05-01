package nQueens;

import java.util.concurrent.ConcurrentLinkedDeque;

public class app {

	private static int size;

	public static void main(String[] args) throws InterruptedException {

		int cores = Runtime.getRuntime().availableProcessors();

		size = Integer.parseInt(args[0]);

		if (args.length > 1) {
			cores = Integer.parseInt(args[1]);
		}

		RowResultService resultService = new RowResultService(size);

		ConcurrentLinkedDeque<RowThread> threadPool = new ConcurrentLinkedDeque<>();

		for (int threads = 0; threads < size;) {
			for (int i = 0; i <= cores-1; i++) {
				if (threads < size) {
					SimpleConcurentFifo queue = new SimpleConcurentFifo();
					Row second = createSecond(threads);
					queue.push(second);
					RowThread rowThread = new RowThread(queue, resultService);
					threadPool.add(rowThread);
					rowThread.start();
					threads++;
				}
			}

			for (RowThread rowThread : threadPool) {
				rowThread.join();
				threadPool.remove(rowThread);
			}
		}

		System.out.print("Number of threads: " + cores);
		resultService.print();
	}

	private static Row createSecond(int position) {
		Row result = new Row();
		result.setColumn(1);
		int[] currentQueens = new int[size];
		byte[] currentRow = new byte[size];

		currentQueens[0] = position;

		if (position != 0)
			currentRow[position - 1] += 1;
		currentRow[position] += 2;
		if (position != size - 1)
			currentRow[position + 1] += 4;
		result.setCurrentQueens(currentQueens);
		result.setCurrentRow(currentRow);
		result.setLength(size);
		return result;
	}

}
