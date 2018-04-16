package nQueens;

import java.util.List;

public interface RowRunnerService {

	void add(Row row);

	int getRunning();

	void start();

	void setResultService(RowResultService resultService);

	void add(List<Row> compute);

}
