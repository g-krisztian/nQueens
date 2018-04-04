package nQueen;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class Row implements Runnable {
	private static final int Queen = 7;
	private byte[] previousField;
	private int[] previousQueens;
	int currentRow;
	int lenght;
	ExecutorService es;
	List<int[]> rs;

	public Row(byte[] previousField, int[] previousQueens, int currentRow, ExecutorService es, List<int[]> rs) {
		this.lenght = previousField.length;
		this.es = es;
		this.rs = rs;
		this.previousField = new byte[lenght];
		this.previousQueens = new int[lenght];
		System.arraycopy(previousField, 0, this.previousField, 0, previousField.length);
		System.arraycopy(previousQueens, 0, this.previousQueens, 0, previousQueens.length);
		this.currentRow = currentRow;
	}

	@Override
	public void run() {
		synchronized (es) {
			printQueens();
		}
		if (currentRow < previousField.length -1) {
			byte[] currentField = computeField();
			for (int i = 0; i < currentField.length - 1; i++) {
				if (currentField[i] == 0) {
					createNewRow(currentField, i);
				}
			}
		} else {
			System.out.println("hihi");
			rs.add(previousQueens);
		}

	}

	private void printQueens() {
		System.out.println("Row: " + currentRow);
		System.out.print(" Queens: ");
		for (int b : previousQueens) {
			System.out.print(b + ", ");
		}
		System.out.println();
		System.out.print("Field: ");
		for (byte b : previousField) {
			System.out.print(b + ", ");
		}
		System.out.println();
	}

	private void createNewRow(byte[] currentField, int i) {
		byte[] nextField = new byte[currentField.length];
		System.arraycopy(currentField, 0, nextField, 0, currentField.length);
		nextField[i] = Queen;
		int[] nextQueens = previousQueens.clone();
		System.arraycopy(previousQueens, 0, nextQueens, 0, previousQueens.length);
		nextQueens[currentRow]=i;
		Row next = new Row(nextField, nextQueens, currentRow++, es, rs);
		
	}

	private byte[] computeField() {
		byte[] nextField = new byte[previousField.length];

		for (int i = 0; i < previousField.length; i++) {
			if (i != 0) {
				nextField[i] += previousField[i - 1] & 4;
			}
			nextField[i] += previousField[i] & 2;
			if (i != previousField.length - 1) {
				nextField[i] += previousField[i + 1] & 1;
			}
		}
		return nextField;
	}

}
