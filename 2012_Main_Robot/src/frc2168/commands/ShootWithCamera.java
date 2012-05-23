package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootWithCamera extends CommandGroup {

	public ShootWithCamera(){
		
		//close backflap
		addSequential(new BackFlapClose());
		
		//prep ball
		addSequential(new DriveLiftUntilBall());
		
		//get the shooterwheel speed set
		addSequential(new GetCameraDistance());
		
		//wait
		addSequential(new sleep(), 0.200);
		
		//verify shooter is at speed
		addSequential(new PID_ShooterAtSpeed());
		
		//shoot ball
		addSequential(new DriveLiftUntilNoBall());
		
		//get another ball or timeout
		addSequential(new DriveLiftUntilBall(),2);
		
		//wheel will still run until it is stopped by calling Pause shooter wheel
	}
	
}
