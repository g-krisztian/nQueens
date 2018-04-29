package nQueens;

public class SimpleConcurentFifo {

	private class Block {
		Row row;
		Block next;
	}

	private int size;
	private Block head;
	private Object lock = new Object();

	public SimpleConcurentFifo() {
		head = new Block();
		head.next = null;
		head.row = null;
		size = 0;
	}

	public Row poll() {
		synchronized (lock) {
			if (head.row == null)
				return null;
			Row result = head.row;
			head = head.next;
			size--;
			return result;

		}
	}

	public void push(Row nextRow) {
		synchronized (lock) {
			Block next = new Block();
			next.row = nextRow;
			next.next = head;
			head = next;
			size++;
		}
	}

	public int size() {
		return size;
	}

}
