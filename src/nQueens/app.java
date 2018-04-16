package nQueens;

public class app {

	public static void main(String[] args) {
		final int SIZE = 4;
		byte[] firstField = new byte[SIZE];
		int[] noQueens = new int[SIZE];

		RowResultService resultService = new RowResultService();

		RowRunnerService rowRunnerService = new RowRunnerServiceJava();

		Row firstRow = new Row();
		firstRow.setColumn(0);
		firstRow.setLength(SIZE);
		firstRow.setCurrentRow(firstField);
		firstRow.setCurrentQueens(noQueens);
		
		rowRunnerService.setResultService(resultService);
		
		rowRunnerService.add(firstRow);
		rowRunnerService.start();

		while (rowRunnerService.getRunning() > 0) {

		}

		resultService.print();
	}

}
