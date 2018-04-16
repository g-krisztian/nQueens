package nQueens;

import java.util.ArrayList;
import java.util.List;

public class RowThread extends Thread {
	private Row currentRow;
	private RowRunnerService rowRunnerService;
	private RowResultService resultService;
	private Row nextRowProtoType = null;

	public RowThread() {

	}

	public RowThread(Row nextRow) {
		this.currentRow = nextRow;
	}

	@Override
	public void run() {
		List<Row> compute= new ArrayList<>();
		for (int i = 0; i < currentRow.getLength(); i++) {
			if (currentRow.getCurrentRow()[i] == 0) {
				if (currentRow.getColumn() == currentRow.getLength() - 1) {
					resultService.add(currentRow.getCurrentQueens(), i);
				} else {
					if (nextRowProtoType == null) {
						nextRowProtoType = createProto();
					}
					Row nextRow = new Row(nextRowProtoType);
					addQueen(nextRow, i);
					compute.add(nextRow);
				}
			}
		}
		rowRunnerService.add(compute);
		nextRowProtoType = null;

	}

	private Row createProto() {
		Row row = new Row();
		row.setColumn(currentRow.getColumn() + 1);
		row.setCurrentQueens(currentRow.getCurrentQueens());
		row.setLength(currentRow.getLength());
		row.setCurrentRow(computeNextRow());
		return row;
	}

	private byte[] computeNextRow() {
		byte[] result = new byte[currentRow.getLength()];
		for (int i = 0; i < result.length; i++) {
			if (i != 0) {
				result[i] += currentRow.getCurrentRow()[i - 1] & 4;
			}
			result[i] += currentRow.getCurrentRow()[i] & 2;
			if (i != result.length - 1) {
				result[i] += currentRow.getCurrentRow()[i + 1] & 1;
			}
		}
		return result;
	}

	private void addQueen(Row row, int i) {
		if (i != 0) {
			row.getCurrentRow()[i - 1] += 1;
		}
		row.getCurrentRow()[i] += 2;
		if (i != currentRow.getLength() - 1) {
			row.getCurrentRow()[i + 1] += 4;
		}
		row.getCurrentQueens()[currentRow.getColumn()] = i;
	}

	public void setRowRunnerService(RowRunnerService executorService) {
		this.rowRunnerService = executorService;
	}

	public void setResultService(RowResultService resultService) {
		this.resultService = resultService;
	}

	public void setRow(Row row) {
		this.currentRow = row;
	}
}
