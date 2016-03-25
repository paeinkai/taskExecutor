package edu.utdallas.blockingFIFO;
import edu.utdallas.taskExecutor.*;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingFIFOQueue {
	public final int queueSize = 100;
	//int tasks[] = new int[queueSize];
	
	//TODO: replace with our implementation of BlockingFIFOQueue
	private static ArrayBlockingQueue<Task> tasks;
	
	public BlockingFIFOQueue()
	{
		tasks = new ArrayBlockingQueue<Task>(queueSize);
	}
	
    public static void put(Task item) throws InterruptedException, NullPointerException {
    	tasks.put(item);
    }
    
    public static Task take() throws Exception {
    	return tasks.take();
    }
}

