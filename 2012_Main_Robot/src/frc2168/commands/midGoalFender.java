package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class midGoalFender extends CommandGroup
{
	
	public midGoalFender(){
		
		//Raising hood and then shooting at middle goal
		addSequential(new RaiseHood(true));
		addSequential(new PID_DriveShooter(RobotMap.MIDDLE_GOAL_FORWARD));
		
		
	}

}
