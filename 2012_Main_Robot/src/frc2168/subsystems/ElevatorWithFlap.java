package frc2168.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168.RobotMap;

public class ElevatorWithFlap extends Subsystem {
	Victor lift1;   //2 motors on the lift that get balls and move them up
	Victor lift2;   //both move at the same time
	DoubleSolenoid backFlap; //opens and closes flap at the back bottom end of the elevator
	DigitalInput ballExitSensor;  //sensor that detects ball at the top of the elevator
	
	
	public ElevatorWithFlap() {
		lift1 = new Victor(RobotMap.lift1Victor);
		lift2 = new Victor(RobotMap.lift2Victor);
		backFlap = new DoubleSolenoid(RobotMap.backFlapSolenoidClose , RobotMap.backFlapSolenoidOpen);
		ballExitSensor = new DigitalInput(RobotMap.ballExitSensor);
	}
	
	protected void initDefaultCommand() {
		
		// TODO Auto-generated method stub

	}
	
	/**
	 * set the speed of the lift motors
	 * @param speed is the speed of the motor
	 */
	public void liftBallsElevator(double speed){
		
		lift1.set(speed);
		lift2.set(speed);
		
	}
	
	/**
	 * close the Flap to allow balls to go up the lift
	 */
	public void backFlapClose(){
		backFlap.set(DoubleSolenoid.Value.kReverse);
		
	}
	
	/**
	 * open the flap to prevent balls from going up the lift
	 */
	public void backFlapOpen(){
		backFlap.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * Tells us whether a ball is at the top of the lift
	 * 
	 * @return true if there is a ball at the top of the lift
	 */
	public boolean ballAtExit(){
		return ballExitSensor.get();
	}
}
