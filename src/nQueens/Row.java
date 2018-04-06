package nQueens;

public class Row {
	private byte[] previousField;
	private int[] previousQueens;
	int length;
	int currentRow;

	public Row() {
	
	}

	public Row(byte[] previousField, int[] previousQueens, int currentRow) {
		this.length = previousField.length;
		this.previousField = new byte[length];
		this.previousQueens = new int[length];
		this.currentRow = currentRow;
		setPreviousField(previousField);
		setPreviousQueens(previousQueens);
	}

	public byte[] getPreviousField() {
		return previousField;
	}

	public void setPreviousField(byte[] previousField) {
		System.arraycopy(previousField, 0, this.previousField, 0, this.length);
	}

	public int[] getPreviousQueens() {
		return previousQueens;
	}

	public void setPreviousQueens(int[] previousQueens) {
		System.arraycopy(previousQueens, 0, this.previousQueens, 0, this.length);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int lenght) {
		this.length = lenght;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

}
