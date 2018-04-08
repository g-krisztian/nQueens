package nQueens;

import java.util.concurrent.ExecutorService;

public class RowThread implements Runnable {

	private Row currentRow;
	private int length;
	private int column;

	private ExecutorService rowRunnerService;

	private RowResultService resultService;

	private byte[] nextRowProtoType = null;
	private byte[] current;

	@Override
	public void run() {

		current = currentRow.getCurrentRow();
		int lastColumn = current.length - 1;
		for (int i = 0; i < length; i++) {
			if (current[i] == 0) {
				if (column == lastColumn) {
					resultService.add(currentRow.getCurrentQueens(), i);
				} else {
					if (nextRowProtoType == null) {
						nextRowProtoType = createProto();
					}
					Row nextRow = new Row();
					nextRow.setColumn(column + 1);
					nextRow.setCurrentQueens(currentRow.getCurrentQueens());
					nextRow.setCurrentRow(nextRowProtoType);
					nextRow.addQueen(i);
					RowThread nextThread = new RowThread();
					nextThread.setCurrentRow(nextRow);
					nextThread.setResultService(resultService);
					nextThread.setRowRunnerService(rowRunnerService);
					System.out.println(nextRow.getColumn());
					rowRunnerService.execute(nextThread);
				}
			}
		}

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

	private Row createNextRow(int i) {

		return null;
		// TODO Auto-generated method stub

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
