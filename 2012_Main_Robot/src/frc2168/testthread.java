package frc2168;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.Encoder;

public class testthread 
{

	java.util.Timer timer;
	int i;
	double clock;
	double executionTime;
	Encoder encoder;
	

	/**
	 * main program opens classes.... class constructor spawns threads
	 * @param args
	 */
	public static void main(String[] args)
	{


	}
/**
 * 
 * @param threadNum
 int param constructor for class which 
 */
	public testthread(int threadNum, Encoder encRef)
	{
		encoder=encRef;
		long delay = 2;
		long period = 20;

		timer = new java.util.Timer();
		timer.schedule(new testTimer(this, threadNum), delay, period);
		
	}



	public void calculate(int number)
	{
		i++;
		 
		 executionTime=System.currentTimeMillis() -  clock;
			 
	if (clock!=0)
	{
		
			 System.out.println("I am a thread" + number + " and execution # " + i + " and clock is " + executionTime + " encoder rate: " + encoder.getRate());
	}
			 clock = System.currentTimeMillis();
	
	
	
	}

}

class testTimer extends TimerTask
{
	int i;
	int taskNumber;
	private testthread test;

	//testTimer constructor
	public testTimer(testthread classtest, int number)
	{

		taskNumber = number;
		test=classtest;
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		test.calculate(taskNumber);

	}
}
