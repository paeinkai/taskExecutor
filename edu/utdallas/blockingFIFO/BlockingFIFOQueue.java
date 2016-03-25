package edu.utdallas.blockingFIFO;
import edu.utdallas.taskExecutor.*;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingFIFOQueue {
	public final int queueMaxSize = 100;
	Task queue[];
	
	int nextin,nextout;
	int count;
	
	
	
	//TODO: replace with our implementation of BlockingFIFOQueue
	private static ArrayBlockingQueue<Task> tasks;
	
	public BlockingFIFOQueue()
	{
		tasks = new ArrayBlockingQueue<Task>(queueMaxSize);
	}

	public BlockingFIFOQueue(int n)
	{
		if (n < queueMaxSize)
			tasks = new ArrayBlockingQueue<Task>(n);
		else
			tasks = new ArrayBlockingQueue<Task>(queueMaxSize);
	}
	
    public static void put(Task item) throws InterruptedException, NullPointerException {
    	tasks.put(item);
    	
    	
    	
    }
    
    public static Task take() throws InterruptedException {
    	return tasks.take();
    }
}

