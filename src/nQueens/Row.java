package nQueens;

public class Row {
	private byte[] currentRow;
	private int[] currentQueens;
	private int length;
	private int column;
	
	public Row() {
		
	}

	public Row(Row row) {
		this.length = row.getLength();
		this.column =row.getColumn();
		this.currentRow = new byte[length];
		this.currentQueens = new int[length];

		System.arraycopy(row.getCurrentRow(),0 , this.currentRow, 0, length);
		System.arraycopy(row.getCurrentQueens(),0,this.currentQueens,0,length);
		
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

	public void setCurrentRow(byte[] currentRow) {
		this.currentRow = currentRow;
	}

	public void setCurrentQueens(int[] currentQueens) {
		this.currentQueens = currentQueens;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public byte[] getCurrentRow() {
		return currentRow;
	}

	public int getLength() {
		return length;
	}

	public int getColumn() {
		return column;
	}

	public int[] getCurrentQueens() {
		return currentQueens;
	}

}
