package nQueens;

public class app {

	public static void main(String[] args) {
		final int SIZE = 4;
		byte[] firstField = new byte[SIZE];
		int[] noQueens = new int[SIZE];
		RowRunnerService rowRunnerService = new RowRunnerServiceJava();
		RowResultService resultService = new RowResultService();
		
		Row firstRow = new Row();
		firstRow.setColumn(0);
		firstRow.setLength(SIZE);
		firstRow.setCurrentRow(firstField);
		firstRow.setCurrentQueens(noQueens);
		
		rowRunnerService.setResultService(resultService);
		rowRunnerService.add(firstRow);
		
		 
//		boolean run = true;
//		 while (run) {
//			run = !rowRunnerService.getRowThreadPool().isTerminated(); 
//		 }
		resultService.print();
	}

}
