package frc2168.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168.RobotMap;
import frc2168.nPointAveragor;
import frc2168.commands.DriveElevatorJoystick;
import edu.wpi.first.wpilibj.AnalogChannel;

public class ElevatorWithFlap extends Subsystem {
	Victor lift1;   //2 motors on the lift that get balls and move them up
	Victor lift2;   //both move at the same time
	DoubleSolenoid backFlap; 					//opens and closes flap at the back bottom end of the elevator
	//DigitalInput ballExitSensor;  			//sensor that detects ball at the top of the elevator
	private AnalogChannel topBallDetector;		//sensor that detects ball at the top of the elevator
	private nPointAveragor topBallDetectorAvg;	//running average of the top ball detect sensor voltage
	private AnalogChannel bottomBallDetector;	//sensor that detects ball made it into the lift
	private nPointAveragor bottomBallDetectorAvg;//running average of the bot. ball detect sensor voltage
	private Relay ballPresentLight;				//light at back of bot to indicate a ball is present
	
	public ElevatorWithFlap() {
		lift1 = new Victor(RobotMap.lift1Victor);
		lift2 = new Victor(RobotMap.lift2Victor);
		backFlap = new DoubleSolenoid(RobotMap.backFlapSolenoidClose , RobotMap.backFlapSolenoidOpen);
		
		// initialize ball detection sensors
		topBallDetector = new AnalogChannel(1);
		topBallDetectorAvg = new nPointAveragor(3);  //average the ball detector values
		bottomBallDetector = new AnalogChannel(2);
		bottomBallDetectorAvg = new nPointAveragor(3); //average the ball detector values
		
		//preload the topBall averager w/ data
		topBallDetectorAvg.putData(topBallDetector.getVoltage());
		topBallDetectorAvg.putData(topBallDetector.getVoltage());
		topBallDetectorAvg.putData(topBallDetector.getVoltage());
		
		ballPresentLight = new Relay(RobotMap.ballPresentLight);
	}
	
	protected void initDefaultCommand() {
		
		setDefaultCommand(new DriveElevatorJoystick());

	}
	
	/**
	 * Turn the light at the back of the robot on and off
	 * 
	 * @param light the state of the light, true = on
	 */
	public void setBackLight(boolean light){
		if(light){
			ballPresentLight.set(Relay.Value.kOn);
		} else {
			ballPresentLight.set(Relay.Value.kOff);
		}
	}
	
	/**
	 * Returns the voltage on the ball detection sensor at the top of the lift
	 * 
	 * @return the sensors voltage
	 */
	public double getTopBallDetector(){
		double sensorVoltage = topBallDetector.getVoltage();
		
		if(sensorVoltage < 4.0 && sensorVoltage > 0.0){	//dont add in bogus data points
			topBallDetectorAvg.putData(topBallDetector.getVoltage());
		}
		
		return topBallDetectorAvg.getAverage();
	}
	
	/**
	 * Returns the voltage on the ball detection sensor at the top of the lift
	 * 
	 * @return the sensors voltage
	 */
	public double getBottomBallDetector(){
		double sensorVoltage = bottomBallDetector.getVoltage();
		
		if(sensorVoltage < 4.0 && sensorVoltage > 0.0){	//don't add in bogus data points
			bottomBallDetectorAvg.putData(bottomBallDetector.getVoltage());
		}
		
		return bottomBallDetectorAvg.getAverage();
	}
	
	/**
	 * set the speed of the lift motors
	 * @param speed is the speed of the motor
	 */
	public void setBallElevatorSpeed(double speed){
		
		lift1.set(speed);
		lift2.set(speed);
		
	}
	
	/**
	 * close the Flap to allow balls to go up the lift
	 */
	public void backFlapClose(){
		backFlap.set(DoubleSolenoid.Value.kReverse);
		
	}
	
	public boolean isClose(){
		return backFlap.get()==DoubleSolenoid.Value.kReverse;
	}
	
	
	/**
	 * open the flap to prevent balls from going up the lift
	 */
	public void backFlapOpen(){
		backFlap.set(DoubleSolenoid.Value.kForward);
	}

	
	public boolean isOpen(){
		return backFlap.get()==DoubleSolenoid.Value.kForward;
	}
	

}
