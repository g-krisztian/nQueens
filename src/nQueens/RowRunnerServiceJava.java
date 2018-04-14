package nQueens;

import java.util.concurrent.ExecutorService;

public class RowRunnerServiceJava implements RowRunnerService{
	private ExecutorService rowThreadPool;
	Long runningThreads; 

	public RowRunnerServiceJava(ExecutorService executorService) {
		rowThreadPool = executorService;
	}

	@Override
	public void add(Row row) {
		// TODO Auto-generated method stub
		
	}

}
