package edu.utdallas.blockingFIFO;
import edu.utdallas.taskExecutor.*;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingFIFOQueue {
	public final int queueMaxSize = 100;
	static Task queue[];
	
	static int nextin,nextout;
	static int count;
	private static Object notfull;
	private static Object notempty;
	
	
	public BlockingFIFOQueue()
	{
		queue = new Task[queueMaxSize];
		init();
	}

	public BlockingFIFOQueue(int n)
	{
		if (n <= queueMaxSize)
			queue = new Task[n];
		else
			queue = new Task[queueMaxSize];
		init();
	}
	
	private void init()
	{
		this.count = 0;
		this.nextin = 0;
		this.nextout = 0;
		notfull = new Object();
		notempty = new Object();
	}
	
    public static void put(Task item) throws InterruptedException, NullPointerException {
    	
    	synchronized (notfull)
    	{
    		while (count == queue.length)
    		{
    			notfull.wait();
    		}
    		queue[nextin] = item;
    		nextin = ++nextin % queue.length;
    	}
    	synchronized (notempty)
    	{
    		if (count++ == 0)
    		{
    			notempty.notifyAll();
    		}
    	}
    	
    }
    
    public static Task take() throws InterruptedException {
    	Task result = null;
    	
    	synchronized (notempty)
    	{
    		while (count == 0)
    		{
    			notempty.wait();
    		}
    		result = queue[nextout];
    		nextout = ++nextout % queue.length;
    	}
    	synchronized (notfull)
    	{
    		if (count-- == queue.length)
    		{
    			notfull.notifyAll();
    		}
    	}

    	return result;
    }
}

