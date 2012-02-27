package frc2168.PIDController;

import java.util.TimerTask;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * @author Kevin Harrilal, First Robotics Team 2168
 * <br><br>
 * The PID Speed class implements a PID controller used to perform speed control
 * on a DC motor. The purpose of this class is to keep a DC motor rotating at a constant
 * speed when the correct P, I, and D gains have been chosen.
 * <br><br>
 * The controller implements the parallel form the PID controller.
 * <br><br>
 * In addition to a parallel form PID Controller. This class also
 * implements the following features: Derivative Filtering, Integral Windup
 * Prevention, Gain Scheduling, Dead Band Removal, Error Tolerance, Output Coercion,
 * and many other features to allow for a stable controller.
 * <br><br>
 * This class was intended to work with the 2012 FRC Java Library developed by WPI. This class
 * will not run if the WPI libraries are not installed on the local machine.
 * <br><br>
 * <u>Precondition:</u> An Encoder of the WPI class is instantiated. Proportial, Derivative, and Integral gains are set,
 * and a period in miliseconds in known.
 * <br><br>
 * <u>PostCondition:</u> Once Constructed, a new thread will be spawned which will have a periodic execution rate as specified
 * by the period value. The new thread will be paused and a call to the Objects start() method will allow the thread to start executing
 * the PID loop.
 * <br><br>
 * To use this class is simple, an example is below <br><br>
 * 
 * Encoder leftEncoder= new Encoder(1,2) //Encoder on DIO ports 1 and 2 <br>
 * double P = 1; //P gain <br>
 * double I = 2; //I gain <br>
 * double D = 3; //D gain <br>
 * long period = 40; // 40 millisecond period (note: Type is Long)
 * <br><br>
 * leftEncoder.start(); //start the encoder<br>
 * leftEncoder.reset(); //reset the encoder (not needed but usefull)<br>
 * <br><br>
 * PIDSpeed speedController = new PIDSpeed("DriveTrain Speed Controller", P, I, D, leftEncoder, period); //launch the PID thread <br>
 * speedController.Enable(); //start the PID thread<br>
 * 
 * 
 * <br><br>
 * Multiple instances of the PIDSpeed Object can be created for multiple PID loops to run. Each loop will run in its own thread at the
 * period specified in its constructor.
 *
 * 
 * }
 * 
 * 
 */
public class PIDSpeed
{
	// gains for gain schedule
	private double pGain;
	private double iGain;
	private double dGain;

	private double pGain2;
	private double iGain2;
	private double dGain2;
	
	//internal PID variables
	private double p;
	private double i;
	private double d;
	
	// enable
	private boolean enable;
	
	//enable debug mode
	private boolean debugEnabled;
	
	//isFinished
	private boolean isFinished;
	
	// create local PID portions
	double prop;
	double integ;
	double deriv;

	// Other internal variable
	private boolean enGainSched;
	private boolean enDerivFilter;

	// internal calcs
	private double err; // error
	private double olderr; // oldError
	private double sp; // setpoint
	private double cp; // current position
	private double co; // control output
	private double coNotSaturated; // control output unmodified for graphing
	private double coOld; //Control Output of last iteration
	private double errsum; //total of all errors this loop iteration
	private double olderrsum; //total of all errors last loop iteration

	// timers
	private double clock;
	private double executionTime;

	// deriv filters
	private double filterDerivOld;
	private double r; // between 0 and 1

	// max and min limit variables
	private double maxPosOutput; // max positive output (+1)
	private double maxNegOutput; // max negative output (-1)
	private double minPosOutput; // min positive output, use to get rid of deadband
	private double minNegOutput; // min negative output, use to get rid of deadband

	// acceptable steadyState error
	private double acceptErrorDiff; // allowable error (in units of setpoint)

	// tread executor
	java.util.Timer executor;
	long period;

	// encoder
	Encoder encoder = null;

	// Name of Thread
	String name;
	
	//isfinsh double
	private int SIZE;
	private double[] atSpeed;
	private int count;
	private double percent;
	private double temp =0;

	


/**
 * This is the default constructor for the {@link PIDSpeed} class. All other constructors within
 * this class make a call to this constructor first.
 *
 * This constructor also instantiates the new thread for the PID loop will run in. Although the 
 * PID loop thread has been created, the PID loop will not start running until a call to {@link enable} 
 * has been made.
 * 
 *
 * @param name - type String used to identify this PID Instance and thread i.e "LeftDrivePID"
 * @param P - type double which represents Proportional Gain for the Speed Controller
 * @param I - type double which represents Integral Gain for the Speed Controller
 * @param D - type double which represents Derivative Gain for the Speed Controller
 * @param currentPos - type Encoder Object which is used to reference the encoder object this PID loop will use as feedback
 * @param period - type long which represents the time the thread will execute at in milliseconds. i.e 40 means the loop will execute every 40ms.
 * 
 * @throws NullPointerException if the Encoder object passed to the {@link currentPos} is null;
 */
	public PIDSpeed(String name, double P, double I, double D,
			Encoder currentPos, long period)
	{

		if (currentPos == null)
			throw new NullPointerException("Encoder Object of " + name + " is null");

		//copy values
		this.name = name;
		this.pGain = P;
		this.iGain = I;
		this.dGain = D;
		this.encoder = currentPos; //point to reference of encoder instead of creating new
		this.period = period;
		this.pGain2 = P;
		this.iGain2 = I;
		this.dGain2 = D;

		//disable PID loop
		this.enable = false;
		
		//zero all other parameters
		this.acceptErrorDiff=0;
		this.clock=0;
		this.co=0;
		this.coNotSaturated=0;
		this.coOld=0;
		this.cp=0;
		this.p=0;
		this.i=0;
		this.d=0;
		this.prop=0;
		this.deriv=0;
		this.integ=0;
		this.err=0;
		this.errsum=0;
		this.filterDerivOld=0;
		this.olderr=0;
		this.olderrsum=0;
		this.r=1;
		this.sp=0;
		
		//set Output Limits
		this.maxNegOutput=-1;
		this.maxPosOutput=1;
		this.minNegOutput=0;
		this.minPosOutput=0;
		
		
		//set all booleans to false
		this.debugEnabled=false;
		this.enDerivFilter=false;
		this.enGainSched=false;
		this.isFinished=false;
		
		//at speed size
		this.atSpeed = new double[SIZE];
		this.count=0;
		
		//reset encoder
		this.encoder.reset();
		
		this.executor = new java.util.Timer();
		this.executor.schedule(new PIDSpeedTask(this), 0L, this.period);


	}

	/**
	 * This constructor for the {@link PIDSpeed} class allows the user to set PID gains for gainScheduling.<br><br>
	 * This is handy for when one would like to have separate gains when the Error between the setpoint and the CurrentValue 
	 * is Positive verse Negative. For example this would be useful if one wished to have separate gains to go forward and reverse on a drivetrain.<br><br>
	 * This constructor also instantiates the new thread for the PID loop will run in. Although the 
	 * PID loop thread has been created, the PID loop will not start running until a call to {@link enable} 
	 * has been made.
	 * 
	 *
	 * @param name - type String used to identify this PID Instance and thread i.e "LeftDrivePID"
	 * @param pUp - type double which represents Proportional Gain for the Speed Controller to use when the Error is greater than zero.
	 * @param iUp - type double which represents Integral Gain for the Speed Controller to use when the Error is greater than zero.
	 * @param dUp - type double which represents Derivative Gain for the Speed Controller to use when the Error is greater than zero.
	 * @param pDown - type double which represents Proportional Gain for the Speed Controller to use when the Error is less than zero.
	 * @param iDown - type double which represents Integral Gain for the Speed Controller to use when the Error is less than zero.
	 * @param dDown - type double which represents Derivative Gain for the Speed Controller to use when the Error is less than zero.

	 * @param currentPos - type Encoder Object which is used to reference the encoder object this PID loop will use as feedback
	 * @param period - type long which represents the time the thread will execute at in milliseconds. i.e 40 means the loop will execute every 40ms.
	 * 
	 * @throws NullPointerException if the Encoder object passed to the {@link currentPos} is null;
	 */
	
	public PIDSpeed(String name, double pUp, double iUp, double dUp,
			double pDown, double iDown, double dDown, Encoder currentPos,
			long period)
	{
		this(name, pUp, iUp, dUp, currentPos, period);
		this.pGain2 = pDown;
		this.iGain2 = iDown;
		this.dGain2 = dDown;
		this.enGainSched=true;

	}

	/**
	 * @return the Propotional Gain in type double
	 */
	public synchronized double getpGain()
	{
		return pGain;
	}

	/**
	 * @return the Integral Gain in type double
	 */
	public synchronized double getiGain()
	{
		return iGain;
	}

	/**
	 * @return the Integral Gain in type double
	 */
	public synchronized double getdGain()
	{
		return dGain;
	}

	/**
	 * @return If Gain Scheduling is enabled this will return the Proportional Gain to be used when the error is less than zero
	 */
	public synchronized double getpGain2()
	{
		return pGain2;
	}

	/**
	 * @return If Gain Scheduling is enabled this will return the Integral Gain to be used when the error is less than zero
	 */
	public synchronized double getiGain2()
	{
		return iGain2;
	}

	/**
	 * @return If Gain Scheduling is enabled this will return the Derivative Gain to be used when the error is less than zero
	 */
	public synchronized double getdGain2()
	{
		return dGain2;
	}

	/**
	 * @return the boolean flag indicating if gain Scheduling is enabled. Gain scheduling is enabled when true, if false gain scheduling is not enabled. Gain Scheduling is set to false by default.
	 */
	public synchronized boolean isEnGainSched()
	{
		return enGainSched;
	}
	
	/**
	 * @return the boolean flag indicating if the PID loop is enabled. PID is enabled when true, if false gain scheduling is not enabled. Gain Scheduling is set to false by default.
	 */
	public synchronized boolean isEnabled()
	{
		return enable;
	}

	/**
	 * @return the boolean flag indicating if debugged output is enabled. debugging is enabled when true, if false debugging is not enabled. debugging is set to false by default.
	 */
	public synchronized boolean isDebugEnabled()
	{
		return debugEnabled;
	}

	/**
	 * @return the boolean flag indicating if Derivative filtering is enabled. Derivative filtering is enabled when true, if false Derivative filtering is not enabled. Derivative filtering is set to false by default.
	 */
	public synchronized boolean isEnDerivFilter()
	{
		return enDerivFilter;
	}

	/**
	 * @return the current Error between the current velocity and the setpoint velocity
	 */
	public synchronized double getErr()
	{
		return err;
	}

	/**
	 * @return the current setpoint the PID controller is to achieve
	 */
	public synchronized double getSp()
	{
		return sp;
	}

	/**
	 * @return the encoder rate which represents the current speed of the device being controllerd
	 */
	public synchronized double getCp()
	{
		return cp;
	}

	/**
	 * @return The controller output value. This is the output to use to drive the motors based on the PID control.
	 */
	public synchronized double getCo()
	{
		return co;
	}

	/**
	 * @return This is for debugging purposed only and should not be used to drive an actual device. This returns the raw controller
	 * output before any filtering is done. The values returned may very well be higher than the values your device can handle and
	 * can command it to dangerous levels. This is for debugging purposes only. <br><br> Use {@link getCo} method to drive a device based on controller output.
	 */
	public synchronized double getCoNotSaturated()
	{
		return coNotSaturated;
	}

	/**
	 * @return the clock value
	 */
	public synchronized double getClock()
	{
		return clock;
	}

	/**
	 * @return the executionTime
	 */
	public synchronized double getExecutionTime()
	{
		return executionTime;
	}

	/**
	 * @return the filterDerivOld
	 */
	public synchronized double getFilterDerivOld()
	{
		return filterDerivOld;
	}

	/**
	 * @return the r
	 */
	public synchronized double getR()
	{
		return r;
	}

	/**
	 * @return the maxPosOutput
	 */
	public synchronized double getMaxPosOutput()
	{
		return maxPosOutput;
	}

	/**
	 * @return the maxNegOutput
	 */
	public synchronized double getMaxNegOutput()
	{
		return maxNegOutput;
	}

	/**
	 * @return the minPosOutput
	 */
	public synchronized double getMinPosOutput()
	{
		return minPosOutput;
	}

	/**
	 * @return the minNegOutput
	 */
	public synchronized double getMinNegOutput()
	{
		return minNegOutput;
	}

	/**
	 * @return the acceptErrorDiff
	 */
	public synchronized double getAcceptErrorDiff()
	{
		return acceptErrorDiff;
	}

	/**
	 * @return the period
	 */
	public synchronized long getPeriod()
	{
		return period;
	}

	/**
	 * @return the encoderValue
	 */
	public synchronized double getEncoderRate()
	{
		// return copy of value
		Double encVal = new Double(encoder.getRate());
		return encVal.doubleValue();
	}

	/**
	 * @param pGain
	 *            the pGain to set
	 */
	public synchronized void setpGain(double pGain)
	{
		this.pGain = pGain;
	}

	/**
	 * @param iGain
	 *            the iGain to set
	 */
	public synchronized void setiGain(double iGain)
	{
		this.iGain = iGain;
	}

	/**
	 * @param dGain
	 *            the dGain to set
	 */
	public synchronized void setdGain(double dGain)
	{
		this.dGain = dGain;
	}

	/**
	 * @param pGain2
	 *            the pGain2 to set
	 */
	public synchronized void setpGain2(double pGain2)
	{
		this.pGain2 = pGain2;
	}

	/**
	 * @param iGain2
	 *            the iGain2 to set
	 */
	public synchronized void setiGain2(double iGain2)
	{
		this.iGain2 = iGain2;
	}

	/**
	 * @param dGain2
	 *            the dGain2 to set
	 */
	public synchronized void setdGain2(double dGain2)
	{
		this.dGain2 = dGain2;
	}

	/**
	 * @param enable
	 *            the enable to set
	 */
	public  void Enable()
	{
	
		this.enable = true;
	}
	
	public  void Pause()
	{

		//disable PID loop
		this.enable = false;
		
		this.isFinished=false;

		reset();
		
		//give up CPU
		Thread.yield();
	}
	
	public void reset()
	{
		this.isFinished=false;

		//zero all other parameters
		this.acceptErrorDiff=0;
		this.clock=0;
		this.co=0;
		this.coNotSaturated=0;
		this.coOld=0;
		this.cp=0;
		this.prop=0;
		this.deriv=0;
		this.integ=0;
		this.err=0;
		this.errsum=0;
		this.filterDerivOld=0;
		this.olderr=0;
		this.olderrsum=0;
		
		
	}
	
	public synchronized void enDebug()
	{
		this.debugEnabled = true;
	}

	public synchronized void disableDebug()
	{
		this.debugEnabled = false;
	}

	/**
	 * @param enGainSched
	 *            the enGainSched to set
	 */
	public synchronized void setEnGainSched(boolean enGainSched)
	{
		this.enGainSched = enGainSched;
	}

	/**
	 * @param enDerivFilter
	 *            the enDerivFilter to set
	 */
	public synchronized void setEnDerivFilter(boolean enDerivFilter)
	{
		this.enDerivFilter = enDerivFilter;
	}

	/**
	 * @param sp
	 *            the sp to set
	 */
	public synchronized void setSp(double sp)
	{
		this.sp = sp;
	}

	/**
	 * @param filterDerivOld
	 *            the filterDerivOld to set
	 */
	public synchronized void setFilterDerivOld(double filterDerivOld)
	{
		this.filterDerivOld = filterDerivOld;
	}

	/**
	 * @param r
	 *            the r to set
	 */
	public synchronized void setR(double r)
	{
		this.r = r;
	}

	/**
	 * @param maxPosOutput
	 *            the maxPosOutput to set
	 */
	public synchronized void setMaxPosOutput(double maxPosOutput)
	{
		this.maxPosOutput = maxPosOutput;
	}

	/**
	 * @param maxNegOutput
	 *            the maxNegOutput to set
	 */
	public synchronized void setMaxNegOutput(double maxNegOutput)
	{
		this.maxNegOutput = maxNegOutput;
	}

	/**
	 * @param minPosOutput
	 *            the minPosOutput to set
	 */
	public synchronized void setMinPosOutput(double minPosOutput)
	{
		this.minPosOutput = minPosOutput;
	}

	/**
	 * @param minNegOutput
	 *            the minNegOutput to set
	 */
	public synchronized void setMinNegOutput(double minNegOutput)
	{
		this.minNegOutput = minNegOutput;
	}

	/**
	 * @param acceptErrorDiff
	 *            the acceptErrorDiff to set
	 */
	public synchronized void setAcceptErrorDiff(double acceptErrorDiff)
	{
		this.acceptErrorDiff = acceptErrorDiff;
	}

	/**
	 * @param encoder
	 *            the encoder to set
	 */
	public synchronized void setEncoder(Encoder encoder)
	{
		// encoder points to reference encoder
		this.encoder = encoder;
	}
	
	public synchronized int getSIZE()
	{
		return SIZE;
	}

	public synchronized void setSIZE(int sIZE)
	{
		this.SIZE = sIZE;
		this.atSpeed = new double[SIZE];
	}

	public synchronized void setPercent(double percent)
	{
		this.percent = percent;
	}

	public synchronized double getPercent()
	{
		return percent;
	}

	/**
	 * @return the name
	 */
	public synchronized String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public synchronized void setName(String name) {
		this.name = name;
	}
	
	public boolean atSpeed()
	{
		
		return this.isFinished;
	}
	
	private void isFinished()
	{
		
		if (count==this.atSpeed.length)
			count=0;
		
		atSpeed[count]=co;
		count++;
		
		int inRange=0;
		for(int i=0,j=1; j<atSpeed.length; i++,j++)
		{
			if (Math.abs(atSpeed[j]) < Math.abs(atSpeed[i]+atSpeed[i]*percent) && Math.abs(atSpeed[j]) > Math.abs(atSpeed[i]-atSpeed[i]*percent))
			 inRange++;
			else
				inRange=0;
		}
		if (inRange==atSpeed.length-1)
		isFinished=true;
		else
		{
			isFinished=false;
			temp=0;
		}

	}
	

	private void debug()
	{
	
		
		SmartDashboard.putDouble(this.name+"_expected Period", this.period);
		SmartDashboard.putDouble(this.name+"_execcution Time", this.executionTime);
		SmartDashboard.putDouble(this.name+"_output", this.co);
		SmartDashboard.putDouble(this.name+"_error", this.err);
		SmartDashboard.putDouble(this.name+"_Prop Term", this.prop);
		SmartDashboard.putDouble(this.name+"_Integ Term", this.integ);
		SmartDashboard.putDouble(this.name+"_Deriv Term", this.deriv);
		SmartDashboard.putDouble(this.name+"_Error Sum", this.errsum);
		SmartDashboard.putDouble(this.name+"_CO Unsaturated", this.coNotSaturated);
		SmartDashboard.putDouble(this.name+"_P_Used", this.p);
		SmartDashboard.putDouble(this.name+"_I_Used", this.i);
		SmartDashboard.putDouble(this.name+"_D_Used", this.d);
		
		SmartDashboard.putDouble(this.name+"_Encoder Rate", this.cp);
		SmartDashboard.putDouble(this.name+"_setPoint", this.sp);
		
		SmartDashboard.putDouble(this.name+"_max Pos Output", this.maxPosOutput);
		SmartDashboard.putDouble(this.name+"_max Neg Output", this.maxNegOutput);
		SmartDashboard.putDouble(this.name+"_min Pos Output", this.minPosOutput);
		SmartDashboard.putDouble(this.name+"_min Neg Output", this.minNegOutput);
		
		SmartDashboard.putDouble(this.name+"_deriv Filter Constant", this.r);
		SmartDashboard.putDouble(this.name+"_acceptable Err", this.acceptErrorDiff);
		
		//boolean dashboard
		SmartDashboard.putBoolean(this.name+"_PID Enabled", this.enable);
		SmartDashboard.putBoolean(this.name+"_debug Enabled", this.debugEnabled);
		SmartDashboard.putBoolean(this.name+"_deriv Filter Enabled", this.enDerivFilter);
		SmartDashboard.putBoolean(this.name+"_Gain Sched Enabled", this.enGainSched);
		SmartDashboard.putBoolean(this.name+"_is Finished", this.isFinished);	
	}
	

	/**
	 * Method runs a PID loop. This method should not be called directly. This
	 * method should only be called from a PIDSpeedTask object which runs this
	 * method in a periodic thread.
	 */
	private synchronized void calculate()
	{
		

		if (enable)
		{
			// poll encoder
			if (encoder == null)
			{
				throw new NullPointerException(" Feedback Encoder was null");
			}
			cp = encoder.getRate();// cp is in units distance per second (i.e
									// inches/sec)
			//System.out.println(cp);
			//SmartDashboard.putDouble(name + "_Rate:", cp);
			//System.out.println("I am a thread" + name + " and execution # " + count++ + " and clock is " + executionTime + " encoder rate: " + encoder.getRate());


			// if setpoint is 0, set output to zero
			if (sp == 0)
			{
				co = 0;
			} 
			else // setpoint is not zero... so we do PID calc
			{

				// calculate error between current position and setpoint
				err = sp - cp;

				// if gain schedule has been enabled, make sure we use
				// proper PID gains
				if (enGainSched == true && err < 0)
				{
					p = pGain2;
					i = iGain2;
					d = dGain2;
				} else
				{
					p = pGain;
					i = iGain;
					d = dGain;
				}

				// calculate proportional gain
				prop = p * err;

				// calculate integral gain by summing past errors
				errsum = err + olderrsum;
				integ = i * errsum;
				olderrsum += errsum;

				// calculate derivative gain d/dt
				executionTime = System.currentTimeMillis() - clock; // time
																	// between
																	// loops

				// prevent divide by zero error, by disabiling deriv term
				// if execution time is zero.
				if (executionTime >= 0)
				{
					deriv = d * ((err - olderr) / (executionTime)); // delta
																	// error/delta
				}													// time
				else
				{
					deriv = 0;
				}
				// update clock with current time for next loop
				clock = System.currentTimeMillis();

				// filter derivative noise using euler filter method
				// if filtering is enabled
				double filteredDeriv = 0;
				if (enDerivFilter)
				{
					filteredDeriv = (1 - r) * filterDerivOld + r * deriv;
					filterDerivOld = filteredDeriv;
					deriv = filteredDeriv;
				}

				// calculate new control output based on filtering
				co = prop + integ + deriv;

				// save control output for graphing
				coNotSaturated = co;

				// The below statements allow us to condition the output
				// of our controller so that it perfoms better than
				// a standard PID controller.

				// if there is an integral term we prevent integral windup
				// and clamp the output to the max output value to
				// prevent output saturation
				// clamp output to min and max output value to prevent

				if (i != 0)
				{
					// clamp to max values
					if (co > maxPosOutput)
						integ = maxPosOutput - prop - deriv;
					if (co < maxNegOutput)
						integ = maxNegOutput - prop - deriv;

					// prevent integral windup
					if (co > maxPosOutput)
						errsum = integ / i;
					if (co < maxNegOutput)
						errsum = integ / i;

					// generate new control output based on min and max and
					// integral windup.
					co = prop + integ + deriv;
					olderrsum=errsum;
				} else
				{
					// no integral term so no need to prevent windup
					// we can just clamp to max/min value to prevent
					// saturation
					if (co > maxPosOutput)
						co = maxPosOutput;
					if (co < maxNegOutput)
						co = maxNegOutput;
				}

				// check to see if we met our setpoint
				// if current value is within exceptable range make control
				// output last
				// output value and stop integrating error
 				if (Math.abs(err) <= acceptErrorDiff)
				{
					co = coOld;
					olderrsum = errsum;
					
				} else
				{
					
					// there is still a significant error
					// we now check if output signal is below
					// the deadband, if it is, we increase the
					// output above deadband
					// to drive the motor

					if (err > 0 && coNotSaturated < minPosOutput
							&& co < (maxPosOutput - minPosOutput))
						co = coOld + prop + integ + deriv;

					if (err < 0 && coNotSaturated < maxNegOutput
							&& co < (maxNegOutput - minNegOutput))
						co = coOld + prop + integ + deriv;

				}
 				

				coOld = co;
				
				//see if setpoint is reached
				isFinished();
				
			}
			//output to SmartDashboard if deBug field is enabled
			if (debugEnabled)
				this.debug();
			
		}
	}

	/**
	 * Private internal class which spawns a new thread for every PID object
	 * 
	 * @author HarrilalEngineering
	 * 
	 */
	private class PIDSpeedTask extends TimerTask
	{
		// internal PIDSpeed object to run in new thread
		private PIDSpeed speedController;


		/**
		 * constructor for the private class PIDSpeedTask, which will be used to
		 * spawn a new thread. Each thread will continuously run the run()
		 * function
		 * 
		 * @param id
		 *            a string used to identify this particular controller
		 * @param controller
		 *            the controller parameters used to create the thread
		 */
		private PIDSpeedTask(PIDSpeed controller)
		{

			if (controller == null)
			{
				throw new NullPointerException(" PIDController was null");
			}
			speedController = controller;
		}

		/**
		 * called periodically in its own thread
		 */
		public void run()
		{
			speedController.calculate();

		}
	}

}
