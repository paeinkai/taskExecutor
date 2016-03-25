package edu.utdallas.taskExecutorImpl;

import java.util.ArrayList;
import java.util.List;

import edu.utdallas.blockingFIFO.BlockingFIFOQueue;
import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;

public class TaskExecutorImpl implements TaskExecutor
{
	@SuppressWarnings("unused")
	private static BlockingFIFOQueue bfqueue;
	private List<TaskRunner> runnerPool;
	
	public TaskExecutorImpl(int n) {
		bfqueue = new BlockingFIFOQueue();
		
		runnerPool = new ArrayList<TaskRunner>();
		
		for (int i=0; i<n; i++) {
			TaskRunner taskrunner = new TaskRunner();
			Thread newThread = new Thread(taskrunner);
			newThread.setName("TaskRunner"+i);
			runnerPool.add(taskrunner);
			newThread.start();
		}
	}
	
	@Override
	public void addTask(Task task)
	{
		try {
			BlockingFIFOQueue.put(task);
		} catch (InterruptedException ex)
		{
			System.err.println("Interrupted Exception adding: " + task.getName() + "\n" + ex.getMessage());
		} catch (NullPointerException ex)
		{
			System.err.println("NullPointerException adding task");
			ex.printStackTrace();
		}
	}
	


}
