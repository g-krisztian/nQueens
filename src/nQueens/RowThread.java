package nQueens;

import java.util.concurrent.ConcurrentLinkedDeque;

public class RowThread extends Thread {


	private RowResultService resultService;

	private Row nextRowProtoType = null;

	private ConcurrentLinkedDeque<Row> queue;
	private boolean running;


	public RowThread(ConcurrentLinkedDeque<Row> queue, RowResultService resultService) {
		this.queue = queue;
		this.resultService = resultService;
	}

	@Override
	public void run() {
		running=true;
		int failcount = 0;

		while (failcount < 5) {
			nextRowProtoType=null;
			Row currentRow = null;
			currentRow = queue.poll();
			
			if (currentRow != null) {
				for (int i = 0; i < currentRow.getLength(); i++) {
					if (currentRow.getCurrentRow()[i] == 0) {
						if (currentRow.getColumn() == currentRow.getLength() - 1) {
							resultService.add(currentRow.getCurrentQueens(), i);
						} else {
							if (nextRowProtoType == null) {
								nextRowProtoType = createProto(currentRow);
							}
							Row nextRow = new Row(nextRowProtoType);
							addQueen(nextRow, i);
							queue.push(nextRow);
						}
					}
				}
			} else {
				failcount++;
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		running=false;
	}

	private Row createProto(Row currentRow) {
		Row row = new Row();
		row.setColumn(currentRow.getColumn() + 1);
		row.setCurrentQueens(currentRow.getCurrentQueens());
		row.setLength(currentRow.getLength());
		row.setCurrentRow(computeNextRow(currentRow));
		return row;
	}

	private byte[] computeNextRow(Row currentRow) {
		byte[] result = new byte[currentRow.getLength()];
		for (int i = 0; i < currentRow.getLength(); i++) {
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
		if (i != row.getLength() - 1) {
			row.getCurrentRow()[i + 1] += 4;
		}
		row.getCurrentQueens()[row.getColumn()-1] = i;
	}

	public boolean isRunning() {
		return running;
	}





}
