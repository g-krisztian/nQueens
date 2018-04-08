package nQueens;

import java.util.concurrent.ExecutorService;

public interface RowRunnerService {

	void add(Row row);


	void setResultService(RowResultService resultService);


	ExecutorService getRowThreadPool();

}
