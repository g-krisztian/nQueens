package nQueens;

public class Row {
	private static final byte QUEEN = 7;
	private byte[] currentRow;
	private int[] currentQueens;
	private int length;
	private int column;

	public void addQueen(int index) {
		currentRow[index] = QUEEN;
		currentQueens[column] = index;
	}

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

}
