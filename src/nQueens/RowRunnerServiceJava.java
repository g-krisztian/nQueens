package nQueens;

import java.util.concurrent.ConcurrentSkipListSet;

public class RowRunnerServiceJava implements RowRunnerService {
	
	private RowResultService resultService;
	
	ConcurrentSkipListSet<Row> queue = new ConcurrentSkipListSet<>();
	RowThread[] runners;

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
		runners = new RowThread[Runtime.getRuntime().availableProcessors()];
		for (RowThread thread : runners) {
			thread.setResultService(resultService);
			thread.setRowRunnerService(this);

		}
		try {
			wait(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean isRunning=true;
		
		while (isRunning) {
			
		}
		
	}

	public RowResultService getResultService() {
		return resultService;
	}

	public void setResultService(RowResultService resultService) {
		this.resultService = resultService;
	}

	public ConcurrentSkipListSet<Row> getQueue() {
		return queue;
	}

	public void setQueue(ConcurrentSkipListSet<Row> queue) {
		this.queue = queue;
	}

	public RowThread[] getRunners() {
		return runners;
	}

	public void setRunners(RowThread[] runners) {
		this.runners = runners;
	}

}
