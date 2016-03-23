package edu.utdallas.taskExecutorImpl;
import edu.utdallas.taskExecutor.*;
import edu.utdallas.blockingFIFO.*;


public class TaskRunner implements Runnable {
	
	public void run() {
	    while(true) {
	        try {
	        	// take() blocks if queue is empty
		        Task newTask = BlockingFIFOQueue.take();
	            newTask.execute();
	        }
	        catch(Throwable th) {
	           // Log (e.g. print exception’s message to console) 
	           // and drop any exceptions thrown by the task’s
	           // execution.
	        	System.err.println("TaskRunner execution error");
	        }
	    }
	}
}