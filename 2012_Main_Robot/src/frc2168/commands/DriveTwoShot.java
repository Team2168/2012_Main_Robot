package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class DriveTwoShot extends CommandGroup
{
	public DriveTwoShot(){
		
		//go up to bumper
		addSequential(new DriveToSpeed(0.6),1);		//change value according to testing.
		
		//raise bridge arm
		addSequential(new RaiseBridge());
		
		//raise flap to allow the elevator to pull balls up
		addSequential(new BackFlapClose());
		
		//Shift the drive train to high gear
		addSequential(new ShiftGearsLowToHigh());
		
		//shoot one ball to high goal
		addSequential(new MiddleGoalAuto());
		
		//shoot one ball to high goal
		addSequential(new MiddleGoalAuto());
		
		addSequential(new sleep(),1);
		
	}
	
	
}
