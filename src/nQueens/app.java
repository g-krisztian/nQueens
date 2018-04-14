package nQueens;

public class app {

	public static void main(String[] args) {
		final int SIZE = 5;
		byte[] firstField = new byte[SIZE];
		int[] noQueens = new int[SIZE];
		RowRunnerService rowRunnerService = new RowRunnerServiceJava();
		RowResultService resultService = new RowResultService();
		
		Row firstRow = new Row();
		firstRow.setColumn(0);
		firstRow.setLength(SIZE);
		firstRow.setCurrentRow(firstField);
		firstRow.setCurrentQueens(noQueens);
		RowThread rowThread = new RowThread();
		
		rowThread.setCurrentRow(firstRow);
		rowThread.setResultService(resultService);
		//rowThread.setRowRunnerService(executorService);
		rowThread.run();
		 
//		boolean run = true;
//		 while (run) {
//			run = !rowRunnerService.getRowThreadPool().isTerminated(); 
//		 }
		resultService.print();
	}

}
