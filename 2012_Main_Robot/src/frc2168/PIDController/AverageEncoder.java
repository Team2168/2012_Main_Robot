package frc2168.PIDController;

import edu.wpi.first.wpilibj.Encoder;

/**
 * Encoder Averager Misspelling intentional. 
 *
 * @author Kevin Harrilal, Team 2168 The Aluminum Falcons
 *
 */
public class AverageEncoder extends Encoder{
	
	private int averagorSize;
	private double[] averagorArray;
	private int arrayPos = 0;		//Next array position to put values to be averaged
	private double clock;
	private double oldCount;
	
	
	/**
	 * Constructor for end point average class
	 * @param n the size of end point average
	 */
	public AverageEncoder(int channelA, int channelB, boolean reverseDirection, EncodingType encoderType, int averageN){
		
		super(channelA,channelB,reverseDirection,encoderType);
		
		averagorSize = averageN;
		averagorArray = new double[averagorSize];
		clock = 0;
		oldCount=0;
	}
	
	
	/**
	 * returns (gets) Average of last n values sent, as name says.
	 * @return the Average
	 */
	private double getAverage(){
		double holder = 0;
		for(int i = 0; i<averagorSize; i++){
			
			holder+=averagorArray[i];
			
		}
		
		return holder/averagorSize;
		
		
	}
	
	/**
	 * puts data in to array to be averaged, hence the class name and method name. Its like magic but cooler.
	 * @param value the value being inserted into the array to be averaged.
	 */
	
	private void putData(double value){
		
		averagorArray[arrayPos] = value;
		arrayPos++;
		
		if (arrayPos>=averagorSize) {		//Is equal or greater to averagorSize because array is zero indexed. Rolls over index position.
			arrayPos=0;
		}
		
	}
	
	//@Override
	public double getRate()
	{
		putData(super.getRate());
		
		
		// TODO Auto-generated method stub
		return getAverage();
	}
	
	public double getRawRate()
	{
		if (clock==0 || oldCount==0)
			return 0;
		else
		{
			double deltaCount=super.getRaw()-oldCount;
			double deltaTime=System.currentTimeMillis()-clock;
			
			oldCount=super.getRaw();
			clock=System.currentTimeMillis();
			
			//
			return (deltaCount/deltaTime)*1000;
		}
		
	}
	
	
	
	
}
