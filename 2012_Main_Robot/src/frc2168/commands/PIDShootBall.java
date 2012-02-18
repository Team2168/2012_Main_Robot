package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

/**
 * 
 * @author Kevin Harrilal, First Robotics Team 2168
 *
 *This is a macro command to automatically fire a ball
 *one after the next while waiting for the 
 *shooter wheel to be at speed.
 *
 */
public class PIDShootBall extends CommandGroup
{
	public PIDShootBall()
	{
		//drive shooter wheel to PID speed
		addParallel(new PID_DriveShooter());
		
		//get ball ready
		addParallel(new DriveLiftUntilBall());
		
		//wait for shooter to be at speed
		addSequential(new PID_ShooterAtSpeed());
		
		//shoot ball
		addSequential(new DriveLiftUntilNoBall());
		
		//end command
		addSequential(new PID_ShooterPause());
	}

}
