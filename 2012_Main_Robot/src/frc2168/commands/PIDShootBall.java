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
 *
 * @deprecated  As of Suffield Shakedown 2012, replaced by PID_DriveShooter and shootSingleBall
 * 
 * @see frc2168.commands.PID_DriveShooter#PID_DriveShooter(double) PID_DriveShooter(double speed)
 * @see frc2168.commands.shootSingleBall#shootSingleBall() shootSingleBall() 
 *
 *
 */
public class PIDShootBall extends CommandGroup
{
	public PIDShootBall(double setPoint)
	{
		//drive shooter wheel to PID speed
		addParallel(new PID_DriveShooter(setPoint));
		
		addSequential(new BackFlapClose());
		
		
		//wait for shooter to be at speed
		addSequential(new PID_ShooterAtSpeed());
		
		//get ball ready
		addSequential(new DriveLiftUntilBall());
		
		//shoot ball
		addSequential(new DriveLiftUntilNoBall());
		
		//get another ball
		addSequential(new DriveLiftUntilBall(),2);
		
		//end command
		addSequential(new PID_ShooterPause());
	}

}
