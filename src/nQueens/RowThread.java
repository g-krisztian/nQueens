package nQueens;

import java.util.concurrent.ConcurrentLinkedDeque;

public class RowThread extends Thread {

	private RowResultService resultService;

	ConcurrentLinkedDeque<Row> queue;

	private int length;

	public RowThread(ConcurrentLinkedDeque<Row> queue, RowResultService resultService) {
		this.queue = queue;
		this.resultService = resultService;
		length=resultService.getSize();
	}

	@Override
	public void run() {
		int failcount = 0;
		Row nextRowProtoType= new Row();
		Row nextRow;
		Row currentRow = null;
		while (failcount < 5) {
			boolean hasPrototype=false;
			currentRow = queue.poll();
			if (currentRow != null) {
				for (int i = 0; i < length; ++i) {
					if (currentRow.getCurrentRow()[i] == 0) {
						if (currentRow.getColumn() == length - 1) {
							resultService.add(currentRow.getCurrentQueens(), i);
						} else {
							if (!hasPrototype) {
								nextRowProtoType = createProto(currentRow);
								hasPrototype=true;
							}
							nextRow = new Row(nextRowProtoType);
							addQueen(nextRow, i);
							queue.add(nextRow);
						}
					}
				}
			} else {
				failcount++;
			}
		}
	}

	private Row createProto(Row currentRow) {
		Row row = new Row(currentRow);
		row.setColumn(currentRow.getColumn() + 1);
		row.setCurrentRow(computeNextRow(currentRow));
		return row;
	}

	private byte[] computeNextRow(Row currentRow) {
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++) {
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
			row.getCurrentRow()[i - 1] |= 1;
		}
		row.getCurrentRow()[i] |= 2;
		if (i != length - 1) {
			row.getCurrentRow()[i + 1] |= 4;
		}
		row.getCurrentQueens()[row.getColumn() - 1] = i;
	}

}
