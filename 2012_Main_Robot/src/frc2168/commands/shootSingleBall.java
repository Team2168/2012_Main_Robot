package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 * @author Kevin Harrilal, First Robotics Team 2168
 *
 *
 *This command will fire one ball when it is pressed
 *
 *
 */


public class shootSingleBall extends CommandGroup
{
	public shootSingleBall()
	{
	
	addSequential(new BackFlapClose()); //need to check limit switch
	
	//get ball ready
	addSequential(new DriveLiftUntilBall());
	
	//wait for some length of time
	
	addSequential(new sleep(), .200); //wait for 1 second;
	
	//verify shooter is at speed
	addSequential(new PID_ShooterAtSpeed());
	
	//shoot ball
	addSequential(new DriveLiftUntilNoBall());
	
	//get another ball or timeout
	addSequential(new DriveLiftUntilBall(),2);
	
	
	
	
	//wheel will still run until it is stopped by calling Pause shooter wheel
	
	}
}
