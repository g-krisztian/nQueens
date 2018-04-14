package nQueens;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class app {


	public static void main(String[] args) {
		final int SIZE = 12;
		byte[] firstField = new byte[SIZE];
		int[] noQueens = new int[SIZE];
		ExecutorService executorService = Executors.newCachedThreadPool();
		RowResultService resultService = new RowResultService();
		
		RowRunnerService rowRunnerService = new RowRunnerServiceJava(executorService);

		
		Row firstRow = new Row();
		firstRow.setColumn(0);
		firstRow.setLength(SIZE);
		firstRow.setCurrentRow(firstField);
		firstRow.setCurrentQueens(noQueens);
		RowThread rowThread = new RowThread();
		
		rowThread.setRow(firstRow);
		rowThread.setResultService(resultService);
		rowThread.setRowRunnerService(executorService);
		//rowThread.run();
		executorService.execute(rowThread);
//		while (!executorService.isShutdown()) {
//			
//		}
		
		resultService.print();
	}

}
