package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class lowGoalFender extends CommandGroup
{
	
	public lowGoalFender(){
		
		//Raising hood and then shooting at low goal
		addSequential(new RaiseHood());
		addSequential(new PID_DriveShooter(RobotMap.LOW_GOAL_FORWARD));
		
		
	}

}
