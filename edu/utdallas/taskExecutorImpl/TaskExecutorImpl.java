package edu.utdallas.taskExecutorImpl;

import java.util.List;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;

public class TaskExecutorImpl implements TaskExecutor
{

	public TaskExecutorImpl(int n) {
		for (int i=0; i<n; i++) {
//			Thread.setName(i);
		}
	}
	
	@Override
	public void addTask(Task task)
	{
		// TODO Complete the implementation
	}
	
	private List<TaskRunner> runnerPool() {
		return null;
	}

}
