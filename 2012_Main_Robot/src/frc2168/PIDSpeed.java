package frc2168;


import java.util.TimerTask;

import edu.wpi.first.wpilibj.Encoder;

/**
 * The PID Speed class implements a PID controller used to perform speed control
 * on a DC motor.
 * 
 * The controller implements the parallel form the PID controller.
 * 
 * In addition to a standard parallel form PID Controller. This class also
 * implements the following features: Derivative filtering, Integral Windup
 * prevention, Gain Scheduling, Dead Band Removal, Error Tolerance.
 * 
 * @author HarrilalEngineering
 * 
 */
public class PIDSpeed
{
	//gains for gain schedule
	private double pGain;
	private double iGain;
	private double dGain;

	private double pGain2;
	private double iGain2;
	private double dGain2;
	


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
	private double coOld;
	private double errsum;
	private double olderrsum;

	// timers
	private double clock;

	private double executionTime;
	
	//deriv filters
	private double filterDerivOld;
	private double r;
	
	//max and min limit variables
	private double maxPosOutput; //max positive output (+1)
	private double maxNegOutput; //max negative output (-1)
	private double minPosOutput; //max negative output, use to get rid of deadband
	private double minNegOutput; //max negative output, use to get rid of deadband
	
	
	//acceptable steadyState error
	private double acceptErrorDiff; //allowable error (in units of setpoint)
	
	//tread executor
	java.util.Timer executor;
	long period;
	
	//encoder
	Encoder encoder = null;
	
	/**
	 * No Parameter constructor... inintialized all
	 * paramters to 0, inclusing the PID gains. This PID conroller will not
	 * do anything because all gains are zero. Period is set to 50ms
	 * @return
	 */
	public PIDSpeed()
	{
		
		
	}
	


	/**
	 * @return the pGain
	 */
	public synchronized double getpGain()
	{
		return pGain;
	}



	/**
	 * @return the iGain
	 */
	public synchronized double getiGain()
	{
		return iGain;
	}



	/**
	 * @return the dGain
	 */
	public synchronized double getdGain()
	{
		return dGain;
	}



	/**
	 * @return the pGain2
	 */
	public synchronized double getpGain2()
	{
		return pGain2;
	}



	/**
	 * @return the iGain2
	 */
	public synchronized double getiGain2()
	{
		return iGain2;
	}



	/**
	 * @return the dGain2
	 */
	public synchronized double getdGain2()
	{
		return dGain2;
	}



	/**
	 * @return the enGainSched
	 */
	public synchronized boolean isEnGainSched()
	{
		return enGainSched;
	}



	/**
	 * @return the enDerivFilter
	 */
	public synchronized boolean isEnDerivFilter()
	{
		return enDerivFilter;
	}



	/**
	 * @return the err
	 */
	public synchronized double getErr()
	{
		return err;
	}



	/**
	 * @return the sp
	 */
	public synchronized double getSp()
	{
		return sp;
	}



	/**
	 * @return the cp
	 */
	public synchronized double getCp()
	{
		return cp;
	}



	/**
	 * @return the co
	 */
	public synchronized double getCo()
	{
		return co;
	}



	/**
	 * @return the coNotSaturated
	 */
	public synchronized double getCoNotSaturated()
	{
		return coNotSaturated;
	}



	/**
	 * @return the clock
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
		//return copy of value
		Double encVal=new Double(encoder.getRate());
		return  encVal.doubleValue();
	}



	/**
	 * @param pGain the pGain to set
	 */
	public synchronized void setpGain(double pGain)
	{
		this.pGain = pGain;
	}



	/**
	 * @param iGain the iGain to set
	 */
	public synchronized void setiGain(double iGain)
	{
		this.iGain = iGain;
	}



	/**
	 * @param dGain the dGain to set
	 */
	public synchronized void setdGain(double dGain)
	{
		this.dGain = dGain;
	}



	/**
	 * @param pGain2 the pGain2 to set
	 */
	public synchronized void setpGain2(double pGain2)
	{
		this.pGain2 = pGain2;
	}



	/**
	 * @param iGain2 the iGain2 to set
	 */
	public synchronized void setiGain2(double iGain2)
	{
		this.iGain2 = iGain2;
	}



	/**
	 * @param dGain2 the dGain2 to set
	 */
	public synchronized void setdGain2(double dGain2)
	{
		this.dGain2 = dGain2;
	}



	/**
	 * @param enGainSched the enGainSched to set
	 */
	public synchronized void setEnGainSched(boolean enGainSched)
	{
		this.enGainSched = enGainSched;
	}



	/**
	 * @param enDerivFilter the enDerivFilter to set
	 */
	public synchronized void setEnDerivFilter(boolean enDerivFilter)
	{
		this.enDerivFilter = enDerivFilter;
	}



	/**
	 * @param sp the sp to set
	 */
	public synchronized void setSp(double sp)
	{
		this.sp = sp;
	}



	/**
	 * @param filterDerivOld the filterDerivOld to set
	 */
	public synchronized void setFilterDerivOld(double filterDerivOld)
	{
		this.filterDerivOld = filterDerivOld;
	}



	/**
	 * @param r the r to set
	 */
	public synchronized void setR(double r)
	{
		this.r = r;
	}



	/**
	 * @param maxPosOutput the maxPosOutput to set
	 */
	public synchronized void setMaxPosOutput(double maxPosOutput)
	{
		this.maxPosOutput = maxPosOutput;
	}



	/**
	 * @param maxNegOutput the maxNegOutput to set
	 */
	public synchronized void setMaxNegOutput(double maxNegOutput)
	{
		this.maxNegOutput = maxNegOutput;
	}



	/**
	 * @param minPosOutput the minPosOutput to set
	 */
	public synchronized void setMinPosOutput(double minPosOutput)
	{
		this.minPosOutput = minPosOutput;
	}



	/**
	 * @param minNegOutput the minNegOutput to set
	 */
	public synchronized void setMinNegOutput(double minNegOutput)
	{
		this.minNegOutput = minNegOutput;
	}



	/**
	 * @param acceptErrorDiff the acceptErrorDiff to set
	 */
	public synchronized void setAcceptErrorDiff(double acceptErrorDiff)
	{
		this.acceptErrorDiff = acceptErrorDiff;
	}



	/**
	 * @param encoder the encoder to set
	 */
	public synchronized void setEncoder(Encoder encoder)
	{
		//encoder points to reference encoder
		this.encoder = encoder;
	}



	/**
	 * Method runs a PID loop. This method should not be called directly. This method
	 * should only be called from a PIDSpeedTask object which runs this method
	 * in a periodic thread.
	 */
	private void calculate()
	{
		
		//poll encoder
		if (encoder == null)
		{
			throw new NullPointerException(" Feedback Encoder was null");
		}
		cp=encoder.getRate();//cp is in units distance per second (i.e inches/sec)
		
		
		
		// create local PID gains
		double p = 0.0;
		double i = 0.0;
		double d = 0.0;


		// if setpoint is 0, set output to zero
		if (sp == 0)
		{
			co = 0;
		} else
		// setpoint is not zero... so we do PID calc
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
			executionTime = System.currentTimeMillis() - clock; // time between loops
														
			//prevent divide by zero error, by disabiling deriv term
			//if execution time is zero.
			if (executionTime != 0)
				deriv = d * ((err - olderr) / (executionTime)); //delta error/delta time
			else
				deriv = 0;
			
			//update clock with current time for next loop
			clock = System.currentTimeMillis();

			
			// filter derivative noise using euler filter method
			// if filtering is enabled
			double filteredDeriv = 0;
			if (enDerivFilter)
			{
				filteredDeriv = (1 - r) * filterDerivOld + r * deriv;
				filterDerivOld=filteredDeriv;
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
			// if current value is within exceptable range make control output last
			// output value and stop integrating error
			if (Math.abs(err) <= acceptErrorDiff)
			{
				co = coOld;
				errsum = olderrsum;
			} else
			{
				// there is still a significant error
				// we now check if output signal is below
				// the deadband, if it is, we increase the
				// output above deadband
				// to drive the motor

				if (err > 0 && coNotSaturated < maxPosOutput//this used to be min?
						&& co < (maxPosOutput - minPosOutput))
					co = coOld + prop + integ + deriv;

				if (err < 0 && coNotSaturated < maxNegOutput
						&& co < (maxNegOutput - minNegOutput))
					co = coOld + prop + integ + deriv;

			}
            coOld=co;

		}
	}

	
	/**
	 * Private internal class which spawns a new thread 
	 * for every PID object
	 * @author HarrilalEngineering
	 *
	 */
	private class PIDSpeedTask extends TimerTask
	{
		//internal PIDSpeed object to run in new thread
		private PIDSpeed speedController;
		
		//string to identify each thread
		private String controllerName;

		/**
		 * constructor for the private class PIDSpeedTask, which will be used to
		 * spawn a new thread. Each thread will continuously run the run() function
		 * @param id a string used to identify this particular controller
		 * @param controller the controller parameters used to create the thread
		 */
		private PIDSpeedTask(String id, PIDSpeed controller)
		{
			
			controllerName=id;
			
			if (controller == null)
			{
				throw new NullPointerException(controllerName + " PIDController was null");
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
