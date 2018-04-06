package nQueens;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RowRunnerServiceJava implements RowRunnerService {
	ConcurrentLinkedQueue<Row> rowQueue;
	private OperatingSystemMXBean mxBean;
	
	
	public RowRunnerServiceJava() {
		rowQueue = new ConcurrentLinkedQueue<>();
		mxBean = ManagementFactory.getOperatingSystemMXBean();
	}


	@Override
	public void add(Row row) {
		rowQueue.add(row);
	}


	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	
	
}
