package nQueens;

public class Row {
	private byte[] currentRow;
	private int[] currentQueens;
	private int length;
	private int column;

	public byte[] getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(byte[] currentRow) {
		this.currentRow = new byte[currentRow.length];
		System.arraycopy(currentRow, 0, this.currentRow, 0, currentRow.length);
	}

	public void setCurrentQueens(int[] currentQueens) {
		this.currentQueens = new int[currentQueens.length];
		System.arraycopy(currentQueens, 0, this.currentQueens, 0, this.length);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getColumn() {
		return column;
	}

	public int[] getCurrentQueens() {
		return currentQueens;
	}

	public void printTable() {
		for (int i = 0; i < column; i++) {
			printQueens(i);
		}
		printCurrent();
		System.out.println();
	}

	private void printQueens(int index) {
		for (int i = 0; i < length; i++) {
			if (currentQueens[index] == i)
				System.out.print("Q ");
			else
				System.out.print("_ ");
		}
		System.out.println();
	}

	private void printCurrent() {

		for (byte b : currentRow) {
			System.out.print(b + " ");
		}
		System.out.println();
	}

}
