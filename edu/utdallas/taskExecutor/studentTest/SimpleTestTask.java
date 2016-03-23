package edu.utdallas.taskExecutor.studentTest;

import edu.utdallas.taskExecutor.Task;

public class SimpleTestTask implements Task 
{
	private static MutableInt mutableInt = new MutableInt();
	
	private String name;
	private boolean started;
	
	public SimpleTestTask(String name)
	{
		this.name = name;
		started = false;
	}
	
	@Override
	public String getName()
	{
		return name;
	}
	
	@Override
    public void execute()
    {
		if(started) {
			throw new RuntimeException("Task started twice " + name);
		}
		else {
			started = true;
		}
		
		try {
	        Thread.sleep(250);
	        
	        // Note that the printed message includes the Thread's unique name. 
	        System.out.println("Hello From Thread: " + Thread.currentThread().getName() + " Task: " + name + ":: numOfActivations: " + mutableInt.increment());
        }
        catch (InterruptedException e) {
	        e.printStackTrace();
        }
    }
}

class MutableInt 
{
	private int count = 0;
	
	synchronized int increment() 
	{
		return ++count;
	}
}
