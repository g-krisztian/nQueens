package nQueens;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RowRunnerServiceJava implements RowRunnerService {

	private RowResultService resultService;
	private ConcurrentLinkedQueue<RowThread> runners = new ConcurrentLinkedQueue<>();
	private ConcurrentLinkedQueue<Row> queue = new ConcurrentLinkedQueue<>();

	@Override
	public void add(Row row) {
		queue.add(row);
	}

	@Override
	public int getRunning() {
		return queue.size();
	}

	@Override
	public void start() {

		int targetThreads = Runtime.getRuntime().availableProcessors() * 2 + 1;
		while (queue.size() > 0 || runners.size() > 0) {
			while (queue.size() >= 0) {
				cleanThreads();
				if (queue.size() > 0)
					if (runners.size() <= targetThreads)
						newThread();
			}

			// sleep();
		}

	}

	private void cleanThreads() {
		Iterator<RowThread> threadIterator = runners.iterator();
		while (threadIterator.hasNext()) {
			if (!threadIterator.next().isAlive()) {
				threadIterator.remove();
			}
		}
	}

	private void newThread() {
		RowThread thread = new RowThread(queue.poll());
		thread.setResultService(resultService);
		thread.setRowRunnerService(this);
		runners.add(thread);
		thread.start();
	}

	private void sleep() {
		try {
			Thread.sleep(200, 0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public RowResultService getResultService() {
		return resultService;
	}

	@Override
	public void setResultService(RowResultService resultService) {
		this.resultService = resultService;
	}

	public ConcurrentLinkedQueue<Row> getQueue() {
		return queue;
	}

	public void setQueue(ConcurrentLinkedQueue<Row> queue) {
		this.queue = queue;
	}

	@Override
	public void add(List<Row> compute) {
		queue.addAll(compute);

	}

}
