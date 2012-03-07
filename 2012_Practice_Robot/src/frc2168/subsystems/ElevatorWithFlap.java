package frc2168.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ElevatorWithFlap extends Subsystem {
	Victor lift1;   //2 motors on the lift that get balls and move them up
	Victor lift2;   //both move at the same time
	DoubleSolenoid backFlap; //opens and closes flap at the back bottom end of the elevator
	DigitalInput ballExitSensor;  //sensor that detects ball at the top of the elevator
	
	
	
	
	protected void initDefaultCommand() {
		
		// TODO Auto-generated method stub

	}
	
	public void liftBallsElevator(double speed){
		
		
		
	}
	
	public void controlBackFlap(){
		
	}

	public void overfloodWithBalls(){
		
	}
}
