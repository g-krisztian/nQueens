package nQueens;

import java.util.concurrent.ExecutorService;

public class RowThread implements Runnable {

	private Row currentRow;
	private int length;

	private ExecutorService rowRunnerService;

	private RowResultService resultService;

	private byte[] nextRowProtoType = null;
	private byte[] current;

	@Override
	public void run() {

		current = currentRow.getCurrentRow();
		System.out.println(current);
		int lastColumn = current.length - 1;
		for (int i = 0; i < length; i++) {
			if (current[i] == 0) {
				if (currentRow.getColumn() == length-1) {
					resultService.add(currentRow.getCurrentQueens(), i);
					System.out.println("queenAdding: " + i);
				} else {

					if (nextRowProtoType == null) {
						nextRowProtoType = createProto();
					}
					Row nextRow = new Row();
					nextRow.setColumn(currentRow.getColumn() + 1);
					nextRow.setCurrentQueens(currentRow.getCurrentQueens());
					nextRow.setCurrentRow(nextRowProtoType);
					nextRow.setLength(length);
					addQueen(nextRow, i);
					RowThread nextThread = new RowThread();
					nextThread.setCurrentRow(nextRow);
					nextThread.setResultService(resultService);
					nextThread.setRowRunnerService(rowRunnerService);
					nextThread.run();
					// rowRunnerService.execute(nextThread);
				}
			}
		}

	}

	private void addQueen(Row row, int i) {
		int[] currentQueens = row.getCurrentQueens();
		currentQueens[currentRow.getColumn()] = i;
		row.setCurrentQueens(currentQueens);
		byte[] nextRow = row.getCurrentRow();
		if (i != 0) {
			nextRow[i - 1] += 1;
		}
		nextRow[i] += 2;
		if (i != length - 1) {
			nextRow[i + 1] += 4;
		}
		row.setCurrentRow(nextRow);
	}

	private byte[] createProto() {
		byte[] response = new byte[length];
		for (int i = 0; i < length; i++) {
			if (i != 0) {
				response[i] += current[i - 1] & 1;
			}
			response[i] += current[i] & 2;
			if (i != length - 1) {
				response[i] += current[i + 1] & 4;
			}
		}
		return response;
	}


	public void setRowRunnerService(ExecutorService executorService) {
		this.rowRunnerService = executorService;
	}

	public void setResultService(RowResultService resultService) {
		this.resultService = resultService;
	}

	public void setCurrentRow(Row row) {
		this.currentRow = row;
		this.length = row.getLength();
	}
}
