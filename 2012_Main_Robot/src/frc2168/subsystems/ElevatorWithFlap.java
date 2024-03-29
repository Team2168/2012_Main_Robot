package frc2168.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168.RobotMap;
import frc2168.nPointAveragor;
import frc2168.commands.DriveElevatorJoystick;
import edu.wpi.first.wpilibj.AnalogChannel;

public class ElevatorWithFlap extends Subsystem {
	Victor lift1;   //2 motors on the lift that get balls and move them up
	Victor lift2;   //both move at the same time
	DoubleSolenoid backFlap; //opens and closes flap at the back bottom end of the elevator
	DigitalInput ballExitSensor;  //sensor that detects ball at the top of the elevator
	private AnalogChannel ballDetectorUpper;
	private AnalogChannel ballDetectorLower;
	private nPointAveragor ballDetectorUpperAvg;
	private nPointAveragor ballDetectorLowerAvg;
	int AVERAGER_SIZE = 1;
	
	public ElevatorWithFlap() {
		lift1 = new Victor(RobotMap.lift1Victor);
		lift2 = new Victor(RobotMap.lift2Victor);
		backFlap = new DoubleSolenoid(RobotMap.backFlapSolenoidClose , RobotMap.backFlapSolenoidOpen);
		ballDetectorUpper = new AnalogChannel(RobotMap.ballDetectorUpper);
		ballDetectorLower = new AnalogChannel(RobotMap.ballDetectorLower);
		ballDetectorUpperAvg = new nPointAveragor(AVERAGER_SIZE); //average the ball detector values, window size=3
		ballDetectorLowerAvg = new nPointAveragor(AVERAGER_SIZE);
		//preload the averagers w/ data
		for(int i = 0; i < AVERAGER_SIZE; i++) {
			ballDetectorUpperAvg.putData(ballDetectorUpper.getVoltage());
			ballDetectorLowerAvg.putData(ballDetectorLower.getVoltage());
		}

	}
	
	protected void initDefaultCommand() {
		
		// TODO Auto-generated method stub
		setDefaultCommand(new DriveElevatorJoystick());

	}
	
	/**
	 * Returns the voltage on the ball detection sensor at the top of the lift
	 * 
	 * @return the sensors voltage
	 */
	public double getBallDetectorUpper(){
		double sensorVoltage = ballDetectorUpper.getVoltage();
		
		if(sensorVoltage < 4.0 && sensorVoltage > 0.0){	//dont add in bogus data points
			ballDetectorUpperAvg.putData(ballDetectorUpper.getVoltage());
		}
		
		return ballDetectorUpperAvg.getAverage();
	}
	
	public double getBallDetectorLower(){
		double sensorVoltage = ballDetectorLower.getVoltage();
		
		if(sensorVoltage < 4.0 && sensorVoltage > 0.0){	//dont add in bogus data points
			ballDetectorLowerAvg.putData(ballDetectorLower.getVoltage());
		}
		
		return ballDetectorLowerAvg.getAverage();
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
