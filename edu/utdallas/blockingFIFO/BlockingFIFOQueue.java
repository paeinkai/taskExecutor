package edu.utdallas.blockingFIFO;
import edu.utdallas.taskExecutor.*;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingFIFOQueue {
	public final static int queueMaxSize = 100;
	static Task queue[];
	
	static int nextin,nextout;
	static int count;
	private static Lock lock;
	private static Condition notfull;
	private static Condition notempty;
	
	public BlockingFIFOQueue()
	{
		queue = new Task[queueMaxSize];
		lock = new ReentrantLock();
		notfull = lock.newCondition();
		notempty = lock.newCondition();
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
		count = 0;
		nextin = 0;
		nextout = 0;
	}

    public static void put(Task item) throws InterruptedException {
    	lock.lock();
    	try {
    		while (count == queueMaxSize) 
    			notfull.await();
    		queue[nextin] = item;
    		nextin = (nextin + 1) % queueMaxSize;
    		count++;
    		notempty.signal();
    	} finally {
    		lock.unlock();
    	}
    }
    
    public static Task take() throws InterruptedException {
    	lock.lock();
    	Task t;
    	try {
    		while (count == 0) 
    			notempty.await();
    		t = queue[nextout];
    		nextout = (nextout + 1) % queueMaxSize;
    		count --;
    		notfull.signal();

    		return t;
    	} finally {
    		lock.unlock();
    	}
    }
}

