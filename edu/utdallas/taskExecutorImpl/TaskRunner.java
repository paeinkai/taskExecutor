package edu.utdallas.taskExecutorImpl;
import edu.utdallas.taskExecutor.*;
import edu.utdallas.blockingFIFO.*;


public class TaskRunner implements Runnable {

	
	@Override
	public void run() {
	    while(true) {
	        try {
		        Task newTask = BlockingFIFOQueue.take();		        
	            newTask.execute();
	        }
	        catch(Throwable th) {
	        	System.err.println("TaskRunner execution error");
	        }
	    }
	}
	
}
