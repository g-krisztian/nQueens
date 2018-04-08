package nQueens;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RowRunnerServiceJava implements RowRunnerService {
	private ExecutorService rowThreadPool;

	@Override
	public ExecutorService getRowThreadPool() {
		return rowThreadPool;
	}

	private RowResultService resultService;

	public RowRunnerServiceJava() {
		rowThreadPool = Executors.newCachedThreadPool();
	}

	@Override
	public void add(Row row) {
		RowThread rowRunnable = new RowThread();
		rowRunnable.setCurrentRow(row);
		rowRunnable.setResultService(resultService);
		rowRunnable.setRowRunnerService(getRowThreadPool());

		rowThreadPool.execute(rowRunnable);
	}

	@Override
	public void setResultService(RowResultService resultService) {
		this.resultService = resultService;

	}
}
