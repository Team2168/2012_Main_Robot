package frc2168.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168.RobotMap;
import frc2168.commands.DriveElevatorJoystick;

public class ElevatorWithFlap extends Subsystem {
	Victor lift1;   //2 motors on the lift that get balls and move them up
	Victor lift2;   //both move at the same time
	DoubleSolenoid backFlap; //opens and closes flap at the back bottom end of the elevator
	DigitalInput ballExitSensor;  //sensor that detects ball at the top of the elevator
	

	public ElevatorWithFlap() {
		lift1 = new Victor(RobotMap.lift1Victor);
		lift2 = new Victor(RobotMap.lift2Victor);
		backFlap = new DoubleSolenoid(RobotMap.backFlapSolenoidClose , RobotMap.backFlapSolenoidOpen);	
	}
	
	protected void initDefaultCommand() {
		
		// TODO Auto-generated method stub
		setDefaultCommand(new DriveElevatorJoystick());

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
